package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="brandEntityService")
public class BrandServiceImpl implements IBrandService {

	@Autowired
	private IBrandDao brandDao; 

	@Override
	public List<Brand> findAll() {
		// TODO Auto-generated method stub
		return brandDao.findAll();
	}

	@Override
	public Optional<Brand> findById(Long Id) {
		// TODO Auto-generated method stub
		return brandDao.findById(Id);
	}

	@Override
	public Optional<Brand> findByCode(String brandCode) {
		// TODO Auto-generated method stub
		return brandDao.findByCode(brandCode);
	}

	@Override
	public List<Brand> findAll(List<Long> categoryIds, List<Long> tagIds) {
		// TODO Auto-generated method stub
		return brandDao.findAll(categoryIds, tagIds);
	}
	
}