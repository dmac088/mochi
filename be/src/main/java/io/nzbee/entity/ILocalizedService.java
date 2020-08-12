package io.nzbee.entity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ILocalizedService<T> {

	
	List<T> findAll(String locale);
	
	List<T> findAll(String locale, Set<String> codes);
	
	Optional<T> findById(String locale, long id);
	
	Optional<T> findByCode(String locale, String code);
	
	Optional<T> findByDesc(String locale, String desc);
	
	void save(T t);
	
	void update(T t);
	
	void delete(T t);

}
