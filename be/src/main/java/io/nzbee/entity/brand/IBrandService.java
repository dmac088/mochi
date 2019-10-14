package io.nzbee.entity.brand;

import java.util.List;
import io.nzbee.entity.ILocalizedService;

public interface IBrandService extends ILocalizedService<Brand> {
	
	List<Brand> findAll(String locale, String currency, List<String> categoryCodes, List<String> tagCodes);

}
