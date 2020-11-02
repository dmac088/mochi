package io.nzbee.entity;

import java.util.Optional;
import java.util.Set;

public interface IDao<T> {
    
    Optional<T> findById(long id);
    
    Optional<T> findByCode(String code);
    
    Set<T> findAll();
    
    Set<T> findAll(Set<String> codes);
     
    void save(T t);
     
    void update(T t, String[] params);
     
    void delete(T t);

	
}