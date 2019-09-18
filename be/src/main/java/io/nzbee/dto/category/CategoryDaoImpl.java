
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
		
		return em.createNativeQuery(

				"WITH RECURSIVE  " +
				"descendants AS " +
				"( " +
				"  SELECT 	t.cat_id,  " +
				"			t.cat_cd, " +
				"			t.cat_lvl, " +
				"			t.cat_prnt_id,  " +
				"			t.cat_typ_id, " +
				"			cast('/' || cast(t.cat_id as text) || '/' as text) node " +
				"  FROM mochi.category AS t " +
				"  WHERE cat_prnt_id iS NULL " +
				"  UNION ALL " +
				"  SELECT 	t.cat_id,  " +
				"			t.cat_cd,  " +
				"			t.cat_lvl, " +
				"			t.cat_prnt_id,  " +
				"			t.cat_typ_id, " +
				"			cast(d.node || CAST(t.cat_id as text) || '/' as text) node " +
				"  FROM mochi.category AS t  " +
				"  JOIN descendants AS d  " +
				"  ON t.cat_prnt_id = d.cat_id " +
				"), " +
				"categories AS  " +
				"( " +
				"  SELECT 	descendants.cat_id des_cat_id, " +
				"			descendants.cat_cd des_cat_cd, " +
				"			descendants.cat_lvl des_cat_lvl, " +
				"			descendants.cat_prnt_id des_cat_prnt_id, " +
				"			descendants.cat_typ_id des_cat_type_id, " +
				"			descendants.node " +
				"FROM descendants " +
				"), summaries_pta " +
				"AS " +
				"( " +
				"select " +
				"    cc.des_cat_id 				AS cat_id, " +
				"    cc.des_cat_cd 				AS cat_cd, " +
				"    cc.des_cat_lvl 				AS cat_lvl, " +
				"    cc.des_cat_prnt_id 			AS prnt_id, " +
				"    cc.des_cat_type_id 			AS cat_type_id, " +
				"    cc.node, " +
				"    COUNT(DISTINCT prd.upc_cd) AS product_count, " +
				"    MAX(markdown_price.prc_val) AS max_markdown_price,  " +
				"    MAX(retail_price.prc_val) AS max_retail_price " +
				"FROM categories cc " +
				"LEFT JOIN mochi.product_category pc ON cc.des_cat_id = pc.cat_id " +
				"LEFT JOIN 	( " +
				"		 SELECT prd.prd_id,  " +
				"			upc_cd " +
				"		 FROM mochi.product prd " +
				"		  " +
				"		 INNER JOIN mochi.product_status ps " +
				"		 ON prd.prd_sts_id = ps.prd_sts_id " +
				"		  " +
				"		 WHERE prd_sts_cd = :activeProductCode " +
				"		 ) prd  " +
				"		 ON pc.prd_id = prd.prd_id " +
				"		  " +
				"LEFT JOIN 	( " +
				"		 SELECT prd.prd_id,  " +
				"			prc_typ_cd, " +
				"			prc_val  " +
				"		 FROM mochi.product prd " +
				
				"		 INNER JOIN mochi.price prc  " +
				"		 ON prd.prd_id = prc.prd_id " +
				"		  " +
				"		 INNER JOIN mochi.currency curr  " +
				"		 ON prc.ccy_id = curr.ccy_id  " +
				"		  " +
				"		 INNER JOIN mochi.price_type pt  " +
				"		 ON prc.prc_typ_id = pt.prc_typ_id " +

				"		 INNER JOIN mochi.product_status ps " +
				"		 ON prd.prd_sts_id = ps.prd_sts_id " +
				"		  " +
				"		 WHERE now() >= prc.prc_st_dt AND now() <= prc.prc_en_dt " +
				"		 AND curr.ccy_cd = :currency " +
				"		 AND prc_typ_cd::text = :retailPriceCode " +
				"		 AND prd_sts_cd = :activeProductCode " +
				"		 ) retail_price " +
				"		 ON pc.prd_id = retail_price.prd_id " +
				"		  " +
				"LEFT JOIN 	(SELECT prd.prd_id,  " +
				"			prc_typ_cd, " +
				"			prc_val  " +
				"		 FROM mochi.product prd " +

				"		 INNER JOIN mochi.price prc  " +
				"		 ON prd.prd_id = prc.prd_id " +
				"		  " +
				"		 INNER JOIN mochi.currency curr  " +
				"		 ON prc.ccy_id = curr.ccy_id  " +
				"		  " +
				"		 INNER JOIN mochi.price_type pt  " +
				"		 ON prc.prc_typ_id = pt.prc_typ_id " +

				"		 INNER JOIN mochi.product_status ps " +
				"		 ON prd.prd_sts_id = ps.prd_sts_id " +
				"		  " +
				"		 WHERE now() >= prc.prc_st_dt  " +
				"		 AND now() <= prc.prc_en_dt " +
				"		 AND curr.ccy_cd = :currency " +
				"		 AND prc_typ_cd::text = :markdownPriceCode " +
				"		 AND prd_sts_cd = :activeProductCode " +
				"		 )  markdown_price		  " +
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
				"UNION ALL " +
				"SELECT  " +
				"    cc.des_cat_id 		AS cat_id, " +
				"    cc.des_cat_cd 		AS cat_cd, " +
				"    cc.des_cat_lvl 		AS cat_lvl, " +
				"    cc.des_cat_prnt_id 	AS prnt_id, " +
				"    cc.des_cat_type_id 	AS cat_type_id, " +
				"    cc.node, " +
				"    count(DISTINCT prd.bnd_cd) 	AS product_count, " +
				"    max(markdown_price.prc_val) AS max_markdown_price,  " +
				"    max(retail_price.prc_val) 	AS max_retail_price " +
				"FROM categories cc " +
				"LEFT JOIN mochi.brand_category pc  " +
				"ON cc.des_cat_id = pc.cat_id " +

				"LEFT JOIN 	( " +
				"			SELECT  prd.bnd_id,  " +
				"					bnd_cd " +
				"			FROM mochi.brand bnd " +

				"			INNER JOIN mochi.product prd " +
				"			ON bnd.bnd_id =  prd.bnd_id " +
		
				"			INNER JOIN mochi.product_status ps " +
				"			ON prd.prd_sts_id = ps.prd_sts_id " +
		
				"			WHERE prd_sts_cd = :activeProductCode " +
				"			) prd  " +
				"			ON pc.bnd_id = prd.bnd_id " +
			
				"LEFT JOIN 	( " +
				"			SELECT 	prd.bnd_id, " +
				"					prc_typ_cd, " +
				"					prc_val  " +
				"			FROM mochi.brand bnd " +

				"			INNER JOIN mochi.product prd " +
				"			ON bnd.bnd_id =  prd.bnd_id " +

				"			INNER JOIN mochi.price prc  " +
				"			ON prd.prd_id = prc.prd_id " +
		
				"			INNER JOIN mochi.currency curr  " +
				"			ON prc.ccy_id = curr.ccy_id  " +
		
				"			INNER JOIN mochi.price_type pt  " +
				"			ON prc.prc_typ_id = pt.prc_typ_id " +

				"			INNER JOIN mochi.product_status ps " +
				"			ON prd.prd_sts_id = ps.prd_sts_id " +
	
				"			WHERE now() >= prc.prc_st_dt AND now() <= prc.prc_en_dt " +
				"			AND curr.ccy_cd = :currency " +
				"			AND prc_typ_cd::text = :retailPriceCode " +
				"			AND prd_sts_cd = :activeProductCode " +
				"			) retail_price " +
				"			ON pc.bnd_id = retail_price.bnd_id " +

				"LEFT JOIN 	( " +
				"			SELECT prd.bnd_id,  " +
				"					prc_typ_cd, " +
				"					prc_val  " +
				"			FROM mochi.brand bnd " +

				"			INNER JOIN mochi.product prd " +
				"			ON bnd.bnd_id =  prd.bnd_id " +
	
				"			INNER JOIN mochi.price prc  " +
				"			ON prd.prd_id = prc.prd_id " +

				"			INNER JOIN mochi.currency curr  " +
				"			ON prc.ccy_id = curr.ccy_id  " +
	
				"			INNER JOIN mochi.price_type pt  " +
				"			ON prc.prc_typ_id = pt.prc_typ_id " +

				"			INNER JOIN mochi.product_status ps " +
				"			ON prd.prd_sts_id = ps.prd_sts_id " +

				"			WHERE now() >= prc.prc_st_dt AND now() <= prc.prc_en_dt " +
				"			AND curr.ccy_cd = :currency " +
				"			AND prc_typ_cd::text = :markdownPriceCode " +
				"			AND prd_sts_cd = :activeProductCode " +
				"		 )  markdown_price		  " +
				"		 ON pc.bnd_id = markdown_price.bnd_id " +

				"WHERE cc.des_cat_type_id = 2 " +
				"GROUP BY  " +
				"	cc.des_cat_id, " +
				"	cc.des_cat_cd, " +
				"	cc.des_cat_lvl, " +
				"	cc.des_cat_prnt_id, " +
				"	cc.des_cat_type_id, " +
				"	cc.node " +
				"), summaries_ptb AS " +
				"( " +
				"SELECT 	 " +
				"	s1.cat_id, " +
				"	s1.cat_cd, " +
				"	s1.cat_lvl, " +
				"	s1.prnt_id, " +
				"	s1.cat_type_id, " +
				"	coalesce(s1.product_count, 0) + " +
				"	sum(coalesce(s2.product_count,0)) as object_count, " +
				"	greatest(0, s1.max_retail_price, max(s2.max_retail_price)) as max_retail_price, " +
				"	greatest(0, s1.max_markdown_price, max(s2.max_markdown_price)) as max_markdown_price " +
				"FROM summaries_pta s1 " +
				"LEFT JOIN summaries_pta s2 " +
				"ON s1.node <> s2.Node and left(s2.node, length(s1.node)) = s1.node " +
				"GROUP BY " +
				"	s1.cat_id, " +
				"	s1.cat_cd, " +
				"	s1.cat_lvl, " +
				"	s1.prnt_id, " +
				"	s1.cat_type_id, " +
				"	s1.product_count, " +
				"	s1.max_retail_price, " +
				"	s1.max_markdown_price " +
				") " +
				"SELECT s.cat_id, " +
				"       s.cat_cd, " +
				"       s.cat_lvl, " +
				"       s.prnt_id, " +
				"       parent.cat_cd as prnt_cd, " +
				"       a.cat_desc, " +
				"       a.cat_img_pth, " +
				"       ct.cat_typ_cd, " +
				"       a.lcl_cd, " +
				"       s.object_count, " +
				"       s.max_retail_price, " +
				"       s.max_markdown_price " +

				"FROM summaries_ptb s " +

				"INNER JOIN mochi.category_attr_lcl a " +
				"ON s.cat_id = a.cat_id " +

				"LEFT JOIN mochi.category parent " +
				"ON s.prnt_id = parent.cat_id  " +

				"INNER JOIN mochi.category_type ct " +
				"ON s.cat_type_id = ct.cat_typ_id " +
				
				"WHERE a.lcl_cd = :locale", io.nzbee.dto.category.CategoryWithNameAndStats.class
				
		).setParameter("locale", locale)
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
				categoryType.get(CategoryType_.code),
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
				categoryType.get(CategoryType_.code),
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

		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
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
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
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
