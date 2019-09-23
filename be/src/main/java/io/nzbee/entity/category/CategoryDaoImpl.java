
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
		Query query = em.createNativeQuery("WITH RECURSIVE  " +
				"descendants AS " +
				"( " +
				"  SELECT 	t.cat_id,  " +
				"			t.hir_id, " +
				"			t.cat_cd, " +
				"			t.cat_lvl, " +
				"			t.cat_prnt_id,  " +
				"			t.cat_typ_id, " +
				"			cast('/' || cast(t.cat_id as text) || '/' as text) node " +
				"  FROM mochi.category AS t " +
				"  WHERE cat_prnt_id iS NULL " +
				"  UNION ALL " +
				"  SELECT 	t.cat_id,  " +
				"			t.hir_id, " +
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
				"			descendants.hir_id des_hir_id, " +
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
				"    cc.des_cat_id 					AS cat_id, " +
				"    cc.des_hir_id 					AS hir_id, " +
				"    cc.des_cat_cd 					AS cat_cd, " +
				"    cc.des_cat_lvl 				AS cat_lvl, " +
				"    cc.des_cat_prnt_id 			AS prnt_id, " +
				"    cc.des_cat_type_id 			AS cat_type_id, " +
				"    cc.node, " +
				"    COUNT(DISTINCT prd.upc_cd) 	AS object_count, " +
				"    MAX(markdown_price.prc_val) 	AS max_markdown_price,  " +
				"    MAX(retail_price.prc_val) 		AS max_retail_price " +
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
				"		 AND curr.ccy_cd = 	:currency " +
				"		 AND prc_typ_cd = 	:retailPriceCode " +
				"		 AND prd_sts_cd = 	:activeProductCode " +
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
				"		 AND curr.ccy_cd = 	:currency " +
				"		 AND prc_typ_cd = 	:markdownPriceCode " +
				"		 AND prd_sts_cd = 	:activeProductCode " +
				"		 )  markdown_price		  " +
				"		 ON pc.prd_id = markdown_price.prd_id " +
				"	 " +
				"WHERE cc.des_cat_type_id = 1 " +
				"GROUP BY  " +
				"	 cc.des_cat_id, " +
				"    cc.des_hir_id, " +
				"	 cc.des_cat_cd, " +
				"	 cc.des_cat_lvl, " +
				"	 cc.des_cat_prnt_id, " +
				"	 cc.des_cat_type_id, " +
				"	 cc.node " +
				"UNION ALL " +
				"SELECT  " +
				"    cc.des_cat_id 					AS cat_id, " +
				"    cc.des_hir_id 					AS hir_id, " +
				"    cc.des_cat_cd 					AS cat_cd, " +
				"    cc.des_cat_lvl 				AS cat_lvl, " +
				"    cc.des_cat_prnt_id 			AS prnt_id, " +
				"    cc.des_cat_type_id 			AS cat_type_id, " +
				"    cc.node 						AS node, " +
				"    count(DISTINCT prd.bnd_cd) 	AS object_count, " +
				"    max(markdown_price.prc_val) 	AS max_markdown_price,  " +
				"    max(retail_price.prc_val) 		AS max_retail_price " +
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
				"			AND curr.ccy_cd = 	:currency " +
				"			AND prc_typ_cd = 	:retailPriceCode " +
				"			AND prd_sts_cd = 	:activeProductCode " +
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
				"			AND curr.ccy_cd = 	:currency " +
				"			AND prc_typ_cd = 	:markdownPriceCode " +
				"			AND prd_sts_cd = 	:activeProductCode " +
				"		 )  markdown_price		  " +
				"		 ON pc.bnd_id = markdown_price.bnd_id " +

				"WHERE cc.des_cat_type_id = 2 " +
				"GROUP BY  " +
				"	cc.des_cat_id, " +
				"   cc.des_hir_id, " +
				"	cc.des_cat_cd, " +
				"	cc.des_cat_lvl, " +
				"	cc.des_cat_prnt_id, " +
				"	cc.des_cat_type_id, " +
				"	cc.node " +
				"), summaries_ptb AS " +
				"( " +
				"SELECT 	 " +
				"	s1.cat_id, " +
				"	s1.hir_id, " +
				"	s1.cat_cd, " +
				"	s1.cat_lvl, " +
				"	s1.prnt_id, " +
				"	s1.cat_type_id, " +
				"	coalesce(s1.object_count, 0) + " +
				"	sum(coalesce(s2.object_count,0)) as object_count, " +
				"	greatest(0, s1.max_retail_price, max(s2.max_retail_price)) as max_retail_price, " +
				"	greatest(0, s1.max_markdown_price, max(s2.max_markdown_price)) as max_markdown_price " +
				"FROM summaries_pta s1 " +
				"LEFT JOIN summaries_pta s2 " +
				"ON s1.node <> s2.Node and left(s2.node, length(s1.node)) = s1.node " +
				"GROUP BY " +
				"	s1.cat_id, " +
				"	s1.hir_id, " +
				"	s1.cat_cd, " +
				"	s1.cat_lvl, " +
				"	s1.prnt_id, " +
				"	s1.cat_type_id, " +
				"	s1.object_count, " +
				"	s1.max_retail_price, " +
				"	s1.max_markdown_price " +
				") " +
				"SELECT s.cat_id 				AS cat_id, " +
				"       s.cat_cd 				AS cat_cd, " +
				"       s.cat_lvl 				AS cat_lvl, " +
				"       s.hir_id				AS hir_id, " +
				"		h.hir_cd				AS hir_cd, " + 
				"		h.hir_desc 				AS hir_desc, " +		
				"		a.cat_lcl_id 			AS cat_lcl_id, "	+	
				"		s.cat_type_id 			AS cat_typ_id, 	" +
				"       ct.cat_typ_cd			AS cat_typ_cd, " +
				"       ct.cat_typ_desc 		AS cat_typ_desc, " +
				"		a.cat_id 				AS cat_id, " +	
				"       a.cat_desc 				AS cat_desc, " +
				"       a.lcl_cd 				AS lcl_cd, " +
				"		s.prnt_id   			AS cat_prnt_id, " +
				"		pc.cat_cd   			AS cat_prnt_cd, " +
				"		pc.cat_lvl   			AS cat_prnt_lvl, " +
				"		pc.cat_typ_id 			AS cat_prnt_typ_id, " +
				"		pct.cat_typ_cd			AS cat_prnt_typ_cd, " +
				"		pct.cat_typ_desc		AS cat_prnt_typ_desc, " +
				"		pc.cat_prnt_id 			AS cat_prnt_prnt_id, " + 
				" 		pa.cat_lcl_id 			AS cat_prnt_lcl_id, " +
				"       pa.cat_desc 			AS cat_prnt_desc, " +
				"       pa.lcl_cd 				AS cat_prnt_lcl_cd, " +
				"		ph.hir_id 				AS cat_prnt_hir_id, " +
				"		ph.hir_cd 				AS cat_prnt_hir_cd, " +
				"		ph.hir_desc 			AS cat_prnt_hir_desc, " +
				"		a.cat_lcl_id			AS cat_lcl_id, " +
				"       a.cat_img_pth			AS cat_img_pth, " +
				"       s.object_count			AS object_count, " +
				"       s.max_retail_price		AS max_retail_price, " +
				"       s.max_markdown_price	AS max_markdown_price, " +
				"       ps.object_count			AS cat_prnt_object_count, " +
				"       ps.max_retail_price		AS cat_prnt_max_retail_price, " +
				"       ps.max_markdown_price 	AS cat_prnt_max_markdown_price " +

				"FROM summaries_ptb s " +

				"LEFT JOIN summaries_ptb ps " +
				"ON ps.cat_id = s.prnt_id " +
				
				"INNER JOIN mochi.category_attr_lcl a " +
				"ON s.cat_id = a.cat_id " +
				
				"INNER JOIN mochi.hierarchy h " +
				"ON s.hir_id = h.hir_id " +
				
				"LEFT JOIN mochi.category parent " +
				"ON s.prnt_id = parent.cat_id  " +
				
				"LEFT JOIN mochi.hierarchy ph " +
				"ON parent.hir_id = ph.hir_id " +
				
				"INNER JOIN mochi.category_type ct " +
				"ON ct.cat_typ_id = s.cat_type_id  " +
				
				"LEFT JOIN mochi.category pc " +
				"ON pc.cat_id = s.prnt_id  " +
				
				"LEFT JOIN mochi.category_type pct " +
				"ON pc.cat_typ_id = pct.cat_typ_id  " +
				
				"LEFT OUTER JOIN mochi.category_attr_lcl pa " +
				"ON pc.cat_id = pa.cat_id " +
				
				"WHERE a.lcl_cd = :locale " +
				"AND pa.lcl_cd = :locale " +
				"AND case " +
				"	 when :parentCategoryCode = '-1' " +
				"  then '0' " +
				"  else parent.cat_cd" +
				"	 end = " +
				"	 case" +
				"  when :parentCategoryCode = '-1' " +
				"  then '0' " +
				"  else :parentCategoryCode" +
				"  end", "CategoryMapping")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
				 .setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
				 .setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE);
		
		List<Object[]> results = query.getResultList();
		List<Category> lc = results.stream().map(c -> (Category) c[0]).collect(Collectors.toList());
				
		lc.stream().forEach(c -> {
			System.out.println(c.getCategoryCode());
			System.out.println(c.getCategoryAttribute().getCategoryDesc());
			System.out.println(c.getParent().getCategoryAttribute().getCategoryDesc());
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
