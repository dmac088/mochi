package io.nzbee.dto.product.shipping;

import org.springframework.stereotype.Component;
import io.nzbee.domain.product.Product;

@Component
public class ShippingProductDTOMapperImpl implements IShippingProductDTOMapper {

	@Override
	public ShippingProductDTO doToDto(Product d) {
		ShippingProductDTO pdto = new ShippingProductDTO();
		
		//brand
		pdto.setBrandCode(d.getBrand().getBrandCode());
		pdto.setBrandDesc(d.getBrand().getBrandDesc());
		
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
	public Product dtoToDo(ShippingProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
