package io.nzbee.entity.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicMapper;

@Component(value = "promotionMapper")
public class PromotionMapperImpl implements IPromotionMapper {

	@Autowired
	private IPromotionMechanicMapper mechanicMapper;

	@Override
	public Promotion DTOToDo(PromotionDTO dto) {

		return new Promotion(dto.getPromotionCode(), dto.getPromotionDesc(), dto.getPromotionStartDate(),
				dto.getPromotionEndDate(), mechanicMapper.DTOToDo(dto.getMechanicDTO()));

	}

	@Override
	public PromotionEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
