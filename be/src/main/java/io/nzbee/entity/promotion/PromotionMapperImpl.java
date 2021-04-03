package io.nzbee.entity.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.order.IPromotionOrderMapper;
import io.nzbee.entity.promotion.order.PromotionOrderDTO;
import io.nzbee.entity.promotion.product.IPromotionProductMapper;
import io.nzbee.entity.promotion.product.PromotionProductDTO;

@Component(value = "promotionMapper")
public class PromotionMapperImpl implements IPromotionMapper {

	@Autowired
	private IPromotionOrderMapper promotionOrderMapper;
	
	@Autowired
	private IPromotionProductMapper promotionProductMapper;
	
	@Override
	public Promotion DTOToDo(PromotionDTO dto) {
		System.out.println("The instance is of type......");
		System.out.println(dto.getClass());
		if(dto instanceof PromotionProductDTO) {
			return promotionProductMapper.DTOToDo((PromotionProductDTO) dto);
		}
		
		if(dto instanceof PromotionOrderDTO) {
			return promotionOrderMapper.DTOToDo((PromotionOrderDTO) dto);
		}
		return null;
	}

	@Override
	public PromotionEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
