package io.nzbee.entity.brand.attribute;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;

@Service
public class BrandAttributeServiceImpl implements IBrandAttributeService {

	@Autowired
	private BrandAttributeRepository BrandAttributeRepository; 
	
	@Override
	public Optional<BrandAttribute> findById(String locale, long id) {
		return BrandAttributeRepository.findById(id);
	}
	
	@Override
	public List<BrandAttribute> findAll(String locale) {
		return null;//BrandAttributeRepository.findAll();
	}
	

	@Override
	public Optional<BrandAttribute> findByCode(String locale, String code) {
		return BrandAttributeRepository.findByLclCdAndBrandBrandCode(locale, code);
	}

	@Override
	public Optional<BrandAttribute> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<BrandAttribute> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(BrandAttribute t) {
		BrandAttributeRepository.save(t);
	}

	@Override
	public void update(BrandAttribute t, String[] params) {
		BrandAttributeRepository.save(t);
	}

	@Override
	public void delete(BrandAttribute t) {
		BrandAttributeRepository.delete(t);		
	}

	@Override
	public Optional<BrandAttribute> getBrandAttribute(Long id, String locale) {
		return BrandAttributeRepository.findByLclCdAndBrandBrandId(locale, id);
	}
	
	@Override
	public Optional<BrandAttribute> getBrandAttributeEN(Long id) {
		return BrandAttributeRepository.findByLclCdAndBrandBrandId(Constants.localeENGB, id);
	}
	
	@Override
	public Optional<BrandAttribute> getBrandAttributeHK(Long id) {
		return BrandAttributeRepository.findByLclCdAndBrandBrandId(Constants.localeZHHK, id);
	}

	@Override
	public BrandAttribute objectToEntity(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandAttribute objectToEntity(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandAttribute objectToEntity(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandAttribute objectToEntity(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
