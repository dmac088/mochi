package io.nzbee.entity.tag;

import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface ITagDao  extends ILocalizedDao<TagDTO, TagEntity> {

	Set<TagDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Double maxPrice);

	Optional<TagEntity> findByCode(String code);

}
