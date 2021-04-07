package io.nzbee.entity.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.product.physical.IPhysicalProductDomainObjectMapper;
import io.nzbee.entity.product.physical.PhysicalProductDomainObjectDTO;
import io.nzbee.entity.product.shipping.IShippingProductMapper;
import io.nzbee.entity.product.shipping.ShippingProductDTO;

@Component(value="productMapper")
public class ProductMapperImpl implements IProductMapper {

	@Autowired
	private IShippingProductMapper shippingProductMapper;
	
	@Autowired
	private IPhysicalProductDomainObjectMapper physicalProductMapper;
	
	@Override
	public Product DTOToDo(ProductDTO dto) {
		
		if(dto instanceof PhysicalProductDomainObjectDTO) {
			return physicalProductMapper.DTOToDo((PhysicalProductDomainObjectDTO) dto);
		} else if (dto instanceof ShippingProductDTO) {
			return shippingProductMapper.DTOToDo((ShippingProductDTO) dto);
		}
		return null;
	}

	@Override
	public ProductEntity doToEntity(io.nzbee.domain.product.Product d) {
		// TODO Auto-generated method stub
		return null;
	}
}
