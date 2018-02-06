package io.javabrains.springbootstarter.party;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyTypeService {

	@Autowired
	private PartyTypeRepository PartyTypeRepository; 
	
	public List<PartyType> getAllPartyType() {
		List<PartyType> PartyTypes = new ArrayList<>();
		Iterator<PartyType> i = PartyTypeRepository.findAll().iterator();
		while(i.hasNext()) {
			PartyTypes.add(i.next());
		}
		return PartyTypes;
	}
	
	public PartyType getPartyType(Long id) {
		PartyType p = PartyTypeRepository.findOne(id);
		return p;
	}
	
	public PartyType getPartyType(String partyTypeDesc) {
		PartyType p = PartyTypeRepository.findByPartyTypeDesc(partyTypeDesc);
		return p;
	}
	
	public void addPartyType(PartyType PartyType) {
		PartyTypeRepository.save(PartyType);
	}
	
	public void updatePartyType(String id, PartyType PartyType) {
		PartyTypeRepository.save(PartyType);
	}
	
	public void deletePartyType(Long id) {
		PartyTypeRepository.delete(id);
	}
	
}
