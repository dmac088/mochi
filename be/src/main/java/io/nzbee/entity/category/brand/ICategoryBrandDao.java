package io.nzbee.entity.category.brand;

import java.util.List;
import io.nzbee.entity.ILocalizedDao;

public interface ICategoryBrandDao extends ILocalizedDao<CategoryBrand> {
	
	List<CategoryBrand> findAllByBrandCode(String locale, String currency, String brandCode);

}
