package io.javabrains.springbootstarter.party;

import org.springframework.data.repository.CrudRepository;

public interface PartyRepository extends CrudRepository<Party, Long> {

	//getAllTopics()
	//getTopic(id)
	//updateTopic(id)
	//deleteTopic(id) 

}
