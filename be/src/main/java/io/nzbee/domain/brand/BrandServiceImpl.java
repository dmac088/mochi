package io.nzbee.domain.brand;

import java.util.Set;
import org.springframework.transaction.annotation.Transactional;

import io.nzbee.search.dto.facet.IFacet;

import org.springframework.beans.factory.annotation.Autowired;

public class BrandServiceImpl implements IBrandService {
    
	@Autowired
    private io.nzbee.domain.ports.IBrandPortService brandService;
	
	@Override
	@Transactional(readOnly=true)
	public Brand findByCode(String locale, String currency, String code) {
		return brandService.findByCode(locale, currency, code);
	}

	@Override
	@Transactional(readOnly=true)
	public Brand findByDesc(String locale, String currency, String desc) {
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
    @Transactional(readOnly=true)
	public Set<Brand> findAll(String locale, String currency, String categoryCode, Set<IFacet> selectedFacets) {
		return brandService.findAll(locale, currency, categoryCode, selectedFacets);
	}
    
	@Override
	public Brand findByProductCode(String locale, String currency, String productCode) {
		return brandService.findByProductCode(locale, currency, productCode);
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