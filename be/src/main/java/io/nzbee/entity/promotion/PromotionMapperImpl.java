package io.nzbee.entity.promotion;


import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.OrderPromotion;
import io.nzbee.domain.promotion.ProductPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.order.PromotionOrderDTO;
import io.nzbee.entity.promotion.product.PromotionProductDTO;

@Component(value = "promotionMapper")
public class PromotionMapperImpl implements IPromotionMapper {

	@Override
	public Promotion DTOToDo(PromotionDTO dto) {
		if(dto instanceof PromotionProductDTO) {
			return new ProductPromotion(	dto.getPromotionCode(), 
											dto.getPromotionDesc(), 
											dto.getPromotionStartDate(),
											dto.getPromotionEndDate(),
											dto.getMechanicDTO().getMechanicCode(),
											dto.getMechanicDTO().getMechanicDesc(),
											dto.getTypeDTO().getPromotionTypeCode(),
											dto.getTypeDTO().getPromotionTypeDesc());
		}
		
		if(dto instanceof PromotionOrderDTO) {
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
		return null;
	}

	@Override
	public PromotionEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
