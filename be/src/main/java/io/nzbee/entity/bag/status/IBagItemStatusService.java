package io.nzbee.entity.bag.status;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IBagItemStatusService extends IService<BagItemStatus>  {

	Optional<BagItemStatus> findByCode(String code);

	Optional<BagItemStatus> findById(Long id);

}
