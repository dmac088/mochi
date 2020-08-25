package io.nzbee.entity.bag;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.nzbee.entity.IService;

@Service
public interface IBagService extends IService<Bag> {

	Optional<Bag> findByUsername(String userName);

}
