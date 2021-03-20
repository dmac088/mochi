package io.nzbee.domain.brand;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

public class BrandServiceImpl implements IBrandService {
    
	@Autowired
    private io.nzbee.domain.ports.IBrandPortService brandService;
	
	@Override
	public Brand findByCode(String locale, String code) {
		return brandService.findByCode(locale, code);
	}

	@Override
	public Brand findByDesc(String locale, String desc) {
		return brandService.findByDesc(locale, desc);
	}
  
    @Override
	public List<Brand> findAll(String locale) {
    	return brandService.findAll(locale);
	}
    
    @Override
	public List<Brand> findByAllProductType(String locale, Class<?> cls) {
    	return brandService.findAllByProductType(locale, cls);
	}	
    
    @Override
	public List<Brand> findAll(String locale, Set<String> codes) {
		return brandService.findAll(locale, codes);
	}
    
    @Override
	public List<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice) {
		return brandService.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice);
	}

	@Override
	public void save(Brand object) {
		brandService.save(object);
	}

	@Override
	public void delete(Brand object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Brand object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Brand findByProductCode(String locale, String productCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
}