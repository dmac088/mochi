package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IBrandDao extends IDao<Brand> {

	List<Brand> findAll(List<Long> categoryIds, List<Long> tagIds);
	
	Optional<Brand> findByCode(String brandCode);
	
}
