package io.nzbee.entity.product.tag;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.entity.product.tag.attribute.ProductTagAttribute;

@Service(value = "productTagService")
public class ProductTagServiceImpl implements IProductTagService {

	@Autowired
	private IProductTagDao productTagDAO;
	
	@Override
	public List<ProductTagAttribute> findAll(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd,
			String priceType, String currency, Date priceDateStart, Date priceDateEnd, List<Long> brandIds) {
		// TODO Auto-generated method stub
		return productTagDAO.findAll(categoryIds, locale, priceStart, priceEnd, priceType, currency, priceDateStart, priceDateEnd, brandIds);
	}
	
}
