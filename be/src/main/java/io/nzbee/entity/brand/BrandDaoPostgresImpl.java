package io.nzbee.entity.brand;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.Globals;
import io.nzbee.entity.brand.Brand_;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.brand.attribute.BrandAttribute_;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.brand.CategoryBrand_;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.product.status.ProductStatus_;

@Component
public class BrandDaoPostgresImpl  implements IBrandDao { 
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Autowired
	private Globals globalVars;
	
	@Override
	public Optional<Brand> findById(String locale, String currency, long id) {
		LOGGER.debug("call BrandDaoImpl.findById parameters : {}, {}, {}", locale, currency, id);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);
		
		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.brandAttributeId).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		cq.where(cb.and(
				cb.equal(root.get(Brand_.brandId), id),
				cb.equal(attribute.get(BrandAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Brand brand = this.objectToEntity(tuple, locale, currency);
			return Optional.ofNullable(brand);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<Brand> findByCode(String locale, String currency, String code) {
		LOGGER.debug("call BrandDaoImpl.findByCode parameters : {}, {}, {}", locale, currency, code);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.brandAttributeId).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		cq.where(cb.and(
				cb.equal(root.get(Brand_.brandCode), code),
				cb.equal(attribute.get(BrandAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Brand brand = this.objectToEntity(tuple, locale, currency);
			return Optional.ofNullable(brand);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	
	@Override
	public Optional<Brand> findByDesc(String locale, String currency, String desc) {
		LOGGER.debug("call BrandDaoImpl.findByDesc parameters : {}, {}, {}", locale, currency, desc);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);
		
		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.brandAttributeId).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		cq.where(cb.and(
				cb.equal(attribute.get(BrandAttribute_.brandDesc), desc),
				cb.equal(attribute.get(BrandAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Brand brand = this.objectToEntity(tuple, locale, currency);
			return Optional.ofNullable(brand);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public List<Brand> findAll(String locale, String currency, Set<String> brandCodes) {
		LOGGER.debug("call BrandDaoImpl.findAll parameters : {}, {}, {}", locale, currency, StringUtil.join(brandCodes, ','));
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), globalVars.getActiveSKUCode()));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));
		if(!brandCodes.isEmpty()) {
			conditions.add(root.in(Brand_.brandCode).in(brandCodes));
		}

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.brandAttributeId).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> this.objectToEntity(t, locale, currency)).collect(Collectors.toList());
	}
	
	@Override
	public List<Brand> findAllByCategory(String locale, String currency, String categoryCode) {
		LOGGER.debug("call BrandDaoImpl.findAllByCategory parameters : {}, {}, {}", locale, currency, categoryCode);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Brand, CategoryBrand> category = root.join(Brand_.categories);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), globalVars.getActiveSKUCode()));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(category.get(CategoryBrand_.categoryCode), categoryCode));

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.brandAttributeId).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> this.objectToEntity(t, locale, currency)).collect(Collectors.toList());
	}
	

	@Override
	public Optional<Brand> findByProductCode(String locale, String currency, String productCode) {
		LOGGER.debug("call BrandDaoImpl.findByProductCode parameters : {}, {}, {}", locale, currency, productCode);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);
		Join<Brand, Product> product = root.join(Brand_.products);

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.brandAttributeId).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		cq.where(cb.and(
				cb.equal(product.get(Product_.productUPC), productCode),
				cb.equal(attribute.get(BrandAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Brand brand = this.objectToEntity(tuple, locale, currency);
			return Optional.ofNullable(brand);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public List<Brand> findAll(String locale, String currency) {
		LOGGER.debug("call BrandDaoImpl.findAll parameters : {}, {}, {}", locale, currency);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);
		
		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.brandAttributeId).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		cq.where(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> this.objectToEntity(t, locale, currency)).collect(Collectors.toList());
		
	}
	
	
	@Override
	public List<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> tagCodes, Double maxPrice) {
		LOGGER.debug("call BrandDaoImpl.findAll with parameters : {}, {}, {}, {}, {}, {}", locale, currency, categoryCode, StringUtil.join(categoryCodes, ','), StringUtil.join(tagCodes, ','), maxPrice);
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(
															 !categoryCodes.isEmpty(),
															 !tagCodes.isEmpty(),
															 !(maxPrice == null)), "BrandMapping")
				 .setParameter("locale", locale)
				 .setParameter("categoryCode", categoryCode);
		
		if(!categoryCodes.isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes);
		}
		
		if(!tagCodes.isEmpty()) {
			query.setParameter("tagCodes", tagCodes);
		}
		
		if(!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
			query.setParameter("currency", currency);
			query.setParameter("markdownPriceCode", globalVars.getMarkdownPriceCode());
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		return results.stream().map(b -> this.objectToEntity(b, locale, currency)).collect(Collectors.toList());
		
	}
	
	@Override
	public Brand objectToEntity(Tuple t, String locale, String currency) {
		Brand brandEntity = new Brand();
		BrandAttribute brandAttribute = new BrandAttribute();
		
		brandAttribute.setId(Long.parseLong(t.get("brandAttributeId").toString()));
		brandAttribute.setBrand(brandEntity);
		brandAttribute.setBrandDesc(t.get("brandDesc").toString());
		brandAttribute.setLclCd(locale);
		
		brandEntity.setBrandAttribute(brandAttribute);
		brandEntity.setId(Long.parseLong(t.get("brandId").toString()));
		brandEntity.setBrandCode(t.get("brandCode").toString());
		brandEntity.setLocale(locale);
		brandEntity.setCurrency(currency);
		
		return brandEntity;
	}
	
	@Override
	public void save(Brand t) {
		em.persist(t);
		em.flush();
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
	public Brand objectToEntity(Object[] o, String locale, String currency) {
		Brand brand = (Brand) o[0];
		brand.setBrandAttribute(((BrandAttribute) o[1]));
		
		brand.setObjectCount(((BigInteger)o[2]).intValue());
		
		brand.setLocale(locale);
		brand.setCurrency(currency);
		
		return brand;
	}
	
	private String constructSQL(
			boolean hasCategories,
			boolean hasTags,
			boolean hasPrice
		) {
	String sql = "WITH recursive descendants AS " + 
			"( " + 
			"          SELECT    t.cat_id, " + 
			"                    t.cat_cd, " + 
			"                    t.cat_lvl, " + 
			"                    t.cat_prnt_id, " + 
			"                    t.cat_typ_id, " + 
			"                    cast('/' " + 
			"                              || cast(t.cat_id AS text) " + 
			"                              || '/' AS text) node " + 
			"          FROM      mochi.category            AS t " + 
			"          LEFT JOIN mochi.category_attr_lcl a " + 
			"          ON        t.cat_id = a.cat_id " + 
			"          AND       a.lcl_cd = :locale " + 
			"          WHERE     0=0 " + 
			"          AND t.cat_cd = :categoryCode " + 
			"          UNION ALL " + 
			"          SELECT t.cat_id, " + 
			"                 t.cat_cd, " + 
			"                 t.cat_lvl, " + 
			"                 t.cat_prnt_id, " + 
			"                 t.cat_typ_id, " + 
			"                 cast(d.node " + 
			"                        || cast(t.cat_id AS text) " + 
			"                        || '/' AS text) node " + 
			"          FROM   mochi.category         AS t " + 
			"          JOIN   descendants            AS d " + 
			"          ON     t.cat_prnt_id = d.cat_id )" + 
			", summary AS " + 
			"( " + 
			"          SELECT    cc.cat_id," + 
			"					cc.cat_cd," + 
			"					cc.node" + 
			"          FROM      descendants cc 	" + 
			"          GROUP BY  cc.cat_id," + 
			"					cc.cat_cd," + 
			"					cc.node" + 
			" ), categories AS ( " + 
			"" + 
			"          SELECT    s1.node," + 
			"		  			s1.cat_id," + 
			"					s1.cat_cd" + 
			"          FROM      summary s1 " + 
			"          LEFT JOIN summary s2 " + 
			"          ON        s1.node <> s2.node " + 
			"          AND       LEFT(s2.node, length(s1.node)) = s1.node " + 
			"          GROUP BY  s1.node," + 
			"					s1.cat_id," + 
			"					s1.cat_cd" + 
			")" + 
			"select b.bnd_id, " + 
			"	   b.bnd_cd," + 
			"	   lcl.bnd_lcl_id," + 
			"	   lcl.bnd_desc," + 
			"	   lcl.lcl_cd, " +		
			"	   count(distinct p.upc_cd) as object_count " + 
			"from categories c " + 
			"	inner join mochi.product_category pc" + 
			"		on c.cat_id = pc.cat_id" + 
			"	" + 
			"	inner join mochi.product p" + 
			"		on pc.prd_id = p.prd_id" + 
			"	" + 
			"	inner join mochi.product_status ps" + 
			"		on p.prd_sts_id = ps.prd_sts_id" + 
			"		and ps.prd_sts_cd = 'ACT01'" + 
			"						 " + 
			"	inner join mochi.brand b" + 
			"		on p.bnd_id = b.bnd_id" + 
			"						 " + 
			"	left join mochi.brand_attr_lcl lcl" + 
			"		on b.bnd_id = lcl.bnd_id" + 
			"		and lcl.lcl_cd = :locale " + 
			"						 " + 
			((hasPrice) ?
			"inner join  ( " +
					"	SELECT prd_id, " +  
					"		   prc_val " + 
					"		FROM mochi.price mprc " + 
					"		INNER JOIN mochi.currency mcurr " + 
				    "		ON         mprc.ccy_id = mcurr.ccy_id  " + 
					"		AND        mcurr.ccy_cd = :currency  " +
					"		INNER JOIN mochi.price_type mpt " +
					"		ON         mprc.prc_typ_id = mpt.prc_typ_id " + 
					"		AND        mpt.prc_typ_cd = :markdownPriceCode " + 
					"		) mprc  " +
			"		ON p.prd_id = mprc.prd_id  " +
			"		AND prc_val <= :maxPrice " 
			: "") +
			
			((hasTags) ?
				"	left join mochi.product_tag pt" + 
				"		on p.prd_id = pt.prd_id" + 
				"" + 
				"	left join mochi.tag t " + 
				"		on pt.tag_id = t.tag_id" 
				: "") + 
			"						 " + 
			"where 0=0 " + 
			((hasCategories) ? 	" 	AND c.cat_cd in 	:categoryCodes " : "") +
			((hasTags) ? 	" 		AND t.tag_cd in 	:tagCodes " : "") +
			"group by b.bnd_id, " + 
			"	   b.bnd_cd," + 
			"	   lcl.bnd_desc," + 
			"	   lcl.bnd_lcl_id, " +
			"		lcl.lcl_cd"	;
		
	return sql;
	}

}