package io.nzbee.domain.services.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import io.nzbee.domain.Product;
import io.nzbee.domain.Category;
import io.nzbee.domain.Brand;
import io.nzbee.domain.Tag;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.brand.attribute.IBrandAttributeService;
import io.nzbee.entity.product.attribute.IProductAttributeService;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.price.IProductPriceService;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.GeneralVars;
import io.nzbee.variables.ProductVars;

@Service(value = "productDomainService")
@Transactional
public class ProductServiceImpl implements IProductService {
	//In service classes, we should only call methods of entity service classes
	//the repositories themselves should not be referenced outside the entity service class
	//this way we ensure proper separation of concerns
    
	@Autowired 
	@Qualifier("productEntityService")
	private io.nzbee.entity.product.IProductService productService;

    @Autowired 
    private IBrandService brandService;
    
    @Autowired
    private IBrandAttributeService brandAttributeService;
	
    @Autowired 
    private IProductPriceService productPriceService;
    
    @Autowired
    private IProductAttributeService productAttributeService;
    
    @Autowired
    @Qualifier("categoryEntityService")
    private io.nzbee.entity.category.ICategoryService categoryService;
   
    @Override
	@Transactional
	@Cacheable(value="product")
	public Product findOne(String lcl, String currency, String code) {
    	io.nzbee.entity.product.Product pa = productService.findOne(code).get();
		Product p = this.convertToProductDO(pa, lcl, currency);
		return p;
	}	
    
    @Override
    @Cacheable(value="products")
	public Page<Product> findAll(String locale, 
								 String currency, 
								 String categoryDesc, 
								 Double price, 
								 int page, 
								 int size, 
								 String sortBy, 
								 List<Category> categories,
								 List<Brand> brands,
								 List<Tag> tags) {
	
    	//we need to convert to lists of IDs or codes here
    	
    	
    	
     	Page<io.nzbee.entity.product.Product> ppa = 
     			productService.findAll( categoryDesc,
     									categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
     									locale, 
     									new Double(0), 
     									price, 
     									ProductVars.MARKDOWN_SKU_DESCRIPTION, 
     									currency, 
     									new Date(), 
     									new Date(), 
     									PageRequest.of(page, size, this.sortByParam(sortBy)), 
     									brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()), 
     									tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList()));

     	return ppa.map(pa -> this.convertToProductDO(pa, locale, currency));
	}
    
