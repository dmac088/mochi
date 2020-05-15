package io.nzbee.entity.category.layout;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CategoryLayoutDaoImpl implements ICategoryLayoutDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

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
	public CategoryLayout objectToEntity(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CategoryLayout t) {
		em.persist(t);
		em.flush();
	}

	@Override
	public void update(CategoryLayout t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryLayout t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryLayout objectToEntity(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
