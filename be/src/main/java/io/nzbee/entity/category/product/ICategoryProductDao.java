package io.nzbee.entity.category.product;

import java.util.List;
import io.nzbee.entity.ILocalizedDao;

public interface ICategoryProductDao extends ILocalizedDao<CategoryProduct> {
	
	List<CategoryProduct> findAllByProductCode(String locale, String currency, String productCode);

}
