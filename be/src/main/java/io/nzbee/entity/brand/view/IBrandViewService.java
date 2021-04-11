package io.nzbee.entity.brand.view;

import java.util.List;
import io.nzbee.entity.IService;

public interface IBrandViewService extends IService<BrandViewDTO> {


	List<BrandViewDTO> findAllByProductType(String locale, Class<?> clazz);

}
