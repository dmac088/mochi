package io.nzbee.entity.tag.attribute;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface ITagAttributeRepository extends CrudRepository<TagAttributeEntity, Long> {

	Set<TagAttributeEntity> findAll();

	Optional<TagAttributeEntity> findByLclCdAndTagTagId(String lcl, Long id);
	
	Optional<TagAttributeEntity> findByLclCdAndTagTagCode(String lcl, String code);

}
