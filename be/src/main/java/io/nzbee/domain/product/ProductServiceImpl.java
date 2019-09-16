package io.nzbee.domain.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.tag.Tag;
import io.nzbee.variables.GeneralVars;

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
    			locale, 
    			currency,
    			categoryDesc,
    			price,  
    			page,
    			size,
    			sortBy,
    			categories,
    			brands,
    			tags
    			); 
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
				     			 locale, 
								 currency, 
								 categoryDesc, 
								 page, 
								 size, 
								 sortBy, 
								 categories,
								 brands,
								 tags);

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
	public Double getMaxPrice(	String categoryDesc, 
								String locale, 
								String markdownSkuDescription, 
								String currency,
								List<Category> categories, 
								List<Brand> brands, 
								List<Tag> tags) {
		
		return productDtoService.getMaxPrice(
				categoryDesc, 
				locale, 
				markdownSkuDescription, 
				currency,
				categories, 
				brands, 
				tags);
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
	public Long getCount(	String categoryTypeCode, 
							String categoryDesc, 
							String locale, 
							String currency,
							String productStatusCode, 
							List<Category> categories, 
							List<Brand> brands, 
							List<Tag> tags) {
		
		return 
			    productDtoService.getCount(
									    categoryTypeCode,
										categoryDesc, 
										locale, 
										currency,
										productStatusCode,
										categories,
										brands,  
										tags
									    );
				
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