package io.nzbee.ui.component.web.search;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortedNumericSortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetCombine;
import org.hibernate.search.query.facet.FacetSortOrder;
import org.hibernate.search.query.facet.FacetingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Product;
import io.nzbee.domain.Tag;
import io.nzbee.domain.services.category.ICategoryService;
import io.nzbee.domain.services.product.IProductService;
import io.nzbee.domain.services.tag.ITagService;
import io.nzbee.entity.PageableUtil;
import io.nzbee.domain.services.brand.IBrandService;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;
import io.nzbee.ui.component.web.facet.INavFacetService;
import io.nzbee.ui.component.web.facet.NavFacet;
import io.nzbee.ui.component.web.facet.NavFacetContainer;
import io.nzbee.ui.component.web.generic.UIService;

@Service(value = "SearchService")
@Transactional
@CacheConfig(cacheNames = "products")
public class SearchServiceImpl extends UIService implements ISearchService {

	@Autowired
	@Qualifier("productDomainService")
	private IProductService productService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IBrandService brandService;

	@Autowired
	private ITagService tagService;
	
	@Autowired
	private INavFacetService facetService;

	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Search findAll(String locale, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, NavFacetContainer selectedFacets) {

		// convert selected facets into token lists
		List<String> categoryTokens = this.getFacetTokens(selectedFacets.getCategories());
		List<String> brandTokens 	= this.getFacetTokens(selectedFacets.getBrands());
		List<String> tagTokens 		= this.getFacetTokens(selectedFacets.getTags());
		List<String> priceTokens 	= this.getFacetTokens(selectedFacets.getPrices());

		// call the domain layer service to get a Page of Products
		return this.findAll(locale, 
							currency, 
							categoryDesc, 
							searchTerm, 
							page, 
							size, 
							sortBy, 
							categoryTokens,
							brandTokens,
							tagTokens,
							priceTokens);

	}

	@Override
	public NavFacet<Object> getMaxPrice(String categoryDesc, String locale, String currency,
			NavFacetContainer selectedFacets) {

		// convert selected facets into token lists
		List<Long> categoryIds = selectedFacets.getCategories().stream().map(c -> Long.parseLong(c.getFacetId())).collect(Collectors.toList());
		List<Long> brandIds = selectedFacets.getBrands().stream().map(c -> Long.parseLong(c.getFacetId())).collect(Collectors.toList());
		List<Long> tagIds = selectedFacets.getTags().stream().map(c -> Long.parseLong(c.getFacetId())).collect(Collectors.toList());

		Double maxPrice = productService.getMaxPrice(categoryDesc, locale, ProductVars.MARKDOWN_SKU_DESCRIPTION,
				currency, categoryIds, brandIds, tagIds);

		NavFacet<Object> s = new NavFacet<Object>();
		s.setToken(maxPrice.toString());

		return s;
	}

	private List<Facet> getDiscreteFacets(QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery jpaQuery,
			String facetingName, String fieldReference) {
		// create a category faceting request for the base level
		FacetingRequest facetRequest = qb.facet().name(facetingName).onField(fieldReference) // in category class
				.discrete().orderedBy(FacetSortOrder.COUNT_DESC).includeZeroCounts(false).createFacetingRequest();

		// add all the base level facets to categoryFacets List
		jpaQuery.getFacetManager().enableFaceting(facetRequest);
		return jpaQuery.getFacetManager().getFacets(facetingName);
	}

