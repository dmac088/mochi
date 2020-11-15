package io.nzbee.entity.bag;

import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import io.nzbee.Constants;

public class BagDaoImpl implements IBagDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<BagDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Optional<BagDTO> findByCode(String locale, String currency, String userName) {
		LOGGER.debug("call BagDaoImpl.findByCode parameters : {}, {}", locale, userName);
		
		Query query = em.createQuery("SELECT u.username as userName," +
									 "		 treat(p AS PersonEntity).givenName, " +
									 "		 treat(p AS PersonEntity).familyName, " +
									 "		 pt.partyTypeDesc, " +
									 "		 u.enabled, " + 
									 "		 rt.roleTypeDesc, " +
									 "		 treat(pr AS CustomerEntity).customerNumber " +
									 "		 bi.bagItemStatus, " +
									 "		 bi.quantity, " + 
									 "		 prd.productUPC, " +
									 "		 prd.productCreateDt, " +
									 "		 pa.productDesc " +
									 "FROM BagEntity b " +
									 "JOIN b.party p " +
									 "JOIN p.user u " + 
									 "JOIN p.partyType pt " + 
									 "JOIN p.partyRoles pr " + 
									 "JOIN pr.roleType rt " +
									 "JOIN b.bagItems bi " + 
									 "JOIN b.product prd " +
									 "JOIN prd.attributes pa " +
 									 "WHERE u.username = :userName " +
									 "AND pt.partyTypeDesc = :partyType" +
									 "AND rt.roleTypeDesc = :roleType" +
									 "AND pa.lclCd = :locale")
		.setParameter("userName", userName)
		.setParameter("partyType", "Person")
		.setParameter("roleType", "Customer")
		.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new BagDTOResultTransformer());
			   
		return Optional.ofNullable((BagDTO) query.getSingleResult());
	}

	@Override
	public Optional<BagDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<BagDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<BagDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BagDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BagDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BagDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BagDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagEntity> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<BagEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<BagEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(BagEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BagEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BagEntity t) {
		// TODO Auto-generated method stub
		
	}
	
	private String constructSQL(boolean hasType,
								String sort) {

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
		" 		   AND t.cat_lvl = 0 " + 
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
		"          GROUP BY  coalesce(s2.cat_typ_id,s1.cat_typ_id), " +
		"					 coalesce(s2.cat_id,s1.cat_id), " +
		"		   			 coalesce(s2.cat_cd,s1.cat_cd)" +
		")" + 
		"select    cp.cat_id, " + 
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
			"	   bag.bag_id, " +
			"	   bi.bag_item_id, " + 
			"	   bi.qty, " +
			"	   bis.bag_item_sts_cd, " +
			"	   bis.bag_item_sts_desc, " +
			"	   coalesce(rprc.prc_val,0) as retail_price,  " + 
			"	   coalesce(mprc.prc_val,0) as markdown_price,  " + 
			"	   coalesce(soh.soh_qty, 0) > 0 as prd_in_stock, " +
			"      :currency as ccy_cd, " +
			"	   :locale as lcl_cd " + 
		
		"	FROM categories cc    								" + 
		
		"	INNER JOIN mochi.product_category pc				" + 
		"	ON cc.cat_id = pc.cat_id   							" + 
		
		"	INNER JOIN mochi.product prd    					" + 
		"	ON pc.prd_id = prd.prd_id   						" + 
		
		"	INNER JOIN mochi.bag_item bi						" +
		"	ON bi.prd_id = prd.prd_id							" +
		
		"	INNER JOIN mochi.bag_item_status bis				" +
		"	ON bi.bag_item_sts_id = bis.bag_item_sts_id			" +
		
		"	INNER JOIN mochi.bag bag							" + 
		"	ON bi.bag_id = bag.bag_id							" + 
		
		"	INNER JOIN mochi.person psn							" + 
		"	ON bag.pty_id = psn.psn_id							" +
		
		"	INNER JOIN mochi.user_ user 						" + 
		"	ON psn.psn_id = user.pty_id							" +
		
		"	INNER JOIN mochi.product_attr_lcl attr 				" +
		"	ON prd.prd_id = attr.prd_id 						" + 
		
		"	INNER JOIN mochi.category cp 						" + 
		"	ON pc.cat_id = cp.cat_id 							" +
		
		"	INNER JOIN mochi.category_type ct  					" + 
		"	ON cc.cat_typ_id = ct.cat_typ_id 					" + 	
		"	AND ct.cat_typ_cd = '"+
		Constants.categoryTypeProductCode + "'					" +
		
		"	INNER JOIN mochi.category_attr_lcl ca    			" + 
		"	ON cp.cat_id = ca.cat_id    						" + 
		"	AND ca.lcl_cd = :locale 							" +		
		
		"	LEFT JOIN mochi.category parent 					" +
		"	ON cp.cat_prnt_id = parent.cat_id  					" +
		
		"	INNER JOIN mochi.department dept   					" + 
		"	ON prd.dept_id = dept.dept_id   					" + 
		((hasType) 
		? "	AND dept.dept_id = :typeDiscriminator " : " 		") +
		
		"	INNER JOIN mochi.department_attr_lcl dattr   		" + 
		"	ON dept.dept_id = dattr.dept_id   					" + 
		"	AND dattr.lcl_cd = :locale 							" +
		
		"	INNER JOIN mochi.brand bnd   						" + 
		"	ON prd.bnd_id = bnd.bnd_id   						" + 
		
		"	INNER JOIN mochi.brand_attr_lcl bal   				" + 
		"	ON bnd.bnd_id = bal.bnd_id   						" + 
		
		"	LEFT JOIN  ( 										" + 
		"	SELECT prd_id, 										" +  
		"		   prc_val  									" +  
		"	FROM mochi.price rprc 								" +  
		"	INNER JOIN mochi.currency rcurr 					" +  
		"	ON         rprc.ccy_id = rcurr.ccy_id 				" +  
		"	AND        rcurr.ccy_cd = :currency 				" + 
		
		"	INNER JOIN mochi.price_type rpt 					" + 
		"	ON         rprc.prc_typ_id = rpt.prc_typ_id 		" +  
		"	AND        rpt.prc_typ_cd = :retailPriceCode 		" +  
		"	) rprc 												" + 
		"	ON prd.prd_id = rprc.prd_id 						" +  
		
		"	LEFT JOIN  ( 										" +
		"		SELECT prd_id, 									" +  
		"		   prc_val 										" + 
		"		FROM mochi.price mprc 							" + 
		"		INNER JOIN mochi.currency mcurr 				" + 
		"		ON         mprc.ccy_id = mcurr.ccy_id  			" + 
		"		AND        mcurr.ccy_cd = :currency  			" +
		"		INNER JOIN mochi.price_type mpt 				" +
		"		ON         mprc.prc_typ_id = mpt.prc_typ_id 	" + 
		"		AND        mpt.prc_typ_cd = :markdownPriceCode 	" + 
		"		) mprc  										" +
		"		ON prd.prd_id = mprc.prd_id  					" +
		
		"	LEFT JOIN mochi.product_basic acc 					" + 
		"	ON prd.prd_id = acc.prd_id    						" +
		
		"	INNER JOIN mochi.product_status ps    				" + 
		"	ON prd.prd_sts_id = ps.prd_sts_id   				" + 
		
		"	LEFT JOIN mochi.stock_on_hand soh 					" +
		"	ON prd.prd_id = soh.soh_prd_id 						" +
		
		"WHERE 0=0 " +
		"AND prd_sts_cd = 			:activeProductCode  		" + 
		"AND bal.lcl_cd = 			:locale 					" +
		"AND attr.lcl_cd = 			:locale 					" +
		"AND user.user_name = 		:userName 					" +
 		"ORDER BY " + getOrderby(sort);
		
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

}
