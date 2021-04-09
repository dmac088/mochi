package io.nzbee.entity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IFullLocalizedService<T, Z> extends IService<Z> {
	
	List<T> findAll(String locale, String currency);
	
	List<T> findAll(String locale, String currency, StringCollectionWrapper codes);
	
	List<Z> findAll(Set<String> codes);
	
	Optional<T> findById(String locale, String currency, Long id);
	
	Optional<T> findByDesc(String locale, String currency, String desc);
	
	Optional<T> findByCode(String locale, String currency, String code);

	List<T> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper codes);
	
}
