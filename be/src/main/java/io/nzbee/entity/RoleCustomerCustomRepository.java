package io.nzbee.entity;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository("CustomCustomerRepository")
public class RoleCustomerCustomRepository implements RoleCustomerRepository {

    @Autowired
    @Qualifier("customerRepository") // inject Spring implementation here
    private RoleCustomerRepository customerRepository;

	@SuppressWarnings("unchecked")
	public RoleCustomer save(RoleCustomer entity) {
    	customerRepository.save(entity);
    	return entity;
    }

    // Delegate other methods here ...

    @Override
    public Optional<RoleCustomer> findById(Long id) {
        return customerRepository.findById(id);
    }
    
    @Override
	public RoleCustomer findOne(Specification<RoleCustomer> spec) {
    	return customerRepository.findOne(spec);
    }

	@Override
	public List<RoleCustomer> findAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return customerRepository.count();
	}

	@Override
	public void delete(RoleCustomer entity) {
		customerRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		customerRepository.deleteAll();
		
	}

	@Override
	public <S extends RoleCustomer> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<RoleCustomer> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends RoleCustomer> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Role> findByRoleId(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Role> findByRolePartyPartyUserUsername(String userName) {
		// TODO Auto-generated method stub
		return customerRepository.findByRolePartyPartyUserUsername(userName);
	}

	
    
}
