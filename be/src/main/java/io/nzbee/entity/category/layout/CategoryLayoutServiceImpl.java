package io.nzbee.entity.category.layout;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class CategoryLayoutServiceImpl implements ICategoryLayoutService {

	@Override
	public List<CategoryLayout> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryLayout> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryLayout> findById(String locale, String currency, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryLayout> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryLayout> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CategoryLayout t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CategoryLayout t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryLayout t) {
		// TODO Auto-generated method stub
		
	}


}
