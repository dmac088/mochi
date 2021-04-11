package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.brand.view.IBrandViewMapper;
import io.nzbee.entity.brand.view.IBrandViewService;
import io.nzbee.entity.product.shipping.ShippingProductDTO;
import io.nzbee.view.ports.IBrandViewPortService;
import io.nzbee.view.product.brand.BrandView;
 
public class BrandAdapterImpl implements IBrandViewPortService {
	
	@Autowired
	private IBrandViewService brandViewService;
	
	@Autowired
	private IBrandViewMapper brandMapper;
	
	@Override
	public List<BrandView> findByAllShippingProviders(String locale) {
		return brandViewService.findAllByProductType(locale, ShippingProductDTO.class)
				.stream().map(b -> brandMapper.toView(b)).collect(Collectors.toList());
	}

	@Override
	public List<BrandView> findAllByProductType(String locale, Class<?> clazz) {
		return brandViewService.findAllByProductType(locale, clazz)
				.stream().map(b -> brandMapper.toView(b)).collect(Collectors.toList());
	}

	
}
