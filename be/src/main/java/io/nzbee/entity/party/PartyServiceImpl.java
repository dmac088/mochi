package io.nzbee.entity.party;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="partyService")
@Transactional
public class PartyServiceImpl implements IPartyService {

	@Autowired
	private PartyRepository partyRepository; 
	
	@Autowired
	@Qualifier(value = "partyDao")
	private IPartyDao partyDAO;
	
	@Override
	public List<Party> findByRoleType(Class<?> roleType) {
		return partyDAO.findAllByRoleName(roleType.getSimpleName());
	}
	
	@Override
	public List<Party> findAll() {
		return partyRepository.findAll();
	}

	@Override
	public Optional<Party> findById(long id) {
		// TODO Auto-generated method stub
		return partyRepository.findById(id);
	}

	@Override
	public Optional<Party> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Party Party) {
		// TODO Auto-generated method stub
		partyRepository.save(Party);
	}
	
	@Override
	public void update(Party t) {
		// TODO Auto-generated method stub
		partyRepository.save(t);
	}

	@Override
	public void delete(Party t) {
		// TODO Auto-generated method stub
		partyRepository.delete(t);
	}

	@Override
	public List<Party> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

}
