package io.nzbee.entity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IService<T> {

	
	public List<T> findAll();
	
	public List<T> findAll(String locale, String currency, Set<String> codes);
	
	public Optional<T> findById(long id);
	
	public Optional<T> findByCode(String code);
	
	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
}
