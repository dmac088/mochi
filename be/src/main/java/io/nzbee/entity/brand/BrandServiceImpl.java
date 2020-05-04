package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.nzbee.search.IFacetService;

@Service(value="brandEntityService")
public class BrandServiceImpl implements IBrandService, IFacetService {

	@Autowired
	private IBrandDao brandDao; 

	@Override
	@Transactional
	public Optional<Brand> findById(String locale, String currency, long Id) {
		return brandDao.findById(locale, currency, Id);
	}

	@Override
	@Transactional
	public Optional<Brand> findByCode(String locale, String currency, String brandCode) {
		return brandDao.findByCode(locale, currency, brandCode);
	}
	
	@Override
	@Transactional
	public Optional<Brand> findByDesc(String locale, String currency, String brandDesc) {
		return brandDao.findByDesc(locale, currency, brandDesc);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Brand> findAll(String locale, String currency) {
		return brandDao.findAll(locale, currency);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Brand> findAll(String locale, String currency, Set<String> categoryCodes, Set<String> tagCodes) {
		return brandDao.findAll(locale, currency, categoryCodes, tagCodes);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Brand> findAll(String locale, String currency, Set<String> codes) {
		return brandDao.findAll(locale, currency, codes);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Brand> findAll(String locale, String currency, String categoryCode) {
		return brandDao.findAllByCategory(locale, currency, categoryCode);
	}
	
	@Override
	public Optional<Brand> findByProductCode(String locale, String currency, String productCode) {
		return brandDao.findByProductCode(locale, currency, productCode);
	}

	@Override
	public void save(Brand t) {
		brandDao.save(t);
	}

	@Override
	public void update(Brand t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Brand t) {
		// TODO Auto-generated method stub
		
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
	public String tokenToCode(String token) {
		return token;
	}


}