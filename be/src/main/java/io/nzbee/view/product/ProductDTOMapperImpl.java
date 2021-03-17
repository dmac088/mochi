package io.nzbee.view.product;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.physical.PhysicalProduct;
import io.nzbee.domain.product.shipping.ShippingProduct;
import io.nzbee.view.product.physical.IPhysicalProductDTOMapper;
import io.nzbee.view.product.shipping.IShippingProductDTOMapper;

public class ProductDTOMapperImpl implements IProductDTOMapper {

	@Autowired
	private IPhysicalProductDTOMapper physicalProductDTOMapper;
	
	@Autowired
	private IShippingProductDTOMapper shippingProductDTOMapper;
	
	@Override
	public ProductDTO doToDto(Product d) {
		
		if(d instanceof PhysicalProduct) {
			return physicalProductDTOMapper.doToDto(d);
		} else if (d instanceof ShippingProduct) {
			return shippingProductDTOMapper.doToDto(d);
		}
		return  null;
	}

	@Override
	public Product dtoToDo(ProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
