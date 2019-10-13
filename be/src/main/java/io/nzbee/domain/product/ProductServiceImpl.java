package io.nzbee.domain.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.tag.Tag;

@Service(value = "productDomainService")
@Transactional
public class ProductServiceImpl implements IProductService {
	//In service classes, we should only call methods of entity service classes
	//the repositories themselves should not be referenced outside the entity service class
	//this way we ensure proper separation of concerns
    
    @Autowired 
    @Qualifier(value="productDtoService")
    private io.nzbee.dto.product.IProductService productDtoService;
    
    @Autowired 
    @Qualifier(value="categoryDtoService")
    private io.nzbee.dto.category.ICategoryService categoryDtoService;
    
    @Autowired 
    @Qualifier(value="brandDtoService")
    private io.nzbee.dto.brand.IBrandService brandDtoService;
    
    @Autowired 
    @Qualifier(value="tagDtoService")
    private io.nzbee.dto.tag.ITagService tagDtoService;
    
	@Override
	public Optional<Product> findById(String locale, String currency, Long Id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	@Cacheable(value="product")
	public Optional<Product> findByCode(String locale, String currency, String code) {
	   	return Optional.ofNullable(this.dtoToDO(productDtoService.findByCode(locale, currency, code).get()));
	}	

	@Override
	public Optional<Product> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.dtoToDO(productDtoService.findByDesc(locale, currency, desc).get()));
	}

	@Override
	public List<Product> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return productDtoService.findAll(locale, currency)
				.stream().map(p->this.dtoToDO(p))
				.collect(Collectors.toList());
	}
    
  
    
	@Override
	public List<Product> findAll(String locale, String currency, List<String> productCodes) {
		// TODO Auto-generated method stub
		return productDtoService.findAll(locale, currency, productCodes)
				.stream().map(p->this.dtoToDO(p))
				.collect(Collectors.toList());
	}
    
    @Override
    @Cacheable(value="products")
	public Page<Product> findAll(String locale, 
								 String currency, 
								 Double price, 
								 int page, 
								 int size,  
								 String categoryDesc,
								 List<Category> categories,
								 List<Brand> brands,
								 List<Tag> tags,
								 String sortBy) {
    	
    	//need to map categories domain object to a DTO 
   
    	return productDtoService.findAll(
    			locale, 
    			currency,
    			price,  
    			page,
    			size,
    			categoryDesc,
    			categories.stream().map(c->categoryDtoService.doToDto(c)).collect(Collectors.toList()),
				brands.stream().map(b->brandDtoService.doToDto(b)).collect(Collectors.toList()),
				tags.stream().map(t->tagDtoService.doToDto(t)).collect(Collectors.toList()),
				sortBy
    			).map(c -> this.dtoToDO(c)); 
	}
    
    @Override
    @Cacheable(value="products")
	public Page<Product> findAll(String locale, 
								 String currency,
								 int page, 
								 int size,
								 String categoryDesc, 
								 List<Category> categories,
								 List<Brand> brands,
								 List<Tag> tags,
								 String sortBy) {
	
    	Page<io.nzbee.dto.product.Product> pp
								=   
								productDtoService.findAll( 
										     			 locale, 
														 currency,
														 page, 
														 size,
														 categoryDesc,
														 categories.stream().map(c->categoryDtoService.doToDto(c)).collect(Collectors.toList()),
														 brands.stream().map(b->brandDtoService.doToDto(b)).collect(Collectors.toList()),
														 tags.stream().map(t->tagDtoService.doToDto(t)).collect(Collectors.toList()),
														 sortBy);
    	
     	return new PageImpl<Product>(
    			pp.stream().map(p -> this.dtoToDO(p)).collect(Collectors.toList()),
    			PageRequest.of(page, size),
    			pp.getTotalElements());

	}
	
	



	@Override
	public io.nzbee.domain.product.Product dtoToDO(Object dto) {
		// TODO Auto-generated method stub
		io.nzbee.dto.product.Product productDto = (io.nzbee.dto.product.Product) dto;
		
		Product domainProduct = new Product();
		domainProduct.setProductUPC(productDto.getProductUPC());
		domainProduct.setCurrency(productDto.getCurrency());
		domainProduct.setLclCd(productDto.getLclCd());
		domainProduct.setProductCreateDt(productDto.getProductCreateDt());
		domainProduct.setProductDesc(productDto.getProductDesc());
		domainProduct.setProductImage(productDto.getProductImage());
		//domainProduct.setProductMarkdown(productDto.getProductMarkdown());
		//domainProduct.setProductRetail(productDto.getProductRetail());
		return domainProduct;
	}

}