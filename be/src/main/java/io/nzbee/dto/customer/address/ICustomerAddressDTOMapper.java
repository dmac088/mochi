package io.nzbee.dto.customer.address;

import io.nzbee.domain.customer.address.Address;
import io.nzbee.dto.IMapper;
import io.nzbee.dto.customer.CustomerDTOIn;

public interface ICustomerAddressDTOMapper extends IMapper<CustomerDTOIn, CustomerAddressDTOOut, Address> {

}
