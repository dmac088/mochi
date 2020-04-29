package io.nzbee.domain;

import java.util.Optional;
import java.util.Set;

public interface ILocalizedService<T> {

	void save(T object); 
	
	void delete(T object);

	Optional<T> findByCode(String locale, String currency, String code);

	Optional<T> findByDesc(String locale, String currency, String desc);

	Set<T> findAll(String locale, String currency);

	Set<T> findAll(String locale, String currency, Set<String> codes); 
}
