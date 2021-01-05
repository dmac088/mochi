package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILocalizedDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface ITagDao  extends ILocalizedDao<TagDTO, TagEntity> {

	Optional<TagEntity> findByCode(String code);

	List<TagDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, Double maxPrice);

}
