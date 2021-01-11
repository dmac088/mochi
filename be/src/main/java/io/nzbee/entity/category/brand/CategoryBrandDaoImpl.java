package io.nzbee.entity.category.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;

@Service
public class CategoryBrandDaoImpl implements ICategoryBrandDao {

	//private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Autowired
	private ICategoryBrandRepository categoryBrandRepository;
	
	@Override
	public List<CategoryBrandEntity> findAllByBrandCode(String locale, String currency, String brandCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<CategoryBrandEntity> findById(long id) {
		return categoryBrandRepository.findById(id);
	}

	@Override
	public Optional<CategoryBrandEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBrandEntity> findAll() {
		return categoryBrandRepository.findAll();
	}

	@Override
	public Optional<CategoryBrandDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrandDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrandDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBrandDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CategoryBrandEntity t) {
		em.persist(t);
	}

	@Override
	public void update(CategoryBrandEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryBrandEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryBrandDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryBrandDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryBrandDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryBrandDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBrandEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBrandDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

}
