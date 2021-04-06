package io.nzbee.view.product.physical;

import java.util.stream.Collectors;
import io.nzbee.entity.product.physical.PhysicalProductDTO;

public class PhysicalProductDTOToDTOLightMapperImpl implements IPhysicalProductDTOToDTOLightMapper {

	@Override
	public PhysicalProductDTOLight toDto(PhysicalProductDTO d) {
		PhysicalProductDTOLight pdto = new PhysicalProductDTOLight();
		
		//brand
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
		
		
		String categories = String.join(",", d.getCategories().stream().map(c -> c.getCategoryDesc()).collect(Collectors.toList()));
		pdto.setCategoriesList(categories);
		
		return pdto;
	}

	@Override
	public PhysicalProductDTO toDo(PhysicalProductDTOLight dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
