package io.javabrains.springbootstarter.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService {

	@Autowired
	private PartyRepository PartyRepository; 
	
	public List<Party> getAllPartys() {
		List<Party> Partys = new ArrayList<>();
		Iterator<Party> i = PartyRepository.findAll().iterator();
		while(i.hasNext()) {
			System.out.println("Party Type ID: " + i.next().getPartyType().getPartyTypeDesc());
			Partys.add(i.next());
		}
		return Partys;
	}
	
	public Party getParty(Long id) {
		Party p = PartyRepository.findOne(id);
		return p;
	}
	
	public void addParty(Party Party) {
		PartyRepository.save(Party);
	}
	
	public void updateParty(String id, Party Party) {
		PartyRepository.save(Party);
	}
	
	public void deleteParty(Long id) {
		PartyRepository.delete(id);
	}
	
}
