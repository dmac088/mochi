package io.nzbee.view.bag;

import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;

@Component
public class BagDTOMapperImpl implements IBagDTOMapper {


	@Override
	public BagDTO toView(Bag d) {
		BagDTO dto = new BagDTO();
		dto.setTotalItems(d.getTotalItems());
		dto.setTotalQuantity(d.getTotalQuantity());
		dto.setTotalAmount(d.getTotalAmount());
		dto.setTotalWeight(d.getTotalWeight());
		return dto;
	}

}
