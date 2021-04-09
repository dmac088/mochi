package io.nzbee.entity.party.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.entity.party.Party_;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.role.Role;
import io.nzbee.entity.role.RoleType;
import io.nzbee.entity.role.RoleType_;
import io.nzbee.entity.role.Role_;
import io.nzbee.security.user.User;
import io.nzbee.security.user.User_;

@Component(value="personDao")
public class PersonDaoImpl implements IPersonDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public List<PersonEntity> findAllByRoleName(String roleClassType) {
		
		LOGGER.debug("call PersonDaoImpl.findAllByRoleName parameters : {}", roleClassType);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<PersonEntity> cq = cb.createQuery(PersonEntity.class);
		
		Root<PersonEntity> root = cq.from(PersonEntity.class);
		
		Join<PersonEntity, Role> partyRole = root.join(PersonEntity_.partyRoles);
		Join<Role, RoleType> roleType = partyRole.join(Role_.roleType);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		conditions.add(cb.equal(roleType.get(RoleType_.roleTypeDesc), roleClassType));
		
		TypedQuery<PersonEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return query.getResultList();
	}

	@Override
	public Optional<PersonEntity> findByUsernameAndRole(String userName, String roleClassType) {

		LOGGER.debug("call PersonDaoImpl.findAllByUsernameAndRole parameters : {}, {}", userName, roleClassType);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<PersonEntity> cq = cb.createQuery(PersonEntity.class);
		
		Root<PersonEntity> root = cq.from(PersonEntity.class);
		
		Join<PersonEntity, Role> partyRole = root.join(PersonEntity_.partyRoles);
		Join<PersonEntity, User> partyUser = root.join(Party_.partyUser);
		Join<Role, RoleType> roleType = partyRole.join(Role_.roleType);

		cq.where(cb.and(
			cb.equal(roleType.get(RoleType_.roleTypeDesc), roleClassType),
			cb.equal(partyUser.get(User_.username), userName))
		);
		
		TypedQuery<PersonEntity> query = em.createQuery(cq);
		
		try {
			PersonEntity p = query.getSingleResult();
			return Optional.ofNullable(p);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public void save(PersonEntity t) {
		em.persist(t);
	}

	@Override
	public void update(PersonEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PersonEntity t) {
		// TODO Auto-generated method stub
		
	}

}
