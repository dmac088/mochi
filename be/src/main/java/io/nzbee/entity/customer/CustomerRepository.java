package io.nzbee.entity.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import io.nzbee.entity.role.Role;
import io.nzbee.entity.role.RoleBaseRepository;

@Repository("customerRepository")
public interface CustomerRepository extends RoleBaseRepository<Customer> {

	
	List<Customer> findAll();
	
	
	Optional<Role> findByRoleId(Long CustomerId);
	
	
	Optional<Role> findByRolePartyPartyUserUsername(String userName);
	
	//Customer findOneByCustomerId(String Id);
	//need to use specification since findByCustomerId causes an exception
	Customer findOne(Specification<Customer> spec);
	

}
