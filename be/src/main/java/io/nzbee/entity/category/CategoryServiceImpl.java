package io.nzbee.entity.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.nzbee.search.IFacetService;

@Service(value = "categoryEntityService")
public class CategoryServiceImpl implements ICategoryService, IFacetService {
	
	public static final String CACHE_NAME = "categoryCache";
	
	@Autowired
	@Qualifier(value = "categoryEntityPostgresDao")
	private ICategoryDao categoryDAO;


	public String getFacetField() {
		return "product.categories.categoryToken";
	}

	@Override
	public String getFacetCategory() {
		return "category";
	}

	@Override
	public void save(CategoryDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CategoryDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryDTO t) {
		// TODO Auto-generated method stub
		
	}


}
