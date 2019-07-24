package io.nzbee.entity.brand;

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
import org.springframework.stereotype.Component;
import io.nzbee.entity.brand.Brand_;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.Category_;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.product.status.ProductStatus_;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.entity.product.tag.ProductTag_;
import io.nzbee.variables.ProductVars;

@Component
public class BrandDAO  implements IBrandDao { 

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<Brand> findById(long id) {
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
	public List<Brand> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
		
		Root<Brand> root = cq.from(Brand.class);
		
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		//conditions.add(cb.equal(root.get(Brand_.brandCode), brandCode));

		TypedQuery<Brand> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);

		return query.getResultList();
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

	
	@Override
	public Optional<Brand> findByCode(String brandCode) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
		
		Root<Brand> root = cq.from(Brand.class);
		
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(root.get(Brand_.brandCode), brandCode));

		TypedQuery<Brand> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);

		return Optional.ofNullable(query.getSingleResult());
	}
	
	

	@Override
	public List<Brand> findAll(List<Long> categoryIds, List<Long> tagIds) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
		
		Root<Brand> root = cq.from(Brand.class);
		
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Product, Category> category = brand.join(Product_.categories);
		Join<Product, ProductTag> productTag = brand.join(Product_.tags);
		
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(category.get(Category_.categoryId).in(categoryIds));
		conditions.add(productTag.get(ProductTag_.productTagId).in(tagIds));

		TypedQuery<Brand> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);

		return query.getResultList();
	}



}