package io.nzbee.view.product.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.brand.view.BrandViewDTO;
import io.nzbee.entity.brand.view.IBrandViewService;
import io.nzbee.view.ports.IBrandViewPortService;

public class BrandViewServiceImpl implements IBrandViewService {

	@Autowired
	private IBrandViewPortService brandService;

	@Override
	public void save(BrandViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BrandViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BrandViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BrandView> findByAllShippingProviders(String locale) {
		return brandService.findByAllShippingProviders(locale);
	}

	@Override
	public List<BrandView> findAllByProductType(String locale, Class<?> clazz) {
		return brandService.findAllByProductType(locale, clazz);
	}
	
}
