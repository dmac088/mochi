package io.nzbee.entity.layout;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class LayoutDaoImpl implements ILayoutDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<Layout> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Layout> findByCode(String code) {
		LOGGER.debug("call LayoutServiceImpl.findByCode parameters : {}", code);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Layout> root = cq.from(Layout.class);
		
		cq.multiselect(	root.get(Layout_.layoutId).alias("layoutId"),
						root.get(Layout_.code).alias("layoutCode"),
						root.get(Layout_.desc).alias("layoutDesc")
		);
		
		cq.where(cb.and(
				cb.equal(root.get(Layout_.code), code)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Layout layout = this.objectToEntity(tuple);
			return Optional.ofNullable(layout);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	@Override
	public List<Layout> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Layout> findAll(List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Layout t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Layout t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Layout t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Layout objectToEntity(Object[] o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Layout objectToEntity(Tuple t) {
		Layout l = new Layout();
		l.setLayoutId(Long.parseLong(t.get("layoutId").toString()));
		l.setCode(t.get("layoutCode").toString());
		l.setDesc(t.get("layoutDesc").toString());
		
		return l;
	}

}
