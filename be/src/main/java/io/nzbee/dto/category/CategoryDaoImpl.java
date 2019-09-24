
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
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.CategoryProduct_;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.category.type.CategoryType_;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.entity.product.tag.ProductTag_;
import io.nzbee.variables.ProductVars;

@Component(value="categoryDtoDao")
public class CategoryDaoImpl implements ICategoryWithNameAndStatsDao, ICategoryDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Optional<io.nzbee.dto.category.CategoryWithNameAndStats> findById(long id, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.CategoryWithNameAndStats> cq = cb.createQuery(io.nzbee.dto.category.CategoryWithNameAndStats.class);
		
		Root<Category> root = cq.from(Category.class);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(root.get(Category_.categoryId), id));
	
		TypedQuery<io.nzbee.dto.category.CategoryWithNameAndStats> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<io.nzbee.dto.category.CategoryWithNameAndStats> findAll(String locale, String currency) {

		//we use common table expressions to 
		//hierarchically traverse the category hierarchy 
		//and create aggregate summaries
		
		return em.createNamedQuery("getAllCategories")
		 .setParameter("locale", locale)
		 .setParameter("currency", currency)
		 .setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
		 .setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
		 .setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE)
		 .getResultList();
	
	}
	
	@Override
	public List<io.nzbee.dto.category.CategoryWithName> findAll(String locale) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.CategoryWithName> cq = cb.createQuery(io.nzbee.dto.category.CategoryWithName.class);
		
		Root<Category> root = cq.from(Category.class);
		
		//Root<CategoryProduct> categoryProduct = cb.treat(root, CategoryProduct.class);
		//Root<CategoryBrand> categoryBrand = cb.treat(root, CategoryBrand.class);
		
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, Category> categoryParent = root.join(Category_.parent);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		cq.select(cb.construct(
				io.nzbee.dto.category.CategoryWithName.class,
				root.get(Category_.categoryId),
				root.get(Category_.categoryCode),
				categoryAttribute.get(CategoryAttribute_.categoryDesc),
				root.get(Category_.categoryLevel),
				categoryType.get(CategoryType_.categoryTypeCode),
				categoryAttribute.get(CategoryAttribute_.lclCd),
				categoryParent.get(Category_.categoryCode)
				)
		);
		
		TypedQuery<io.nzbee.dto.category.CategoryWithName> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return query.getResultList();
		
	}


	
	public Optional<io.nzbee.dto.category.CategoryWithNameAndStats> findByCategoryDesc(String categoryDesc, String locale) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.CategoryWithNameAndStats> cq = cb.createQuery(io.nzbee.dto.category.CategoryWithNameAndStats.class);
		
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
				io.nzbee.dto.category.CategoryWithNameAndStats.class,
				root.get(Category_.categoryId),
				root.get(Category_.categoryCode),
				categoryAttribute.get(CategoryAttribute_.categoryDesc),
				root.get(Category_.categoryLevel),
				categoryType.get(CategoryType_.categoryTypeCode),
				categoryAttribute.get(CategoryAttribute_.lclCd),
				root.get(Category_.parent).get(Category_.categoryCode)
				)
		);
		
		TypedQuery<io.nzbee.dto.category.CategoryWithNameAndStats> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
		
	public Optional<io.nzbee.dto.category.CategoryWithNameAndStats> findByCategoryCode(String categoryCode, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<io.nzbee.dto.category.CategoryWithNameAndStats> cq = cb.createQuery(io.nzbee.dto.category.CategoryWithNameAndStats.class);
		
		Root<Category> root = cq.from(Category.class);
		//Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		
		//Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		//conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(categoryCode == null)) {
			conditions.add(cb.equal(root.get(Category_.categoryCode), categoryCode));
		}
		
		TypedQuery<io.nzbee.dto.category.CategoryWithNameAndStats> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
	
	public List<io.nzbee.dto.category.CategoryWithNameAndStats> findByParent(String categoryTypeCode, String parentCategoryCode, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.CategoryWithNameAndStats> cq = cb.createQuery(io.nzbee.dto.category.CategoryWithNameAndStats.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, Category> parent = root.join(Category_.parent);
		
		List<Predicate> conditions = new ArrayList<Predicate>();

		conditions.add(cb.equal(categoryType.get(CategoryType_.categoryTypeCode), categoryTypeCode));
	
		if(!(parentCategoryCode == null)) {
			conditions.add(cb.equal(parent.get(Category_.categoryCode), parentCategoryCode));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<io.nzbee.dto.category.CategoryWithNameAndStats> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	
	
	public List<io.nzbee.dto.category.CategoryWithNameAndStats> findByLevel(String categoryTypeCode, Long level, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.CategoryWithNameAndStats> cq = cb.createQuery(io.nzbee.dto.category.CategoryWithNameAndStats.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryType.get(CategoryType_.categoryTypeCode), categoryTypeCode));
		if(!(level == null)) {
			conditions.add(cb.equal(root.get(Category_.categoryLevel), level));
		}
	
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<io.nzbee.dto.category.CategoryWithNameAndStats> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	@Override
	public List<io.nzbee.dto.category.CategoryWithNameAndStats> findChildrenByCriteria(
			String parentCategoryDesc, 
			List<String> brandCodes, 
			List<String> tagCodes, 
			String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.category.CategoryWithNameAndStats> cq = cb.createQuery(io.nzbee.dto.category.CategoryWithNameAndStats.class);
		
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
		//conditions.add(cb.equal(categoryType.get(CategoryType_.categoryTypeCode), categoryTypeCode));
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
		
		TypedQuery<io.nzbee.dto.category.CategoryWithNameAndStats> query = em.createQuery(cq
				//.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList().stream().map(c -> (io.nzbee.dto.category.CategoryWithNameAndStats) c).collect(Collectors.toList());
	}

	@Override
	public List<io.nzbee.dto.category.CategoryWithNameAndStats> getAll() {
		// TODO Auto-generated method stub
		return this.findAll();
	}

	@Override
	public void save(io.nzbee.dto.category.CategoryWithNameAndStats t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(io.nzbee.dto.category.CategoryWithNameAndStats t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(io.nzbee.dto.category.CategoryWithNameAndStats t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<io.nzbee.dto.category.CategoryWithNameAndStats> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<io.nzbee.dto.category.CategoryWithNameAndStats> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
