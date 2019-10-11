package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="brandEntityService")
public class BrandServiceImpl implements IBrandService {

	@Autowired
	private IBrandDao brandDao; 

	@Override
	public List<Brand> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return brandDao.findAll(locale, currency);
	}

	@Override
	public Optional<Brand> findById(long Id) {
		// TODO Auto-generated method stub
		return brandDao.findById(Id);
	}


	@Override
	public Optional<Brand> findByCode(String brandCode) {
		// TODO Auto-generated method stub
		return brandDao.findByCode(brandCode);
	}

	@Override
	public List<Brand> findAll(List<String> productCategoryCodes, List<String> tagCodes) {
		// TODO Auto-generated method stub
		return brandDao.findAll(productCategoryCodes, tagCodes);
	}
	
	@Override
	public List<Brand> findAll(String brandCategoryCode) {
		// TODO Auto-generated method stub
		return brandDao.findAll(brandCategoryCode);
	}
	
	@Override
	public Optional<Brand> findByDesc(String brandDesc, String locale) {
		// TODO Auto-generated method stub
		return brandDao.findByDesc(brandDesc, locale);
	}

	@Override
	public void save(Brand t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Brand t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Brand t) {
		// TODO Auto-generated method stub
		
	}


	
}