package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;

import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.ICustomerPortService;

public class PostgresCustomerAdapter implements ICustomerPortService {

	@Override
	public Optional<Customer> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Customer> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Customer domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Customer> findAll(String locale, String currency, String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Customer> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
