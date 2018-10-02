package io.javabrains.springbootstarter.customer;

import java.util.List;

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
    public Customer findOne(Long id) {
        return customerRepository.findOne(id);
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
	public Customer findByroleId(Long Id) {
		// TODO Auto-generated method stub
		return customerRepository.findByroleId(Id);
	}

	@Override
	public <S extends Customer> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return customerRepository.save(entities);
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return customerRepository.exists(id);
	}

	@Override
	public Iterable<Customer> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return customerRepository.findAll(ids);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return customerRepository.count();
	}

	@Override
	public void delete(Long id) {
		customerRepository.delete(id);
		
	}

	@Override
	public void delete(Customer entity) {
		customerRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends Customer> entities) {
		customerRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		customerRepository.deleteAll();
		
	};
    
}
