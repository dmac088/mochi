package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;

@Service
public class CategoryProductDaoImpl implements ICategoryProductDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<CategoryProductDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProductDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProductDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryProductDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryProductDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CategoryProductEntity t) {
		em.persist(t);
	}

	@Override
	public void update(CategoryProductEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryProductEntity t) {
		// TODO Auto-generated method stub
		
	}


}
