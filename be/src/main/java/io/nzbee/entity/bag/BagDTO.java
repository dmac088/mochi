package io.nzbee.entity.bag;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import io.nzbee.entity.bag.item.BagItemDTO;
import io.nzbee.entity.party.person.PersonDTO;

public class BagDTO {
	
	public static final String ID_ALIAS = "bag_id";
	
	private Long bagId;
	
	private Set<BagItemDTO> bagItems  = new HashSet<BagItemDTO>();
	
	private PersonDTO customer;

	public BagDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagId = ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
	}

	public Set<BagItemDTO> getBagItems() {
		return bagItems;
	}

	public Long getBagId() {
		return bagId;
	}

	public PersonDTO getCustomer() {
		return customer;
	}

	public void setCustomer(PersonDTO customer) {
		this.customer = customer;
	}
	
}
