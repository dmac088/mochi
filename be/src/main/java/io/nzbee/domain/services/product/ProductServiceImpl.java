package io.nzbee.domain.services.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import io.nzbee.domain.Product;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.product.attribute.IProductAttributeService;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.price.IProductPriceService;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.GeneralVars;
import io.nzbee.variables.ProductVars;

@Service(value = "productDomainService")
@Transactional
@CacheConfig(cacheNames="products")
public class ProductServiceImpl implements IProductService {
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
    @Qualifier("categoryEntityService")
    private io.nzbee.entity.category.ICategoryService categoryService;
    

    
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
     			productService.findAll( categoryDesc,
     									categoryIds, 
     									locale, 
     									new Double(0), 
     									price, 
     									ProductVars.MARKDOWN_SKU_DESCRIPTION, 
     									currency, 
     									new Date(), 
     									new Date(), 
     									PageRequest.of(page, size, this.sortByParam(sortBy)), 
     									brandIds, 
     									tagIds);

     	return ppa.map(pa -> this.convertToProductDO(pa, locale, currency));
	}
	
    @Override
	@Cacheable
	public List<Product> findAll(String locale, String currency, List<Long> productIds) {
		
	    List<io.nzbee.entity.product.Product> lp = 
	    		productService.findAll(locale, currency, productIds);
     	
		return lp.stream().map(p -> { return this.convertToProductDO(p, locale, currency);}).collect(Collectors.toList());
	}
    
    @Override
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
		
		return productService.getMaxMarkDownPrice(
				CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
				categoryDesc, 
				locale, 
				currency,  
				ProductVars.ACTIVE_SKU_CODE, 
				brandIds, 
				brandIds.stream().filter(b -> b.longValue() > -1).collect(Collectors.toList()).size(), 
				categoryIds, 
				categoryIds.stream().filter(c -> c.longValue() > -1).collect(Collectors.toList()).size()//, 
				//tagIds, 
				//tagIds.stream().filter(t -> t.longValue() > -1).collect(Collectors.toList()).size()
				);
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
	
	
//	 public Product convertToProductDO(final io.nzbee.entity.product.Product product, String lcl, String currency) {
//	    	final Product pDo = new Product();
//	    	Optional<ProductAttribute> pa = productAttributeService.findByIdAndLocale(product.getProductId(), lcl);
//	        pDo.setProductId(product.getProductId());
//	        pDo.setProductCreateDt(product.getProductCreateDt());
//	        pDo.setProductUPC(product.getUPC());
//	        pDo.setProductDesc(pa.get().getProductDesc());
//	        pDo.setProductRetail(productPriceService.get(product.getProductId(), ProductVars.PRICE_RETAIL_CODE, new Date(), new Date(), currency).get().getPriceValue());
//	        pDo.setProductMarkdown(productPriceService.get(product.getProductId(), ProductVars.PRICE_MARKDOWN_CODE, new Date(), new Date(), currency).get().getPriceValue());
//	        pDo.setProductImage(pa.get().getProductImage());
//	        pDo.setLclCd(lcl);
//	        pDo.setBrandDesc(product.getBrand().getAttributes().stream()
//	        .filter( ba -> ba.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getBrandDesc());
//	        
//	        StringBuilder sb = new StringBuilder();
//	        product.getCategories().stream().filter(c -> {return c.getHierarchy().getCode().equals(CategoryVars.PRIMARY_HIERARCHY_CODE);}).collect(Collectors.toList())
//	        .stream().sorted(Comparator.comparingLong(Category::getCategoryLevel)).collect(Collectors.toList())
//	        .stream().forEach(c -> sb.append(c.getAttributes().stream().filter(ca -> { return ca.getLclCd().equals(lcl);}).collect(Collectors.toList()).get(0).getCategoryDesc()));
//	        pDo.setPrimaryCategoryPath(sb.toString());        
//	        return pDo;
//	    }

}