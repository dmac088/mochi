package io.nzbee.test.integration.beans.entity;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.bag.BagEntity;
import io.nzbee.entity.party.Party;

@Service
@Profile(value = "tst")
public class BagEntityBeanFactory {

	@Bean
	public final BagEntity getBagEntityBean(Party customer/*, BagStatus status*/) {
		final BagEntity bag = new BagEntity();
	
		bag.setParty(customer);
		bag.setBagCreatedDateTime(LocalDateTime.now());
		bag.setBagUpdatedDateTime(LocalDateTime.now());
		
		return bag;
	}
	
}
