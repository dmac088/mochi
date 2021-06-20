package io.nzbee.entity.category.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.entity.category.ICategoryRepository;

@Service
public class CategoryProductServiceImpl implements ICategoryProductService {

	@Autowired
	private ICategoryRepository<CategoryProductEntity> categoryRepository;
	
	@Override
	public List<CategoryProductEntity> findAll() {
		return categoryRepository.findAll();
	}
	
	@Override
	public void save(CategoryProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CategoryProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryProductEntity t) {
		// TODO Auto-generated method stub
		
	}

}
