package io.javabrains.springbootstarter.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository("CustomCustomerRepository")
public class CustomCustomerRepository implements CustomerRepository {

    @Autowired
    @Qualifier("customerRepository") // inject Spring implementation here
    private CustomerRepository customerRepository;

	@SuppressWarnings("unchecked")
	public Customer save(Customer entity) {
    	System.out.println("calling CustomCustomerRepository.save()");
    	System.out.println("Customer ID = " + entity.getCustomerId());
    	customerRepository.save(entity);
    	return entity;
    }

    // Delegate other methods here ...

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
    
    @Override
	public Customer findOne(Specification<Customer> spec) {
    	return customerRepository.findOne(spec);
    }

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer findByRoleId(Long Id) {
		// TODO Auto-generated method stub
		return customerRepository.findByRoleId(Id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return customerRepository.count();
	}

	@Override
	public void delete(Customer entity) {
		customerRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		customerRepository.deleteAll();
		
	}

	@Override
	public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Customer> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		// TODO Auto-generated method stub
		
	};
    
}
