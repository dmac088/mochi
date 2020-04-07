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
	public Brand findByCode(String locale, String currency, String code) {
		return brandService.findByCode(locale, currency, code);
	}

	@Override
	public Brand findByDesc(String locale, String currency, String desc) {
		return brandService.findByDesc(locale, currency, desc);
	}
  
    @Override
	@Cacheable
	public Set<Brand> findAll(String locale, String currency) {
    	return brandService.findAll(locale, currency);
	}	
    
    @Override
	public Set<Brand> findAll(String locale, String currency, String category) {
    	return brandService.findAll(locale, currency, category);
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
	public Set<Brand> findAll(String locale, String currency, Set<String> codes) {
		return brandService.findAll(locale, currency, codes);
	}
	
}