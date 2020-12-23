package io.nzbee.test.integration.beans.entity;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.address.PartyAddressEntity;
import io.nzbee.entity.party.address.type.AddressTypeEntity;

@Service
@Profile(value = "tst")
public class PartyAddressEntityBeanFactory {

	public PartyAddressEntity getPartyAddressEntityBean(Party customer, AddressTypeEntity addressType) {
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
