package io.javabrains.springbootstarter.person;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {

	//getAllTopics()
	//getTopic(id)
	//updateTopic(id)
	//deleteTopic(id) 
	
}
