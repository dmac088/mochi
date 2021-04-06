package io.nzbee.view.product.physical;

import io.nzbee.view.product.physical.PhysicalProductDTO;

public class PhysicalProductDTOToDTOMapperImpl implements IPhysicalProductDTOToDTOMapper {

	@Override
	public PhysicalProductDTO toDto(io.nzbee.entity.product.physical.PhysicalProductDTO d) {
		PhysicalProductDTO pdto = new PhysicalProductDTO();
		
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
		pdto.setInStock(d.isInStock());
		pdto.setWeight(d.getWeight());
		
		return pdto;
	}

	@Override
	public io.nzbee.entity.product.physical.PhysicalProductDTO toDo(PhysicalProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
