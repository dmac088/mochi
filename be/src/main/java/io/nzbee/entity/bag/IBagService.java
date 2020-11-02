package io.nzbee.entity.bag;

import java.util.Optional;

import org.springframework.stereotype.Service;
import io.nzbee.entity.ILocalizedService;

@Service
public interface IBagService extends ILocalizedService<BagDTO, BagEntity> {

	Optional<BagDTO> findByCode(String locale, String currency, String userName);

}
