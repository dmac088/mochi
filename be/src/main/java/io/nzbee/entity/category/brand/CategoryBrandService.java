package io.nzbee.entity.category.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryBrandService implements ICategoryBrandService {

	@Autowired
	private ICategoryBrandDao brandCategoryDao;
	
	@Override
	public List<CategoryBrand> findAllByBrandCode(String locale, String currency, String brandCode) {
		return brandCategoryDao.findAllByBrandCode(locale, currency, brandCode);
	}

}
