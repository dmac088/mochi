package io.nzbee.entity.promotion.mechanic;

import io.nzbee.domain.promotion.PromotionType;

public class PromotionMechanicMapperImpl implements IPromotionMechanicMapper {

	@Override
	public PromotionType DTOToDo(PromotionMechanicDTO dto) {
		return new PromotionType(
				dto.getMechanicCode(),
				dto.getMechanicDesc());
	}

	@Override
	public PromotionMechanicEntity doToEntity(PromotionType d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
