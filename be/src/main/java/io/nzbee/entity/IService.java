package io.nzbee.entity;

import java.util.Optional;
import java.util.Set;

public interface IService<T> {

	public Set<T> findAll();
	
	public Optional<T> findById(Long id);
	
	public Optional<T> findByCode(String code);
	
	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
}
