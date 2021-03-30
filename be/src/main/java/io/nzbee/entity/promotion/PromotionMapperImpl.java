package io.nzbee.entity.promotion;

import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;

@Component(value = "promotionMapper")
public class PromotionMapperImpl implements IPromotionMapper {

	@Override
	public Promotion DTOToDo(PromotionDTO dto) {

		return new Promotion(	dto.getPromotionCode(), 
								dto.getPromotionDesc(), 
								dto.getPromotionStartDate(),
								dto.getPromotionEndDate(), 
								dto.getMechanicDTO().getMechanicCode(),
								dto.getMechanicDTO().getMechanicDesc());

	}

	@Override
	public PromotionEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
