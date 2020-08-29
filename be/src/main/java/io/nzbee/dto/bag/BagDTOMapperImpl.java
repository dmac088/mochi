package io.nzbee.dto.bag;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.domain.bag.Bag;
import io.nzbee.dto.bag.item.BagItemDTO;
import io.nzbee.dto.bag.item.IBagItemDTOMapper;

@Component
public class BagDTOMapperImpl implements IBagDTOMapper {

	@Autowired
	private IBagItemDTOMapper bagItemMapper;
	
	@Override
	public BagDTO doToDto(Bag d) {
		BagDTO dto = new BagDTO();
		dto.setBagStatusCode(d.getBagStatus().toString());
		dto.setBagStatusDesc(d.getBagStatus().toString());
		dto.setCustomerUserName(d.getCustomer().getUserName());
		dto.setTotalItems(d.getTotalItems());
		dto.setTotalQuantity(d.getTotalQuantity());
		Set<BagItemDTO> sbi = d.getBagItems().stream().map(i -> bagItemMapper.doToDto(i)).collect(Collectors.toSet());
		dto.setBagItems(sbi);
		return dto;
	}

	@Override
	public Bag dtoToDo(BagDTO dto) {
		return null;
	}

}
