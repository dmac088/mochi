package io.nzbee.entity.product.physical;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.physical.PhysicalProduct;
import io.nzbee.entity.promotion.IPromotionMapper;

@Component(value="physicalProductMapper")
public class PhysicalProductDomainObjectMapperImpl implements IPhysicalProductDomainObjectMapper {
	
	@Autowired
	private IPromotionMapper promotionMapper;
	
	@Override
	public Product DTOToDo(PhysicalProductDomainObjectDTO dto) {
		return new PhysicalProduct(
				dto.getProductUPC(),
			   	dto.getProductCreateDt(),
			   	dto.getProductStatusCode(),
			   	dto.getProductDesc(),
			   	dto.getProductLongDesc(),
			   	dto.getRetailPrice(),
			   	dto.getMarkdownPrice(),
			   	dto.getProductImage(),
			   	dto.getLocale(),
			   	dto.getCurrency(),
			   	dto.isInStock(),
			   	dto.getWeight(),
			   	dto.getPromotions().stream().map(promo -> promotionMapper.DTOToDo(promo)).collect(Collectors.toList()));
	}

	@Override
	public PhysicalProductEntity doToEntity(Product d) {
		// TODO Auto-generated method stub
		return null;
	}

}
