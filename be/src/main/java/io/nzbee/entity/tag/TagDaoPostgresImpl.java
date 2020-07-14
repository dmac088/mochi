package io.nzbee.entity.tag;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.Brand_;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.brand.attribute.BrandAttribute_;
import io.nzbee.entity.category.Category_;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.attribute.ProductAttribute_;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.Currency_;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.price.ProductPriceType_;
import io.nzbee.entity.product.price.ProductPrice_;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.product.status.ProductStatus_;
import io.nzbee.entity.tag.Tag_;
import io.nzbee.entity.tag.attribute.TagAttribute;
import io.nzbee.entity.tag.attribute.TagAttribute_;

@Component 
public class TagDaoPostgresImpl implements ITagDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Autowired
	private Globals globalVars;
	
	@Override
	public Optional<Tag> findById(String locale, String currency, long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
		
		Root<Tag> root = cq.from(Tag.class);

		List<Predicate> conditions = new ArrayList<Predicate>();	
		conditions.add(cb.equal(root.get(Tag_.tagId), id));
		
		TypedQuery<Tag> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
	
	@Override
	public Optional<Tag> findByCode(String locale, String currency, String code) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
		
		Root<Tag> root = cq.from(Tag.class);

		List<Predicate> conditions = new ArrayList<Predicate>();	
		conditions.add(cb.equal(root.get(Tag_.tagCode), code));
		
		TypedQuery<Tag> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		try {
			Tag tag = query.getSingleResult();
			return Optional.ofNullable(tag);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	
	@Override
	public Optional<Tag> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
		Root<Tag> root = cq.from(Tag.class);
		Join<Tag, TagAttribute> attribute = root.join(Tag_.attributes);
		

		List<Predicate> conditions = new ArrayList<Predicate>();	
		conditions.add(cb.equal(attribute.get(TagAttribute_.lclCd), locale));
		conditions.add(cb.equal(attribute.get(TagAttribute_.tagDesc), desc));
		
		TypedQuery<Tag> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		try {
			Tag tag = query.getSingleResult();
			return Optional.ofNullable(tag);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	@Override
	public List<Tag> findAll(String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, List<String> categoryCodes, List<String> brandCodes) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
	
		CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
		
		Root<Tag> root = cq.from(Tag.class);
		//Join<ProductTagAttribute, ProductTag> tag = root.join(ProductTagAttribute_.tag);
		Join<Tag, Product> product = root.join(Tag_.products);
		Join<Product, ProductAttribute> productAttribute = product.join(Product_.attributes);
		Join<Product, CategoryProduct> category = product.join(Product_.categories);
		Join<Product, Brand> brand = product.join(Product_.brand);
		Join<Product, ProductStatus> status = product.join(Product_.productStatus);
		
		
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.attributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(io.nzbee.entity.category.product.CategoryProduct_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryCodes.isEmpty()) {
			conditions.add(category.get(Category_.categoryCode).in(categoryCodes));
		}
		if(!brandCodes.isEmpty()) {
			conditions.add(brand.get(Brand_.brandCode).in(brandCodes));
		}
		if(!(priceStart == null && priceEnd == null)) {
			Join<Product, ProductPrice> price = product.join(Product_.prices);
			Join<ProductPrice, ProductPriceType> type = price.join(ProductPrice_.type);
			Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
			conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.priceValue), priceStart));
			conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.priceValue), priceEnd));
			conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
			conditions.add(cb.equal(curr.get(Currency_.code), currency));
		}
		
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), globalVars.getActiveSKUCode()));

		TypedQuery<Tag> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
    }

	@Override
	public List<Tag> findAll(String locale, String currency, Set<String> codes) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
				
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
				
		Root<Tag> root = cq.from(Tag.class);
		Join<Tag, Product> tag = root.join(Tag_.products);
		Join<Product, ProductStatus> status = tag.join(Product_.productStatus);
		Join<Tag, TagAttribute> attribute = root.join(Tag_.attributes);
				
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), globalVars.getActiveSKUCode()));
		conditions.add(cb.equal(attribute.get(TagAttribute_.lclCd), locale));
		if(!codes.isEmpty()) {
			conditions.add(root.in(Tag_.tagCode).in(codes));
		}

		cq.multiselect(	root.get(Tag_.tagId).alias("tagId"),
						root.get(Tag_.tagCode).alias("tagCode"),
						attribute.get(TagAttribute_.tagAttributeId).alias("tagAttributeId"),
						attribute.get(TagAttribute_.tagDesc).alias("tagDesc")
		);
				
		TypedQuery<Tuple> query = em.createQuery(cq);
				
		List<Tuple> tuples = query.getResultList();
				
		return tuples.stream().map(t -> this.objectToEntity(t, locale, currency)).collect(Collectors.toList());
	}
	
	@Override
	public void save(Tag t) {
		em.persist(t);
		em.flush();
	}

	@Override
	public void update(Tag t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tag t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tag> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag objectToEntity(Object[] o, String locale, String currency) {
		Tag tag = (Tag) o[0];
	
		tag.setTagAttribute(((TagAttribute) o[1]));
		
		tag.setObjectCount(((BigInteger)o[2]).intValue());
		
		tag.setLocale(locale);
		tag.setCurrency(currency);
		
		return tag;
	}

	@Override
	public Tag objectToEntity(Tuple t, String locale, String currency) {
		Tag tagEntity = new Tag();
		TagAttribute tagAttribute = new TagAttribute();
				
		tagAttribute.setId(Long.parseLong(t.get("tagAttributeId").toString()));
		tagAttribute.setTag(tagEntity);
		tagAttribute.setTagDesc(t.get("tagDesc").toString());
		tagAttribute.setLclCd(locale);
				
		tagEntity.addTagAttribute(tagAttribute);
		tagEntity.setTagAttribute(tagAttribute);
		tagEntity.setTagId(Long.parseLong(t.get("tagId").toString()));
		tagEntity.setCode(t.get("tagCode").toString());
		
		tagEntity.setLocale(locale);
		tagEntity.setCurrency(currency);
		return tagEntity;
	}
	
	@Override
	public List<Tag> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Double maxPrice) {
		LOGGER.debug("call TagDaoPostgresImpl.findAll with parameters : {}, {}, {}, {}, {}, {}", locale, currency, categoryCode, StringUtil.join(categoryCodes, ','), StringUtil.join(brandCodes, ','), maxPrice);
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(
															 !categoryCodes.isEmpty(),
															 !brandCodes.isEmpty(),
															 !(maxPrice == null)),"TagMapping")
				 .setParameter("locale", locale)
				 .setParameter("categoryCode", categoryCode);
		
		if(!categoryCodes.isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes);
		}
		
		if(!brandCodes.isEmpty()) {
			query.setParameter("brandCodes", brandCodes);
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
	

	private String constructSQL(
			boolean hasCategories,
			boolean hasBrands,
			boolean hasPrice
		) {
	String sql = "WITH recursive descendants AS  " + 
			"			(  " + 
			"			          SELECT    t.cat_id,  " + 
			"			                    t.cat_cd,  " + 
			"			                    t.cat_lvl,  " + 
			"			                    t.cat_prnt_id,  " + 
			"			                    t.cat_typ_id,  " + 
			"			                    cast('/'  " + 
			"			                              || cast(t.cat_id AS text)  " + 
			"			                              || '/' AS text) node  " + 
			"			          FROM      mochi.category            AS t  " + 
			"			          LEFT JOIN mochi.category_attr_lcl a  " + 
			"			          ON        t.cat_id = a.cat_id  " + 
			"			          AND       a.lcl_cd =  :locale " + 
			"			          WHERE     0=0  " + 
			"			           AND t.cat_cd = :categoryCode " + 
			"			          UNION ALL  " + 
			"			          SELECT t.cat_id,  " + 
			"			                 t.cat_cd,  " + 
			"			                 t.cat_lvl,  " + 
			"			                 t.cat_prnt_id,  " + 
			"			                 t.cat_typ_id,  " + 
			"			                 cast(d.node  " + 
			"			                        || cast(t.cat_id AS text)  " + 
			"			                        || '/' AS text) node  " + 
			"			          FROM   mochi.category         AS t  " + 
			"			          JOIN   descendants            AS d  " + 
			"			          ON     t.cat_prnt_id = d.cat_id ) " + 
			"			, summary AS  " + 
			"			(  " + 
			"			          SELECT    cc.cat_id, " + 
			"								cc.cat_cd, " + 
			"								cc.node " + 
			"			          FROM      descendants cc 	 " + 
			"			          GROUP BY  cc.cat_id, " + 
			"								cc.cat_cd, " + 
			"								cc.node " + 
			"			 ), categories AS (  " + 
			"			 " + 
			"			          SELECT    s1.node, " + 
			"					  			s1.cat_id, " + 
			"								s1.cat_cd " + 
			"			          FROM      summary s1  " + 
			"			          LEFT JOIN summary s2  " + 
			"			          ON        s1.node <> s2.node  " + 
			"			          AND       LEFT(s2.node, length(s1.node)) = s1.node  " + 
			"			          GROUP BY  s1.node, " + 
			"								s1.cat_id, " + 
			"								s1.cat_cd " + 
			"			) " + 
			"			select t.tag_id,  " + 
			"				   t.tag_cd, " + 
			"				   lcl.tag_lcl_id, " + 
			"				   lcl.tag_desc, " + 
			"				   lcl.lcl_cd, 		" + 
			"				   count(distinct p.upc_cd) as object_count  " + 
			"			from categories c  " + 
			"				inner join mochi.product_category pc " + 
			"					on c.cat_id = pc.cat_id " + 
			"				 " + 
			"				inner join mochi.product p " + 
			"					on pc.prd_id = p.prd_id " + 
			"				 " + 
			"				inner join mochi.product_status ps " + 
			"					on p.prd_sts_id = ps.prd_sts_id " + 
			"					and ps.prd_sts_cd = 'ACT01' " + 
			"									  " + 
			"				inner join mochi.brand b " +
			" 					on p.bnd_id = b.bnd_id " +
						
			"				inner join mochi.product_tag pt " + 
			"					on p.prd_id = pt.prd_id " + 
			 
			"				inner join mochi.tag t " + 
			"					on pt.tag_id = t.tag_id " + 
			 
			"				inner join mochi.tag_attr_lcl lcl " + 
			"					on t.tag_id = lcl.tag_id " + 
			"					and lcl.lcl_cd = :locale  " + 
			 
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
			
			"			where 0=0  " +			
			((hasCategories) ? 	" 	AND c.cat_cd in 	:categoryCodes " : "") +
			((hasBrands) ? 	" 		AND b.bnd_cd in 	:brandCodes " : "") +
			"			group by t.tag_id,  " + 
			"				   t.tag_cd, " + 
			"				   lcl.tag_lcl_id, " + 
			"				   lcl.tag_desc, " + 
			"				   lcl.lcl_cd"	;
		
	return sql;
	}


}
