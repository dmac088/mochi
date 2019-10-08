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
    
    @Override
	@Transactional
	@Cacheable(value="product")
	public Optional<Product> findOne(String locale, String currency, String code) {
    	return Optional.ofNullable(convertProductDtoToProductDO(productDtoService.findByCode(locale, currency, code).get()));
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
    	
    	//need to map categories domain object to a DTO 
   
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
    			).map(c -> convertProductDtoToProductDO(c)); 
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
														 categories.stream().map(c->productDtoService.doToDto(c)).collect(Collectors.toList()),
														 brands.stream().map(b->productDtoService.doToDto(b)).collect(Collectors.toList()),
														 tags.stream().map(t->productDtoService.doToDto(t)).collect(Collectors.toList()),
														 sortBy);
    	
     	return new PageImpl<Product>(
    			pp.stream().map(p -> this.dtoToDO(p)).collect(Collectors.toList()),
    			PageRequest.of(page, size),
    			pp.getTotalElements());

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
	public List<Product> findAll(String locale, String currency, List<String> productCodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product save(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public io.nzbee.domain.product.Product dtoToDO(Object dto) {
		// TODO Auto-generated method stub
		io.nzbee.dto.product.Product productDto = (io.nzbee.dto.product.Product) dto;
		
		Product domainProduct = new Product();
		domainProduct.setCurrency(productDto.getCurrency());
		domainProduct.setLclCd(productDto.getLclCd());
		domainProduct.setProductCreateDt(productDto.getProductCreateDt());
		domainProduct.setProductDesc(productDto.getProductDesc());
		domainProduct.setProductImage(productDto.getProductImage());
		domainProduct.setProductMarkdown(productDto.getProductMarkdown());
		domainProduct.setProductRetail(productDto.getProductRetail());
		return domainProduct;
	}

}