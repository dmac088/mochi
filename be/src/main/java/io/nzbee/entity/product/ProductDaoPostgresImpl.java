package io.nzbee.entity.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.shipping.ShippingProductDTO;
import io.nzbee.entity.product.shipping.ShippingProductEntity;

@Component(value = "productEntityDao")
public class ProductDaoPostgresImpl implements IProductDao {
 
	private final Logger LOGGER = LoggerFactory.getLogger(ProductDaoPostgresImpl.class);   
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public List<ProductEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<ProductEntity> findByCode(String productUPC) {
		LOGGER.debug("call ProductDaoPostgresImpl.findByCode parameters : {}", productUPC);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<ProductEntity> cq = cb.createQuery(ProductEntity.class);
		
		Root<ProductEntity> root = cq.from(ProductEntity.class);

		List<Predicate> conditions = new ArrayList<Predicate>();

		conditions.add(
				cb.equal(root.get(ProductEntity_.productUPC), productUPC)
		);
		
		TypedQuery<ProductEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		try {
			ProductEntity product = query.getSingleResult();
			return Optional.ofNullable(product);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@SuppressWarnings({ "unchecked", "deprecation", "hiding" })
	@Override
	@Caching(
			put = {
					@CachePut(value = ProductServiceImpl.CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #cls.getSimpleName()")
			}
	)
	public <T> List<ProductDTO> findAllByType(String locale, String currency, String rootCategory, Class<T> cls) {
		LOGGER.debug("call ProductDaoPostgresImpl.findAllByType parameters : {}, {}, {}, {}", locale, currency, rootCategory, cls.getSimpleName());
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(false,
															 false,
															 false,
															 !(rootCategory == null),
															 false,
															 false,
															 false,
															 false,
															 true,
							   								 false,
							   								 false,
							   								 false,
															 false,
															 false,
															 ""))
				.setParameter("categoryCode", rootCategory)
				.setParameter("locale", locale)
				.setParameter("currency", currency)
				.setParameter("activeProductCode", Constants.activeSKUCode)
				.setParameter("retailPriceCode", Constants.retailPriceCode)
				.setParameter("markdownPriceCode", Constants.markdownPriceCode)
				.setParameter("typeDiscriminator", Long.parseLong(cls.getAnnotation(DiscriminatorValue.class).value()))
		
		.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new ProductDTOResultTransformer());
		
		return query.getResultList();
	}
	
	
	@SuppressWarnings({"deprecation" })
	@Override
	@Caching(
			put = {
					@CachePut(value = ProductServiceImpl.CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #productId.toString()")
			}
	)
	public Optional<ProductDTO> findById(String locale, String currency, Long productId) {
		LOGGER.debug("call ProductDaoPostgresImpl.findById parameters : {}, {}, {}", locale, currency, productId);
		
		Query query = em.createNativeQuery(this.constructSQL(false,
															 false,
															 true,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
							   								 false,
							   								 false,
							   								 false,
															 false,
															 false,
															 ""))
				
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("productId", productId)
		.setParameter("activeProductCode", Constants.activeSKUCode)
		.setParameter("retailPriceCode", Constants.retailPriceCode)
		.setParameter("markdownPriceCode", Constants.markdownPriceCode)
		
		.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new ProductDTOResultTransformer());
		
		try {
			return Optional.ofNullable((ProductDTO) query.getSingleResult());
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	
	
	@SuppressWarnings({"deprecation" })
	@Override
	public Optional<ShippingProductDTO> findShippingProductByDestinationAndTypeAndWeight(String locale, String currency, String destinationCode, String type, Double weightKg) {
		LOGGER.debug("call ProductDaoPostgresImpl.findByShippingProductByDestinationAndTypeAndWeight parameters : {}, {}, {}, {}, {}", locale, currency, destinationCode, type, weightKg);
		
		Query query = em.createNativeQuery(this.constructSQL(false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 true, //hasType
							   								 true, //hasShippingDestiantion
							   								 true, //hasShippingType
							   								 true, //hasWeight
															 false,
															 false,
															 ""))
				
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("activeProductCode", Constants.activeSKUCode)
		.setParameter("retailPriceCode", Constants.retailPriceCode)
		.setParameter("markdownPriceCode", Constants.markdownPriceCode)
		.setParameter("typeDiscriminator", Long.parseLong(ShippingProductEntity.class.getAnnotation(DiscriminatorValue.class).value()))
		.setParameter("shippingDestinationCode", destinationCode)
		.setParameter("shippingTypeCode", type)
		.setParameter("shippingWeight", weightKg)
		
		.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new ProductDTOResultTransformer());
		
		try {
			return Optional.ofNullable((ShippingProductDTO) query.getSingleResult());
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@SuppressWarnings({"deprecation" })
	@Override
	@Caching(
			put = {
					@CachePut(value = ProductServiceImpl.CACHE_NAME, key = "#locale + \", \" + #currency + \", \" + #productUPC")
			}
	)
	public Optional<ProductDTO> findByCode(String locale, String currency, String productUPC) {
		LOGGER.debug("call ProductDaoPostgresImpl.findByCode with parameters : {}, {}, {}", locale, currency, productUPC);
		
		final List<String> productCodes = new ArrayList<String>();
		productCodes.add(productUPC);
		
		Query query = em.createNativeQuery(this.constructSQL(!productCodes.isEmpty(),
															 false,
															 false,
															 false,
															 false, 
															 false,
															 false,
															 false,
															 false,
							   								 false,
							   								 false,
							   								 false,
															 false,
															 false,
															 ""))
				
				.setParameter("locale", locale)
				.setParameter("currency", currency)
				.setParameter("activeProductCode", Constants.activeSKUCode)
				.setParameter("retailPriceCode", Constants.retailPriceCode)
				.setParameter("markdownPriceCode", Constants.markdownPriceCode);
		
		if(!productCodes.isEmpty()) {
			query.setParameter("productCodes", productCodes);
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new ProductDTOResultTransformer());

		try {
			return Optional.ofNullable((ProductDTO) query.getSingleResult());
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@SuppressWarnings({"deprecation" })
	@Override
	@Caching(
			put = {
					@CachePut(value = ProductServiceImpl.CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #productDesc")
			}
	)
	public Optional<ProductDTO> findByDesc(String locale, String currency, String productDesc) {
		LOGGER.debug("call ProductDaoPostgresImpl.findByDesc with parameters : {}, {}, {}", locale, currency, productDesc);
		
		Query query = em.createNativeQuery(this.constructSQL(false,
															 true,
															 false,
															 false,
															 false, 
															 false,
															 false,
															 false,
															 false,
							   								 false,
							   								 false,
							   								 false,
															 false,
															 false,
															 ""))
				
				.setParameter("locale", locale)
				.setParameter("currency", currency)
				.setParameter("productDesc", productDesc)
				.setParameter("activeProductCode", Constants.activeSKUCode)
				.setParameter("retailPriceCode", Constants.retailPriceCode)
				.setParameter("markdownPriceCode", Constants.markdownPriceCode);
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new ProductDTOResultTransformer());

		try {
			return Optional.ofNullable((ProductDTO) query.getSingleResult());
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public List<ProductDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@SuppressWarnings({"deprecation","unchecked"})
	@Override
	@Caching(
			put = {
					@CachePut(value = ProductServiceImpl.CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #codes.getCacheKey()")
			}
	)
	public List<ProductDTO> findAll(	String locale, 
										String currency, 
										String rootCategory,
										StringCollectionWrapper codes) {
		
		LOGGER.debug("call ProductDaoPostgresImpl.findAll with parameters : {}, {}, {}", locale, currency, codes.getCacheKey());
		
		Query query = em.createNativeQuery(this.constructSQL(true,
															 false,
															 false,
															 !(rootCategory == null),
															 false, 
															 false,
															 false,
															 false,
															 false,
							   								 false,
							   								 false,
							   								 false,
															 false,
															 false,
															 ""))
				 .setParameter("categoryCode", rootCategory)
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("retailPriceCode", Constants.retailPriceCode)
				 .setParameter("markdownPriceCode", Constants.markdownPriceCode);
		
		if(!codes.getCodes().isEmpty()) {
			query.setParameter("productCodes", codes.getCodes());
		} else {
			Set<String> dummy = new HashSet<String>();
			dummy.add("-1");
			query.setParameter("productCodes", dummy);
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new ProductDTOResultTransformer());
		
		return query.getResultList();
	}

	@SuppressWarnings({"deprecation","unchecked"})
	@Override
	public Page<ProductDTO> findAll(	String locale, 
										String currency, 
										String rootCategory,
										Pageable pageable, 
										String orderby) {
		LOGGER.debug("call ProductDaoPostgresImpl.findAll with parameters : {}, {}, {}, {}", locale, currency, pageable, orderby);
		
		//first get the result count
		Query query = em.createNativeQuery(this.constructSQL(false,
															 false,
															 false,
															 !(rootCategory == null),
															 false, 
															 false, 
															 false,
															 false,
															 false,
							   								 false,
							   								 false,
							   								 false,
															 true,
															 false,
															 ""))
				 .setParameter("categoryCode", 		rootCategory)
				 .setParameter("locale", 			locale)
				 .setParameter("currency", 			currency)
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("retailPriceCode", 	Constants.retailPriceCode)
				 .setParameter("markdownPriceCode", Constants.markdownPriceCode);
		
		Object result = query.getSingleResult();
		Long total = ((Number) result).longValue();
		
		query = em.createNativeQuery(this.constructSQL(
													  false,
													  false,
													  false,
													  !(rootCategory == null),
				   									  false,
													  false, 
													  false,
													  false,
													  false,
					   								  false,
					   							      false,
					   								  false,
													  false,
													  true,
													  ""))
				 .setParameter("categoryCode", rootCategory)
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("retailPriceCode", Constants.retailPriceCode)
				 .setParameter("markdownPriceCode", Constants.markdownPriceCode)
				 
				 //these should contain default values for these parameters
				 //.setParameter("orderby", "1")
				 .setParameter("limit", pageable.getPageSize())
				 .setParameter("offset", pageable.getOffset());
		

		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new ProductDTOResultTransformer());
		
		List<ProductDTO> results = query.getResultList();
		
		return new PageImpl<ProductDTO>(results, pageable, total);
	}
	
	@SuppressWarnings({"deprecation","unchecked", "hiding"})
	@Override
	@Caching(
			put = {															   
					@CachePut(value = ProductServiceImpl.CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString()) + \", \" + #cls.getSimpleName() + \", \" + #page.toString() + \", \" + #size.toString() + \", \" + #sort.toString()")
			}
	)
	public <T> Page<ProductDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes, Double maxPrice, Class<T> cls, String page, String size, String sort) {
		
		LOGGER.debug("call ProductDaoPostgresImpl.findAll with parameters: {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}",
																							 locale, 
																							 currency, 
																							 categoryCode, 
																							 categoryCodes.getCodes(),
																							 brandCodes.getCodes(),
																							 tagCodes.getCodes(),
																							 maxPrice,
																							 cls.getSimpleName(),
																							 page,
																							 size,
																							 sort);

		Query query = em.createNativeQuery(this.constructSQL(false,
	 														 false,
															 false,
															 !(categoryCode == null),
															 categoryCodes.getCodes().size()>=1, 
															 brandCodes.getCodes().size()>=1,
  				 											 tagCodes.getCodes().size()>=1,
  				 											 !(maxPrice == null),
  				 											 true,
  						   									 false,
  						   									 false,
  						   									 false,
  				 											 true,
  				 											 false,
  				 											 ""))
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("activeProductCode", Constants.activeSKUCode)
		.setParameter("retailPriceCode", Constants.retailPriceCode)
		.setParameter("markdownPriceCode", Constants.markdownPriceCode)
		.setParameter("categoryCode", categoryCode)
		.setParameter("typeDiscriminator", Long.parseLong(cls.getAnnotation(DiscriminatorValue.class).value()));
		
		if(!categoryCodes.getCodes().isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes.getCodes());
		}
		
		if(!brandCodes.getCodes().isEmpty()) {
			query.setParameter("brandCodes", brandCodes.getCodes());
		}
		
		if(!tagCodes.getCodes().isEmpty()) {
			query.setParameter("tagCodes", tagCodes.getCodes());
		}
		
		if(!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
		}
		
		if(!(categoryCode == null)) {
			query.setParameter("categoryCode", categoryCode);
		}
		
		Object result = query.getSingleResult();
		long total = ((Number) result).longValue();
		
		query = em.createNativeQuery(this.constructSQL(	false,
														false,
														false,
														!(categoryCode == null),
														!categoryCodes.getCodes().isEmpty(), 
														!brandCodes.getCodes().isEmpty(),
					   									!tagCodes.getCodes().isEmpty(),
					   									!(maxPrice == null),
					   									true,
					   									false,
					   									false,
					   									false,
					   									false,
					   									true,
					   									sort))
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("activeProductCode", 	Constants.activeSKUCode)
		.setParameter("retailPriceCode", 	Constants.retailPriceCode)
		.setParameter("markdownPriceCode", 	Constants.markdownPriceCode)
		.setParameter("categoryCode", categoryCode)
		.setParameter("typeDiscriminator", Long.parseLong(cls.getAnnotation(DiscriminatorValue.class).value()));
		
		Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
		//these should contain default values for these parameters
		query
		//.setParameter("orderby", getOrderby(sort))
		.setParameter("limit", pageable.getPageSize())
		.setParameter("offset", pageable.getOffset());
		
		if(!categoryCodes.getCodes().isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes.getCodes());
		}
		
		if(!brandCodes.getCodes().isEmpty()) {
			query.setParameter("brandCodes", brandCodes.getCodes());
		}
		
		if(!tagCodes.getCodes().isEmpty()) {
			query.setParameter("tagCodes", tagCodes.getCodes());
		}
		
		if(!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new ProductDTOResultTransformer());
		
		List<ProductDTO> results = query.getResultList();
		
		return new PageImpl<ProductDTO>(results, PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), total);
	}
	
	@SuppressWarnings({"deprecation","unchecked"})
	@Override
	@Caching(
			put = {
					@CachePut(value = ProductServiceImpl.CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString()) + \", \" + #page.toString() + \", \" + #size.toString() + \", \" + #sort.toString()")
			}
	)
	public Page<ProductDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort) {
		LOGGER.debug("call ProductDaoPostgresImpl.findAll with parameters: {}, {}, {}, {}, {}, {}, {}, {}, {}, {}",
																							 locale, 
																							 currency, 
																							 categoryCode, 
																							 categoryCodes.getCodes(),
																							 brandCodes.getCodes(),
																							 tagCodes.getCodes(),
																							 maxPrice,
																							 page,
																							 size,
																							 sort);

		Query query = em.createNativeQuery(this.constructSQL(false,
	 														 false,
															 false,
															 !(categoryCode == null),
															 categoryCodes.getCodes().size()>=1, 
															 brandCodes.getCodes().size()>=1,
  				 											 tagCodes.getCodes().size()>=1,
  				 											 !(maxPrice == null),
  				 											 false,
  						   									 false,
  						   									 false,
  						   									 false,
  				 											 true,
  				 											 false,
  				 											 ""))
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("activeProductCode", Constants.activeSKUCode)
		.setParameter("retailPriceCode", Constants.retailPriceCode)
		.setParameter("markdownPriceCode", Constants.markdownPriceCode)
		.setParameter("categoryCode", categoryCode);
		
		if(!categoryCodes.getCodes().isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes.getCodes());
		}
		
		if(!brandCodes.getCodes().isEmpty()) {
			query.setParameter("brandCodes", brandCodes.getCodes());
		}
		
		if(!tagCodes.getCodes().isEmpty()) {
			query.setParameter("tagCodes", tagCodes.getCodes());
		}
		
		if(!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
		}
		
		if(!(categoryCode == null)) {
			query.setParameter("categoryCode", categoryCode);
		}
		
		Object result = query.getSingleResult();
		long total = ((Number) result).longValue();
		
		query = em.createNativeQuery(this.constructSQL(	false,
														false,
														false,
														!(categoryCode == null),
														!categoryCodes.getCodes().isEmpty(), 
														!brandCodes.getCodes().isEmpty(),
					   									!tagCodes.getCodes().isEmpty(),
					   									!(maxPrice == null),
					   									false,
					   									false,
					   									false,
					   									false,
					   									false,
					   									true,
					   									sort))
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("activeProductCode", 	Constants.activeSKUCode)
		.setParameter("retailPriceCode", 	Constants.retailPriceCode)
		.setParameter("markdownPriceCode", 	Constants.markdownPriceCode)
		.setParameter("categoryCode", categoryCode);
		
		Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
		//these should contain default values for these parameters
		query
		//.setParameter("orderby", getOrderby(sort))
		.setParameter("limit", pageable.getPageSize())
		.setParameter("offset", pageable.getOffset());
		
		if(!categoryCodes.getCodes().isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes.getCodes());
		}
		
