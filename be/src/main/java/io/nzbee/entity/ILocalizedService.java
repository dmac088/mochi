package io.nzbee.entity;

import java.util.List;
import java.util.Optional;

public interface ILocalizedService<T> {

	
	public List<T> findAll(String locale, String currency);
	
	public List<T> findAll(String locale, String currency, List<String> codes);
	
	public Optional<T> findById(String locale, String currency, long id);
	
	public Optional<T> findByCode(String locale, String currency, String code);
	
	public Optional<T> findByDesc(String locale, String currency, String desc);
	
	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
}
