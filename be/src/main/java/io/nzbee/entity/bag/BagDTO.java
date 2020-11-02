package io.nzbee.entity.bag;

import java.util.Set;

import io.nzbee.entity.bag.item.BagItemDTO;
import io.nzbee.entity.party.person.CustomerDTO;

public class BagDTO {
	
	private Set<BagItemDTO> bagItems;
	
	private CustomerDTO customer;

	public Set<BagItemDTO> getBagItems() {
		return bagItems;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}
	
}