		if(!brandCodes.getCodes().isEmpty()) {
			query.setParameter("brandCodes", brandCodes.getCodes());
		}
		
		if(!tagCodes.getCodes().isEmpty()) {
			query.setParameter("tagCodes", tagCodes.getCodes());
		}
		
		if(!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new ProductDTOResultTransformer());
		
		List<ProductDTO> results = query.getResultList();
		
		return new PageImpl<ProductDTO>(results, PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), total);
	}
	
	private String constructSQL(boolean hasProductCodes,
								boolean hasProductDesc,
								boolean hasProductId,
								boolean hasCategory,
								boolean hasCategories,
								boolean hasBrands,
								boolean hasTags,
								boolean hasPrice,
								boolean hasType,
								boolean hasShippingDestination,
								boolean hasShippingType,
								boolean hasShippingWeight,
								boolean countOnly,
								boolean offset,
								String 	sort) {
		
		String sql = "WITH recursive descendants AS " + 
				"( " + 
				"          SELECT    t.cat_id, " + 
				"                    t.cat_cd, " + 
				"                    t.cat_lvl, " + 
				"                    t.cat_prnt_id, " +
				"                    t.cat_prnt_cd, " + 
				"                    t.cat_typ_id, " + 
				"                    cast('/' " + 
				"                              || cast(t.cat_id AS text) " + 
				"                              || '/' AS text) node " + 
				"          FROM      mochi.category            AS t " + 
				"          WHERE     0=0 " +
				((hasCategory) 
				? " AND t.cat_cd = :categoryCode " 
				: " AND t.cat_lvl = 0 ") + 
				"          UNION ALL " + 
				"          SELECT t.cat_id, " + 
				"                 t.cat_cd, " + 
				"                 t.cat_lvl, " + 
				"                 t.cat_prnt_id, " +
				"                 t.cat_prnt_cd, " +
				"                 t.cat_typ_id, " + 
				"                 cast(d.node " + 
				"                        || cast(t.cat_id AS text) " + 
				"                        || '/' AS text) node " + 
				"          FROM   mochi.category         AS t " + 
				"          JOIN   descendants            AS d " + 
				"          ON     t.cat_prnt_id = d.cat_id )" + 
				", categories AS " + 
				"( " +
				"          SELECT    " +
				"					 coalesce(s2.cat_typ_id,s1.cat_typ_id) as cat_typ_id, " +
				"					 coalesce(s2.cat_id,s1.cat_id) as cat_id, " +
				"		   			 coalesce(s2.cat_cd,s1.cat_cd) as cat_cd " +
				"          FROM      descendants s1 " + 
				"          LEFT JOIN descendants s2 " + 
				"          ON        s1.node <> s2.node " + 
				"          AND       LEFT(s2.node, length(s1.node)) = s1.node " +				
				"		   WHERE 0=0 " + 
				((hasCategories) 
				? " AND  s1.cat_cd IN (:categoryCodes) " 
				: "") +
						   
				"          GROUP BY  coalesce(s2.cat_typ_id,s1.cat_typ_id), " +
				"					 coalesce(s2.cat_id,s1.cat_id), " +
				"		   			 coalesce(s2.cat_cd,s1.cat_cd)" +
				"), promotions AS " + 
				"( " +
				" select pc.prd_id, " +
				"   	cp.prm_id, " +
				"		prm.prm_cd, " +
				"		pat.prm_desc " +
				" from categories c " +
				"	INNER JOIN mochi.category_promotion cp  " +
				"		on c.cat_id = cp.cat_id  " +
					
				"	INNER JOIN mochi.promotion prm  " +
				"		on cp.prm_id = prm.prm_id " +
					
				"	INNER JOIN mochi.promotion_attr_lcl pat " +
				"		on prm.prm_id = pat.prm_id " +
	
				"	INNER JOIN mochi.product_category pc " +
				"		on c.cat_id = pc.cat_id " +
				
				"   INNER JOIN mochi.product prd " +
				"		on pc.prd_id = prd.prd_id " +
				
				"	INNER JOIN mochi.product_attr_lcl attr " + 
				"		on prd.prd_id = attr.prd_id " +
											 
				" where pat.lcl_cd 	= :locale " +
				" and attr.lcl_cd 	= :locale " +
				((hasProductCodes) 	? 	" 	AND prd.upc_cd 		in :productCodes" 	: "") +
				((hasProductDesc) 	? 	" 	AND attr.prd_desc 	=  :productDesc " 	: "") +
				((hasProductId) 	? 	" 	AND prd.prd_id 		=  :productId " 	: "") +
				 
				" UNION  " +
											 
				" select pp.prd_id,  " +
				"	   pp.prm_id, " +
				"	   prm.prm_cd,  " +
				"	   pat.prm_desc  " +
				" from mochi.product prd  " + 
			
				"	INNER JOIN mochi.product_promotion pp  " +
				"		on prd.prd_id = pp.prd_id  " +
				
				"	INNER JOIN mochi.promotion prm  " +
				"		on pp.prm_id = prm.prm_id  " +
						
				"	INNER JOIN mochi.promotion_attr_lcl pat " +
				"		on prm.prm_id = pat.prm_id " +
				
				"	INNER JOIN mochi.product_attr_lcl attr " + 
				"		on prd.prd_id = attr.prd_id " +
	
				" where pat.lcl_cd 	= :locale " +
				" and attr.lcl_cd 	= :locale " +
				((hasProductCodes) 	? 	" 	AND prd.upc_cd 		in :productCodes" 	: "") +
				((hasProductDesc) 	? 	" 	AND attr.prd_desc 	=  :productDesc " 	: "") +
				((hasProductId) 	? 	" 	AND prd.prd_id 		=  :productId " 	: "") + ")"  +
						
		
		"select 	    " + 
		((countOnly) 
					? 	"	   count(distinct prd.prd_id) as product_count  "
					: 	"	   cp.cat_id, " + 
						"	   cp.cat_cd, " +	
						"	   cp.cat_lvl, " +
						"	   cp.cat_prnt_id, " +	
						"	   ca.cat_lcl_id, " +
						"	   ca.cat_desc, " +
						"	   ca.cat_img_pth, " +
						"	   ct.cat_typ_id 		AS cat_typ_id, 	" +
						"      ct.cat_typ_cd		AS cat_typ_cd, " +
						"      ct.cat_typ_desc 		AS cat_typ_desc, " + 
						"	   parent.cat_cd 		AS cat_prnt_cd, " + 
						"	   parent.cat_lvl 		AS cat_prnt_lvl, " + 
						"	   parent.cat_prnt_id	AS cat_prnt_prnt_id, " +
						"	   parent.cat_prnt_cd	AS cat_prnt_prnt_cd, " + 
						"	   prd.prd_id,   " + 
						"	   prd.upc_cd,   " + 
						"	   coalesce(acc.width, 0) as width, 	 " +
						"	   coalesce(acc.height, 0) as height, 	 " +
						"	   coalesce(acc.length, 0) as length, 	 " +
						"	   coalesce(acc.weight, 0) as weight, 	 " +
						"	   prd.prd_crtd_dt,   " +
						"	   attr.prd_lcl_id, " +
						"	   attr.prd_desc, " +
						"	   attr.prd_lng_desc, " +	
						"	   attr.prd_img_pth, " +
						"	   dept.dept_id,   " + 
						"	   dept.dept_cd,   " + 
						"	   dept.dept_class,   " +
						"	   dattr.dept_lcl_id, " +	
						"	   dattr.dept_desc,   " +
						"	   bnd.bnd_id,   " + 
						"	   bnd.bnd_cd,   " + 
						"	   bal.bnd_lcl_id,  " + 
						"	   bal.bnd_desc,   " + 
						"	   ps.prd_sts_id,   " + 
						"	   ps.prd_sts_cd,   " + 
						"	   ps.prd_sts_desc,  " +
						"	   promo.prm_id, " +
						"	   promo.prm_cd, " +
						"      prmlcl.prm_desc, " +
						"      promo.prm_st_dt, " +
						"      promo.prm_en_dt, " + 
						"	   promo.prm_mec_id, " +
						"	   promomec.prm_mec_cd, " +
						"	   promomec.prm_mec_desc, " +
						"	   coalesce(rprc.prc_val,0) as retail_price,  " + 
						"	   coalesce(mprc.prc_val,0) as markdown_price,  " + 
						"	   coalesce(soh.soh_qty, 0) > 0 as prd_in_stock, " +
						"	   coalesce(ship.shp_wgt_lim, 0) as shp_wgt_lim, " +
						"	   coalesce(ship.shp_wgt_frm, 0) as shp_wgt_frm, " +
						"	   coalesce(ship.shp_wgt_to, 0) as shp_wgt_to, " +
						"	   ship.shp_dst_id, " +
						"	   sd.shp_dst_cd, " +
						"	   sd.shp_zne_cd, " +
						"	   sd.shp_dst_desc, " +
						"	   sd.shp_dst_sht_cd, " +
						"	   ship.shp_typ_id, " +
						"	   st.shp_typ_cd, " +
						"	   sta.shp_typ_desc, " +
						"      :currency as ccy_cd, " +
						"	   :locale as lcl_cd ") + 
		
		"	FROM categories cc    " + 
		
		"	INNER JOIN mochi.product_category pc	" + 
		"	ON cc.cat_id = pc.cat_id   " + 
		
		"	INNER JOIN mochi.product prd    " + 
		"	ON pc.prd_id = prd.prd_id   " + 
		
		"	INNER JOIN mochi.product_attr_lcl attr " +
		"	ON prd.prd_id = attr.prd_id " + 

		"	INNER JOIN mochi.category cp " + 
		"	ON pc.cat_id = cp.cat_id " +

		"	INNER JOIN mochi.category_type ct  " + 
		"	ON cc.cat_typ_id = ct.cat_typ_id " + 	
		"	AND ct.cat_typ_cd = '"+
				Constants.categoryTypeProductCode + "'" +

		"	INNER JOIN mochi.category_attr_lcl ca    " + 
		"	ON cp.cat_id = ca.cat_id    " + 
		"	AND ca.lcl_cd = :locale " +		
		
		"	LEFT JOIN mochi.category parent " +
		"	ON cp.cat_prnt_id = parent.cat_id  " +
		
		"	INNER JOIN mochi.department dept   " + 
		"	ON prd.dept_id = dept.dept_id   " + 
		((hasType) 
				? "AND dept.dept_id = :typeDiscriminator "  
				: " ") +
		
		"	INNER JOIN mochi.department_attr_lcl dattr   " + 
		"	ON dept.dept_id = dattr.dept_id   " + 
		"	AND dattr.lcl_cd = :locale " +
			
		"	INNER JOIN mochi.brand bnd   " + 
		"	ON prd.bnd_id = bnd.bnd_id   " + 
		 
		"	INNER JOIN mochi.brand_attr_lcl bal   " + 
		"	ON bnd.bnd_id = bal.bnd_id   " + 
		
		"	LEFT JOIN  ( " + 
		"	SELECT prd.prd_id, " +  
		"		   prc_val  " +  
		"	FROM mochi.price rprc " +  
		
		"	INNER JOIN mochi.currency rcurr " +  
		"	ON         rprc.ccy_id = rcurr.ccy_id " +  
		"	AND        rcurr.ccy_cd = :currency " + 
		
		"	INNER JOIN mochi.price_type rpt " + 
		"	ON         rprc.prc_typ_id = rpt.prc_typ_id " +  
		"	AND        rpt.prc_typ_cd = :retailPriceCode " +
		
		"	INNER JOIN mochi.product prd " + 
		"	ON rprc.prd_id = prd.prd_id " +
		
		"	INNER JOIN mochi.product_attr_lcl attr " + 
		"	ON prd.prd_id = attr.prd_id " +
		"	AND attr.lcl_cd = :locale " +	
		
		"	WHERE 0=0 " +
		((hasProductCodes) 	? 	" 	AND prd.upc_cd 		IN :productCodes" 	: "") +
		((hasProductDesc) 	? 	" 	AND attr.prd_desc 	=  :productDesc " 	: "") +
		((hasProductId) 	? 	" 	AND prd.prd_id 		=  :productId " 	: "") +
		
		"	) rprc " + 
		"	ON prd.prd_id = rprc.prd_id " +  
		
		"	LEFT JOIN  ( " +
		"	SELECT prd.prd_id, " +  
		"		   mprc.prc_val " + 
		"	FROM mochi.price mprc " + 
		
		"	INNER JOIN mochi.currency mcurr " + 
	    "	ON         mprc.ccy_id = mcurr.ccy_id  " + 
		"	AND        mcurr.ccy_cd = :currency  " +
	    
		"	INNER JOIN mochi.price_type mpt " +
		"	ON         mprc.prc_typ_id = mpt.prc_typ_id " + 
		"	AND        mpt.prc_typ_cd = :markdownPriceCode " + 
		
		"	INNER JOIN mochi.product prd " + 
		"	ON mprc.prd_id = prd.prd_id " +
		
		"	INNER JOIN mochi.product_attr_lcl attr " + 
		"	ON prd.prd_id = attr.prd_id " +
		"	AND attr.lcl_cd = :locale " +	
		
		"	WHERE 0=0 " +
		((hasProductCodes) 	? 	" 	AND prd.upc_cd 		IN :productCodes" 	: "") +
		((hasProductDesc) 	? 	" 	AND attr.prd_desc 	=  :productDesc " 	: "") +
		((hasProductId) 	? 	" 	AND prd.prd_id 		=  :productId " 	: "") +
		
		"	) mprc  " +
		"	ON prd.prd_id = mprc.prd_id  " +
	
		"	LEFT JOIN mochi.product_basic acc " + 
		"	ON prd.prd_id = acc.prd_id    " +
			 
		"	INNER JOIN mochi.product_status ps    " + 
		"	ON prd.prd_sts_id = ps.prd_sts_id   " + 
		
		
		((hasTags) ? 
						"	INNER JOIN mochi.product_tag ptags	 " +
						"	ON prd.prd_id = ptags.prd_id " +
		
						"	INNER JOIN mochi.tag tag	 " +
						"	ON ptags.tag_id = tag.tag_id "
				   : 	"") +
		
		"	LEFT JOIN mochi.stock_on_hand soh " +
		"	ON prd.prd_id = soh.soh_prd_id " +
		
		"	LEFT JOIN mochi.product_promotion prdpromo " + 
		"	ON prd.prd_id = prdpromo.prd_id " +
		
		"	LEFT JOIN mochi.promotion promo " +
		"	ON prdpromo.prm_id = promo.prm_id " +
		
		"	LEFT JOIN mochi.promotion_attr_lcl prmlcl " +
		"	ON promo.prm_id = prmlcl.prm_id " +
		"	AND prmlcl.lcl_cd = :locale " +
		
		"	LEFT JOIN mochi.promotion_mechanic promomec " +
		"	ON promo.prm_mec_id =  promomec.prm_mec_id " +
		
		"	LEFT JOIN mochi.product_shipping ship " +
		"	ON prd.prd_id =  ship.prd_id " +
		
		"	LEFT JOIN mochi.shipping_destination sd " +
		"	ON ship.shp_dst_id = sd.shp_dst_id " +
		
		"	LEFT JOIN mochi.shipping_type st " +
		"	ON ship.shp_typ_id = st.shp_typ_id " +
		
		"	LEFT JOIN mochi.shipping_type_attr_lcl sta " +
		"	ON st.shp_typ_id = sta.shp_typ_id " +
		"	AND sta.lcl_cd = :locale " +
		
		"WHERE 0=0 " +
		"AND prd_sts_cd = 			:activeProductCode  " + 
		"AND bal.lcl_cd = 			:locale " +
		"AND attr.lcl_cd = 			:locale " +	
		((hasBrands) 
					? "AND bnd.bnd_cd in 		:brandCodes " 
					: "") +
		((hasTags) 
					? " AND tag.tag_cd in 		:tagCodes " 
					: "") +
		((hasPrice) 
					? " AND coalesce(mprc.prc_val, rprc.prc_val,0) <= :maxPrice " 
					: "") +
		((hasProductCodes) 				? 	" 	AND prd.upc_cd 		in :productCodes " 								: "") +
		((hasProductDesc) 				? 	" 	AND attr.prd_desc 	=  :productDesc " 								: "") +
		((hasProductId) 				? 	" 	AND prd.prd_id 		=  :productId " 								: "") +
		((hasShippingDestination) 		? 	" 	AND sd.shp_dst_cd 	=  :shippingDestinationCode " 					: "") +
		((hasShippingType) 				? 	" 	AND st.shp_typ_cd 	=  :shippingTypeCode " 							: "") +
		((hasShippingWeight) 			? 	" 	AND :shippingWeight > ship.shp_wgt_frm " +
											"	AND :shippingWeight <= ship.shp_wgt_to " 	: "") +
			
		((countOnly || !offset) 
		? 	""
		: 	" ORDER BY " + getOrderby(sort) + 
						" LIMIT 	:limit " +
						" OFFSET 	:offset ");
		
		return sql;
	}

	
	private String getOrderby(String param) {
		switch (param) {
			case "nameAsc":
				return "lower(attr.prd_desc) asc";
			case "nameDesc":
				return "lower(attr.prd_desc) desc";
			case "priceAsc":
				return "mprc.prc_val asc";
			case "priceDesc":
			  	return "mprc.prc_val desc";
			default:
				return "lower(prd_desc) asc";
			}
	}
	
	@Override
	public void save(ProductEntity t) {
		em.persist(t);
	}

	@Override
	public void update(ProductEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<ProductDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<ProductDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ProductDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductEntity> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
