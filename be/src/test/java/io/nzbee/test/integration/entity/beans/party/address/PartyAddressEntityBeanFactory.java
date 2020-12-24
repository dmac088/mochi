package io.nzbee.test.integration.entity.beans.party.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.Constants;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.address.PartyAddressEntity;
import io.nzbee.entity.party.address.type.AddressTypeEntity;
import io.nzbee.entity.party.address.type.IAddressTypeService;
import io.nzbee.entity.party.person.IPersonService;

@Service
@Profile(value = "it")
public class PartyAddressEntityBeanFactory implements IPartyAddressEntityBeanFactory {

	@Autowired 
    private IPersonService personService;
    
    @Autowired 
    private IAddressTypeService addressTypeService;
	
	@Override
	public PartyAddressEntity getBean() {
		
		AddressTypeEntity addressType = addressTypeService.findByCode("BIL01").get();
		Party customer = personService.findByUsernameAndRole("bob@bob", Constants.partyRoleCustomer).get();
		
		final PartyAddressEntity address = new PartyAddressEntity();
		
		address.setAddressLine1("Test address line 1");
		address.setAddressLine2("Test address line 2");
		address.setAddressLine3("Test address line 3");
		address.setAddressCountry("Test Country");
		address.setAddressPostCode("Test PC");
		address.setType(addressType);
	
		address.setParty(customer);
		return address;
	}

}
