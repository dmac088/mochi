
package io.nzbee.entity.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.CategoryProduct_;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.category.type.CategoryType_;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.hierarchy.Hierarchy;
import io.nzbee.entity.product.hierarchy.Hierarchy_;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.entity.product.tag.ProductTag_;
import io.nzbee.variables.ProductVars;

@Component(value="categoryEntityDao")
public class CategoryDaoImpl implements ICategoryDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Optional<Category> findById(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(root.get(Category_.categoryId), id));
	
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}

	
	@Override
	public List<Category> findAll(String locale, String currency) {
		
		@SuppressWarnings("unchecked")
		Query query = em.createNamedQuery("getCategories")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
				 .setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
				 .setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE);
		
		List<Object[]> results = query.getResultList();
		List<Category> lc = results.stream().map(c -> (Category) c[0]).collect(Collectors.toList());
				
		System.out.println(((Category)results.get(0)[0]).getObjectCount());
		
		lc.stream().forEach(c -> {
			System.out.println(c.getCategoryCode());
//			System.out.println(c.getCategoryAttribute().getCategoryDesc());
//			System.out.println(c.getParent().getCategoryAttribute().getCategoryDesc());
			System.out.println(c.getClass().getSimpleName());
			System.out.println(c.getCategoryCode() + " - " + c.getObjectCount());
		});
		
		return null;
	}
	
//	@Override 
//	public List<Category> getChildren(Category category, String currency) {
//		
//		@SuppressWarnings("unchecked")
//		List<Category> c = em.createNamedQuery("getAllCategories")
//				 .setParameter("locale", category.getCategoryAttribute().getLclCd())
//				 .setParameter("currency", currency)
//				 .setParameter("parentCategoryCode", category.getParent().getCategoryCode())
//				 .setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
//				 .setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
//				 .setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE)
//				 .getResultList();
//	
//		return c;
//	}


	@Override
	public void save(Category t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Category t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Category t) {
		// TODO Auto-generated method stub
	}

	
	
	public Optional<Category> findByCategoryDesc(String categoryTypeCode, String categoryDesc, String locale) {
//		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		
//		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
//		
//		Root<Category> root = cq.from(Category.class);
//		
//		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
//		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.categoryAttribute);
//		//Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
//		
//		List<Predicate> conditions = new ArrayList<Predicate>();
//		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
//		conditions.add(cb.equal(categoryType.get(CategoryType_.categoryTypeCode), categoryTypeCode));
//	
//		if(!(categoryDesc == null)) {
//			conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.categoryDesc), categoryDesc));
//		}
//		
//		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
//		
//		TypedQuery<Category> query = em.createQuery(cq
//				.select(root)
//				.where(conditions.toArray(new Predicate[] {}))
//				.distinct(false)
//		);
//		
//		return Optional.ofNullable(query.getSingleResult());
		return null;
	}
		
	public Optional<Category> findByCategoryCode(String categoryTypeCode, String categoryCode, String locale) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//
//		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
//		
//		Root<Category> root = cq.from(Category.class);
//		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
//		
//		//Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
//		
//		List<Predicate> conditions = new ArrayList<Predicate>();
//		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
//		conditions.add(cb.equal(categoryType.get(CategoryType_.categoryTypeCode), categoryTypeCode));
//	
//		if(!(categoryCode == null)) {
//			conditions.add(cb.equal(root.get(Category_.categoryCode), categoryCode));
//		}
//		
//		TypedQuery<Category> query = em.createQuery(cq
//				.select(root)
//				.where(conditions.toArray(new Predicate[] {}))
//				.distinct(false)
//		);
//		
//		return Optional.ofNullable(query.getSingleResult());
		return null;
	}
	
	public List<Category> findByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId, String locale) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		
//		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
//		
//		Root<Category> root = cq.from(Category.class);
//		
//		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
//		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.categoryAttribute);
//		Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
//		Join<Category, Category> parent = root.join(Category_.parent);
//		
//		List<Predicate> conditions = new ArrayList<Predicate>();
//		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.hierarchyCode), hieararchyCode));
//		conditions.add(cb.equal(categoryType.get(CategoryType_.categoryTypeCode), categoryTypeCode));
//	
//		if(!(parentCategoryId == null)) {
//			conditions.add(cb.equal(parent.get(Category_.categoryId), parentCategoryId));
//		}
//		
//		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
//		
//		TypedQuery<Category> query = em.createQuery(cq
//				.select(root)
//				.where(conditions.toArray(new Predicate[] {}))
//				.distinct(true)
//		);
//		
//		return query.getResultList();
		return null;
	}
	
	
	
	public List<Category> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		
//		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
//		
//		Root<Category> root = cq.from(Category.class);
//		
//		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
//		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.categoryAttribute);
//		Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
//		
//		List<Predicate> conditions = new ArrayList<Predicate>();
//		conditions.add(cb.equal(categoryType.get(CategoryType_.categoryTypeCode), categoryTypeCode));
//		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.hierarchyCode), hieararchyCode));
//		if(!(level == null)) {
//			conditions.add(cb.equal(root.get(Category_.categoryLevel), level));
//		}
//	
//		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
//		
//		TypedQuery<Category> query = em.createQuery(cq
//				.select(root)
//				.where(conditions.toArray(new Predicate[] {}))
//				.distinct(true)
//		);
//		
//		return query.getResultList();
		return null;
	}
	
	@Override
	public List<Category> findChildrenByCriteria(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc, List<String> brandCodes, List<String> tagCodes, String locale) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		
//		CriteriaQuery<CategoryProduct> cq = cb.createQuery(CategoryProduct.class);
//		
//		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
//		
//		Join<CategoryProduct, CategoryType> categoryType = root.join(Category_.categoryType);
//		Join<CategoryProduct, CategoryAttribute> categoryAttribute = root.join(Category_.categoryAttribute);
//		Join<CategoryProduct, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
//		Join<CategoryProduct, Product> product = root.join(CategoryProduct_.products);
//		Join<Product, Brand> brand = product.join(Product_.brand);
//		Join<CategoryProduct, Category> parent = root.join(Category_.parent);
//		Join<Category, CategoryAttribute> parentCategoryAttribute = parent.join(Category_.categoryAttribute);
//		
//		List<Predicate> conditions = new ArrayList<Predicate>();
//		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.hierarchyCode), hieararchyCode));
//		conditions.add(cb.equal(categoryType.get(CategoryType_.categoryTypeCode), categoryTypeCode));
//		if(!brandCodes.isEmpty()) {
//			conditions.add(brand.get(Brand_.brandCode).in(brandCodes));
//		}
//		if(!tagCodes.isEmpty()) {
//			Join<Product, ProductTag> tags = product.join(Product_.tags);
//			conditions.add(tags.get(ProductTag_.productTagCode).in(tagCodes));
//		}
//		if(!(parentCategoryDesc == null)) {
//			conditions.add(cb.equal(parentCategoryAttribute.get(CategoryAttribute_.categoryDesc), parentCategoryDesc));
//		}
//		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
//		
//		TypedQuery<CategoryProduct> query = em.createQuery(cq
//				.select(root)
//				.where(conditions.toArray(new Predicate[] {}))
//				.distinct(true)
//		);
//		
//		return query.getResultList().stream().map(c -> (Category) c).collect(Collectors.toList());
	return null;
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return this.findAll();
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Category> getChildren(Category category, String currency) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
