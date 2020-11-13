package io.nzbee.entity.bag;

import java.util.Optional;
import java.util.Set;
import javax.persistence.Tuple;

public class BagDaoImpl implements IBagDao {

	@Override
	public Optional<BagDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<BagDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<BagDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BagDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BagDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BagDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BagDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagEntity> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<BagEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<BagEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(BagEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BagEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BagEntity t) {
		// TODO Auto-generated method stub
		
	}

}
