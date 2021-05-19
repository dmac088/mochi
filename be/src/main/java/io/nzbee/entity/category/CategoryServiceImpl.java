package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Override
	public void save(CategoryEntity t) {
		categoryRepository.save(t);
	}

	@Override
	public void update(CategoryEntity t) {
		categoryRepository.save(t);
	}

	@Override
	public void delete(CategoryEntity t) {
		categoryRepository.delete(t);
	}

	@Override
	public Optional<CategoryEntity> findByCode(String categoryCode) {
		return categoryRepository.findByCategoryCode(categoryCode);
	}

	@Override
	public List<CategoryEntity> findAll() {
		return categoryRepository.findAll();
	}

}
