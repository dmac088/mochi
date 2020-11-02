package io.nzbee.entity.role.customer;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import io.nzbee.entity.role.IRoleBaseRepository;

@Repository("customerRepository")
public interface ICustomerRepository extends IRoleBaseRepository<CustomerEntity> {

	List<CustomerEntity> findAll();
	
	Optional<CustomerEntity> findByRoleId(Long CustomerId);
	
	Optional<CustomerEntity> findByRolePartyPartyUserUsername(String userName);

	Optional<CustomerEntity> findByCustomerNumber(String customerNumber);

}
