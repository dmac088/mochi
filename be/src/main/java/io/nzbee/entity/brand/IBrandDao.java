package io.nzbee.entity.brand;

import java.util.List;
import io.nzbee.entity.IDao;

public interface IBrandDao extends IDao<Brand> {

	List<Brand> findAll(String locale, String currency, List<String> categoryCodes, List<String> tagCodes);
	
	List<Brand> findAll(String locale, String currency, List<String> brandCodes);
	
}
