package io.nzbee.test.integration.entity.beans.bag;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.bag.BagEntity;

@Service
@Profile(value = "tst")
public class BagEntityBeanFactory implements IBagEntityBeanFactory {

	@Override
	public BagEntity getBean() {
		final BagEntity bag = new BagEntity();
		
		//bag.setParty(customer);
		bag.setBagCreatedDateTime(LocalDateTime.now());
		bag.setBagUpdatedDateTime(LocalDateTime.now());
		
		return bag;
	}
	
}
