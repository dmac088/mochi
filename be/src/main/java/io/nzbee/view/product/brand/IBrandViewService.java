package io.nzbee.view.product.brand;

import java.util.List;

import io.nzbee.view.IViewService;

public interface IBrandViewService extends IViewService<BrandView> {

	List<BrandView> findByAllShippingProviders(String locale);

	List<BrandView> findAllByProductType(String locale, Class<?> clazz);


}
