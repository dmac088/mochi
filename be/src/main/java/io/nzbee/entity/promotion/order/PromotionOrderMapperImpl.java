package io.nzbee.entity.promotion.order;

import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.order.OrderPromotion;

@Component(value = "promotionOrderMapper")
public class PromotionOrderMapperImpl implements IPromotionOrderMapper {

	@Override
	public Promotion DTOToDo(PromotionOrderDTO dto) {
		return new OrderPromotion(	
				dto.getPromotionCode(), 
				dto.getPromotionDesc(), 
				dto.getPromotionStartDate(),
				dto.getPromotionEndDate(),
				dto.getMechanicDTO().getMechanicCode(),
				dto.getMechanicDTO().getMechanicDesc(),
				dto.getTypeDTO().getPromotionTypeCode(),
				dto.getTypeDTO().getPromotionTypeDesc(),
				((PromotionOrderDTO) dto).getCouponCode());
	}


	@Override
	public PromotionOrderEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