//    @Override
//	//@Cacheable
//	public Page<Product> findAll(String locale, 
//								 String currency, 
//								 String categoryDesc, 
//								 int page, 
//								 int size, 
//								 String sortBy, 
//								 List<Category> categories,
//								 List<Brand> brands,
//								 List<Tag> tags) {
//	
//     	Page<io.nzbee.entity.product.Product> ppa = 
//     			productService.findAll( categoryDesc,
//     									categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
//     									locale, 
//     									ProductVars.MARKDOWN_SKU_DESCRIPTION, 
//     									currency, 
//     									new Date(), 
//     									new Date(), 
//     									PageRequest.of(page, size, this.sortByParam(sortBy)), 
//     									brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()), 
//     									tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList()));
//
//     	return ppa.map(pa -> this.convertToProductDO(pa, locale, currency));
//	}
	
    @Override
	@Cacheable(value="selectedProducts")
	public List<Product> findAll(String locale, String currency, List<Long> productIds) {
		
	    List<io.nzbee.entity.product.Product> lp = 
	    		productService.findAll(locale, currency, productIds);
     	
		return lp.stream().map(p -> { return this.convertToProductDO(p, locale, currency);}).collect(Collectors.toList());
	}
    
    @Override
    public Product convertToProductDO(final io.nzbee.entity.product.Product product, String lcl, String currency) {
    	final Product pDo = new Product();
    	Optional<ProductAttribute> pa = productAttributeService.findByIdAndLocale(product.getProductId(), lcl);
        pDo.setProductUPC(product.getUPC());
        pDo.setProductCreateDt(product.getProductCreateDt());
        pDo.setProductUPC(product.getUPC());
        pDo.setProductDesc(pa.get().getProductDesc());
        pDo.setProductRetail(productPriceService.get(product.getProductId(), ProductVars.PRICE_RETAIL_CODE, new Date(), new Date(), currency).get().getPriceValue());
        pDo.setProductMarkdown(productPriceService.get(product.getProductId(), ProductVars.PRICE_MARKDOWN_CODE, new Date(), new Date(), currency).get().getPriceValue());
        pDo.setProductImage(pa.get().getProductImage());
        pDo.setLclCd(lcl);
        
        final Brand bDo = new Brand();
        bDo.setBrandCode(product.getBrand().getCode());
        bDo.setBrandDesc(product.getBrand().getAttributes().stream().filter(ba -> ba.getLclCd().equals(lcl)).findFirst().get().getBrandDesc());
        
        //we need to do something about brand being a reference object
        pDo.setBrand(Optional.ofNullable(bDo));
        
        StringBuilder sb = new StringBuilder();
        product.getCategories().stream().filter(c -> {return c.getHierarchy().getCode().equals(CategoryVars.PRIMARY_HIERARCHY_CODE);}).collect(Collectors.toList())
        .stream().forEach(c -> sb.append(c.getAttributes().stream().filter(ca -> { return ca.getLclCd().equals(lcl);}).collect(Collectors.toList()).get(0).getCategoryDesc()));
        pDo.setPrimaryCategoryPath(sb.toString());        
        return pDo;
    }
    
    @Override
    public Product convertToProductDO(
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
    
    @Override
    //evicts all from the "products" List cache, 
    @Caching(evict = { 
    	@CacheEvict(value="products", allEntries=true),
    	@CacheEvict(value="selectedProducts", allEntries=true) })
    //overwrites the existing product if it exists in the cache 
    @CachePut(value="product", key = "{ #p.getProductUPC().concat(#p.getLclCd()).concat(#p.getCurrency()) }") 
    public Product save(Product p) {
    	
    	System.out.println("Saving....." + p.getProductUPC());
    	
    	//use entity beans to save, and evict from domain cache, and entity level cache if caching exists there.
    	
    	//we need keys to drive an update
		Optional<io.nzbee.entity.product.Product> oProduct = productService.findOne(p.getProductUPC());
		io.nzbee.entity.product.Product product = oProduct.isPresent() ? oProduct.get() : new io.nzbee.entity.product.Product();
		product.setUPC(p.getProductUPC());
		product.setProductCreateDt(p.getProductCreateDt());
		
		List<ProductAttribute> lpa = new ArrayList<ProductAttribute>();
		//Product Attribute English
		Optional<ProductAttribute> oProductAttributeLcl = productAttributeService.getProductAttribute(product.getProductId(), p.getLclCd());
		ProductAttribute productAttribute = oProductAttributeLcl.isPresent() ? oProductAttributeLcl.get() : new ProductAttribute();
		productAttribute.setProductDesc(p.getProductDesc());
		productAttribute.setLclCd(p.getLclCd());
		productAttribute.setProductImage(p.getProductImage());
		productAttribute.setProduct(product);
		lpa.add(productAttribute);

		product.setAttributes(lpa);
		
		//Brand
		Optional<io.nzbee.entity.brand.Brand> oBrand = brandService.findOne(product);
		io.nzbee.entity.brand.Brand brand = oBrand.isPresent() ? oBrand.get() : new io.nzbee.entity.brand.Brand();
		Optional<Brand> bDo = p.getBrand(); 
		brand.setCode(bDo.get().getBrandCode());
		
		//Brand Attributes
		List<BrandAttribute> lba = new ArrayList<BrandAttribute>();
		Optional<BrandAttribute> oBrandAttribute = brandAttributeService.getBrandAttributes(brand.getId(), p.getLclCd());
		BrandAttribute brandAttributeEN  = oBrandAttribute.isPresent() ? oBrandAttribute.get() : new io.nzbee.entity.brand.attribute.BrandAttribute();
		brandAttributeEN.setBrandDesc(brand.getAttributes().stream().filter(ba -> ba.getLclCd().equals(p.getLclCd())).findFirst().get().getBrandDesc());
		brandAttributeEN.setLclCd(p.getLclCd());
		lba.add(brandAttributeEN);
		
		brand.setAttributes(lba);
		
		product.setBrand(brand);
		return this.convertToProductDO(product, p.getLclCd(), p.getCurrency());
		
		//Price
		//ProductPriceType oPriceType = priceTypeService.
		
		//	Optional<ProductPriceType> oPriceType = productPriceTypeService;
		//	ProductPriceType priceType = oPriceType.isPresent() ? oPriceType.get() : new ProductPriceType();

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
		List<Category> categories, List<Brand> brands, List<Tag> tags) {
		
		return productService.getMaxMarkDownPrice(
				CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
				categoryDesc, 
				locale, 
				currency,  
				ProductVars.ACTIVE_SKU_CODE, 
				brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()), 
				categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList())
				);
	}
	
	
	@Override
	public Double getMaxPriceForCategory(Category category, String currencyCode) {
		return productService.getMaxMarkDownPriceForCategory(category.getCategoryCode(), currencyCode);
	}
	
	@Override
	public Long getCountForCategory(Category category) {
		return productService.getCountForCategory(category.getCategoryCode());
	}
	

	@Override
	public Long getCount(String categoryTypeCode, String categoryDesc, String locale, String currency,
			String productStatusCode, List<Category> categories, List<Brand> brands, List<Tag> tags) {
		
		return tags.isEmpty()
			    ?
				productService.getCount(
				categoryDesc, 
				locale, 
				productStatusCode, 
				brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()),  
				categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()))
				:
				productService.getCountForTags(
				categoryDesc, 
				locale, 
				productStatusCode, 
				brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()),  
				categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
				tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList()));	
	}


}