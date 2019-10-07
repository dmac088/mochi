package io.nzbee.entity;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

	
	public List<T> findAll(String locale, String currency);
	
	public Optional<T> findById(long id);
	
	public Optional<T> findByCode(String code);
	
	public Optional<T> findByDesc(String locale, String desc);
	
	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
}
