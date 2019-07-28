package io.nzbee.domain.services.product;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
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
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetCombine;
import org.hibernate.search.query.facet.FacetSortOrder;
import org.hibernate.search.query.facet.FacetingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Sort;
import io.nzbee.domain.Product;
import io.nzbee.entity.PageableUtil;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.product.attribute.IProductAttributeService;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.price.IProductPriceService;
import io.nzbee.ui.component.web.sidebar.Sidebar;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.GeneralVars;
import io.nzbee.variables.ProductVars;

@Service(value = "productDomainService")
@Transactional
@CacheConfig(cacheNames="products")
public class ProductService implements IProductService {
	//In service classes, we should only call methods of entity service classes
	//the repositories themselves should not be referenced outside the entity service class
	//this way we ensure proper separation of concerns
    
	@Autowired 
	@Qualifier("productEntityService")
	private io.nzbee.entity.product.IProductService productService;

    @Autowired 
    private IProductPriceService productPriceService;
    
    @Autowired
    private IProductAttributeService productAttributeService;
    
    @Autowired
    private IBrandService brandService;
    
    @Autowired
    @Qualifier("categoryEntityService")
    private io.nzbee.entity.category.ICategoryService categoryService;
    
	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;
    
    @Override
	@Transactional
	@Cacheable
	public Product findOne(String lcl, String currency, Long id) {
    	io.nzbee.entity.product.Product pa = productService.findOne(id).get();
		Product p = this.convertToProductDO(pa, lcl, currency);
		return p;
	}	
    
    @Override
	@Cacheable
	public Page<Product> findAll(String locale, 
								 String currency, 
								 String categoryDesc, 
								 Double price, 
								 int page, 
								 int size, 
								 String sortBy, 
								 List<Long> categoryIds,
								 List<Long> brandIds,
								 List<Long> tagIds) {
	
     	Page<io.nzbee.entity.product.Product> ppa = 
     			productService.findAll(categoryIds, locale, new Double(0), price, ProductVars.MARKDOWN_SKU_DESCRIPTION, currency, new Date(), new Date(), PageRequest.of(page, size, this.sortByParam(sortBy)), brandIds, tagIds);

     	return ppa.map(pa -> this.convertToProductDO(pa, locale, currency));
	}
	
    @Override
	@Cacheable
	public List<Product> findAll(String locale, String currency, List<Long> productIds) {
		
	    List<io.nzbee.entity.product.Product> lp = 
	    		productService.findAll(locale, currency, productIds);
     	
		return lp.stream().map(p -> { return this.convertToProductDO(p, locale, currency);}).collect(Collectors.toList());
	}
	
