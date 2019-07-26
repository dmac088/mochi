package io.nzbee.entity.product.tag;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "productTagService")
public class ProductTagServiceImpl implements IProductTagService {

	@Autowired
	private IProductTagDao productTagDAO;
	
	@Override
	public List<ProductTag> findAll(String locale, Double priceStart, Double priceEnd,
			String priceType, String currency, Date priceDateStart, Date priceDateEnd, List<Long> categoryIds, List<Long> brandIds) {
		// TODO Auto-generated method stub
		return productTagDAO.findAll(locale, priceStart, priceEnd, priceType, currency, priceDateStart, priceDateEnd, categoryIds, brandIds);
	}
	
}
