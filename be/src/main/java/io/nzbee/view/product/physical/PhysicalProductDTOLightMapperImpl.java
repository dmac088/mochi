package io.nzbee.view.product.physical;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.nzbee.domain.product.PhysicalProduct;
import io.nzbee.domain.product.Product;

@Component
public class PhysicalProductDTOLightMapperImpl implements IPhysicalProductDTOLightMapper {

	@Override
	public PhysicalProductDTOLight doToDto(Product d) {
		PhysicalProductDTOLight pdto = new PhysicalProductDTOLight();
		
		//brand
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
		
		if(d instanceof PhysicalProduct) {
			pdto.setInStock(((PhysicalProduct) d).isInStock());
		}
		
		String categories = String.join(",", d.getCategories().stream().map(c -> c.getCategoryDesc()).collect(Collectors.toList()));
		pdto.setCategoriesList(categories);
		
		return pdto;
	}

	@Override
	public Product dtoToDo(PhysicalProductDTOLight dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
