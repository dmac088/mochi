package io.nzbee.entity.tag;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface ITagRepository extends CrudRepository<TagEntity, Long> {

	Set<TagEntity> findAll();
	
	Optional<TagEntity> findByTagCode(String code);
	
	Optional<TagEntity> findByAttributesLclCdAndAttributesTagDesc(String locale, String desc);
}
