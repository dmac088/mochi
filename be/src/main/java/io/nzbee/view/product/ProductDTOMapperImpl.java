package io.nzbee.view.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.physical.PhysicalProduct;
import io.nzbee.domain.product.shipping.ShippingProduct;
import io.nzbee.view.product.physical.IPhysicalProductDomainObjectToDTOMapper;
import io.nzbee.view.product.shipping.IShippingProductDomainObjectToDTOMapper;

@Component
public class ProductDTOMapperImpl implements IProductDTOMapper {

	@Autowired
	private IPhysicalProductDomainObjectToDTOMapper physicalProductDTOMapper;
	
	@Autowired
	private IShippingProductDomainObjectToDTOMapper shippingProductDTOMapper;
	
	@Override
	public ProductDTO toDto(Product d) {
		
		if(d instanceof PhysicalProduct) {
			return physicalProductDTOMapper.toDto(d);
		} else if (d instanceof ShippingProduct) {
			return shippingProductDTOMapper.toDto(d);
		}
		return  null;
	}

	@Override
	public Product toDo(ProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
