package io.nzbee.entity;

import java.util.Optional;
import java.util.Set;

public interface ILocalizedService<T, Z> extends IService<Z> {

	
	Set<T> findAll(String locale);
	
	Set<T> findAll(String locale, Set<String> codes);
	
	Set<Z> findAll(Set<String> codes);
	
	Optional<T> findById(String locale, Long id);
	
	Optional<T> findDTOByCode(String locale, String code);
	
	Optional<Z> findEntityByCode(String locale, String code);
	
	Optional<T> findByDesc(String locale, String desc);
	
}
