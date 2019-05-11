package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
import org.hibernate.search.query.engine.spi.FacetManager;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetCombine;
import org.hibernate.search.query.facet.FacetSelection;
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
import org.springframework.data.domain.Sort;
import io.javabrains.springbootstarter.domain.Product;
import io.javabrains.springbootstarter.dto.SearchDTO;
import io.javabrains.springbootstarter.dto.SidebarFacetDTO;
import io.javabrains.springbootstarter.entity.Brand;
import io.javabrains.springbootstarter.entity.BrandRepository;
import io.javabrains.springbootstarter.entity.Category;
import io.javabrains.springbootstarter.entity.CategoryRepository;
import io.javabrains.springbootstarter.entity.PageableUtil;
import io.javabrains.springbootstarter.entity.ProductAttribute;
import io.javabrains.springbootstarter.entity.ProductAttributeRepository;
import io.javabrains.springbootstarter.entity.ProductPagingAndSortingRepository;
import io.javabrains.springbootstarter.entity.ProductPriceRepository;
import io.javabrains.springbootstarter.entity.ProductRepository;
import variables.CategoryVars;

@Service
@Transactional
@CacheConfig(cacheNames="products")
public class ProductService implements IProductService {

    @Autowired
    private ProductPagingAndSortingRepository productPagingAndSortingRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired ProductPriceRepository productPriceRepository;
    
    @Autowired
    private ProductAttributeRepository productAttributeRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private BrandRepository brandRepository;
    
	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;
    
    // API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
    @Override
	@Transactional
	@Cacheable
	public SearchDTO getProducts(String lcl, String currency, int page, int size, String sortBy) {
    	Page<Product> pp;
		Page<io.javabrains.springbootstarter.entity.Product> ppa = productPagingAndSortingRepository.findAll(PageRequest.of(page, size, this.sortByParam(sortBy)));
		pp = ppa.map(p -> this.convertToProductDO(p, lcl, currency));
		SearchDTO rc = new SearchDTO();
		rc.setProducts(pp);
		return rc;
	}	
    
