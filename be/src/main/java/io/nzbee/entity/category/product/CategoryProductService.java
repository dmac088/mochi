package io.nzbee.entity.category.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryProductService implements ICategoryProductService {

	@Autowired
	private ICategoryProductDao productCategoryDao;
	
	@Override
	public List<CategoryProduct> findAllByProductCode(String locale, String currency, String productCode) {
		return productCategoryDao.findAllByProductCode(locale, currency, productCode);
	}

}
