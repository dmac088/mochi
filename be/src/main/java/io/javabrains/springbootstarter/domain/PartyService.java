package io.javabrains.springbootstarter.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartyService {

	@Autowired
	private PartyRepository PartyRepository; 
	
	@PreAuthorize("hasAuthority('PARTY_READER')")
	@Transactional(readOnly = true)
	public List<Party> getAllPartys() {
		List<Party> Partys = new ArrayList<>();
		Iterator<Party> i = PartyRepository.findAll().iterator();
		while(i.hasNext()) {
			System.out.println("Party Type ID: " + i.next().getPartyType().getPartyTypeDesc());
			Partys.add(i.next());
		}
		return Partys;
	}
	
	@PreAuthorize("hasAuthority('PARTY_READER')")
	@Transactional(readOnly = true)
	public Optional<Party> getParty(Long id) {
		Optional<Party> p = PartyRepository.findById(id);
		return p;
	}
	
	@PreAuthorize("hasAuthority('PARTY_CREATE')")
	@Transactional
	public void addParty(Party Party) {
		PartyRepository.save(Party);
	}
	
	@PreAuthorize("hasAuthority('PARTY_UPDATE')")
	@Transactional
	public void updateParty(String id, Party Party) {
		PartyRepository.save(Party);
	}
	
	@PreAuthorize("hasAuthority('PARTY_DELETE')")
	@Transactional
	public void deleteParty(Long id) {
		PartyRepository.deleteById(id);
	}
	
}
