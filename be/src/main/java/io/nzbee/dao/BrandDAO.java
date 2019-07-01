package io.nzbee.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import io.nzbee.entity.Brand;
import io.nzbee.entity.BrandAttribute;
import io.nzbee.entity.BrandAttribute_;
import io.nzbee.entity.Brand_;
import io.nzbee.entity.Category;
import io.nzbee.entity.CategoryAttribute;
import io.nzbee.entity.CategoryAttribute_;
import io.nzbee.entity.Category_;
import io.nzbee.entity.Product;
import io.nzbee.entity.ProductAttribute;
import io.nzbee.entity.ProductAttribute_;
import io.nzbee.entity.ProductStatus;
import io.nzbee.entity.ProductStatus_;
import io.nzbee.entity.ProductTag;
import io.nzbee.entity.ProductTag_;
import io.nzbee.entity.Product_;
import io.nzbee.variables.ProductVars;

@Component
public class BrandDAO  implements Dao<Brand> { 

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<Brand> get(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
		
		Root<Brand> root = cq.from(Brand.class);
		
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(root.get(Brand_.brandId), id));

		TypedQuery<Brand> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);

		return Optional.ofNullable(query.getSingleResult());
	}
	
	@Override
	public List<Brand> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
		
		Root<Brand> root = cq.from(Brand.class);
		
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));

		TypedQuery<Brand> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);

		return query.getResultList();
	}
	
	@Override
	public Page<Brand> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void save(Brand t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(Brand t, String[] params) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Brand t) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Brand> getAll(List<Long> categoryIds, String categoryDesc, String locale, List<Long> tagIds) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
	
		CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
		
		Root<Brand> root = cq.from(Brand.class);
		
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductAttribute> productAttribute = brand.join(Product_.attributes);
		Join<Product, Category> category = brand.join(Product_.categories);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> brandAttribute = root.join(Brand_.brandAttributes);
		Join<Category, CategoryAttribute> categoryAttribute = category.join(Category_.attributes);
		//Join<Category, Hierarchy> categoryHierarchy = category.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.categoryDesc), categoryDesc));

		if(!categoryIds.isEmpty()) {
			conditions.add(category.get(Category_.categoryId).in(categoryIds));
		}
		
		if(!tagIds.isEmpty()) {
			Join<Product, ProductTag> tag = brand.join(Product_.tags);
			conditions.add(tag.get(ProductTag_.productTagId).in(tagIds));
		}
		
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));

		TypedQuery<Brand> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);

		
		return query.getResultList();
    }

}