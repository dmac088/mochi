package io.nzbee.entity;

import java.util.List;
import java.util.Optional;


public interface IDao<T> {
    
    Optional<T> findById(long id);
    
    Optional<T> findByCode(String code);
    
    Optional<T> findByDesc(String locale, String desc);
     
    List<T> getAll(String locale, String currency);
    
    List<T> findAll(String locale, String currency);
     
    void save(T t);
     
    void update(T t, String[] params);
     
    void delete(T t);

	
    
}