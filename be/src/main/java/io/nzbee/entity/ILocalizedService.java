package io.nzbee.entity;

import java.util.Optional;
import java.util.Set;

public interface ILocalizedService<T, Z> {

	
	Set<T> findAll(String locale);
	
	Set<T> findAll(String locale, Set<String> codes);
	
	Optional<T> findById(String locale, Long id);
	
	Optional<T> findByCode(String locale, String code);
	
	Optional<T> findByDesc(String locale, String desc);
	
	void save(Z t);
	
	void update(Z t);
	
	void delete(Z t);


}
