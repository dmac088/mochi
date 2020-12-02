package io.nzbee.entity.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.PromotionBNGNPCT;
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicMapper;

public class PromotionMapperImpl implements IPromotionMapper {

	@Autowired
	private IPromotionMechanicMapper mechanicMapper;
	
	@Override
	public Promotion DTOToDo(PromotionDTO dto) {
		return new PromotionBNGNPCT(
				 dto.getPromotionCode(), 
				 dto.getPromotionDesc(),
				 dto.getPromotionStartDate(),
				 dto.getPromotionEndDate(),
				 mechanicMapper.DTOToDo(dto.getMechanicDTO()),
				 1,
				 1
				);
	}

	@Override
	public PromotionEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
