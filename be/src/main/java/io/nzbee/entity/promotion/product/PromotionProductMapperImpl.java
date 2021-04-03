package io.nzbee.entity.promotion.product;

import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.product.ProductPromotion;
import io.nzbee.entity.promotion.product.PromotionProductDTO;

@Component(value = "promotionProductMapper")
public class PromotionProductMapperImpl implements IPromotionProductMapper {

	@Override
	public Promotion DTOToDo(PromotionProductDTO dto) {
		
			return new ProductPromotion(	dto.getPromotionCode(), 
											dto.getPromotionDesc(), 
											dto.getPromotionStartDate(),
											dto.getPromotionEndDate(),
											dto.getMechanicDTO().getMechanicCode(),
											dto.getMechanicDTO().getMechanicDesc(),
											dto.getTypeDTO().getPromotionTypeCode(),
											dto.getTypeDTO().getPromotionTypeDesc());
		
	}

	@Override
	public PromotionProductEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
