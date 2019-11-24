package io.nzbee.dto.customer;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.dto.IDto;
import io.nzbee.entity.party.IPartyService;
import io.nzbee.entity.party.Party;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	IPartyService customerService;
	
	@Override
	public Optional<Customer> findById(long Id) {
		// TODO Auto-generated method stub
		return this.entityToDTO(customerService.findById(Id).get());
	}
	
	@Override
	public Optional<Customer> findByCode(String userName) {
		// TODO Auto-generated method stub
		return this.entityToDTO(customerService.findByCode(userName).get());
	}

	@Override
	public Optional<Customer> findByDesc(String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findAll(List<IDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customer> entityToDTO(Party entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customer> doToDto(io.nzbee.domain.customer.Customer domainObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean customerExist(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerNewCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer convertToCustomerDO(Party party) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
    // API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
	


}