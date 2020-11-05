package io.nzbee.entity.tag;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ITagRepository extends CrudRepository<TagEntity, Long> {

	List<TagEntity> findAll();
	
}
