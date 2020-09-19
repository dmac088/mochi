package io.nzbee.test.integration.beans;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.bag.Bag;
import io.nzbee.entity.party.Party;

@Service
@Profile(value = "tst")
public class BagEntityBeanFactory {

	@Bean
	public final Bag getBagEntityBean(Party customer/*, BagStatus status*/) {
		final Bag bag = new Bag();
	
		bag.setParty(customer);
		bag.setBagCreatedDateTime(LocalDateTime.now());
		bag.setBagUpdatedDateTime(LocalDateTime.now());
		
		return bag;
	}
	
}