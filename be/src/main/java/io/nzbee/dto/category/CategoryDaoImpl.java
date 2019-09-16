
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
import io.nzbee.entity.category.brand.readonly.CategoryBrand;
import io.nzbee.entity.category.brand.readonly.CategoryBrand_;
import io.nzbee.entity.category.product.readonly.CategoryProduct_;
import io.nzbee.entity.category.product.readonly.CategoryProduct;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.category.type.CategoryType_;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.entity.product.tag.ProductTag_;
import io.nzbee.variables.CategoryVars;

@Component(value="categoryDomainDao")
public class CategoryDaoImpl implements ICategoryDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Optional<io.nzbee.dto.category.Category> findById(long id, String locale) {
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
	public List<io.nzbee.dto.category.Category> findAll(String locale) {
		System.out.println("bang!");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Root<CategoryProduct> categoryProduct = cb.treat(root, CategoryProduct.class);
		Root<CategoryBrand> categoryBrand = cb.treat(root, CategoryBrand.class);
		

		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, Category> categoryParent = root.join(Category_.parent);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		cq.select(cb.construct(
				io.nzbee.dto.category.Category.class,
				root.get(Category_.categoryId),
				root.get(Category_.categoryCode),
				categoryAttribute.get(CategoryAttribute_.categoryDesc),
				root.get(Category_.categoryLevel),
				categoryType.get(CategoryType_.code),
				categoryAttribute.get(CategoryAttribute_.lclCd),
				categoryParent.get(Category_.categoryCode),
				cb.selectCase()
				.when(cb.equal(categoryType.get(CategoryType_.code), CategoryVars.CATEGORY_TYPE_CODE_PRODUCT), categoryProduct.get(CategoryProduct_.productCount))
				.when(cb.equal(categoryType.get(CategoryType_.code), CategoryVars.CATEGORY_TYPE_CODE_BRAND), categoryBrand.get(CategoryBrand_.brandCount))
				.otherwise(new Long(0))
				)
		);
		
		TypedQuery<io.nzbee.dto.category.Category> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		
		return query.getResultList();
	
	}


	
	public Optional<io.nzbee.dto.category.Category> findByCategoryDesc(String categoryDesc, String locale) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		//Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		//Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		//conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(categoryDesc == null)) {
			conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.categoryDesc), categoryDesc));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		cq.select(cb.construct(
				io.nzbee.dto.category.Category.class,
				root.get(Category_.categoryId),
				root.get(Category_.categoryCode),
				categoryAttribute.get(CategoryAttribute_.categoryDesc),
				root.get(Category_.categoryLevel),
				categoryType.get(CategoryType_.code),
				categoryAttribute.get(CategoryAttribute_.lclCd),
				root.get(Category_.parent).get(Category_.categoryCode)
				)
		);
		
		TypedQuery<io.nzbee.dto.category.Category> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
		
	public Optional<io.nzbee.dto.category.Category> findByCategoryCode(String categoryCode, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<Category> root = cq.from(Category.class);
		//Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		
		//Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		//conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
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
	
	public List<io.nzbee.dto.category.Category> findByParent(String categoryTypeCode, String parentCategoryCode, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, Category> parent = root.join(Category_.parent);
		
		List<Predicate> conditions = new ArrayList<Predicate>();

		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(parentCategoryCode == null)) {
			conditions.add(cb.equal(parent.get(Category_.categoryCode), parentCategoryCode));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<io.nzbee.dto.category.Category> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	
	
	public List<io.nzbee.dto.category.Category> findByLevel(String categoryTypeCode, Long level, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
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
	public List<io.nzbee.dto.category.Category> findChildrenByCriteria(
			String parentCategoryDesc, 
			List<String> brandCodes, 
			List<String> tagCodes, 
			String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.Category> cq = cb.createQuery(io.nzbee.dto.category.Category.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		
		//Join<CategoryProduct, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		//Join<CategoryProduct, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		Join<CategoryProduct, Product> product = root.join(CategoryProduct_.products);
		Join<Product, Brand> brand = product.join(Product_.brand);
		Join<CategoryProduct, Category> parent = root.join(Category_.parent);
		Join<Category, CategoryAttribute> parentCategoryAttribute = parent.join(Category_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		//conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
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
		
		TypedQuery<io.nzbee.dto.category.Category> query = em.createQuery(cq
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

	@Override
	public Optional<io.nzbee.dto.category.Category> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<io.nzbee.dto.category.Category> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
