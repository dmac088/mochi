package io.nzbee.domain.brand;

import java.util.Set;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.domain.IFacetService;

@Service(value = "brandDomainService")
@CacheConfig(cacheNames="brands")
public class BrandServiceImpl implements IBrandService, IFacetService {
    
	@Autowired
    private io.nzbee.domain.ports.IBrandPortService brandService;
	
	@Override
	@Transactional
	public Brand findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return brandService.findByCode(locale, currency, code);
	}

	@Override
	@Transactional
	public Brand findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return brandService.findByDesc(locale, currency, desc);
	}
  
    @Override
	@Cacheable
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
	public String tokenToCode(String token) {
		// TODO Auto-generated method stub
		return token;
	}

	@Override
	public String getFacetField() {
		// TODO Auto-generated method stub
		return "product.brand.brandToken";
	}

	@Override
	public String getFacetCategory() {
		// TODO Auto-generated method stub
		return "brand";
	}

	@Override
	public Set<Brand> findAll(String lcl, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}
	
}