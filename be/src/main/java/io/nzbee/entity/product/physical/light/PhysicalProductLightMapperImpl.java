package io.nzbee.entity.product.physical.light;

import org.springframework.stereotype.Component;
import io.nzbee.entity.product.physical.PhysicalProductEntity;
import io.nzbee.view.product.physical.light.PhysicalProductLightView;

@Component(value="physicalProductLightMapper")
public class PhysicalProductLightMapperImpl implements IPhysicalProductLightMapper {

	@Override
	public PhysicalProductLightView DTOToView(PhysicalProductLightDTO dto) {
		PhysicalProductLightView pplv = new PhysicalProductLightView();
		pplv.setProductUPC(dto.getProductupc());
		pplv.setProductDesc(dto.getProductdesc());
		pplv.setProductRetail(dto.getRetailprice());
		pplv.setProductMarkdown(dto.getMarkdownprice());
		pplv.setBrandDesc(dto.getBranddesc());
		pplv.setInStock(dto.getInstock());
		pplv.setProductImage(dto.getProductimage());
		return pplv;
	}

	@Override
	public PhysicalProductEntity viewToEntity(PhysicalProductLightView d) {
		// TODO Auto-generated method stub
		return null;
	}

}
