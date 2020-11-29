package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface ITagRepository extends CrudRepository<TagEntity, Long> {

	List<TagEntity> findAll();
	
	Optional<TagEntity> findByTagCode(String code);
	
	List<TagEntity> findByTagCodeIn(Set<String> codes);
	
	Optional<TagEntity> findByAttributesLclCdAndAttributesTagDesc(String locale, String desc);
}
