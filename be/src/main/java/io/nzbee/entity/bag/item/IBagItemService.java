package io.nzbee.entity.bag.item;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IService;

public interface IBagItemService extends IService<BagItemEntity> {

	List<BagItemEntity> findAll();

	Optional<BagItemEntity> findById(Long id);

}