    @Override
	@Transactional
	@Cacheable
	public Product getProduct(String lcl, String currency, Long id) {
    	io.javabrains.springbootstarter.entity.Product pa = productRepository.findById(id).get();
		Product p = this.convertToProductDO(pa, lcl, currency);
		return p;
	}	

    
    @Override
  	@Transactional
  	@Cacheable
  	public List<Product> getPreviewProductsForCategory(String lcl, String currency, Long categoryId) {
     	Category pc = categoryRepository.findByCategoryId(categoryId);
     	List<Category> pcl = IProductService.recurseCategories(new ArrayList<Category>(), pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		List<io.javabrains.springbootstarter.entity.Product> ppa = productRepository.findByCategoriesCategoryIdIn(categoryIds);
  		List<Product> pp = ppa.stream().map(pa -> this.convertToProductDO(pa, lcl, currency)).collect(Collectors.toList());
  		return pp;
  	}	
    
	@Override
	@Cacheable
	public SearchDTO getProductsForCategory(String lcl, String currency, String categoryDesc, int page, int size, String sortBy) {
     	Category pc = categoryRepository.findByAttributesLclCdAndAttributesCategoryDescAndHierarchyCode(lcl, categoryDesc, CategoryVars.PRIMARY_HIERARCHY_CODE);
     	List<Category> pcl = IProductService.recurseCategories(new ArrayList<Category>(), pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<io.javabrains.springbootstarter.entity.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCd(categoryIds, lcl, PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDO(pa, lcl, currency));
  		SearchDTO rc = new SearchDTO();
		rc.setProducts(pp);
		return rc;
	}
	
	@Override
	@Cacheable
	public SearchDTO getProductsForCategory(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy) {
		Category pc = categoryRepository.findByAttributesLclCdAndAttributesCategoryDescAndHierarchyCode(lcl, categoryDesc, CategoryVars.PRIMARY_HIERARCHY_CODE);
     	List<Category> pcl = IProductService.recurseCategories(new ArrayList<Category>(), pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<io.javabrains.springbootstarter.entity.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetween(categoryIds, lcl, new Double(0), price, PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDO(pa, lcl, currency));
  		SearchDTO rc = new SearchDTO();
		rc.setProducts(pp);
		return rc;
	}
	
	@Override
	@Cacheable
	public SearchDTO getProductsForCategoryAndBrand(String lcl, String currency, String categoryDesc, String brandDesc, int page, int size, String sortBy) {
		Category pc = categoryRepository.findByAttributesLclCdAndAttributesCategoryDescAndHierarchyCode(lcl, categoryDesc, CategoryVars.PRIMARY_HIERARCHY_CODE);
     	List<Category> pcl = IProductService.recurseCategories(new ArrayList<Category>(), pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<io.javabrains.springbootstarter.entity.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesBrandDescAndBrandBrandAttributesLclCd(categoryIds, lcl, brandDesc, lcl, PageRequest.of(page, size, this.sortByParam(sortBy)));		
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDO(pa, lcl, currency));
  		SearchDTO rc = new SearchDTO();
		rc.setProducts(pp);
		return rc;
	}
	
	@Override
 	@Cacheable
	public SearchDTO getProductsForCategoryAndPrice(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy) {
		Category pc = categoryRepository.findByAttributesLclCdAndAttributesCategoryDescAndHierarchyCode(lcl, categoryDesc, CategoryVars.PRIMARY_HIERARCHY_CODE);
     	List<Category> pcl = IProductService.recurseCategories(new ArrayList<Category>(), pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<io.javabrains.springbootstarter.entity.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(categoryIds, lcl, new Double(0), price, "markdown", currency, new Date(), new Date(),PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDO(pa, lcl, currency));
  		SearchDTO rc = new SearchDTO();
		rc.setProducts(pp);
		return rc;
	}

	
	@Override
	@Cacheable
	public SearchDTO getProductsForCategoryAndBrandAndPrice(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets) {
		
		//SelectedCategory
		Category parent = categoryRepository.findByAttributesLclCdAndAttributesCategoryDesc(lcl, categoryDesc);
		List<Category> allCategories = IProductService.recurseCategories(new ArrayList<Category>(), parent);
		List<Long> allCategoryIds = allCategories.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());
		
		//Facets
		List<SidebarFacetDTO> selectedCategories = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals("CategoryFR");}).collect(Collectors.toList());
		List<Category> lpc = selectedCategories.stream().map(f-> {return categoryRepository.findByAttributesLclCdAndAttributesCategoryDescAndHierarchyCode(lcl, f.getDesc(), CategoryVars.PRIMARY_HIERARCHY_CODE);}).collect(Collectors.toList());
		
		List<Category> lpcf = new ArrayList<Category>();
		lpc.stream().forEach(pc -> { lpcf.addAll(IProductService.recurseCategories(new ArrayList<Category>(), pc)); });

     	List<Long> facetCategoryIds = lpcf.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());

     	List<SidebarFacetDTO> selectedBrands = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals("BrandFR");}).collect(Collectors.toList());
     	List<Long> selectedBrandIds = selectedBrands.stream().map(b -> {return b.getId();}).collect(Collectors.toList());
     	
     	List<Long> categoryIds = (selectedCategories.size() > 0) ? facetCategoryIds : allCategoryIds;
     	
     	Page<io.javabrains.springbootstarter.entity.Product> ppa = (selectedBrands.size() > 0)
     	?	productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThanAndBrandBrandIdIn(categoryIds, lcl, lcl, new Double(0), price, "markdown", currency, new Date(), new Date(),PageRequest.of(page, size, this.sortByParam(sortBy)), selectedBrandIds)
     	:   productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(categoryIds, lcl, lcl, new Double(0), price, "markdown", currency, new Date(), new Date(), PageRequest.of(page, size, this.sortByParam(sortBy)));
     	
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDO(pa, lcl, currency));
  		SearchDTO rc = new SearchDTO();
		rc.setProducts(pp);
		
		return rc;
	}
	
	@Override
	public Double getMaxPrice(String lcl, String curr, String category, List<SidebarFacetDTO> selectedFacets) {
		
		Category c = categoryRepository.findByAttributesLclCdAndAttributesCategoryDescAndHierarchyCode(lcl, category, CategoryVars.PRIMARY_HIERARCHY_ROOT_CODE);
		
		List<Long> brandIds = selectedFacets.stream().filter(f -> f.getFacetingName().equals("BrandFR")).collect(Collectors.toList()).stream().map(f-> f.getId()).collect(Collectors.toList());
		
		List<Long> categoryIds =  selectedFacets.stream().filter(f -> f.getFacetingName().equals("CategoryFR")).collect(Collectors.toList()).stream().map(f-> f.getId()).collect(Collectors.toList());

		List<Long> bids = brandRepository.findAll().stream().map(b-> b.getBrandId()).collect(Collectors.toList());
		
		if(categoryIds == null) { categoryIds = new ArrayList<Long>(); }
		if(brandIds == null) 	{ brandIds = new ArrayList<Long>(); }
		if(bids == null) 		{ bids = new ArrayList<Long>(); }
		
		if (categoryIds.size() <= 0) {categoryIds.add(new Long(c.getCategoryId()));}
		if(brandIds.size() <= 0) { brandIds.addAll(bids); }
		
		Double maxPrice = productRepository.maxMarkdownPricesPriceValueByPriceCurrenciesCodeAndPricePriceTypeDescAndCategoriesHierarchyCodeAndCategoriesCategoryIdInAndBrandBrandIdIn(curr, "markdown", CategoryVars.PRIMARY_HIERARCHY_CODE, categoryIds, brandIds);
		
		maxPrice = (maxPrice == null) ? 0 : maxPrice;
		
		return maxPrice;
	}
	


	@SuppressWarnings("unchecked")
	@Override
	//@Cacheable
	public SearchDTO findProduct(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets) {		
		
		List<SidebarFacetDTO> receivedCategoryFacets = selectedFacets.stream().filter(sf -> {
			return sf.getFacetingName().equals("CategoryFR");
		}).collect(Collectors.toList());
		
		List<SidebarFacetDTO> receivedBrandFacets = selectedFacets.stream().filter(sf -> {
			return sf.getFacetingName().equals("BrandFR");
		}).collect(Collectors.toList());
		
		List<SidebarFacetDTO> receivedPriceFacets = selectedFacets.stream().filter(sf -> {
			return sf.getFacetingName().equals("PriceFR");
		}).collect(Collectors.toList());
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
				
		String transLcl = lcl.substring(0, 2).toUpperCase() + lcl.substring(3, 5).toUpperCase();
		
		QueryBuilder productQueryBuilder = 
				fullTextEntityManager.getSearchFactory()
				  .buildQueryBuilder()
				  .forEntity(ProductAttribute.class)				  
				  //language discriminator needed on productDesc
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
																 "primaryCategory." + "primaryCategoryDesc" + transLcl,
																 "product.brand.brandDesc" + transLcl,
																 "productDesc")
													.matching(searchTerm)													
													.createQuery())
													.must(productQueryBuilder.keyword()
													.onFields("lclCd")
													.matching(lcl)
													.createQuery())
													.createQuery();
		
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery
		  = fullTextEntityManager.createFullTextQuery(searchQuery, ProductAttribute.class);
		
		//declare a set of facets (we need to avoid Duplicates)
		Set<Facet> categoryFacets = new HashSet<Facet>();
		Set<Facet> brandFacets = new HashSet<Facet>();
		Set<Facet> priceFacets = new HashSet<Facet>();
		
		//create a category faceting request for the base level 
		FacetingRequest categoryFacetRequest = productQueryBuilder.facet()
				.name("CategoryFR")
				.onField("primaryCategory.categoryToken") //in category class
				.discrete()
				.orderedBy(FacetSortOrder.COUNT_DESC)
				.includeZeroCounts(false)
				.maxFacetCount(10)
				.createFacetingRequest();
		
		//add all the base level facets to categoryFacets List
		FacetManager facetMgr = jpaQuery.getFacetManager();
		facetMgr.enableFaceting(categoryFacetRequest);
		categoryFacets.addAll(facetMgr.getFacets("CategoryFR"));
		FacetSelection categoryFacetSelection = facetMgr.getFacetGroup("CategoryFR");
		List<Facet> lcf = receivedCategoryFacets.stream().flatMap(x -> categoryFacets.stream().filter(y -> x.getToken().equals(y.getValue())).limit(1)).collect(Collectors.toList());
		categoryFacetSelection.selectFacets(FacetCombine.OR, lcf.toArray(new Facet[0]));
		
		final Set<SidebarFacetDTO> cs = new HashSet<SidebarFacetDTO>();
		categoryFacets.stream().forEach(cf ->  {
			String categoryCode = (new LinkedList<String>(Arrays.asList(cf.getValue().split("/")))).getLast();
			SidebarFacetDTO cfDto = convertToCategorySidebarDTO(categoryCode, lcl, currency);
			cfDto.setProductCount(new Long(cf.getCount()));
			cfDto.setToken(cf.getValue());
			cfDto.setFacetingName(cf.getFacetingName());
			cfDto.setFieldName(cf.getFieldName());
			cs.add(cfDto);
		});
		
		//create a brand faceting request for the base level 
		FacetingRequest brandFacetRequest = productQueryBuilder.facet()
				.name("BrandFR")
				.onField("brandCode") //in product class
				.discrete()
				.orderedBy(FacetSortOrder.COUNT_DESC)
				.includeZeroCounts(false)
				.createFacetingRequest();

		//add all the base level facets to brandFacets List
		facetMgr.enableFaceting(brandFacetRequest);
		brandFacets.addAll(facetMgr.getFacets("BrandFR"));
		FacetSelection brandFacetSelection = facetMgr.getFacetGroup("BrandFR");
		List<Facet> lbf = receivedBrandFacets.stream().flatMap(x -> brandFacets.stream().filter(y -> x.getToken().equals(y.getValue())).limit(1)).collect(Collectors.toList());
		brandFacetSelection.selectFacets(FacetCombine.OR, 		lbf.toArray(new Facet[0]));
		final Set<SidebarFacetDTO> bs = new HashSet<SidebarFacetDTO>();
		brandFacets.stream().forEach(bf ->     {
			SidebarFacetDTO bfDto = convertToBrandSidebarDTO(bf.getValue(), lcl, currency);
			bfDto.setProductCount(new Long(bf.getCount()));
			bfDto.setToken(bf.getValue());
			bfDto.setFacetingName(bf.getFacetingName());
			bfDto.setFieldName(bf.getFieldName());
			bs.add(bfDto);
		});
		
		
		List<ProductAttribute> results =  jpaQuery.getResultList();
		
