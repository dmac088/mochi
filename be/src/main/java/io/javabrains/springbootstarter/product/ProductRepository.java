package io.javabrains.springbootstarter.product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

	//getAllTopics()
	//getTopic(id)
	//updateTopic(id)
	//deleteTopic(id) 
	
}
