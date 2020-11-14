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
									 "		 prd.productCreateDt " +
									 "FROM BagEntity b " +
									 "JOIN b.party p " +
									 "JOIN p.user u " + 
									 "JOIN p.partyType pt " + 
									 "JOIN p.partyRoles pr " + 
									 "JOIN pr.roleType rt " +
									 "JOIN b.bagItems bi " + 
									 "JOIN b.product prd " +
									 "WHERE u.username = :userName " +
									 "AND pt.partyTypeDesc = :partyType" +
									 "AND rt.roleTypeDesc = :roleType")
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



}