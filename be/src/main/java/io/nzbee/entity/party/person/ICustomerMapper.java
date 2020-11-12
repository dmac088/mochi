package io.nzbee.entity.party.person;

import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.IMapper;

public interface ICustomerMapper extends IMapper<Customer, PersonEntity, CustomerDTO> {

	Customer EntityToDo(PersonEntity person);

}
