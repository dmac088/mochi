package io.nzbee.entity.bag;

import java.util.Map;
import java.util.Set;

import io.nzbee.entity.bag.item.BagItemDTO;
import io.nzbee.entity.party.person.CustomerDTO;

public class BagDTO {
	
	public static final String ID_ALIAS = "bag_id";
	
	private Set<BagItemDTO> bagItems;
	
	private CustomerDTO customer;

	public BagDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		// TODO Auto-generated constructor stub
	}

	public Set<BagItemDTO> getBagItems() {
		return bagItems;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}
	
}
