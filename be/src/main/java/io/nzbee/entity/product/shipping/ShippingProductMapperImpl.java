package io.nzbee.entity.product.shipping;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.shipping.ShippingProduct;
import io.nzbee.entity.promotion.IPromotionMapper;

@Component(value="shippingProductMapper")
public class ShippingProductMapperImpl implements IShippingProductMapper {
	
	@Autowired
	private IPromotionMapper promotionMapper;
	
	@Override
	public Product DTOToDo(ShippingProductDTO dto) {
		return new ShippingProduct(
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
			   	true,
			   	dto.getPromotions().stream().map(promo -> promotionMapper.DTOToDo(promo)).collect(Collectors.toList()),
			   	Double.valueOf(((ShippingProductDTO) dto).getWeightLimit()),
			   	Double.valueOf(((ShippingProductDTO) dto).getWeightFrom()),
			   	Double.valueOf(((ShippingProductDTO) dto).getWeightTo()),
			   	((ShippingProductDTO) dto).getShippingDestination().getShippingDestinationCode(),
			   	((ShippingProductDTO) dto).getShippingDestination().getShippingDestinationDesc(),
			   	((ShippingProductDTO) dto).getShippingtype().getShippingTypeCode(),
			   	((ShippingProductDTO) dto).getShippingtype().getShippingTypeDesc());
	}

	@Override
	public ShippingProductEntity doToEntity(Product d) {
		// TODO Auto-generated method stub
		return null;
	}

}
