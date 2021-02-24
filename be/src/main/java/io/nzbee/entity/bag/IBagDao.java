package io.nzbee.entity.bag;

import java.util.Optional;
import io.nzbee.entity.ILocalizedDao;

public interface IBagDao extends ILocalizedDao<BagDTO, BagEntity> {

	Optional<BagDTO> findByCode(String locale, String currency, String rootCategory, String userName);
	
}
