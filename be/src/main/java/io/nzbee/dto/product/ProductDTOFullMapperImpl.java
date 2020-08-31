package io.nzbee.dto.product;

import org.springframework.stereotype.Component;
import io.nzbee.domain.product.Product;

@Component
public class ProductDTOFullMapperImpl implements IProductDTOFullMapper {

	@Override
	public ProductDTOFull doToDto(Product d) {
		ProductDTOFull pdto = new ProductDTOFull();
		
		//brand
		pdto.setBrandCode(d.getBrand().getBrandCode());
		pdto.setBrandDesc(d.getBrand().getBrandDesc());
		
		//cateogry
		pdto.setPrimaryCategoryCode(d.getPrimaryCategory().getCategoryCode());
		pdto.setPrimaryCategoryDesc(d.getPrimaryCategory().getCategoryDesc());
		
		//product 
		pdto.setProductUPC(d.getProductUPC());
		pdto.setProductDesc(d.getProductDesc());
		pdto.setProductLongDesc(d.getProductLongDesc());
		pdto.setProductLongDesc(d.getProductLongDesc());
		pdto.setProductMarkdown(d.getProductMarkdown());
		pdto.setProductRetail(d.getProductRetail());
		pdto.setLocale(d.getLclCd());
		pdto.setCurrency(d.getCurrency());
		pdto.setProductType(d.getProductType());
		
		return pdto;
	}

	@Override
	public Product dtoToDo(ProductDTOFull dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
