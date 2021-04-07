package io.nzbee.entity.product.physical.light;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.physical.PhysicalProductEntity;

public class PhysicalProductLightDaoImpl implements IPhysicalProductLightDao {

	@Override
	public Optional<PhysicalProductLightDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PhysicalProductLightDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PhysicalProductLightDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhysicalProductLightDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhysicalProductLightDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PhysicalProductEntity> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PhysicalProductEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhysicalProductEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhysicalProductEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(PhysicalProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PhysicalProductEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PhysicalProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<PhysicalProductLightDTO> findAll(String locale, String currency, String rootCategory, Pageable pageable,
			String orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Page<PhysicalProductLightDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, Class<T> cls, String page, String size, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PhysicalProductLightDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

}
