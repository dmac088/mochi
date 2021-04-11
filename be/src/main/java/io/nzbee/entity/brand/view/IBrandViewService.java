package io.nzbee.entity.brand.view;

import java.util.List;
import io.nzbee.entity.IService;
import io.nzbee.view.product.brand.BrandView;

public interface IBrandViewService extends IService<BrandViewDTO> {

	List<BrandView> findByAllShippingProviders(String locale);

	List<BrandView> findAllByProductType(String locale, Class<?> clazzz);

}
