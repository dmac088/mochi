package io.nzbee.entity.brand.view;

import java.util.List;
import io.nzbee.entity.IService;

public interface IBrandDTOService extends IService<BrandDTO> {
	
	List<BrandDTO> findAllByProductType(String locale, Class<?> clazz);

}
