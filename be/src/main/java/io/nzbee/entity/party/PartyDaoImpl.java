package io.nzbee.entity.party;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import org.springframework.transaction.annotation.Transactional;

import io.nzbee.entity.role.Role;
import io.nzbee.entity.role.RoleType;
import io.nzbee.entity.role.RoleType_;
import io.nzbee.entity.role.Role_;


@Component(value="partyDao")
public class PartyDaoImpl implements IPartyDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public List<Party> findAllByRoleName(String roleClassType) {
		
		System.out.println("the class type is = " + roleClassType);
		
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Party> cq = cb.createQuery(Party.class);
		
		Root<Party> root = cq.from(Party.class);
		
		Join<Party, Role> partyRole = root.join(Party_.partyRoles);
		Join<Role, RoleType> roleType = partyRole.join(Role_.roleType);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		conditions.add(cb.equal(roleType.get(RoleType_.roleTypeDesc), roleClassType));
		
		TypedQuery<Party> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return query.getResultList();
	}
	
	@Override
	public Optional<Party> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Party> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Party> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Party> findAll(List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Party t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Party t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Party t) {
		// TODO Auto-generated method stub
		
	}
}
