package io.nzbee.view.bag;

import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;

@Component
public class BagDTOMapperImpl implements IBagDTOMapper {


	@Override
	public BagDTO doToDto(Bag d) {
		BagDTO dto = new BagDTO();
		dto.setTotalItems(d.getTotalItems());
		dto.setTotalQuantity(d.getTotalQuantity());
		dto.setTotalAmount(d.getTotalAmount());
		return dto;
	}

	@Override
	public Bag dtoToDo(BagDTO dto) {
		return null;
	}

}
