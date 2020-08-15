package io.nzbee.entity;

import java.util.Optional;
import java.util.Set;
import javax.persistence.Tuple;


public interface ILocalizedDao<T> {
    
    Optional<T> findById(String locale, long id);
    
    Optional<T> findByCode(String locale, String code);
    
    Optional<T> findByDesc(String locale, String desc);
    
    Set<T> findAll(String locale);
    
    Set<T> findAll(String locale, Set<String> codes);
     
    T objectToEntity(Object[] o, String locale, String currency);
    
    void save(T t);
     
    void update(T t, String[] params);
     
    void delete(T t);

	T objectToEntity(Tuple t, String locale, String currency);

	T objectToEntity(Object[] o, String locale);

	T objectToEntity(Tuple t, String locale);

	
    
}