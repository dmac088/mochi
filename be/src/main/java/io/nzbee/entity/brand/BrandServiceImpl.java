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
	public Optional<Brand> findById(String locale, String currency, long Id) {
		// TODO Auto-generated method stub
		return brandDao.findById(locale, currency, Id);
	}


	@Override
	public Optional<Brand> findByCode(String locale, String currency, String brandCode) {
		// TODO Auto-generated method stub
		return brandDao.findByCode(locale, currency, brandCode);
	}

	@Override
	public List<Brand> findAll(String locale, String currency, List<String> categoryCodes, List<String> tagCodes) {
		// TODO Auto-generated method stub
		return brandDao.findAll(locale, currency, categoryCodes, tagCodes);
	}
	
	@Override
	public List<Brand> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return brandDao.findAll(locale, currency, codes);
	}
	
	@Override
	public Optional<Brand> findByDesc(String locale, String currency, String brandDesc) {
		// TODO Auto-generated method stub
		return brandDao.findByDesc(locale, currency, brandDesc);
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