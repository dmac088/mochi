package io.nzbee.view.product.shipping;

import io.nzbee.view.product.shipping.ShippingProductDTO;

public class ShippingProductDTOToDTOMapperImpl implements IShippingProductDTOToDTOMapper {

	@Override
	public ShippingProductDTO toDto(io.nzbee.entity.product.shipping.ShippingProductDTO d) {

		ShippingProductDTO pdto = new ShippingProductDTO();
		
		//brand
		pdto.setBrandCode(d.getBrand().getBrandCode());
		pdto.setBrandDesc(d.getBrand().getBrandDesc());
		
		//product 
		pdto.setProductUPC(d.getProductUPC());
		pdto.setProductDesc(d.getProductDesc());
		pdto.setProductLongDesc(d.getProductLongDesc());
		pdto.setProductMarkdown(d.getMarkdownPrice());
		pdto.setProductRetail(d.getRetailPrice());
		pdto.setLocale(d.getLocale());
		pdto.setCurrency(d.getCurrency());
		
		pdto.setShippingDestinationCode(d.getShippingDestination().getShippingDestinationCode());
		pdto.setShippingDestinationDesc(d.getShippingDestination().getShippingDestinationDesc());
		pdto.setShippingTypeCode(d.getShippingtype().getShippingTypeCode());
		pdto.setShippingTypeDesc(d.getShippingtype().getShippingTypeDesc());
		
		pdto.setWeightLimit(Double.parseDouble(d.getWeightLimit()));
		pdto.setWeightFrom(Double.parseDouble(d.getWeightFrom()));
		pdto.setWeightTo(Double.parseDouble(d.getWeightTo()));
		
		return pdto;
	}

	@Override
	public io.nzbee.entity.product.shipping.ShippingProductDTO toDo(io.nzbee.view.product.shipping.ShippingProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