	private List<Facet> getDiscreteFacets(QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery jpaQuery, String facetingName, String fieldReference) {		
		//create a category faceting request for the base level 
		FacetingRequest facetRequest = qb.facet()
				.name(facetingName)
				.onField(fieldReference) //in category class
				.discrete()
				.orderedBy(FacetSortOrder.COUNT_DESC)
				.includeZeroCounts(false)
				.createFacetingRequest();
		
		//add all the base level facets to categoryFacets List
		jpaQuery.getFacetManager().enableFaceting(facetRequest);
		return jpaQuery.getFacetManager().getFacets(facetingName);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	//@Cacheable
	public Page<Product> findAll(String lcl, 
								 String currency, 
								 String categoryDesc, 
								 String searchTerm, 
								 int page, 
								 int size, 
								 String sortBy, 
								 List<String> categoryTokens,
								 List<String> brandTokens,
								 List<String> tagTokens) {		
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
				
		String transLcl = lcl.substring(0, 2).toUpperCase() + lcl.substring(3, 5).toUpperCase();
		
		QueryBuilder productQueryBuilder = 
				  fullTextEntityManager.getSearchFactory()
				  .buildQueryBuilder()
				  .forEntity(ProductAttribute.class)	
				  .overridesForField("productDesc", lcl)
				  .get();
		
		//this is a Lucene query using the Lucene api
		org.apache.lucene.search.Query searchQuery = productQueryBuilder
													.bool()
													.must(productQueryBuilder.keyword()
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
																 "tagC")
													.matching(searchTerm)													
													.createQuery())
													.must(productQueryBuilder.keyword()
													.onFields("lclCd")
													.matching(lcl)
													.createQuery())
													.createQuery();
		
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(searchQuery, ProductAttribute.class);
		
		final Set<Facet> allFacets = new HashSet<Facet>();
		final Set<Sidebar> cs, bs;
		List<Facet> lf;
		
		//initialize the facets
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.PRIMARY_CATEGORY_FACET_NAME, "primaryCategory.categoryToken"));
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.PRIMARY_CATEGORY_FACET_NAME, "secondaryCategory.categoryToken"));
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.BRAND_FACET_NAME, "brandCode"));
		allFacets.addAll(this.getRangeFacets(productQueryBuilder, 		jpaQuery, currency));
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.TAG_FACET_NAME, "tagAFacet"));
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.TAG_FACET_NAME, "tagBFacet"));
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.TAG_FACET_NAME, "tagCFacet"));
		

		allFacets.addAll(allFacets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)).map(f -> {
								return getParentCategoryFacets(new HashSet<Facet>(), f, productQueryBuilder, jpaQuery, lcl, currency);
		}).collect(Collectors.toSet()).stream().flatMap(Set::stream).collect(Collectors.toSet()));
		
		List<String> allTokens = new ArrayList<String>();
		allTokens.addAll(categoryTokens);
		allTokens.addAll(brandTokens);
		allTokens.addAll(tagTokens);
		
		//filter to get the facets that are selected
		lf = allTokens.stream().flatMap(x -> {
			return allFacets.stream().filter(y -> x.equals(y.getValue()));
		}).collect(Collectors.toList());
		
		cs = new HashSet<Sidebar>();
		lf.stream().forEach(f -> {
			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
			processFacets(allFacets, productQueryBuilder, jpaQuery, currency, f.getFacetingName());
			if(f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)) {
				allFacets.addAll(getParentCategoryFacets(new HashSet<Facet>(), f, productQueryBuilder, jpaQuery, lcl, currency));
			}
		});
		
		lf = allTokens.stream().flatMap(x -> {
			return allFacets.stream().filter(y -> x.equals(y.getValue()));
		}).collect(Collectors.toList());
		
		lf.stream().forEach(f -> {
			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
		});
		
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)).collect(Collectors.toList()).stream().forEach(cf ->  		{
													String categoryCode = (new LinkedList<String>(Arrays.asList(cf.getValue().split("/")))).getLast();
													Sidebar cfDto = convertToCategorySidebarDTO(categoryCode, lcl, currency);
													cfDto.setProductCount(new Long(cf.getCount()));
													cfDto.setToken(cf.getValue());
													cfDto.setFacetType("discrete");
													cfDto.setFacetingName(cf.getFacetingName());
													cfDto.setFieldName(cf.getFieldName());
													cs.add(cfDto);
												});
		
		bs = new HashSet<Sidebar>();
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME)).collect(Collectors.toList()).forEach(bf ->     {
													Sidebar bfDto = convertToBrandSidebarDTO(bf.getValue(), lcl, currency);
													bfDto.setProductCount(new Long(bf.getCount()));
													bfDto.setToken(bf.getValue());
													bfDto.setFacetType("discrete");
													bfDto.setFacetingName(bf.getFacetingName());
													bfDto.setFieldName(bf.getFieldName());
													bs.add(bfDto);
												});
		
		//for each of the baseline facets, convert them to Facet DTOs for the client and add them to "s" 
		final List<Sidebar> ps = new ArrayList<Sidebar>();
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.PRICE_FACET_NAME)).collect(Collectors.toList()).forEach(pf ->     {
													//pf.getValue();
													Sidebar pfDto = new Sidebar();
													pfDto.setProductCount(new Long(pf.getCount()));
													pfDto.setToken(pf.getValue());
													pfDto.setFacetType("range");
													pfDto.setDesc(pf.getValue());
													pfDto.setFacetingName(pf.getFacetingName());
													pfDto.setFieldName(pf.getFieldName());
													ps.add(pfDto);
											   });
		
		
		final List<Sidebar> ts = new ArrayList<Sidebar>();
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.TAG_FACET_NAME)).collect(Collectors.toList()).forEach(tf ->     {
													//pf.getValue();
													Sidebar tfDto = new Sidebar();
													tfDto.setProductCount(new Long(tf.getCount()));
													tfDto.setToken(tf.getValue());
													tfDto.setFacetType("discrete");
													tfDto.setDesc(tf.getValue());
													tfDto.setFacetingName(tf.getFacetingName());
													tfDto.setFieldName(tf.getFieldName());
													ts.add(tfDto);
											   });
		
		
		//create a results container to send back to the client 
		//SearchDto src = new SearchDto();
		
		//set pageable definition for jpaQuery
		Pageable pageable = PageRequest.of(page, size);
		PageableUtil pageableUtil = new PageableUtil();
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());
		
		//sort the results
		org.apache.lucene.search.Sort sort = getSortField(sortBy, currency);
		jpaQuery.setSort(sort);
		
		//get the results using jpaQuery object
		List<ProductAttribute> results = jpaQuery.getResultList();
				
		//convert the results of jpaQuery to product Data Transfer Objects 
		List<Product> lp = results.stream().map(pa -> this.convertToProductDO(pa.getProduct(), lcl, currency)).collect(Collectors.toList());
		
		return new PageImpl<Product>(lp, pageable, jpaQuery.getResultSize());
	
	}
	
    public Sidebar convertToCategorySidebarDTO(String categoryCode, String locale, String currency) {
    	Sidebar cf = new Sidebar();
    	Category c = categoryService.findByCategoryCode(
    												CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
    												categoryCode, 
    												locale
    											   ).get();
    	if(c == null) { return cf; }
    	cf.setId(c.getCategoryId());
    	cf.setDesc(c.getAttributes().stream().filter(ca -> ca.getLclCd().equals(locale)).collect(Collectors.toList())
    			.stream().findFirst().get().getCategoryDesc());
    	
    	cf.setLevel(c.getCategoryLevel());
    	Optional<Category> parent = Optional.ofNullable(c.getParent());
    	if(parent.isPresent()) {
    		cf.setParentId(parent.get().getCategoryId());
    	}
    	return cf;		
    }
    
    public Sidebar convertToBrandSidebarDTO(String brandCode, String lcl, String currency) {
    	Optional<Brand> b = brandService.findByCode(brandCode);
    	Sidebar bf = new Sidebar();
    	bf.setId(b.get().getId());
    	bf.setDesc(b.get().getAttributes().stream().filter(ba -> ba.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getBrandDesc());
    	return bf;
    }
    
    private Set<Facet> getParentCategoryFacets(Set<Facet> cfs, Facet sf, QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery q, String locale, String currency) {
    	if(sf == null) { return cfs; }
    	
    	String categoryCode = (new LinkedList<String>(Arrays.asList(sf.getValue().split("/")))).getLast();
    	
    	Optional<Category> c = Optional.ofNullable(categoryService.findByCategoryCode(
    															CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
    															categoryCode, 
    															locale).get());
    	if(!c.isPresent()) { return cfs; }
    	Optional<Category> parent = Optional.ofNullable(c.get().getParent());
    	if(!parent.isPresent()) { return cfs; }
    	
    	//if we hit the root node, there are no parents
    	if(parent.get().getCategoryCode().equals(CategoryVars.PRIMARY_HIERARCHY_ROOT_CODE)) { return cfs; }
    	Long parentLevel = parent.get().getCategoryLevel();
    	
    	String frName = sf.getFacetingName();
    	String frField = sf.getFieldName().split("\\.")[0] + StringUtils.repeat(".parent", c.get().getCategoryLevel().intValue() - parentLevel.intValue()) + ".categoryToken";

    	Optional<Facet> parentFacet = this.getDiscreteFacets(qb, q, frName, frField).stream().filter(f -> f.getValue().equals(sf.getValue().replace("/" + categoryCode, ""))).findFirst();
    	if(parentFacet.isPresent()) { cfs.add(parentFacet.get()); } else { return cfs; }
    	return this.getParentCategoryFacets(cfs, parentFacet.get(), qb, q, locale, currency);
    }
    
    @SuppressWarnings("unchecked")
	private List<Facet> getRangeFacets(QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery jpaQuery, String currency) {
		
		org.apache.lucene.search.Sort sort = getSortField("priceDesc", currency);
		jpaQuery.setSort(sort);
		
		List<ProductAttribute> results = jpaQuery.getResultList();
		
		if(results.size() <= 0) { return new ArrayList<Facet>(); }
		
		Double maxPrice = results.get(0).getProduct().getCurrentMarkdownPriceHKD();
		Double minPrice = Lists.reverse(results).get(0).getProduct().getCurrentMarkdownPriceHKD();
		Double inc = (maxPrice > 0) ? (maxPrice - 
						((minPrice.equals(maxPrice)) ? 0 : minPrice)  
				) / 4 : maxPrice;
		
		inc = new BigDecimal(inc).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		
		Double 	below 	= inc, 
				froma 	= (new BigDecimal(inc + new Double(0.01)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()), 
				toa 	= (new BigDecimal(inc * 2).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()), 
				fromb 	= (new BigDecimal(toa + new Double(0.01)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()), 
				tob 	= (new BigDecimal(inc * 4).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()),
				above 	= tob;
		
		FacetingRequest facetRequest = qb.facet()
				.name(CategoryVars.PRICE_FACET_NAME)
				.onField("product.currentMarkdownPrice" + currency + "Facet") //In product class
				.range()
				.below(below)
				.from(froma).to(toa)
				.from(fromb).to(tob)
				.above(above)
				.orderedBy(FacetSortOrder.RANGE_DEFINITION_ORDER)
				.createFacetingRequest();
		
		jpaQuery.getFacetManager().enableFaceting(facetRequest);
		return jpaQuery.getFacetManager().getFacets(CategoryVars.PRICE_FACET_NAME);
	}
	
	private Set<Facet> processFacets(Set<Facet> allFacets, QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery jpaQuery, String currency, String facetingName) {
		List<Facet> processlf = allFacets.stream().filter(c -> (!c.getFacetingName().equals(facetingName))).collect(Collectors.toList());
		allFacets.removeAll(processlf);
		allFacets.addAll(processlf.stream().map(pf -> {
					return (pf.getFacetingName().equals(CategoryVars.PRICE_FACET_NAME)) 
					? this.getRangeFacets(qb, jpaQuery, currency)
					: this.getDiscreteFacets(qb, jpaQuery, pf.getFacetingName(), pf.getFieldName());
					
			}).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toSet()));
		return allFacets;
	}
	
    public Product convertToProductDO(final io.nzbee.entity.product.Product product, String lcl, String currency) {
    	final Product pDo = new Product();
    	Optional<ProductAttribute> pa = productAttributeService.findByIdAndLocale(product.getProductId(), lcl);
        pDo.setProductId(product.getProductId());
        pDo.setProductCreateDt(product.getProductCreateDt());
        pDo.setProductUPC(product.getUPC());
        pDo.setProductDesc(pa.get().getProductDesc());
        pDo.setProductRetail(productPriceService.get(product.getProductId(), ProductVars.PRICE_RETAIL_CODE, new Date(), new Date(), currency).get().getPriceValue());
        pDo.setProductMarkdown(productPriceService.get(product.getProductId(), ProductVars.PRICE_MARKDOWN_CODE, new Date(), new Date(), currency).get().getPriceValue());
        pDo.setProductImage(pa.get().getProductImage());
        pDo.setLclCd(lcl);
        pDo.setBrandDesc(product.getBrand().getAttributes().stream()
        .filter( ba -> ba.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getBrandDesc());
        
        StringBuilder sb = new StringBuilder();
        product.getCategories().stream().filter(c -> {return c.getHierarchy().getCode().equals(CategoryVars.PRIMARY_HIERARCHY_CODE);}).collect(Collectors.toList())
        .stream().sorted(Comparator.comparingLong(Category::getCategoryLevel)).collect(Collectors.toList())
        .stream().forEach(c -> sb.append(c.getAttributes().stream().filter(ca -> { return ca.getLclCd().equals(lcl);}).collect(Collectors.toList()).get(0).getCategoryDesc()));
        pDo.setPrimaryCategoryPath(sb.toString());        
        return pDo;
    }
    
    public static Product convertToProductDO(
    			String productCreatedDate,
    			String productUPC,
    			String productDesc,
    			Double productRetailPrice,
    			Double productMarkdownPrice,
    			String productImage,
    			String productLocale,
    			String productCurrency,
    			String productCategory
    		) {
    	final Product pDo = new Product();
    	pDo.setProductUPC(productUPC);
    	try {
			pDo.setProductCreateDt(new SimpleDateFormat(GeneralVars.DEFAULT_DATE_FORMAT).parse(productCreatedDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
    	pDo.setProductDesc(productDesc);
    	pDo.setProductRetail(productRetailPrice);
    	pDo.setProductMarkdown(productMarkdownPrice);
    	pDo.setProductImage(productImage);
    	pDo.setLclCd(productLocale);
    	pDo.setCurrency(productCurrency);
    	pDo.setPrimaryCategoryPath(productCategory);
    	return pDo;
    }
    
    
    public void persist(Product p) {
    	
		Optional<io.nzbee.entity.product.Product> oProduct = productService.findOne(p.getProductUPC());
		io.nzbee.entity.product.Product product = oProduct.isPresent() ? oProduct.get() : new io.nzbee.entity.product.Product();
		product.setUPC(p.getProductUPC());
		product.setProductCreateDt(p.getProductCreateDt());
		
		
		List<ProductAttribute> lpa = new ArrayList<ProductAttribute>();
		//Product Attribute English
		Optional<ProductAttribute> oProductAttributeEN = productAttributeService.getProductAttributeEN(product.getProductId());
		ProductAttribute productAttributeEN = oProductAttributeEN.isPresent() ? oProductAttributeEN.get() : new ProductAttribute();
		productAttributeEN.setProductDesc(p.getProductDesc());
		productAttributeEN.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
		productAttributeEN.setProductImage(p.getProductImage());
		productAttributeEN.setProduct(product);
		lpa.add(productAttributeEN);
//		
//		//Product Attribute Hong Kong
//		Optional<ProductAttribute> oProductAttributeHK = productAttributeService.getProductAttribute(product.getProductId(), GeneralVars.LANGUAGE_HK);
//		ProductAttribute productAttributeHK = oProductAttributeHK.isPresent() ? oProductAttributeHK.get() : new ProductAttribute();
//		productAttributeHK.setProductDesc(p.get_PRODUCT_DESCRIPTION_HK());
//		productAttributeHK.setLclCd(GeneralVars.LANGUAGE_HK);
//		productAttributeHK.setProductImage(p.get_PRODUCT_IMAGE_HK());
//		productAttributeHK.setProduct(product);
//		lpa.add(productAttributeHK);
//		
//		product.setAttributes(lpa);
//		
//		
//		//Brand
//		Optional<Brand> oBrand = brandService.getBrand(p.get_BRAND_CODE());
//		Brand brand = oBrand.isPresent() ? oBrand.get() : new Brand();
//		brand.setCode(p.get_BRAND_CODE());
//		
//		List<BrandAttribute> lba = new ArrayList<BrandAttribute>();
//		BrandAttribute brandAttributeEN = new BrandAttribute();
//		brandAttributeEN.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
//		brandAttributeEN.setBrandDesc(p.get_BRAND_DESCRIPTION_EN());
//		lba.add(brandAttributeEN);
//		
//		BrandAttribute brandAttributeHK = new BrandAttribute();
//		brandAttributeHK.setLclCd(GeneralVars.LANGUAGE_HK);
//		brandAttributeHK.setBrandDesc(p.get_BRAND_DESCRIPTION_HK());
//		lba.add(brandAttributeEN);
//		
//		brand.setAttributes(lba);
//		product.setBrand(brand);
		
		//Price
		//ProductPriceType oPriceType = priceTypeService.
		
		//	Optional<ProductPriceType> oPriceType = productPriceTypeService;
		//	ProductPriceType priceType = oPriceType.isPresent() ? oPriceType.get() : new ProductPriceType();

	}	
    
    private String getSortFieldName(String field, String currency) {
		switch(field) {
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
	
	private org.apache.lucene.search.Sort getSortField(String field, String currency) {
		switch(field) {
		case "nameAsc":
			return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field, currency), SortField.Type.STRING, false));
		case "nameDesc":
			return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field, currency), SortField.Type.STRING, true));
		case "priceAsc":
			return new org.apache.lucene.search.Sort(new SortedNumericSortField(getSortFieldName(field, currency), SortField.Type.DOUBLE, false	));
		case "priceDesc":
			return new org.apache.lucene.search.Sort(new SortedNumericSortField(getSortFieldName(field, currency), SortField.Type.DOUBLE, true));
		default: 
			return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field, currency), SortField.Type.STRING, true));
		}
	}
	
	private Sort sortByParam(String param) {
    	switch (param) {
    	case "priceAsc": return new Sort(Sort.Direction.ASC, "prices.PriceValue");
    	case "priceDesc": return new Sort(Sort.Direction.DESC, "prices.PriceValue");
    	case "nameAsc": return Sort.by(new Sort.Order(Sort.Direction.ASC, "attributes.ProductDesc").ignoreCase()) ;
    	case "nameDesc": return Sort.by(new Sort.Order(Sort.Direction.DESC, "attributes.ProductDesc").ignoreCase());
    	default: return Sort.by(new Sort.Order(Sort.Direction.ASC, "attributes.ProductDesc"));
    	}
    }
	
	

	@Override
	public Product load() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findOne(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMaxPrice(String categoryDesc, String locale, String markdownSkuDescription, String currency,
		List<Long> categoryIds, List<Long> brandIds, List<Long> tagIds) {
		
		brandIds.add(new Long(-1));
		categoryIds.add(new Long(-1));
		tagIds.add(new Long(-1));
		
		return productService.getMaxMarkDownPriceForTags(
				CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
				categoryDesc, 
				locale, 
				currency, 
				ProductVars.MARKDOWN_SKU_DESCRIPTION, 
				ProductVars.ACTIVE_SKU_CODE, 
				brandIds, 
				brandIds.stream().filter(b -> b.longValue() > -1).collect(Collectors.toList()).size(), 
				categoryIds, 
				categoryIds.stream().filter(c -> c.longValue() > -1).collect(Collectors.toList()).size(), 
				tagIds, 
				tagIds.stream().filter(t -> t.longValue() > -1).collect(Collectors.toList()).size());
	}

	@Override
	public Long getCount(String categoryTypeCode, String categoryDesc, String locale, String currency,
			String productStatusCode, List<Long> categoryIds, List<Long> brandIds, List<Long> tagIds) {
			
		brandIds.add(new Long(-1));
		categoryIds.add(new Long(-1));
		tagIds.add(new Long(-1));
		
		return tagIds.stream().filter(b -> b.longValue() > -1).collect(Collectors.toList()).size() == 0
			   ?
				productService.getCount(
				categoryDesc, 
				locale, 
				productStatusCode, 
				brandIds, 
				brandIds.stream().filter(b -> b.longValue() > -1).collect(Collectors.toList()).size(), 
				categoryIds, 
				categoryIds.stream().filter(c -> c.longValue() > -1).collect(Collectors.toList()).size())
				:
				productService.getCountForTags(
				categoryDesc, 
				locale, 
				productStatusCode, 
				brandIds, 
				brandIds.stream().filter(b -> b.longValue() > -1).collect(Collectors.toList()).size(), 
				categoryIds, 
				categoryIds.stream().filter(c -> c.longValue() > -1).collect(Collectors.toList()).size(),
				tagIds, 
				tagIds.stream().filter(c -> c.longValue() > -1).collect(Collectors.toList()).size());	
	}
	
	


}