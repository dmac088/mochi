package io.nzbee.entity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ILocalizedService<T, Z> extends IService<Z> {
	
	List<T> findAll(String locale);
	
	List<T> findAll(String locale, Set<String> codes);
	
	List<Z> findAll(Set<String> codes);
	
	Optional<T> findById(String locale, Long id);
	
	Optional<T> findByDesc(String locale, String desc);
	
	Optional<T> findByCode(String locale, String code);
	
	Optional<Z> findEntityById(Long id);
	
	Optional<Z> findEntityByCode(String code);
	
	Optional<Z> findEntityByDesc(String locale, String desc);
	
}
