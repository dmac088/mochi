package io.nzbee.entity.category.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.Category_;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;

@Service
public class CategoryProductDao implements ICategoryProductDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public List<CategoryProduct> findAllByProductCode(String locale, String currency, String productCode) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryProduct> cq = cb.createQuery(CategoryProduct.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<CategoryProduct, Product> products = root.join(CategoryProduct_.products);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
	
		if(!(productCode == null)) {
			conditions.add(cb.equal(products.get(Product_.productUPC), productCode));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<CategoryProduct> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	@Override
	public Optional<CategoryProduct> findById(String locale, String currency, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProduct> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProduct> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryProduct> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryProduct> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CategoryProduct t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CategoryProduct t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryProduct t) {
		// TODO Auto-generated method stub
		
	}

}
