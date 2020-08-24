package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.bag.Bag;
import io.nzbee.entity.party.Party;

@Service
@Profile(value = "tst")
public class BagEntityBeanFactory {

	@Bean
	public final Bag getBagEntityBean(Party customer) {
		final Bag bag = new Bag();
	
		bag.setParty(customer);
		
		return bag;
	}
	
}
