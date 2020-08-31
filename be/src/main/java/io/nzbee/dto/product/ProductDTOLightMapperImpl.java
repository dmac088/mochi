package io.nzbee.dto.product;

import org.springframework.stereotype.Component;
import io.nzbee.domain.product.Product;

@Component
public class ProductDTOLightMapperImpl implements IProductDTOLightMapper {

	@Override
	public ProductDTOLight doToDto(Product d) {
		ProductDTOLight pdto = new ProductDTOLight();
		
		//brand
		pdto.setBrandDesc(d.getBrand().getBrandDesc());
		
		//cateogry
		pdto.setPrimaryCategoryDesc(d.getPrimaryCategory().getCategoryDesc());
		
		//product 
		pdto.setProductUPC(d.getProductUPC());
		pdto.setProductDesc(d.getProductDesc());
		pdto.setProductLongDesc(d.getProductLongDesc());
		pdto.setProductMarkdown(d.getProductMarkdown());
		pdto.setProductRetail(d.getProductRetail());
		pdto.setLocale(d.getLclCd());
		pdto.setCurrency(d.getCurrency());
		pdto.setProductType(d.getProductType());
		
		return pdto;
	}

	@Override
	public Product dtoToDo(ProductDTOLight dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
