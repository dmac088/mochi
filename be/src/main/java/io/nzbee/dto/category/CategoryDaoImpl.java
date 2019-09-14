
package io.nzbee.dto.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.Brand_;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.Category_;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.category.product.readonly.CategoryProduct_;
import io.nzbee.entity.category.product.readonly.CategoryProduct;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.category.type.CategoryType_;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.hierarchy.Hierarchy;
import io.nzbee.entity.product.hierarchy.Hierarchy_;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.entity.product.tag.ProductTag_;

@Component
public class CategoryDaoImpl implements ICategoryDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Optional<io.nzbee.dto.category.Category> findById(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(root.get(Category_.categoryId), id));
	
		TypedQuery<io.nzbee.dto.category.Category> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}

	@Override
	public List<io.nzbee.dto.category.Category> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<Category> root = cq.from(Category.class);
				
		TypedQuery<io.nzbee.dto.category.Category> query = em.createQuery(cq
				//.select(root)
				.distinct(true)
		);
		
		return query.getResultList();
	}


	
	public Optional<io.nzbee.dto.category.Category> findByCategoryDesc(String categoryTypeCode, String categoryDesc, String locale) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		//Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(categoryDesc == null)) {
			conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.categoryDesc), categoryDesc));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<io.nzbee.dto.category.Category> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
		
	public Optional<io.nzbee.dto.category.Category> findByCategoryCode(String categoryTypeCode, String categoryCode, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<Category> root = cq.from(Category.class);
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		
		//Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(categoryCode == null)) {
			conditions.add(cb.equal(root.get(Category_.categoryCode), categoryCode));
		}
		
		TypedQuery<io.nzbee.dto.category.Category> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
	
	public List<io.nzbee.dto.category.Category> findByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		Join<Category, Category> parent = root.join(Category_.parent);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(parentCategoryId == null)) {
			conditions.add(cb.equal(parent.get(Category_.categoryId), parentCategoryId));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<io.nzbee.dto.category.Category> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	
	
	public List<io.nzbee.dto.category.Category> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		if(!(level == null)) {
			conditions.add(cb.equal(root.get(Category_.categoryLevel), level));
		}
	
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<io.nzbee.dto.category.Category> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	@Override
	public List<io.nzbee.dto.category.Category> findChildrenByCriteria(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc, List<String> brandCodes, List<String> tagCodes, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.ProductCategory> cq = cb.createQuery(io.nzbee.dto.category.ProductCategory.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		
		Join<CategoryProduct, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<CategoryProduct, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		Join<CategoryProduct, Product> product = root.join(CategoryProduct_.products);
		Join<Product, Brand> brand = product.join(Product_.brand);
		Join<CategoryProduct, Category> parent = root.join(Category_.parent);
		Join<Category, CategoryAttribute> parentCategoryAttribute = parent.join(Category_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
		if(!brandCodes.isEmpty()) {
			conditions.add(brand.get(Brand_.brandCode).in(brandCodes));
		}
		if(!tagCodes.isEmpty()) {
			Join<Product, ProductTag> tags = product.join(Product_.tags);
			conditions.add(tags.get(ProductTag_.productTagCode).in(tagCodes));
		}
		if(!(parentCategoryDesc == null)) {
			conditions.add(cb.equal(parentCategoryAttribute.get(CategoryAttribute_.categoryDesc), parentCategoryDesc));
		}
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<io.nzbee.dto.category.ProductCategory> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList().stream().map(c -> (io.nzbee.dto.category.Category) c).collect(Collectors.toList());
	}

	@Override
	public List<io.nzbee.dto.category.Category> getAll() {
		// TODO Auto-generated method stub
		return this.findAll();
	}

	@Override
	public void save(io.nzbee.dto.category.Category t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(io.nzbee.dto.category.Category t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(io.nzbee.dto.category.Category t) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
