package io.javabrains.springbootstarter.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository("customerRepository")
public interface RoleCustomerRepository extends RoleBaseRepository<RoleCustomer> {

	
	List<RoleCustomer> findAll();
	
	
	Optional<Role> findByRoleId(Long CustomerId);
	
	
	Optional<Role> findByRolePartyPartyUserUsername(String userName);
	
	//Customer findOneByCustomerId(String Id);
	//need to use specification since findByCustomerId causes an exception
	RoleCustomer findOne(Specification<RoleCustomer> spec);
	

}
