package io.nzbee.view.product.shipping;

import org.springframework.stereotype.Component;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.ShippingProduct;

@Component
public class ShippingProductDTOMapperImpl implements IShippingProductDTOMapper {

	@Override
	public ShippingProductDTO doToDto(Product d) {
		ShippingProductDTO pdto = new ShippingProductDTO();
		
		//downcast to a shipping product
		ShippingProduct sp = (ShippingProduct) d;
		
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
		
		pdto.setShippingDestinationCode(sp.getShippingDestinationCode());
		pdto.setShippingDestinationDesc(sp.getShippingDestinationDesc());
		pdto.setShippingTypeCode(sp.getShippingTypeCode());
		pdto.setShippingTypeDesc(sp.getShippingTypeDesc());
		
		pdto.setWeightLimit(sp.getWeightLimit());
		pdto.setWeightFrom(sp.getWeightFrom());
		pdto.setWeightTo(sp.getWeightTo());
		
		return pdto;
	}

	@Override
	public Product dtoToDo(ShippingProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
