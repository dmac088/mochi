package io.nzbee.entity.product.physical.light;

import org.springframework.stereotype.Component;
import io.nzbee.entity.product.physical.PhysicalProductEntity;
import io.nzbee.view.product.physical.PhysicalProductLightView;

@Component(value="physicalProductLightMapper")
public class PhysicalProductLightMapperImpl implements IPhysicalProductLightMapper {

	@Override
	public PhysicalProductLightView DTOToView(PhysicalProductLightDTO dto) {
		PhysicalProductLightView pplv = new PhysicalProductLightView();
		pplv.setProductUPC(dto.getProductUPC());
		pplv.setProductDesc(dto.getProductDesc());
		pplv.setProductRetail(dto.getRetailPrice());
		pplv.setProductMarkdown(dto.getMarkdownPrice());
		pplv.setBrandDesc(dto.getBrandDesc());
		pplv.setInStock(dto.getInStock());
		pplv.setProductImage(dto.getProductImage());
		return pplv;
	}

	@Override
	public PhysicalProductEntity viewToEntity(PhysicalProductLightView d) {
		// TODO Auto-generated method stub
		return null;
	}

}
