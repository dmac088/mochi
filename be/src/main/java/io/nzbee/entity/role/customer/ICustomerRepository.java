package io.nzbee.entity.role.customer;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import io.nzbee.entity.role.IRoleBaseRepository;

@Repository("customerRepository")
public interface ICustomerRepository extends IRoleBaseRepository<Customer> {

	
	List<Customer> findAll();
	
	Optional<Customer> findByRoleId(Long CustomerId);
	
	Optional<Customer> findByRolePartyPartyUserUsername(String userName);
	
	//Customer findOneByCustomerId(String Id);
	//need to use specification since findByCustomerId causes an exception
	Customer findOne(Specification<Customer> spec);
	

}
