package io.nzbee.entity.bag;

import java.util.Optional;
import org.springframework.stereotype.Service;
import io.nzbee.entity.IService;

@Service
public interface IBagService extends IService<BagEntity> {

	Optional<BagDTO> findByCode(String locale, String currency, String rootCategory, String userName);

	Optional<BagEntity> findByCode(String userName);

	Optional<BagEntity> findById(Long id);

}
