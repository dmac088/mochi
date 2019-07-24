package io.nzbee.entity.party;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartyServiceImpl implements IPartyService {

	@Autowired
	private PartyRepository PartyRepository; 
	
	@PreAuthorize("hasAuthority('PARTY_READER')")
	@Transactional(readOnly = true)
	public List<Party> findByRoleTypeDesc(String roleTypeDesc) {
		List<Party> Partys = new ArrayList<>();
		Iterator<Party> i = PartyRepository.findByPartyRolesRoleTypeRoleTypeDesc(roleTypeDesc).iterator();
		while(i.hasNext()) {
			Partys.add(i.next());
		}
		return Partys;
	}
	
	@Override
	@PreAuthorize("hasAuthority('PARTY_READER')")
	@Transactional(readOnly = true)
	public List<Party> findAll() {
		List<Party> Partys = new ArrayList<>();
		Iterator<Party> i = PartyRepository.findAll().iterator();
		while(i.hasNext()) {
			Partys.add(i.next());
		}
		return Partys;
	}

	@Override
	@PreAuthorize("hasAuthority('PARTY_READ')")
	@Transactional(readOnly = true)
	public Optional<Party> findOne(Long id) {
		// TODO Auto-generated method stub
		return PartyRepository.findById(id);
	}

	@Override
	@PreAuthorize("hasAuthority('PARTY_READ')")
	@Transactional(readOnly = true)
	public Optional<Party> findOne(String code) {
		// TODO Auto-generated method stub
		return PartyRepository.findByPartyUserUsername(code);
	}

	@Override
	@PreAuthorize("hasAuthority('PARTY_CREATE')")
	@Transactional
	public void save(Party Party) {
		PartyRepository.save(Party);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PARTY_UPDATE')")
	@Transactional
	public void update(Party t) {
		// TODO Auto-generated method stub
		PartyRepository.save(t);
	}

	@Override
	@PreAuthorize("hasAuthority('PARTY_DELETE')")
	@Transactional
	public void delete(Party t) {
		// TODO Auto-generated method stub
		PartyRepository.delete(t);
	}
	
}
