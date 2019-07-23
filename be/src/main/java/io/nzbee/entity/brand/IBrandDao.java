package io.nzbee.entity.brand;

import java.util.List;

import io.nzbee.entity.IDao;

public interface IBrandDao extends IDao<Brand> {

	List<Brand> getAll(List<Long> categoryIds, String locale, List<Long> tagIds);
	
}
