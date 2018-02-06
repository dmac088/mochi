package io.javabrains.springbootstarter.party;

import org.springframework.data.repository.CrudRepository;

public interface PartyTypeRepository extends CrudRepository<PartyType, Long> {

	//getAllTopics()
	//getTopic(id)
	//updateTopic(id)
	//deleteTopic(id) 

	public PartyType findByPartyTypeDesc(String partyTypeDesc);
	
	public PartyType findByPartyTypeID(Long partyTypeId);
}
