package io.nzbee.dto.bag;

import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;

@Component
public class BagDTOMapperImpl implements IBagDTOMapper {


	@Override
	public BagDTO doToDto(Bag d) {
		BagDTO dto = new BagDTO();
		dto.setBagStatusCode(d.getBagStatus().toString());
		dto.setBagStatusDesc(d.getBagStatus().toString());
		dto.setTotalItems(d.getTotalItems());
		dto.setTotalQuantity(d.getTotalQuantity());
		return dto;
	}

	@Override
	public Bag dtoToDo(BagDTO dto) {
		return null;
	}

}
