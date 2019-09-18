
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
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.brand.readonly.CategoryBrand_;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.readonly.CategoryProduct_;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.category.type.CategoryType_;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.entity.product.tag.ProductTag_;
import io.nzbee.variables.CategoryVars;

@Component(value="categoryDtoDao")
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

		//we use common table expressions to 
		//hierarchically traverse the category hierarchy 
		//and create aggregate summaries
		
		em.createQuery(
						"WITH RECURSIVE  " +
					    "descendants (cat_id, cat_cd, cat_lvl, cat_prnt_id, cat_typ_id) AS " +
					    "( " +
					    "  SELECT 	t.cat_id,  " +
						"	t.cat_cd,  " +
						"	t.cat_lvl, " +
						"	t.cat_prnt_id,  " +
						"	t.cat_typ_id, " +
						"	cast('/' || cast(t.cat_id as text) || '/' as text) node " +
					    "  FROM mochi.category AS t " +
					    "  WHERE t.cat_cd = 'PRM01' " +
					    "  UNION ALL " +
					    "  SELECT 	t.cat_id,  " +
						"	t.cat_cd,  " +
						"	t.cat_lvl, " +
						"	t.cat_prnt_id,  " +
						"	t.cat_typ_id, " +
						"	cast(d.node || CAST(t.cat_id as text) || '/' as text) node " +
					    "  FROM mochi.category AS t  " +
					    "  JOIN descendants AS d  " +
					    "  ON t.cat_prnt_id = d.cat_id " +
					    "), " +
					    "categories AS  " +
					    "( " +
					    "  SELECT 	descendants.cat_id des_cat_id, " +
						"	descendants.cat_cd des_cat_cd, " +
						"	descendants.cat_lvl des_cat_lvl, " +
						"	descendants.cat_prnt_id des_cat_prnt_id, " +
						"	descendants.cat_typ_id des_cat_type_id, " +
						"	descendants.node " +
						"FROM  descendants " +
					    "), summaries " +
					    "AS " +
					    "( " +
					    "select " +
						"    cc.des_cat_id as cat_id, " +
						"    cc.des_cat_cd as cat_cd, " +
						"    cc.des_cat_lvl as cat_lvl, " +
						"    cc.des_cat_prnt_id as prnt_id, " +
						"    cc.des_cat_type_id as cat_type_id, " +
						"    cc.node, " +
						"    count(DISTINCT prd.upc_cd) AS product_count, " +
						"    max(markdown_price.prc_val) AS max_markdown_price,  " +
						"    max(retail_price.prc_val) AS max_retail_price " +
					    "from categories cc " +
						"LEFT JOIN mochi.product_category pc ON cc.des_cat_id = pc.cat_id " +
						"LEFT JOIN mochi.product prd ON pc.prd_id = prd.prd_id " +
						"LEFT JOIN 	(SELECT prd_id,  " +
						"			prc_typ_cd, " +
						"			prc_val  " +
						"		 FROM mochi.price prc  " +
						"		 INNER JOIN mochi.currency curr  " +
						"		 ON prc.ccy_id = curr.ccy_id  " +
						"		 LEFT JOIN mochi.price_type pt  " +
						"		 ON prc.prc_typ_id = pt.prc_typ_id " +
						"		 WHERE now() >= prc.prc_st_dt AND now() <= prc.prc_en_dt " +
						"		 AND curr.ccy_cd = 'HKD' " +
						"		 AND prc_typ_cd::text = 'RET01') retail_price " +
						"		 ON pc.prd_id = retail_price.prd_id " +
						"		  " +
						"LEFT JOIN 	(SELECT prd_id,  " +
						"			prc_typ_cd, " +
						"			prc_val  " +
						"		 FROM mochi.price prc  " +
						"		 INNER JOIN mochi.currency curr  " +
						"		 ON prc.ccy_id = curr.ccy_id  " +
						"		 LEFT JOIN mochi.price_type pt  " +
						"		 ON prc.prc_typ_id = pt.prc_typ_id " +
						"		 WHERE now() >= prc.prc_st_dt AND now() <= prc.prc_en_dt " +
						"		 AND curr.ccy_cd = 'HKD' " +
						"		 AND prc_typ_cd::text = 'MKD01')  markdown_price		  " +
						"		 ON pc.prd_id = markdown_price.prd_id " +
						"	 " +
						"WHERE cc.des_cat_type_id = 1 " +
						"GROUP BY  " +
						"	 cc.des_cat_id, " +
						"	 cc.des_cat_cd, " +
						"	 cc.des_cat_lvl, " +
						"	 cc.des_cat_prnt_id, " +
						"	 cc.des_cat_type_id, " +
						"	 cc.node " +
						") " +
						"SELECT 	 " +
						"	s1.cat_id, " +
						"	s1.cat_cd, " +
						"	s1.cat_lvl, " +
						"	s1.prnt_id, " +
						"	s1.cat_type_id, " +
						"	coalesce(s1.product_count, 0) + " +
						"	sum(coalesce(s2.product_count,0)) as product_count, " +
						"	greatest(0, s1.max_retail_price, max(s2.max_retail_price)) as max_retail_price, " +
						"	greatest(0, s1.max_markdown_price, max(s2.max_markdown_price)) as max_markdown_price " +
						"FROM summaries s1 " +
						"LEFT JOIN summaries s2 " +
						"ON s1.node <> s2.Node and left(s2.node, length(s1.node)) = s1.node " +
						"group by  " +
						"	s1.cat_id, " +
						"	s1.cat_cd, " +
						"	s1.cat_lvl, " +
						"	s1.prnt_id, " +
						"	s1.cat_type_id, " +
						"	s1.product_count, " +
						"	s1.max_retail_price, " +
						"	s1.max_markdown_price "
		).
		
		
		/*
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
		*/
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
