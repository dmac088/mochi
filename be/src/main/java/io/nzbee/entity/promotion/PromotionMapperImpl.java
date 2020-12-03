package io.nzbee.entity.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.PromotionBNGNPCT;
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicMapper;

@Component(value="promotionMapper")
public class PromotionMapperImpl implements IPromotionMapper {

	@Autowired
	private IPromotionMechanicMapper mechanicMapper;
	
	@Override
	public Promotion DTOToDo(PromotionDTO dto) {
		if(dto instanceof PromotionBNGNPCTDTO) {
			PromotionBNGNPCTDTO bngnpctDTO = (PromotionBNGNPCTDTO) dto;
			
			return new PromotionBNGNPCT(
					bngnpctDTO.getPromotionCode(), 
					 bngnpctDTO.getPromotionDesc(),
					 bngnpctDTO.getPromotionStartDate(),
					 bngnpctDTO.getPromotionEndDate(),
					 mechanicMapper.DTOToDo(bngnpctDTO.getMechanicDTO()),
					 bngnpctDTO.getBuyQty(),
					 bngnpctDTO.getDiscountPct()
					);	
		}
		return null;
	}

	@Override
	public PromotionEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
