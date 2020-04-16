package io.nzbee.entity.category.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
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
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.category.type.CategoryType_;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;

@Service
public class CategoryProductDaoImpl implements ICategoryProductDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public List<CategoryProduct> findAllByProductCode(String locale, String currency, String productCode) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		
		Join<CategoryProduct, CategoryAttribute> attribute = root.join(Category_.attributes);
		Join<CategoryProduct, Product> products = root.join(CategoryProduct_.products);
		Join<CategoryProduct, CategoryType> type = root.join(Category_.categoryType);
		
		cq.multiselect(	root.get(CategoryProduct_.categoryId).alias("categoryId"),
						root.get(CategoryProduct_.categoryCode).alias("categoryCode"),
						attribute.get(CategoryAttribute_.categoryAttributeId).alias("categoryAttributeId"),
						attribute.get(CategoryAttribute_.categoryDesc).alias("categoryDesc"),
						type.get(CategoryType_.categoryTypeCode).alias("categoryTypeCode"),
						type.get(CategoryType_.categoryTypeDesc).alias("categoryTypeDesc")
		);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
	
		if(!(productCode == null)) {
			
			cq.where(cb.and(
					cb.equal(products.get(Product_.productUPC), productCode),
					cb.equal(attribute.get(CategoryAttribute_.lclCd), locale)
					)
			);
			
			conditions.add(cb.equal(products.get(Product_.productUPC), productCode));
		}
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> {

			CategoryAttribute ca = new CategoryAttribute();
			ca.setCategoryDesc(t.get("categoryDesc").toString());
			ca.setLclCd(locale);
			
			CategoryType ct = new CategoryType();
			ct.setCategoryTypeCode(t.get("categoryTypeCode").toString());
			ct.setCategoryTypeDesc(t.get("categoryTypeDesc").toString());
			
			CategoryProduct cp = new CategoryProduct();
			cp.setCategoryId(Long.parseLong(t.get("categoryId").toString()));
			cp.setCategoryCode(t.get("categoryCode").toString());
			cp.setLocale(locale);
			cp.setCurrency(currency);
			cp.setCategoryAttribute(ca);
			cp.setCategoryType(ct);
			
			return cp; 
		}).collect(Collectors.toList());
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

	@Override
	public CategoryProduct objectToEntity(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

}
