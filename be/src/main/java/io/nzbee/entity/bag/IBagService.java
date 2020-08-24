package io.nzbee.entity.bag;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IBagService extends IService<Bag> {

	Optional<Bag> findByUsername(String userName);

}
