package io.nzbee.view.ports;

import java.util.List;
import java.util.Set;
import io.nzbee.view.product.brand.BrandView;

public interface IBrandViewPortService extends IViewPortService<BrandView> {

	List<BrandView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

	List<BrandView> findAll(String locale, String rootCategory, String category);

	List<BrandView> findAllByProductType(String locale, Class<?> cls);

	BrandView findByCode(String locale, String brandCode);
}
