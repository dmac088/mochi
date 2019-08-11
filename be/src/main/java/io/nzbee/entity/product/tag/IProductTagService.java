package io.nzbee.entity.product.tag;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IService;


public interface IProductTagService  extends IService<ProductTag> {

	Optional<ProductTag> findOne(String desc, String locale);

	List<ProductTag> findAll(String locale, Double priceStart, Double priceEnd, String priceType, String currency,
			Date priceDateStart, Date priceDateEnd, List<String> categoryCodes, List<String> brandCodes);
	
}
