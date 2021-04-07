package io.nzbee.entity.party.person;

import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.IDomainObjectMapper;

public interface ICustomerMapper extends IDomainObjectMapper<Customer, PersonEntity, PersonDTO> {

	Customer EntityToDo(PersonEntity person);

}
