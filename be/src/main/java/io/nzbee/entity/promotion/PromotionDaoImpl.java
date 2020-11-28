package io.nzbee.entity.promotion;

import java.util.Optional;
import java.util.Set;
import javax.persistence.Tuple;

public class PromotionDaoImpl implements IPromotionDao {

	@Override
	public Optional<PromotionDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PromotionDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionEntity> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PromotionEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PromotionEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(PromotionEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PromotionEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PromotionEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<PromotionDTO> findAll(String locale, Set<String> PromotionCodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionDTO> findByProductCode(String locale, String productCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PromotionDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PromotionDTO> findAllByCategory(String locale, String categoryCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
