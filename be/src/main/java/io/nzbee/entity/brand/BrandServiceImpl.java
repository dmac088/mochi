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
	public Optional<Brand> findById(String locale, long Id) {
		return brandDao.findById(locale, Id);
	}

	@Override
	@Transactional
	public Optional<Brand> findByCode(String locale, String brandCode) {
		return brandDao.findByCode(locale, brandCode);
	}
	
	@Override
	@Transactional
	public Optional<Brand> findByDesc(String locale, String brandDesc) {
		return brandDao.findByDesc(locale, brandDesc);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Set<Brand> findAll(String locale) {
		return brandDao.findAll(locale);
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Brand> findAll(String locale, String currency, String categoryCode,  Set<String> categoryCodes, Set<String> tagCodes, Double maxPrice) {
		return brandDao.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Set<Brand> findAll(String locale, Set<String> codes) {
		return brandDao.findAll(locale, codes);
	}

	@Override
	public Set<Brand> findAll(String lcl, String currency, Set<String> codes) {
		return brandDao.findAll(lcl, codes);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Set<Brand> findAll(String locale, String categoryCode) {
		return brandDao.findAllByCategory(locale, categoryCode);
	}
	
	@Override
	public Optional<Brand> findByProductCode(String locale, String productCode) {
		return brandDao.findByProductCode(locale, productCode);
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