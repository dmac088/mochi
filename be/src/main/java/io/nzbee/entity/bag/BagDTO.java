package io.nzbee.entity.bag;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import io.nzbee.entity.bag.item.BagItemDTO;
import io.nzbee.entity.party.person.CustomerDTO;

public class BagDTO {
	
	public static final String ID_ALIAS = "bag_id";
	
	private Long bagId;
	
	private Set<BagItemDTO> bagItems = new HashSet<BagItemDTO>();
	
	private CustomerDTO customer;

	public BagDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagId = ((BigInteger) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.customer	= new CustomerDTO(tuple, aliasToIndexMap);
	}

	public Set<BagItemDTO> getBagItems() {
		return bagItems;
	}

	public Long getBagId() {
		return bagId;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}
	
}
