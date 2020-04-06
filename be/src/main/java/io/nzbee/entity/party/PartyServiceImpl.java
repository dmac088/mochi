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
	@PreAuthorize("hasAuthority('PARTY_READER')")
	public List<Party> findByRoleType(Class<?> roleType) {
		return partyDAO.findAllByRoleName(roleType.getSimpleName());
	}
	
	@Override
	@PreAuthorize("hasAuthority('PARTY_READER')")
	public List<Party> findAll() {
		return partyRepository.findAll();
	}

	@Override
	@PreAuthorize("hasAuthority('PARTY_READ')")
	public Optional<Party> findById(long id) {
		// TODO Auto-generated method stub
		return partyRepository.findById(id);
	}

	@Override
	//We need to ensure the user is logged in before invoking this method
	//@PreAuthorize("hasAuthority('PARTY_READ')")
	public Optional<Party> findByCode(String code) {
		// TODO Auto-generated method stub
		return partyRepository.findByPartyUserUsername(code);
	}

	@Override
	@PreAuthorize("hasAuthority('PARTY_CREATE')")
	public void save(Party Party) {
		// TODO Auto-generated method stub
		partyRepository.save(Party);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PARTY_UPDATE')")
	public void update(Party t) {
		// TODO Auto-generated method stub
		partyRepository.save(t);
	}

	@Override
	@PreAuthorize("hasAuthority('PARTY_DELETE')")
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
