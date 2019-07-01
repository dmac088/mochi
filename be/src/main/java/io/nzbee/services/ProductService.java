package io.nzbee.services;

import java.math.BigDecimal;
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
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Sort;
import io.nzbee.dao.CategoryDAO;
import io.nzbee.dao.ProductDAO;
import io.nzbee.dao.ProductTagDAO;
import io.nzbee.domain.Product;
import io.nzbee.dto.SearchDTO;
import io.nzbee.dto.SidebarFacetDTO;
import io.nzbee.entity.Brand;
import io.nzbee.entity.BrandRepository;
import io.nzbee.entity.Category;
import io.nzbee.entity.PageableUtil;
import io.nzbee.entity.ProductAttribute;
import io.nzbee.entity.ProductAttributeRepository;
import io.nzbee.entity.ProductPriceRepository;
import io.nzbee.entity.ProductRepository;
import io.nzbee.entity.ProductTagAttribute;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;

@Service
@Transactional
@CacheConfig(cacheNames="products")
public class ProductService implements IProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired 
    private ProductPriceRepository productPriceRepository;
    
    @Autowired
    private ProductAttributeRepository productAttributeRepository;
    
    @Autowired
    private BrandRepository brandRepository;
    
    @Autowired
    private ProductTagDAO productTagDAO;
    
    @Autowired
    private ProductDAO productDAO;
    
    @Autowired
    private CategoryDAO categoryDAO;
    
	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;
    
    
    @Override
	@Transactional
	@Cacheable
	public Product getProduct(String lcl, String currency, Long id) {
    	io.nzbee.entity.Product pa = productRepository.findById(id).get();
		Product p = this.convertToProductDO(pa, lcl, currency);
		return p;
	}	
    
	@Cacheable
	public SearchDTO getProducts(String locale, String currency, String categoryDesc, Double price, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets) {
		
		//all categories (if non selected in facets
		//Category parent = categoryRepository.findByAttributesLclCdAndAttributesCategoryDesc(locale, categoryDesc);
		Category parent = categoryDAO.getByCategoryDesc(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, categoryDesc, locale);
		List<Category> allCategories = recurseCategories(new ArrayList<Category>(), parent);
		List<Long> allCategoryIds = allCategories.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());
		
		//Category Facets
		List<SidebarFacetDTO> selectedCategories = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME);}).collect(Collectors.toList());
		List<Category> lpc = selectedCategories.stream().map(f-> {return categoryDAO.getByCategoryDesc(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, f.getDesc(), locale);}).collect(Collectors.toList());
						
		List<Category> lpcf = new ArrayList<Category>();
		lpc.stream().forEach(pc -> { lpcf.addAll(recurseCategories(new ArrayList<Category>(), pc)); });

		List<Long> facetCategoryIds = lpcf.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());
		List<Long> categoryIds = (selectedCategories.size() > 0) ? facetCategoryIds : allCategoryIds;

		//Brand Facets
		List<SidebarFacetDTO> selectedBrands = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME);}).collect(Collectors.toList());
		List<Long> selectedBrandIds = selectedBrands.stream().map(b -> {return b.getId();}).collect(Collectors.toList());
				
		//Tag Facets
		List<SidebarFacetDTO> selectedTags = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.TAG_FACET_NAME);}).collect(Collectors.toList());
		List<Long> selectedTagIds = selectedTags.stream().map(t -> {return t.getId();}).collect(Collectors.toList());
			
     	Page<io.nzbee.entity.Product> ppa = 
     			productDAO.getAll(categoryIds, locale, new Double(0), price, ProductVars.MARKDOWN_SKU_DESCRIPTION, currency, new Date(), new Date(), PageRequest.of(page, size, this.sortByParam(sortBy)), selectedBrandIds, selectedTagIds);
     	
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDO(pa, locale, currency));
  		SearchDTO rc = new SearchDTO();
		rc.setProducts(pp);
		
		return rc;
	}
	
	@Cacheable
	public List<Product> getProducts(String locale, String currency, List<Long> productIds) {
		
	    List<io.nzbee.entity.Product> lp = 
     			productDAO.getAll(locale, currency, productIds);
     	
		return lp.stream().map(p -> { return this.convertToProductDO(p, locale, currency);}).collect(Collectors.toList());
	}
	
	@Override
	public Double getMaxPrice(String categoryDesc, String locale, String currency, List<SidebarFacetDTO> facets) {
		
		//all categories (if non selected in facets
		Category parent = categoryDAO.getByCategoryDesc(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, categoryDesc, locale);
		List<Category> allCategories = recurseCategories(new ArrayList<Category>(), parent);
		List<Long> allCategoryIds = allCategories.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());
				
		//Category Facets
		List<SidebarFacetDTO> selectedCategories = facets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME);}).collect(Collectors.toList());
		List<Category> lpc = selectedCategories.stream().map(f-> {return categoryDAO.getByCategoryDesc(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, f.getDesc(), locale);}).collect(Collectors.toList());
				
		List<Category> lpcf = new ArrayList<Category>();
		lpc.stream().forEach(pc -> { lpcf.addAll(recurseCategories(new ArrayList<Category>(), pc)); });

		List<Long> facetCategoryIds = lpcf.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());
		List<Long> categoryIds = (selectedCategories.size() > 0) ? facetCategoryIds : allCategoryIds;

		//Brand Facets
		List<SidebarFacetDTO> selectedBrands = facets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME);}).collect(Collectors.toList());
		List<Long> selectedBrandIds = selectedBrands.stream().map(b -> {return b.getId();}).collect(Collectors.toList());
		
		//Tag Facets
		List<SidebarFacetDTO> selectedTags = facets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.TAG_FACET_NAME);}).collect(Collectors.toList());
		List<Long> selectedTagIds = selectedTags.stream().map(t -> {return t.getId();}).collect(Collectors.toList());
	
		Double maxPrice = productDAO.getMaxPrice(categoryDesc, locale, ProductVars.MARKDOWN_SKU_DESCRIPTION, currency, categoryIds, selectedBrandIds, selectedTagIds);
	
		return maxPrice;
	}
	
	@Override
	public List<SidebarFacetDTO> getProductTags(String locale, String currency, String categoryDesc, List<SidebarFacetDTO> selectedFacets) {
			
			//categories
			List<SidebarFacetDTO> selectedCategories = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME);}).collect(Collectors.toList());
			List<Category> lpc = selectedCategories.stream().map(f-> {return categoryDAO.getByCategoryDesc(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, f.getDesc(), locale);}).collect(Collectors.toList());
			List<Category> lpcf = new ArrayList<Category>();
			lpc.stream().forEach(pc -> { lpcf.addAll(recurseCategories(new ArrayList<Category>(), pc)); });
			List<Long> facetCategoryIds = lpcf.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());

			//brands
			List<SidebarFacetDTO> selectedBrands = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME);}).collect(Collectors.toList());
			List<Long> selectedBrandIds = selectedBrands.stream().map(b -> {return b.getId();}).collect(Collectors.toList());
					
			List<ProductTagAttribute> pt = productTagDAO.getAll(facetCategoryIds, locale, null, null, ProductVars.MARKDOWN_SKU_DESCRIPTION, currency, new Date(), new Date(), selectedBrandIds);
			
		
			List<SidebarFacetDTO> lf = pt.stream().map(t -> {
											SidebarFacetDTO f = new SidebarFacetDTO();
											f.setFacetingName(CategoryVars.TAG_FACET_NAME);
											f.setId(t.getTagId());
											f.setToken(t.getTag().get().getCode());
											f.setDesc(t.getTagDesc());
											f.setProductCount(productRepository.countForTags(
																								CategoryVars.PRIMARY_HIERARCHY_CODE, 
																								CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
																								categoryDesc, 
																								locale, 
																								currency, 
																								ProductVars.MARKDOWN_SKU_DESCRIPTION, 
																								ProductVars.ACTIVE_SKU_CODE,
																								((!selectedBrandIds.isEmpty()) 	? selectedBrandIds 	: Arrays.<Long>asList(new Long(-1))), 
																								((!selectedBrandIds.isEmpty()) 	? 1 : 0), 
																								((!facetCategoryIds.isEmpty()) 	? facetCategoryIds 	: Arrays.<Long>asList(new Long(-1))), 
																								((!facetCategoryIds.isEmpty()) 	? 1 : 0), 
																								Arrays.<Long>asList(new Long(t.getTagId())), 
																								1));
											return f;
										}).collect(Collectors.toList());
			
			return lf;	
	}

	
	@Override
	public List<SidebarFacetDTO> getProductTags(String locale, String currency, String categoryDesc, Double price, List<SidebarFacetDTO> selectedFacets) {
		
		//all categories (if non selected in facets
		Category parent = categoryDAO.getByCategoryDesc(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, categoryDesc, locale);
		List<Category> allCategories = recurseCategories(new ArrayList<Category>(), parent);
		List<Long> allCategoryIds = allCategories.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());
						
		//Facets
		List<SidebarFacetDTO> selectedCategories = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME);}).collect(Collectors.toList());
		List<Category> lpc = selectedCategories.stream().map(f-> {return categoryDAO.getByCategoryDesc(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, f.getDesc(), locale);}).collect(Collectors.toList());
							
		List<Category> lpcf = new ArrayList<Category>();
		lpc.stream().forEach(pc -> { lpcf.addAll(recurseCategories(new ArrayList<Category>(), pc)); });

		List<Long> facetCategoryIds = lpcf.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());

		List<SidebarFacetDTO> selectedBrands = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME);}).collect(Collectors.toList());
		List<Long> selectedBrandIds = selectedBrands.stream().map(b -> {return b.getId();}).collect(Collectors.toList());
				     	
		List<Long> categoryIds = (selectedCategories.size() > 0) ? facetCategoryIds : allCategoryIds;
		
		List<ProductTagAttribute> pt = productTagDAO.getAll(categoryIds, locale, new Double(0), price, ProductVars.MARKDOWN_SKU_DESCRIPTION, currency, new Date(), new Date(), selectedBrandIds);
		
		List<SidebarFacetDTO> lf = pt.stream().map(t -> {
										SidebarFacetDTO f = new SidebarFacetDTO();
										f.setFacetingName(CategoryVars.TAG_FACET_NAME);
										f.setId(t.getTagId());
										f.setToken(t.getTag().get().getCode());
										f.setDesc(t.getTagDesc());
										f.setProductCount(new Long(0));
										return f;
									}).collect(Collectors.toList());
		
		return lf;	
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
	public SearchDTO findProduct(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets) {		
		
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
		final Set<SidebarFacetDTO> cs, bs;
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
		
		//filter to get the facets that are selected
		lf = selectedFacets.stream().flatMap(x -> {
			return allFacets.stream().filter(y -> x.getToken().equals(y.getValue()));
		}).collect(Collectors.toList());
		
		cs = new HashSet<SidebarFacetDTO>();
		lf.stream().filter(f -> f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)).forEach(f -> {
			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
			processFacets(allFacets, productQueryBuilder, jpaQuery, currency, f.getFacetingName()); 
			allFacets.addAll(getParentCategoryFacets(new HashSet<Facet>(), f, productQueryBuilder, jpaQuery, lcl, currency));
		});
		
		lf = selectedFacets.stream().flatMap(x -> {
			return allFacets.stream().filter(y -> x.getToken().equals(y.getValue()));
		}).collect(Collectors.toList());
		
		lf.stream().forEach(f -> {
			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
		});
		
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)).collect(Collectors.toList()).stream().forEach(cf ->  		{
													String categoryCode = (new LinkedList<String>(Arrays.asList(cf.getValue().split("/")))).getLast();
													SidebarFacetDTO cfDto = convertToCategorySidebarDTO(categoryCode, lcl, currency);
													cfDto.setProductCount(new Long(cf.getCount()));
													cfDto.setToken(cf.getValue());
													cfDto.setFacetType("discrete");
													cfDto.setFacetingName(cf.getFacetingName());
													cfDto.setFieldName(cf.getFieldName());
													cs.add(cfDto);
												});
		
		bs = new HashSet<SidebarFacetDTO>();
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME)).collect(Collectors.toList()).forEach(bf ->     {
													SidebarFacetDTO bfDto = convertToBrandSidebarDTO(bf.getValue(), lcl, currency);
													bfDto.setProductCount(new Long(bf.getCount()));
													bfDto.setToken(bf.getValue());
													bfDto.setFacetType("discrete");
													bfDto.setFacetingName(bf.getFacetingName());
													bfDto.setFieldName(bf.getFieldName());
													bs.add(bfDto);
												});
		
		//for each of the baseline facets, convert them to Facet DTOs for the client and add them to "s" 
		final List<SidebarFacetDTO> ps = new ArrayList<SidebarFacetDTO>();
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.PRICE_FACET_NAME)).collect(Collectors.toList()).forEach(pf ->     {
													//pf.getValue();
													SidebarFacetDTO pfDto = new SidebarFacetDTO();
													pfDto.setProductCount(new Long(pf.getCount()));
													pfDto.setToken(pf.getValue());
													pfDto.setFacetType("range");
													pfDto.setDesc(pf.getValue());
													pfDto.setFacetingName(pf.getFacetingName());
													pfDto.setFieldName(pf.getFieldName());
													ps.add(pfDto);
											   });
		
		
		final List<SidebarFacetDTO> ts = new ArrayList<SidebarFacetDTO>();
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.TAG_FACET_NAME)).collect(Collectors.toList()).forEach(tf ->     {
													//pf.getValue();
													SidebarFacetDTO tfDto = new SidebarFacetDTO();
													tfDto.setProductCount(new Long(tf.getCount()));
													tfDto.setToken(tf.getValue());
													tfDto.setFacetType("discrete");
													tfDto.setDesc(tf.getValue());
													tfDto.setFacetingName(tf.getFacetingName());
													tfDto.setFieldName(tf.getFieldName());
													ts.add(tfDto);
											   });
		
		
		//create a results container to send back to the client 
		SearchDTO src = new SearchDTO();
		
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
		
		//create a paging object to hold the results of jpaQuery 
		Page<Product> pp = new PageImpl<Product>(lp, pageable, jpaQuery.getResultSize());
		
		List<SidebarFacetDTO> css = cs.stream().sorted(Comparator.comparing(SidebarFacetDTO::getToken)).collect(Collectors.toList());
		
		src.setFacets(css);
		src.getFacets().addAll(bs);
		src.getFacets().addAll(ps);
		src.getFacets().addAll(ts);
		src.setProducts(pp);
		
		return src;
	}
	
    public SidebarFacetDTO convertToCategorySidebarDTO(String categoryCode, String locale, String currency) {
    	SidebarFacetDTO cf = new SidebarFacetDTO();
    	Category c = categoryDAO.getByCategoryCode( CategoryVars.PRIMARY_HIERARCHY_CODE, 
    												CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
    												categoryCode, 
    												locale
    											   );
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
    
    public SidebarFacetDTO convertToBrandSidebarDTO(String brandCode, String lcl, String currency) {
    	Brand b = brandRepository.findByBrandCode(brandCode);
    	SidebarFacetDTO bf = new SidebarFacetDTO();
    	bf.setId(b.getBrandId());
    	bf.setDesc(b.getAttributes().stream().filter(ba -> ba.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getBrandDesc());
    	return bf;
    }
    
    private Set<Facet> getParentCategoryFacets(Set<Facet> cfs, Facet sf, QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery q, String locale, String currency) {
    	if(sf == null) { return cfs; }
    	
    	String categoryCode = (new LinkedList<String>(Arrays.asList(sf.getValue().split("/")))).getLast();
    	Optional<Category> c = Optional.ofNullable(categoryDAO.getByCategoryCode(
    															CategoryVars.PRIMARY_HIERARCHY_CODE, 
    															CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
    															categoryCode, 
    															locale));
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
	
    public Product convertToProductDO(final io.nzbee.entity.Product product, String lcl, String currency) {
    	ProductAttribute pa = productAttributeRepository.findByLclCdAndProductId(lcl, product.getProductId());
        final Product pDo = new Product();
        pDo.setProductId(product.getProductId());
        pDo.setProductCreateDt(product.getProductCreateDt());
        pDo.setProductUPC(product.getProductUPC());
        pDo.setProductDesc(pa.getProductDesc());
        pDo.setProductRetail(productPriceRepository.findByProductProductIdAndTypeDescAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(product.getProductId(), "retail", new Date(), new Date(), currency).getPriceValue());
        pDo.setProductMarkdown(productPriceRepository.findByProductProductIdAndTypeDescAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(product.getProductId(), "markdown", new Date(), new Date(), currency).getPriceValue());
        pDo.setProductImage(pa.getProductImage());
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
	
	public static List<Category> recurseCategories(List<Category> list, Category category) {
		if(category == null) { return list; }
		list.add(category);
		if(category.getChildren().isEmpty()) { return list; }
		category.getChildren().stream().forEach(c -> {
			list.add(c);
			recurseCategories(list, c); 
		});
		return list; 
	}


}