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
	private IBrandAttributeRepository brandAttributeRepository; 
	
	@Override
	public Optional<BrandAttributeDTO> findById(String locale, Long id) {
		return null;//BrandAttributeRepository.findById(id);
	}
	
	@Override
	public List<BrandAttributeDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<BrandAttributeDTO> findByCode(String locale, String code) {
		return null;//BrandAttributeRepository.findByLclCdAndBrandBrandCode(locale, code);
	}

	@Override
	public Optional<BrandAttributeDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<BrandAttributeDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(BrandAttributeEntity t) {
		brandAttributeRepository.save(t);
	}

	@Override
	public void update(BrandAttributeEntity t, String[] params) {
		brandAttributeRepository.save(t);
	}

	@Override
	public void delete(BrandAttributeEntity t) {
		brandAttributeRepository.delete(t);		
	}

	@Override
	public Optional<BrandAttributeEntity> getBrandAttribute(Long id, String locale) {
		return brandAttributeRepository.findByLclCdAndBrandBrandId(locale, id);
	}
	
	@Override
	public Optional<BrandAttributeEntity> getBrandAttributeEN(Long id) {
		return brandAttributeRepository.findByLclCdAndBrandBrandId(Constants.localeENGB, id);
	}
	
	@Override
	public Optional<BrandAttributeEntity> getBrandAttributeHK(Long id) {
		return brandAttributeRepository.findByLclCdAndBrandBrandId(Constants.localeZHHK, id);
	}

	@Override
	public BrandAttributeDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandAttributeDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandAttributeDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandAttributeDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BrandAttributeEntity> findById(long id) {
		return brandAttributeRepository.findById(id);
	}

	@Override
	public Optional<BrandAttributeEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandAttributeEntity> findAll() {
		return brandAttributeRepository.findAll();
	}

	@Override
	public List<BrandAttributeEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
