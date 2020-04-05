package io.nzbee.domain.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.nzbee.domain.IDomainObject;
import io.nzbee.dto.IDto;
import io.nzbee.ui.component.web.facet.IFacet;

@Service(value = "productDomainService")
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
	public Product findById(String locale, String currency, Long Id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	@Cacheable(value="product")
	public Product findByCode(String locale, String currency, String code) {
	   	return this.dtoToDO(productDtoService.findByCode(locale, currency, code)).get();
	}	

	@Override
	public Product findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return this.dtoToDO(productDtoService.findByDesc(locale, currency, desc)).get();
	}

	@Override
	public Set<Product> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return productDtoService.findAll(locale, currency)
				.stream().map(p->this.dtoToDO(Optional.ofNullable(p)).get())
				.collect(Collectors.toSet());
	}
    
	@Override
	public Set<Product> findAll(String locale, String currency, List<String> productCodes) {
		// TODO Auto-generated method stub
		return productDtoService.findAll(locale, currency, productCodes)
				.stream().map(p->this.dtoToDO(Optional.ofNullable(p)).get())
				.collect(Collectors.toSet());
	}
	
	@Override
	public Set<Product> findAll(String locale, String currency, String categoryDesc, List<IDomainObject> lDo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findAll(	String locale, 
									String currency, 
									Pageable pageable, 
									String categoryDesc,
									Set<IFacet> facets, 
									String sortBy) {
		// TODO Auto-generated method stub
		Page<io.nzbee.dto.product.Product> pp =
			productDtoService.findAll(locale, 
									  currency, 
									  pageable, 
									  categoryDesc, 
									  facets.stream()
									  .map(f -> (IDto) productDtoService.doToDto(Optional.ofNullable((Product)f.getPayload())).get()).collect(Collectors.toList()), 
									  sortBy);
				
		    return new PageImpl<Product>(
			pp.stream().map(p -> this.dtoToDO(Optional.ofNullable(p)).get()).collect(Collectors.toList()),
			pageable,
			pp.getTotalElements());
	}
	
	@Override
	public Page<Product> findAll(	String locale, 
									String currency, 
									Double price, 
									Pageable pageable, 
									String categoryDesc,
									Set<IFacet> facets, 
									String sortBy) {
		// TODO Auto-generated method stub
    	Page<io.nzbee.dto.product.Product> pp =
		   productDtoService.findAll(	 locale, 
										 currency, 
										 price, 
										 pageable, 
										 categoryDesc, 
										 facets.stream()
										 .map(f -> (IDto) productDtoService.doToDto(Optional.ofNullable((Product)f.getPayload())).get()).collect(Collectors.toList()), 
										 sortBy);
		
     	return new PageImpl<Product>(pp.stream().map(p -> this.dtoToDO(Optional.ofNullable(p)).get()).collect(Collectors.toList()),
									 pageable,
								 	 pp.getTotalElements());
    	
	}
	
	@Override
	public Page<Product> findAllByBrand(	
									String locale, 
									String currency, 
									Double price, 
									Pageable pageable, 
									String categoryDesc,
									String brandCode,
									String sortBy) {
	
		
     	return null;
    	
	}


	@Override
	public String tokenToCode(String token) {
		// TODO Auto-generated method stub
		return token;
	}

	@Override
	public Optional<Product> dtoToDO(Optional<io.nzbee.dto.product.Product> p) {
		// TODO Auto-generated method stub
		if(p.isPresent()) {
			io.nzbee.dto.product.Product productDto = p.get();
			Product domainProduct = new Product();
			domainProduct.setProductUPC(productDto.getProductUPC());
			domainProduct.setCurrency(productDto.getCurrency());
			domainProduct.setLclCd(productDto.getLclCd());
			domainProduct.setProductCreateDt(productDto.getProductCreateDt());
			domainProduct.setProductDesc(productDto.getProductDesc());
			domainProduct.setProductImage(productDto.getProductImage());
			domainProduct.setProductMarkdown(productDto.getProductMarkdown());
			domainProduct.setProductRetail(productDto.getProductRetail());
			domainProduct.setDisplayCategories(productDto.getDisplayCategories());
			return Optional.ofNullable(domainProduct);
		}
		return Optional.empty();
	}
	
}