package io.nzbee.dto.customer;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.dto.IDto;
import io.nzbee.entity.party.IPartyService;
import io.nzbee.entity.party.Party;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	IPartyService customerService;
	
	@Override
	public Optional<io.nzbee.dto.customer.Customer> findById(long Id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.entityToDTO(customerService.findById(Id).get()));
	}
	
	@Override
	public Optional<io.nzbee.dto.customer.Customer> findByCode(String userName) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.entityToDTO(customerService.findByCode(userName).get()));
	}

	@Override
	public Optional<io.nzbee.dto.customer.Customer> findByDesc(String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<io.nzbee.dto.customer.Customer> findAll(List<IDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public io.nzbee.dto.customer.Customer entityToDTO(Party entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public io.nzbee.dto.customer.Customer doToDto(io.nzbee.domain.customer.Customer domainObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean customerExist(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerNewCustomer(io.nzbee.dto.customer.Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<io.nzbee.dto.customer.Customer> getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(io.nzbee.dto.customer.Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public io.nzbee.dto.customer.Customer convertToCustomerDO(Party party) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCustomer(io.nzbee.dto.customer.Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<io.nzbee.dto.customer.Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



  
	
    // API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
	


}