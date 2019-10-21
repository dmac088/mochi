package io.nzbee.entity.brand;

import java.util.List;
import io.nzbee.entity.ILocalizedDao;

public interface IBrandDao extends ILocalizedDao<Brand> {

	List<Brand> findAll(String locale, String currency, List<String> categoryCodes, List<String> tagCodes);
	
	List<Brand> findAll(String locale, String currency, List<String> brandCodes);


	List<Brand> findAllByCategory(String locale, String currency, String categoryCode);
}
