package io.nzbee.entity.tag;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	List<Tag> findAll();
	
}
