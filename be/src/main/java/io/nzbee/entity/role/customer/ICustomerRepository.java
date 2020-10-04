package io.nzbee.entity.role.customer;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import io.nzbee.entity.role.IRoleBaseRepository;

@Repository("customerRepository")
public interface ICustomerRepository extends IRoleBaseRepository<Customer> {

	List<Customer> findAll();
	
	Optional<Customer> findByRoleId(Long CustomerId);
	
	Optional<Customer> findByRolePartyPartyUserUsername(String userName);

	Optional<Customer> findByCustomerNumber(String customerNumber);

}
