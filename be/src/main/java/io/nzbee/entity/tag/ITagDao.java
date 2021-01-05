package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface ITagDao  extends ILocalizedDao<TagDTO, TagEntity> {

	Optional<TagEntity> findByCode(String code);

	List<TagDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Double maxPrice);

}
