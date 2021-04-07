package io.nzbee.entity.product.physical.light;

import org.springframework.stereotype.Component;
import io.nzbee.entity.product.physical.PhysicalProductEntity;
import io.nzbee.view.product.physical.PhysicalProductLightView;

@Component(value="physicalProductLightMapper")
public class PhysicalProductLightMapperImpl implements IPhysicalProductLightMapper {

	@Override
	public PhysicalProductLightView DTOToView(PhysicalProductLightDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhysicalProductEntity viewToEntity(PhysicalProductLightView d) {
		// TODO Auto-generated method stub
		return null;
	}

}
