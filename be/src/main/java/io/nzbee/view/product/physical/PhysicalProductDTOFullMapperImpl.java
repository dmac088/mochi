package io.nzbee.view.product.physical;

import org.springframework.stereotype.Component;

import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.physical.PhysicalProduct;

@Component
public class PhysicalProductDTOFullMapperImpl implements IPhysicalProductDTOFullMapper {

	@Override
	public PhysicalProductDTOFull doToDto(Product d) {
		PhysicalProductDTOFull pdto = new PhysicalProductDTOFull();
		
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
		
		if(d instanceof PhysicalProduct) {
			pdto.setInStock(((PhysicalProduct) d).isInStock());
		}
		
		return pdto;
	}

	@Override
	public Product dtoToDo(PhysicalProductDTOFull dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
