package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.ICustomerPortService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.Person;

@Component
public class PostgresCustomerAdapter implements ICustomerPortService {

	IPersonService customerService;
	
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
		User u = new User();
		Person p = new Person();
		p.setGivenName("a");
		p.setFamilyName("b");
		
		
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
