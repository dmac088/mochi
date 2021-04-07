package io.nzbee.entity.adapters.view;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.shipping.ShippingProduct;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.IProductMapper;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.entity.product.shipping.IShippingProductService;
import io.nzbee.entity.product.shipping.ShippingProductDTO;
import io.nzbee.view.ports.IShippingProductPortService;
import io.nzbee.view.product.shipping.ShippingProductView;

public class ShippingProductAdapterImpl  implements IShippingProductPortService {

	@Autowired
	private IShippingProductService shippingProductService;
	
	@Autowired
	private IProductMapper productMapper;
	
	@Override
	@Transactional(readOnly = true)
	public Page<ShippingProduct> findAllShippingProducts(String locale, String currency, String categoryCode,
			Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page,
			String size, String sort) {

		Page<ShippingProductDTO> pp = shippingProductService.findAll(
															locale, 
															currency,
															categoryCode, 
															new StringCollectionWrapper(categoryCodes), 
															new StringCollectionWrapper(brandCodes), 
															new StringCollectionWrapper(tagCodes),
															maxPrice,
															page, 
															size, 
															sort);

		return new PageImpl<ShippingProduct>(
						// receive a list of entities and map to domain objects
						pp.stream().map(pe -> (ShippingProduct) mapHelper(pe)).collect(Collectors.toList()), PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)),
						pp.getTotalElements());		
		
	}
	

	@Override
	public void save(ShippingProductView viewObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ShippingProductView viewObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ShippingProductView viewObject) {
		// TODO Auto-generated method stub
		
	}

	private Product mapHelper(ProductDTO dto) {
		return productMapper.DTOToDo(dto);
	}

}
