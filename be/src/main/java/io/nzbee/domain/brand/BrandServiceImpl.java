package io.nzbee.domain.brand;

import java.util.Set;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class BrandServiceImpl implements IBrandService {
    
	@Autowired
    private io.nzbee.domain.ports.IBrandPortService brandService;
	
	@Override
	@Transactional(readOnly=true)
	public Brand findByCode(String locale, String code) {
		return brandService.findByCode(locale, code);
	}

	@Override
	@Transactional(readOnly=true)
	public Brand findByDesc(String locale, String desc) {
		return brandService.findByDesc(locale, desc);
	}
  
    @Override
	@Transactional(readOnly=true)
	public Set<Brand> findAll(String locale) {
    	return brandService.findAll(locale);
	}	
    
    @Override
	@Transactional(readOnly=true)
	public Set<Brand> findAll(String locale, Set<String> codes) {
		return brandService.findAll(locale, codes);
	}
    
    @Override
    @Transactional(readOnly=true)
	public Set<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice) {
		return brandService.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice);
	}
    
	@Override
	public Brand findByProductCode(String locale, String productCode) {
		return brandService.findByProductCode(locale, productCode);
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
	
}