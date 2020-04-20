package io.nzbee.entity.party.person;

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
import io.nzbee.entity.party.Party_;
import io.nzbee.entity.role.Role;
import io.nzbee.entity.role.RoleType;
import io.nzbee.entity.role.RoleType_;
import io.nzbee.entity.role.Role_;
import io.nzbee.security.user.User;
import io.nzbee.security.user.User_;


@Component(value="personDao")
public class PersonDaoImpl implements IPersonDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public List<Person> findAllByRoleName(String roleClassType) {
		
		System.out.println("the class type is = " + roleClassType);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		
		Root<Person> root = cq.from(Person.class);
		
		Join<Person, Role> partyRole = root.join(Person_.partyRoles);
		Join<Role, RoleType> roleType = partyRole.join(Role_.roleType);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		conditions.add(cb.equal(roleType.get(RoleType_.roleTypeDesc), roleClassType));
		
		TypedQuery<Person> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return query.getResultList();
	}

	@Override
	public Optional<Person> findAllByUsernameAndRole(String userName, String roleClassType) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		
		Root<Person> root = cq.from(Person.class);
		
		Join<Person, Role> partyRole = root.join(Person_.partyRoles);
		Join<Person, User> partyUser = root.join(Party_.partyUser);
		Join<Role, RoleType> roleType = partyRole.join(Role_.roleType);

		cq.where(cb.and(
			cb.equal(roleType.get(RoleType_.roleTypeDesc), roleClassType),
			cb.equal(partyUser.get(User_.username), userName))
		);
		
		TypedQuery<Person> query = em.createQuery(cq);
		
		return Optional.ofNullable(query.getSingleResult());
	}
	
	@Override
	public Optional<Person> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Person> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findAll(List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Person t) {
		em.persist(t);
		em.flush();
	}

	@Override
	public void update(Person t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Person t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person objectToEntity(Object[] o) {
		// TODO Auto-generated method stub
		return null;
	}
}
