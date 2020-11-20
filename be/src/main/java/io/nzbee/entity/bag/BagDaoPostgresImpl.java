package io.nzbee.entity.bag;

import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.nzbee.Constants;

@Component
public class BagDaoPostgresImpl implements IBagDao {

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
		LOGGER.debug("call BagDaoPostgresImpl.findByCode parameters : {}, {}, {}", locale, currency, userName);
		
		Query query = em.createNativeQuery(this.getSQL())
						.setParameter("userName", userName)
						.setParameter("locale", locale)
						.setParameter("currency", currency)
						.setParameter("retailPriceCode", Constants.retailPriceCode)
						.setParameter("markdownPriceCode", Constants.markdownPriceCode)
						.setParameter("activeProductCode", Constants.activeSKUCode)
						.unwrap(org.hibernate.query.Query.class)
						.setResultTransformer(new BagDTOResultTransformer());
		try {
			return Optional.ofNullable((BagDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
		
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
	
	private String getSQL() {

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
		"          INNER JOIN descendants s2 " + 
		"          ON        s1.node <> s2.node " + 
		"          AND       LEFT(s2.node, length(s1.node)) = s1.node " +				
		"		   WHERE 0=0 " + 
		"          GROUP BY  coalesce(s2.cat_typ_id,s1.cat_typ_id), " +
		"					 coalesce(s2.cat_id,s1.cat_id), " +
		"		   			 coalesce(s2.cat_cd,s1.cat_cd)" +
		")" + 
		"select    p.cat_id, " + 
		"           p.cat_cd, " + 
		"           p.cat_lvl, " + 
		"           p.cat_prnt_id, " + 
		"           p.cat_lcl_id, " + 
		"           p.cat_desc, " + 
		"           p.cat_img_pth, " + 
		"           p.cat_typ_id, " + 
		"           p.cat_typ_cd, " + 
		"           p.cat_typ_desc, " + 
		"           p.cat_prnt_cd, " + 
		"           p.cat_prnt_lvl, " + 
		"           p.cat_prnt_prnt_id, " + 
		"           p.cat_prnt_prnt_cd, " + 
		"           p.prd_id, " + 
		"           p.upc_cd, " + 
		"           p.prd_crtd_dt, " + 
		"           p.prd_lcl_id, " + 
		"           p.prd_desc, " + 
		"           p.prd_lng_desc, " + 
		"           p.prd_img_pth, " + 
		"           p.dept_id, " + 
		"           p.dept_cd, " + 
		"           p.dept_class, " + 
		"           p.dept_lcl_id, " + 
		"           p.dept_desc, " + 
		"           p.bnd_id, " + 
		"           p.bnd_cd, " + 
		"           p.bnd_lcl_id, " + 
		"           p.bnd_desc, " + 
		"           p.prd_sts_id, " + 
		"           p.prd_sts_cd, " + 
		"           p.prd_sts_desc, " + 
		"           bag.bag_id, " + 
		"           p.bag_item_id, " + 
		"           p.qty, " + 
		"           p.bag_item_sts_cd, " + 
		"           p.bag_item_sts_desc, " + 
		"           rt.rle_typ_id, " + 
		"           rt.rle_typ_desc, " + 
		"           cust.cst_num, " + 
		"           pty.pty_id, " + 
		"           psn.psn_gvn_nm, " + 
		"           psn.psn_fml_nm, " + 
		"           usr.user_name, " + 
		"           usr.enabled, " + 
		"           p.retail_price, " + 
		"           p.markdown_price, " + 
		"           COALESCE(soh.soh_qty, 0) > 0 AS prd_in_stock, " +
		"      		:currency as ccy_cd, " +
		"	   		:locale as lcl_cd " + 
		
		"	FROM mochi.party pty    							" +
		
		"	INNER JOIN mochi.person psn    						" +  
		"	ON pty.pty_id = psn.psn_id    						" +  
		
		"	INNER JOIN mochi.role rle							" + 
		"	ON pty.pty_id = rle.pty_id							" +
		
		"	INNER JOIN mochi.role_type rt						" + 
		"	ON rle.rle_typ_id = rt.rle_typ_id					" +
		
		"	INNER JOIN security.user_ usr 						" + 
		"	ON pty.pty_id = usr.pty_id							" +
		
		"	INNER JOIN mochi.customer cust 						" + 
		"	ON rle.rle_id = cust.rle_id							" + 	
		
		"	INNER JOIN mochi.bag bag							" + 
		"	ON pty.pty_id = bag.pty_id							" + 
		
		"	LEFT JOIN 											" +
		"	(						   							" +
		"	SELECT cp.cat_id, " +
		"	       cp.cat_cd, " +
		"	       cp.cat_lvl, " +
		"	       cp.cat_prnt_id, " +
		"	       ca.cat_lcl_id, " +
		"	       ca.cat_desc, " +
		"	       ca.cat_img_pth, " +
		"	       ct.cat_typ_id      AS cat_typ_id, " +
		"	       ct.cat_typ_cd      AS cat_typ_cd, " +
		"	       ct.cat_typ_desc    AS cat_typ_desc, " +
		"	       parent.cat_cd      AS cat_prnt_cd, " +
		"	       parent.cat_lvl     AS cat_prnt_lvl, " +
		"	       parent.cat_prnt_id AS cat_prnt_prnt_id, " +
		"	       parent.cat_prnt_cd AS cat_prnt_prnt_cd, " +
		"	       prd.prd_id, " +
		"	       prd.upc_cd, " +
		"	       prd.prd_crtd_dt, " +
		"	       attr.prd_lcl_id, " +
		"	       attr.prd_desc, " +
		"	       attr.prd_lng_desc, " +
		"	       attr.prd_img_pth, " +
		"	       dept.dept_id, " +
		"	       dept.dept_cd, " +
		"	       dept.dept_class, " +
		"	       dattr.dept_lcl_id, " +
		"	       dattr.dept_desc, " +
		"	       bnd.bnd_id, " +
		"	       bnd.bnd_cd, " +
		"	       bal.bnd_lcl_id, " +
		"	       bal.bnd_desc, " +
		"	       ps.prd_sts_id, " +
		"	       ps.prd_sts_cd, " +
		"	       ps.prd_sts_desc, " +
		"	       bi.bag_id, " +
		"	       bi.bag_item_id, " +
		"	       bi.qty, " +
		"	       bis.bag_item_sts_cd, " +
		"	       bis.bag_item_sts_desc," +
		"		   COALESCE(rprc.prc_val,0)     AS retail_price, " +
		"	       COALESCE(mprc.prc_val,0)     AS markdown_price " +
		"	FROM mochi.bag_item bi " +
		"	LEFT JOIN mochi.bag_item_status bis " +
		"	ON         bi.bag_item_sts_id = bis.bag_item_sts_id " +
		"	LEFT JOIN mochi.product prd " +
		"	ON         bi.prd_id = prd.prd_id " +
		"	LEFT JOIN mochi.product_status ps " +
		"	ON         prd.prd_sts_id = ps.prd_sts_id " +
		"	AND        ps.prd_sts_cd = :activeProductCode " +
		"	LEFT JOIN mochi.product_category pc " +
		"	ON         prd.prd_id = pc.prd_id " +
		"	LEFT JOIN categories cc " +
		"	ON         pc.cat_id = cc.cat_id " +
		"	LEFT JOIN mochi.product_attr_lcl attr " +
		"	ON         prd.prd_id = attr.prd_id " +
		"	AND        attr.lcl_cd = :locale" +
		"	LEFT JOIN mochi.category cp " +
		"	ON         pc.cat_id = cp.cat_id " +
		"	LEFT JOIN mochi.category_type ct " +
		"	ON         cc.cat_typ_id = ct.cat_typ_id " +
		"	AND        ct.cat_typ_cd = 'PRD01' " +
		"	LEFT JOIN mochi.category_attr_lcl ca " +
		"	ON         cp.cat_id = ca.cat_id " +
		"	AND        ca.lcl_cd = :locale" +
		"	LEFT JOIN mochi.category parent " +
		"	ON         cp.cat_prnt_id = parent.cat_id " +
		"	LEFT JOIN mochi.department dept " +
		"	ON         prd.dept_id = dept.dept_id " +
		"	LEFT JOIN mochi.department_attr_lcl dattr " +
		"	ON         dept.dept_id = dattr.dept_id " +
		"	AND        dattr.lcl_cd = :locale" +
		"	LEFT JOIN mochi.brand bnd " +
		"	ON         prd.bnd_id = bnd.bnd_id " +
		"	LEFT JOIN mochi.brand_attr_lcl bal " +
		"	ON         bnd.bnd_id = bal.bnd_id " +
		"	AND        bal.lcl_cd = :locale" +
		"	LEFT JOIN " +
		"	        ( " +
		"	                    SELECT     prd_id, " +
		"	                                prc_val " +
		"	                    FROM       mochi.price rprc " +
		"	                    INNER JOIN mochi.currency rcurr " +
		"	                    ON         rprc.ccy_id = rcurr.ccy_id " +
		"	                    AND        rcurr.ccy_cd = :currency" +
		"	                    INNER JOIN mochi.price_type rpt " +
		"	                    ON         rprc.prc_typ_id = rpt.prc_typ_id " +
		"	                    AND        rpt.prc_typ_cd = :retailPriceCode ) rprc " +
		"	ON         prd.prd_id = rprc.prd_id " +
		"	LEFT JOIN " +
		"	        ( " +
		"	                    SELECT     prd_id, 							" +
		"	                                prc_val 						" +
		"	                    FROM       mochi.price mprc 				" +
		"	                    INNER JOIN mochi.currency mcurr 			" +
		"	                    ON         mprc.ccy_id = mcurr.ccy_id 		" +
		"	                    AND        mcurr.ccy_cd = :currency				" +
		"	                    INNER JOIN mochi.price_type mpt 			" +
		"	                    ON         mprc.prc_typ_id = mpt.prc_typ_id " +
		"	                    AND        mpt.prc_typ_cd = :markdownPriceCode ) mprc 	" + 
		"	ON         prd.prd_id = mprc.prd_id								" +
		"	LEFT JOIN  mochi.product_basic acc 								" +
		"	ON         prd.prd_id = acc.prd_id 								" +
		"	) p 															" +
		"	ON bag.bag_id = p.bag_id 										" + 
		
		"	LEFT JOIN mochi.stock_on_hand soh 								" +
		"	ON p.prd_id = soh.soh_prd_id 									" +
		
		"WHERE 0=0 															" +
	//	"AND prd_sts_cd = 			:activeProductCode  					" + 
		"AND usr.user_name = 		:userName 								";
		
		LOGGER.debug(sql);
		
		return sql;
	}

}