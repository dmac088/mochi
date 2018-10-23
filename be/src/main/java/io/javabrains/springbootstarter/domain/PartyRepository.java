package io.javabrains.springbootstarter.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PartyRepository extends CrudRepository<Party, Long> {

	Optional<Party> findById(Long id);

}