	@SuppressWarnings("unchecked")
	@Override
	// @Cacheable
	public Search findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, List<String> categoryTokens, List<String> brandTokens, List<String> tagTokens, List<String> priceTokens) {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		String transLcl = lcl.substring(0, 2).toUpperCase() + lcl.substring(3, 5).toUpperCase();

		QueryBuilder productQueryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
				.forEntity(ProductAttribute.class)
				.overridesForField("productDesc", lcl)
				.overridesForField("tagA", lcl)
				.overridesForField("tagB", lcl)
				.overridesForField("tagC", lcl)
				.get();

		// this is a Lucene query using the Lucene api
		org.apache.lucene.search.Query searchQuery = productQueryBuilder.bool().must(productQueryBuilder.keyword()
				.onFields(
						"primaryCategory.parent.parent.parent." + "primaryCategoryDesc" + transLcl,
						"primaryCategory.parent.parent." + "primaryCategoryDesc" + transLcl,
						"primaryCategory.parent." + "primaryCategoryDesc" + transLcl,
						"primaryCategory.primaryCategoryDesc" + transLcl,
						"secondaryCategory.parent.parent.parent." + "secondaryCategoryDesc" + transLcl,
						"secondaryCategory.parent.parent." + "secondaryCategoryDesc" + transLcl,
						"secondaryCategory.parent." + "secondaryCategoryDesc" + transLcl,
						"secondaryCategory." + "secondaryCategoryDesc" + transLcl, 
						"product.brand.brandDesc" + transLcl,
						"productDesc", 
						"tagA",
						"tagB", 
						"tagC"
						)
				.matching(searchTerm).createQuery())
				.must(productQueryBuilder.keyword().onFields("lclCd").matching(lcl).createQuery()).createQuery();

		org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(searchQuery,
				ProductAttribute.class);

		final Set<Facet> allFacets = new HashSet<Facet>();
		final Set<NavFacet<Category>> cs;
		final Set<NavFacet<Brand>> bs;
		List<Facet> lf;

		// initialize the facets
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, jpaQuery, CategoryVars.PRIMARY_CATEGORY_FACET_NAME,
				"primaryCategory.categoryToken"));
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, jpaQuery, CategoryVars.PRIMARY_CATEGORY_FACET_NAME,
				"secondaryCategory.categoryToken"));
		allFacets.addAll(
				this.getDiscreteFacets(productQueryBuilder, jpaQuery, CategoryVars.BRAND_FACET_NAME, "brandCode"));
		allFacets.addAll(this.getRangeFacets(productQueryBuilder, jpaQuery, currency));
		allFacets.addAll(
				this.getDiscreteFacets(productQueryBuilder, jpaQuery, CategoryVars.TAG_FACET_NAME, "tagAFacet"));
		allFacets.addAll(
				this.getDiscreteFacets(productQueryBuilder, jpaQuery, CategoryVars.TAG_FACET_NAME, "tagBFacet"));
		allFacets.addAll(
				this.getDiscreteFacets(productQueryBuilder, jpaQuery, CategoryVars.TAG_FACET_NAME, "tagCFacet"));

		allFacets.addAll(allFacets.stream()
				.filter(f -> f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)).map(f -> {
					return getParentCategoryFacets(new HashSet<Facet>(), f, productQueryBuilder, jpaQuery, lcl,
							currency);
				}).collect(Collectors.toSet()).stream().flatMap(Set::stream).collect(Collectors.toSet()));

		List<String> allTokens = new ArrayList<String>();
		allTokens.addAll(categoryTokens);
		allTokens.addAll(brandTokens);
		allTokens.addAll(tagTokens);
		allTokens.addAll(priceTokens);

		// filter to get the facets that are selected
		lf = allTokens.stream().flatMap(x -> {
			return allFacets.stream().filter(y -> x.equals(y.getValue()));
		}).collect(Collectors.toList());

		cs = new HashSet<NavFacet<Category>>();
		lf.stream().forEach(f -> {
			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
			processFacets(allFacets, productQueryBuilder, jpaQuery, currency, f.getFacetingName());
			if (f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)) {
				allFacets.addAll(
						getParentCategoryFacets(new HashSet<Facet>(), f, productQueryBuilder, jpaQuery, lcl, currency));
			}
		});

		lf = allTokens.stream().flatMap(x -> {
			return allFacets.stream().filter(y -> x.equals(y.getValue()));
		}).collect(Collectors.toList());

		lf.stream().forEach(f -> {
			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
		});

		allFacets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME))
				.collect(Collectors.toList()).stream().forEach(cf -> {
					String categoryCode = (new LinkedList<String>(Arrays.asList(cf.getValue().split("/")))).getLast();
					Category category = categoryService.findOneByCode(lcl, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, categoryCode);
					NavFacet<Category> categoryFacet = facetService.convertCatToNavFacet(category);
					categoryFacet.setFacetProductCount(new Long(cf.getCount()));
					categoryFacet.setToken(cf.getValue());
					categoryFacet.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
					cs.add(categoryFacet);
				});

		bs = new HashSet<NavFacet<Brand>>();
		allFacets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME))
				.collect(Collectors.toList()).forEach(bf -> {
					Brand brand = brandService.findOneByCode(lcl, bf.getValue());
					NavFacet<Brand> brandFacet = facetService.convertBrandToNavFacet(brand);
					brandFacet.setFacetProductCount(new Long(bf.getCount()));
					brandFacet.setToken(bf.getValue());
					brandFacet.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
					bs.add(brandFacet);
				});

		// for each of the baseline facets, convert them to Facet DTOs for the client
		// and add them to "s"
		final List<NavFacet<Object>> ps = new ArrayList<NavFacet<Object>>();
		allFacets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.PRICE_FACET_NAME))
				.collect(Collectors.toList()).forEach(pf -> {
					NavFacet<Object> priceFacet = new NavFacet<Object>();
					priceFacet.setFacetId("Product.productMarkdown[" + pf.getValue()  + "]");
					priceFacet.setFacetDisplayValue(pf.getValue());
					priceFacet.setFacetClassName("Product.productMarkdown");
					priceFacet.setFacetProductCount(new Long(pf.getCount()));
					priceFacet.setToken(pf.getValue());
					priceFacet.setFacetType(ProductVars.FACET_TYPE_RANGE);
					ps.add(priceFacet);
				});

		final List<NavFacet<Tag>> ts = new ArrayList<NavFacet<Tag>>();
		allFacets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.TAG_FACET_NAME))
				.collect(Collectors.toList()).forEach(tf -> {
					if(tf.getValue().equals("Empty"))  return; 
					Tag tag = tagService.findOneByDesc(tf.getValue(), lcl);
					NavFacet<Tag> tagFacet = facetService.convertTagToNavFacet(tag);
					tagFacet.setFacetProductCount(new Long(tf.getCount()));
					tagFacet.setToken(tf.getValue());
					tagFacet.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
					ts.add(tagFacet);
				});

		List<NavFacet> returnFacets = new ArrayList<NavFacet>();
		returnFacets.addAll(cs);
		returnFacets.addAll(bs);
		returnFacets.addAll(ts);
		returnFacets.addAll(ps);

		// set pageable definition for jpaQuery
		Pageable pageable = PageRequest.of(page, size);
		PageableUtil pageableUtil = new PageableUtil();
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());

		// sort the results
		org.apache.lucene.search.Sort sort = getSortField(sortBy, currency);
		jpaQuery.setSort(sort);

		// get the results using jpaQuery object
		List<ProductAttribute> results = jpaQuery.getResultList();

		// convert the results of jpaQuery to product Data Transfer Objects
		List<Product> lp = results.stream().map(pa -> productService.convertToProductDO(pa.getProduct(), lcl, currency))
				.collect(Collectors.toList());

		Search search = new Search();
		search.setProducts(new PageImpl<Product>(lp, pageable, jpaQuery.getResultSize()));
		NavFacetContainer nfc = new NavFacetContainer();
		nfc.setBrands(returnFacets.stream().filter(f -> f.getFacetClassName().equals(Brand.class.getSimpleName())).collect(Collectors.toList()));
		nfc.setCategories(returnFacets.stream().filter(f -> f.getFacetClassName().equals(Category.class.getSimpleName())).collect(Collectors.toList()));
		nfc.setTags(returnFacets.stream().filter(f -> f.getFacetClassName().equals(Tag.class.getSimpleName())).collect(Collectors.toList()));
		nfc.setPrices(returnFacets.stream().filter(f -> f.getFacetClassName().equals("Product.productMarkdown")).collect(Collectors.toList()));
		search.setFacets(nfc);
		return search;
	}

	private Set<Facet> getParentCategoryFacets(Set<Facet> cfs, Facet sf, QueryBuilder qb,
			org.hibernate.search.jpa.FullTextQuery q, String locale, String currency) {
		if (sf == null) {
			return cfs;
		}

		String categoryCode = (new LinkedList<String>(Arrays.asList(sf.getValue().split("/")))).getLast();

		Optional<Category> c = Optional.ofNullable(
				categoryService.findOneByCode(locale, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, categoryCode));
		if (!c.isPresent()) {
			return cfs;
		}
		if (c.get().getParentId() == null) {
			return cfs;
		}
		Optional<Category> parent = Optional.ofNullable(categoryService.findOne(locale, c.get().getParentId()));
		if (!parent.isPresent()) {
			return cfs;
		}

		// if we hit the root node, there are no parents
		if (parent.get().getCategoryCode().equals(CategoryVars.PRIMARY_HIERARCHY_ROOT_CODE)) {
			return cfs;
		}
		Long parentLevel = parent.get().getCategoryLevel();

		String frName = sf.getFacetingName();
		String frField = sf.getFieldName().split("\\.")[0]
				+ StringUtils.repeat(".parent", c.get().getCategoryLevel().intValue() - parentLevel.intValue())
				+ ".categoryToken";

		Optional<Facet> parentFacet = this.getDiscreteFacets(qb, q, frName, frField).stream()
				.filter(f -> f.getValue().equals(sf.getValue().replace("/" + categoryCode, ""))).findFirst();
		if (parentFacet.isPresent()) {
			cfs.add(parentFacet.get());
		} else {
			return cfs;
		}
		return this.getParentCategoryFacets(cfs, parentFacet.get(), qb, q, locale, currency);
	}

	@SuppressWarnings("unchecked")
	private List<Facet> getRangeFacets(QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery jpaQuery,
			String currency) {

		org.apache.lucene.search.Sort sort = getSortField("priceDesc", currency);
		jpaQuery.setSort(sort);

		List<ProductAttribute> results = jpaQuery.getResultList();

		if (results.size() <= 0) {
			return new ArrayList<Facet>();
		}

		Double maxPrice = results.get(0).getProduct().getCurrentMarkdownPriceHKD();
		Double minPrice = Lists.reverse(results).get(0).getProduct().getCurrentMarkdownPriceHKD();
		Double inc = (maxPrice > 0) ? (maxPrice - ((minPrice.equals(maxPrice)) ? 0 : minPrice)) / 4 : maxPrice;

		inc = new BigDecimal(inc).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

		Double below = inc,
				froma = (new BigDecimal(inc + new Double(0.01)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()),
				toa = (new BigDecimal(inc * 2).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()),
				fromb = (new BigDecimal(toa + new Double(0.01)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()),
				tob = (new BigDecimal(inc * 4).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()), above = tob;

		FacetingRequest facetRequest = qb.facet().name(CategoryVars.PRICE_FACET_NAME)
				.onField("product.currentMarkdownPrice" + currency + "Facet") // In product class
				.range().below(below).from(froma).to(toa).from(fromb).to(tob).above(above)
				.orderedBy(FacetSortOrder.RANGE_DEFINITION_ORDER).createFacetingRequest();

		jpaQuery.getFacetManager().enableFaceting(facetRequest);
		return jpaQuery.getFacetManager().getFacets(CategoryVars.PRICE_FACET_NAME);
	}

	private Set<Facet> processFacets(Set<Facet> allFacets, QueryBuilder qb,
			org.hibernate.search.jpa.FullTextQuery jpaQuery, String currency, String facetingName) {
		List<Facet> processlf = allFacets.stream().filter(c -> (!c.getFacetingName().equals(facetingName)))
				.collect(Collectors.toList());
		allFacets.removeAll(processlf);
		allFacets.addAll(processlf.stream().map(pf -> {
			return (pf.getFacetingName().equals(CategoryVars.PRICE_FACET_NAME))
					? this.getRangeFacets(qb, jpaQuery, currency)
					: this.getDiscreteFacets(qb, jpaQuery, pf.getFacetingName(), pf.getFieldName());

		}).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toSet()));
		return allFacets;
	}

	private org.apache.lucene.search.Sort getSortField(String field, String currency) {
		switch (field) {
		case "nameAsc":
			return new org.apache.lucene.search.Sort(
					new SortField(getSortFieldName(field, currency), SortField.Type.STRING, false));
		case "nameDesc":
			return new org.apache.lucene.search.Sort(
					new SortField(getSortFieldName(field, currency), SortField.Type.STRING, true));
		case "priceAsc":
			return new org.apache.lucene.search.Sort(
					new SortedNumericSortField(getSortFieldName(field, currency), SortField.Type.DOUBLE, false));
		case "priceDesc":
			return new org.apache.lucene.search.Sort(
					new SortedNumericSortField(getSortFieldName(field, currency), SortField.Type.DOUBLE, true));
		default:
			return new org.apache.lucene.search.Sort(
					new SortField(getSortFieldName(field, currency), SortField.Type.STRING, true));
		}
	}

	private String getSortFieldName(String field, String currency) {
		switch (field) {
		case "nameAsc":
			return "product.productDesc";
		case "nameDesc":
			return "product.productDesc";
		case "priceDesc":
			return "product.currentMarkdownPrice" + currency;
		case "priceAsc":
			return "product.currentMarkdownPrice" + currency;
		default:
			return "product.productDesc";
		}
	}



}
