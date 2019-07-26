package io.nzbee.entity.product.tag;

import java.util.Date;
import java.util.List;

import io.nzbee.entity.IDao;
import io.nzbee.entity.product.tag.attribute.ProductTagAttribute;

public interface IProductTagDao  extends IDao<ProductTag> {
	
	List<ProductTag> findAll(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, List<Long> brandIds);
	
	
}