//		results.stream().sorted(Comparator.comparing(ProductAttribute::getProduct())
//		we use the results of the query to get the price ranges
//		Double inc = (maxPrice > 0) ? maxPrice / 3 : maxPrice; 
//		
//		Double below = inc, from = inc + new Double(0.01), to = inc * 2, above = to;
//		
//		System.out.println("maxPrice = " + maxPrice);
//		System.out.println("below = " + below);
//		System.out.println("from = " + from);
//		System.out.println("to = " + to);
//		System.out.println("above = " + above);
		
		
		FacetingRequest priceFacetRequest = productQueryBuilder.facet()
				.name("PriceFR")
				.onField("product.currentMarkdownPrice" + currency + "Facet") //In product class
				.range()
				.below(below)
				.from(from).to(to)
				.above(above).excludeLimit()
				.createFacetingRequest();
		
		facetMgr.enableFaceting(priceFacetRequest);
		priceFacets.addAll(facetMgr.getFacets("PriceFR"));
		FacetSelection priceFacetSelection = facetMgr.getFacetGroup("PriceFR");
		
		//run the query and get the results
		results =  jpaQuery.getResultList();
	
		//convert the results to product DTOs and store in a list
		//List<Product> lp;// = results.stream().map(pa -> this.convertToProductDO(pa.getProduct(), lcl, currency)).collect(Collectors.toList());
		
		//create a results container to send back to the client 
		SearchDTO src = new SearchDTO();
		
		//create a hashset of FacetDTOs to send back to the client, with additional metadata
		
		
		final List<SidebarFacetDTO> ps = new ArrayList<SidebarFacetDTO>();
		
		//for each of the baseline facets, convert them to Facet DTOs for the client and add them to "s" 
		
		priceFacets.stream().forEach(pf ->     {
													SidebarFacetDTO pfDto = new SidebarFacetDTO();
													pfDto.setProductCount(new Long(pf.getCount()));
													pfDto.setToken(pf.getValue());
													pfDto.setFacetingName(pf.getFacetingName());
													pfDto.setFieldName(pf.getFieldName());
													ps.add(pfDto);
											   });
		
		//create parent category Facet DTOs
		(new HashSet<SidebarFacetDTO>(cs)).stream().forEach(cf -> {
			createParentCategoryFacets(categoryFacets, cs, cf, productQueryBuilder, jpaQuery, lcl, currency, cf.getLevel());
		});
		
		
		//get a list of facets that exist in the list of received facets
		
		

		
		List<Facet> lpf = 
		receivedPriceFacets.stream().flatMap(x -> 
			priceFacets.stream().filter(y -> 
				x.getToken().equals(y.getValue())).limit(1)).collect(Collectors.toList());
		
		//Apply the selected facets to the facet selection object
		
		
		priceFacetSelection.selectFacets(FacetCombine.OR, 		lpf.toArray(new Facet[0]));
		
		//set pageable definition for jpaQuery
		Pageable pageable = PageRequest.of(page, size);
		PageableUtil pageableUtil = new PageableUtil();
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());
		
		//sort the results
		org.apache.lucene.search.Sort sort = getSortField(sortBy);
		jpaQuery.setSort(sort);
		
		//get the results using jpaQuery object
		results =  jpaQuery.getResultList();
				
		//convert the results of jpaQuery to product Data Transfer Objects 
		List<Product> lp = results.stream().map(pa -> this.convertToProductDO(pa.getProduct(), lcl, currency)).collect(Collectors.toList());
		
		//create a paging object to hold the results of jpaQuery 
		Page<Product> pp = new PageImpl<Product>(lp, pageable, jpaQuery.getResultSize());
		
		List<SidebarFacetDTO> css = cs.stream().sorted(Comparator.comparing(SidebarFacetDTO::getToken)).collect(Collectors.toList());
		
		src.setFacets(css);
		src.getFacets().addAll(bs);
		src.getFacets().addAll(ps);
		src.setProducts(pp);
		
		return src;
	}
	
    public SidebarFacetDTO convertToCategorySidebarDTO(String categoryCode, String lcl, String currency) {
    	Category c = categoryRepository.findByCategoryCode(categoryCode);
    	SidebarFacetDTO cf = new SidebarFacetDTO();
    	cf.setId(c.getCategoryId());
    	cf.setDesc(c.getAttributes().stream().filter(ca -> ca.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getCategoryDesc());
    	cf.setLevel(c.getCategoryLevel());
    	if(c.getParent() != null) {
    		cf.setParentId(c.getParent().getCategoryId());
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
    
    private void createParentCategoryFacets(Set<Facet> cfs, Set<SidebarFacetDTO> sc, SidebarFacetDTO c, QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery q, String lcl, String currency, Long baseLevel) {
    	if(c == null) { return; }
    	if(c.getParentId() == null) { return; }
    	
    	Category p = categoryRepository.findByCategoryId(c.getParentId());
    	SidebarFacetDTO pcf = convertToCategorySidebarDTO(p.getCategoryCode(), lcl, currency);
    	String frName = "CategoryFR";
    	String frField = "primaryCategory" + StringUtils.repeat(".parent", baseLevel.intValue() - p.getCategoryLevel().intValue()) + ".categoryToken";
    		
    	FacetingRequest categoryFacetRequest = qb.facet()
    	.name(frName)
    	.onField(frField)
    	.discrete()
    	.orderedBy(FacetSortOrder.FIELD_VALUE)
    	.createFacetingRequest();
    		
    	FacetManager facetMgr = q.getFacetManager();
    	cfs.addAll(facetMgr.getFacets(frName));
    	facetMgr.enableFaceting(categoryFacetRequest);
    	pcf.setToken(String.join("/", Arrays.copyOfRange(c.getToken().split("/"), 0, c.getLevel().intValue()+1)));
    	Facet tmp = facetMgr.getFacets(frName).stream().filter(f -> f.getValue().equals(pcf.getToken())).collect(Collectors.toList()).get(0);
    	pcf.setProductCount(new Long(tmp.getCount()));
		pcf.setFacetingName(tmp.getFacetingName());
		pcf.setFieldName(tmp.getFieldName());
    	sc.add(pcf);
    	this.createParentCategoryFacets(cfs, sc, pcf, qb, q, lcl, currency, baseLevel);
    }
    
    public Product convertToProductDO(final io.javabrains.springbootstarter.entity.Product product, String lcl, String currency) {
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
    
    private String getSortFieldName(String field) {
		switch(field) {
		case "nameAsc":
			return "product.productDesc";
		case "nameDesc":
			return "product.productDesc";
		case "priceDesc":
			return "product.currentMarkdownPriceHKD";
		case "priceAsc":
			return "product.currentMarkdownPriceHKD";
		default: 
			return "product.productDesc";
		}
	}
	
	private org.apache.lucene.search.Sort getSortField(String field) {
		switch(field) {
		case "nameAsc":
			return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field), SortField.Type.STRING, false));
		case "nameDesc":
			return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field), SortField.Type.STRING, true));
		case "priceAsc":
			return new org.apache.lucene.search.Sort(new SortedNumericSortField(getSortFieldName(field), SortField.Type.DOUBLE, false	));
		case "priceDesc":
			return new org.apache.lucene.search.Sort(new SortedNumericSortField(getSortFieldName(field), SortField.Type.DOUBLE, true));
		default: 
			return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field), SortField.Type.STRING, true));
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

}