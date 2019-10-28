package io.nzbee.dto.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import io.nzbee.dto.product.Product;
import io.nzbee.dto.IDto;
import io.nzbee.variables.ProductVars;

@Service(value = "productDtoService")
@Transactional
public class ProductServiceImpl implements IProductService {
	//In service classes, we should only call methods of entity service classes
	//the repositories themselves should not be referenced outside the entity service class
	//this way we ensure proper separation of concerns
    
	@Autowired 
	@Qualifier("productEntityService")
	private io.nzbee.entity.product.IProductService productService;
    

	@Override
	public Optional<Product> findById(String locale, String currency, long Id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.entityToDTO(locale, currency, productService.findById(locale, currency, Id)));
	}
	
	@Override
	public Optional<Product> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.entityToDTO(locale, currency, productService.findByCode(locale, currency, code)));
	}

	@Override
	public Optional<Product> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.entityToDTO(locale, currency, productService.findByDesc(locale, currency, desc)));
	}

	@Override
	public List<Product> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return productService.findAll(locale, currency)
							 .stream().map(p -> this.entityToDTO(locale, currency, p))
							 .collect(Collectors.toList());
	}
	
	@Override
	public List<Product> findAll(String locale, String currency, List<String> productCodes) {
		// TODO Auto-generated method stub
		return productService.findAll(locale, currency, productCodes)
							 .stream().map(p -> this.entityToDTO(locale, currency, p))
							 .collect(Collectors.toList());
	} 

    @Override
    //@Cacheable(value="products")
	public Page<Product> findAll(String locale, 
								 String currency, 
								 Double price, 
								 Pageable pageable,
								 String categoryDesc, 
								 List<IDto> ldto,
								 String sortBy ) {
	
    	//we need to convert to lists of IDs or codes here
    	Page<io.nzbee.entity.product.Product> pp
    						=  productService.findAll( locale,
						    						   currency, 
						    						   new Double(0), 
						    			    		   price,
						    			    		   ProductVars.PRICE_RETAIL_CODE, 
						    			    		   pageable,
						    						   categoryDesc,
						    						   ldto);
    	
    	return new PageImpl<Product>(
    			pp.stream().map(p -> this.entityToDTO(locale, currency, p)).collect(Collectors.toList()),
    			pageable,
    			pp.getTotalElements());
	}
    
    @Override
    //@Cacheable(value="products")
	public Page<Product> findAll(String locale, 
								 String currency, 
								 Pageable pageable, 
								 String categoryDesc,
								 List<IDto> ldto,
								 String sortBy) {
	
    	Page<io.nzbee.entity.product.Product> pp
				=  productService.findAll(	locale, 
							    			currency, 
							    			ProductVars.PRICE_MARKDOWN_CODE, 
							    			pageable,
							    			categoryDesc, 
							    			ldto);
    	
    	return new PageImpl<Product>(
    			pp.stream().map(p -> this.entityToDTO(locale, currency, p)).collect(Collectors.toList()),
    			pageable,
    			pp.getTotalElements());

	}
   
	@SuppressWarnings("unused")
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
	public Product entityToDTO(String locale, String currency, Object entity) {
		// TODO Auto-generated method stub
		io.nzbee.entity.product.Product p = ((io.nzbee.entity.product.Product) entity);
		
		final Product pDo = new Product();
    	pDo.setProductUPC(p.getUPC());
    	pDo.setProductCreateDt(p.getProductCreateDt());
    	pDo.setProductDesc(p.getProductAttribute().getBrandDesc());
    	pDo.setProductRetail(p.getRetailPrice());
    	pDo.setProductMarkdown(p.getMarkdownPrice());
    	pDo.setProductImage(p.getProductAttribute().getProductImage());
    	pDo.setLclCd(locale);
    	pDo.setCurrency(currency);
    	return pDo;
	}

	

	@Override
	public io.nzbee.dto.product.Product doToDto(Object dO) {
		// TODO Auto-generated method stub
		Product productDO = (Product) dO;
		
		io.nzbee.dto.product.Product dtoProduct = new io.nzbee.dto.product.Product();
		dtoProduct.setProductUPC(productDO.getProductUPC());
		dtoProduct.setCurrency(productDO.getCurrency());
		dtoProduct.setLclCd(productDO.getLclCd());
		dtoProduct.setProductCreateDt(productDO.getProductCreateDt());
		dtoProduct.setProductDesc(productDO.getProductDesc());
		dtoProduct.setProductImage(productDO.getProductImage());
		dtoProduct.setProductMarkdown(productDO.getProductMarkdown());
		dtoProduct.setProductRetail(productDO.getProductRetail());
		return dtoProduct;
	}


}