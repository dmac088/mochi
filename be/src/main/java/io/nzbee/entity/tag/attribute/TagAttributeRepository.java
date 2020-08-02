package io.nzbee.entity.tag.attribute;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TagAttributeRepository extends CrudRepository<TagAttribute, Long> {

	List<TagAttribute> findAll();

	List<TagAttribute> findByLclCd(String lcl);

	Optional<TagAttribute> findByLclCdAndTagTagId(String lcl, Long id);
	
	Optional<TagAttribute> findByLclCdAndTagTagCode(String lcl, String code);

}
