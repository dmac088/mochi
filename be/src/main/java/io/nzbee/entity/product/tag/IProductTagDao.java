package io.nzbee.entity.product.tag;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IProductTagDao  extends IDao<ProductTag> {
	
	List<ProductTag> findAll(String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, List<Long> categoryIds, List<Long> brandIds);

	Optional<ProductTag> findByCode(String code);
	
	
}
