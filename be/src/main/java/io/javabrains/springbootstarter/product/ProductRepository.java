package io.javabrains.springbootstarter.product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

	//getAllTopics()
	//getTopic(id)
	//updateTopic(id)
	//deleteTopic(id) 
	
}
