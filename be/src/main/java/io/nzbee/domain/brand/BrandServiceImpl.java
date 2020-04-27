package io.nzbee.domain.brand;

import java.util.Optional;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.IFacetService;

public class BrandServiceImpl implements IBrandService, IFacetService {
    
	@Autowired
    private io.nzbee.domain.ports.IBrandPortService brandService;
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Brand> findByCode(String locale, String currency, String code) {
		return brandService.findByCode(locale, currency, code);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Brand> findByDesc(String locale, String currency, String desc) {
		return brandService.findByDesc(locale, currency, desc);
	}
  
    @Override
	@Transactional(readOnly=true)
	public Set<Brand> findAll(String locale, String currency) {
    	return brandService.findAll(locale, currency);
	}	
    
    @Override
    @Transactional(readOnly=true)
	public Set<Brand> findAll(String locale, String currency, String category) {
    	return brandService.findAll(locale, currency, category);
	}
    
    @Override
	@Transactional(readOnly=true)
	public Set<Brand> findAll(String locale, String currency, Set<String> codes) {
		return brandService.findAll(locale, currency, codes);
	}

	@Override
	public String tokenToCode(String token) {
		return token;
	}

	@Override
	public String getFacetField() {
		return "product.brand.brandToken";
	}

	@Override
	public String getFacetCategory() {
		return "brand";
	}

	@Override
	public void save(Brand object) {
		brandService.save(object);
	}

	@Override
	public void delete(Brand object) {
		// TODO Auto-generated method stub
		
	}
}