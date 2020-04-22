package io.nzbee.entity.party.person;

import io.nzbee.domain.customer.Customer;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component(value="personMapper")
public class PersonMapper implements IPersonMapper {

	@Override
	public Optional<Customer> entityToDo(Optional<Person> e) {
		if(!e.isPresent()) { return Optional.empty(); }
		Person pe = e.get();
		io.nzbee.domain.customer.Customer co = null;
		co = new Customer(
			pe.getGivenName(),
			pe.getFamilyName(),
			pe.getPartyUser().getUsername(),
			((io.nzbee.entity.role.customer.Customer) 
				pe.getPartyRoles().stream().filter(r -> r.getRoleType().getRoleTypeDesc().equals(Customer.class.getSimpleName())).findFirst().get()).getCustomerNumber(),
			pe.getPartyUser().isEnabled()
		);		
		return Optional.ofNullable(co);	
	}
	
}
