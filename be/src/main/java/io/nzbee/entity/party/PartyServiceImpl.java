package io.nzbee.entity.party;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartyServiceImpl implements IPartyService {

	@Autowired
	private PartyRepository partyRepository; 
	
	@PreAuthorize("hasAuthority('PARTY_READER')")
	@Transactional(readOnly = true)
	public List<Party> findByRoleTypeDesc(String roleTypeDesc) {
		return partyRepository.findByPartyRolesRoleTypeRoleTypeDesc(roleTypeDesc);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PARTY_READER')")
	@Transactional(readOnly = true)
	public List<Party> findAll() {
		return partyRepository.findAll();
	}

	@Override
	@PreAuthorize("hasAuthority('PARTY_READ')")
	@Transactional(readOnly = true)
	public Optional<Party> findOne(Long id) {
		// TODO Auto-generated method stub
		return partyRepository.findById(id);
	}

	@Override
	//We need to ensure the user is logged in before invoking this method
	//@PreAuthorize("hasAuthority('PARTY_READ')")
	@Transactional(readOnly = true)
	public Optional<Party> findOne(String code) {
		// TODO Auto-generated method stub
		return partyRepository.findByPartyUserUsername(code);
	}

	@Override
	@PreAuthorize("hasAuthority('PARTY_CREATE')")
	@Transactional
	public void save(Party Party) {
		// TODO Auto-generated method stub
		partyRepository.save(Party);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PARTY_UPDATE')")
	@Transactional
	public void update(Party t) {
		// TODO Auto-generated method stub
		partyRepository.save(t);
	}

	@Override
	@PreAuthorize("hasAuthority('PARTY_DELETE')")
	@Transactional
	public void delete(Party t) {
		// TODO Auto-generated method stub
		partyRepository.delete(t);
	}
	
}
