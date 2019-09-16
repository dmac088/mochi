package io.nzbee.domain.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
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
    @Qualifier(value="productDtoService")
    private IProductService productDtoService;
    
    @Override
	@Transactional
	@Cacheable(value="product")
	public Optional<Product> findOne(String locale, String currency, String code) {
    	return productDtoService.findOne(code, locale, currency);
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
    	return productDtoService.findAll(
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
	}
    
    @Override
    @Cacheable(value="products")
	public Page<Product> findAll(String locale, 
								 String currency, 
								 String categoryDesc, 
								 int page, 
								 int size, 
								 String sortBy, 
								 List<Category> categories,
								 List<Brand> brands,
								 List<Tag> tags) {
	
     	return productDtoService.findAll( 
     							categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
     							locale, 
     							ProductVars.MARKDOWN_SKU_DESCRIPTION, 
     							currency, 
     							new Date(), 
     							new Date(), 
     							PageRequest.of(page, size, this.sortByParam(sortBy)), 
     							brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()), 
     							tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList()));

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
		
		return productDtoService.getMaxMarkDownPrice(
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
    	return pDo;
    }

	@Override
	public Long getCount(String categoryTypeCode, String categoryDesc, String locale, String currency,
			String productStatusCode, List<Category> categories, List<Brand> brands, List<Tag> tags) {
		
		return tags.isEmpty()
			    ?
			    productDtoService.getCount(
				categoryDesc, 
				locale, 
				productStatusCode, 
				brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()),  
				categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()))
				:
				productDtoService.getCountForTags(
				categoryDesc, 
				locale, 
				productStatusCode, 
				brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()),  
				categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
				tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList()));	
	}

	@Override
	public List<Product> findAll(String locale, String currency, List<String> productCodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product save(Product t) {
		// TODO Auto-generated method stub
		return null;
	}



}