package io.nzbee.entity.ports;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.domain.brand.Brand;

public class PostgresCustomerPortService implements IBrandPortService {

	@Override
	public Optional<Brand> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Brand tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Brand> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Brand findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Brand findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Brand> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Brand> findAll(String locale, String currency, String category) {
		// TODO Auto-generated method stub
		return null;
	}

}
