package io.nzbee.entity;

import java.util.List;
import java.util.Optional;


public interface IDao<T> {
    
    Optional<T> findById(long id);
    
    Optional<T> findByCode(String code);
    
    List<T> findAll();
    
    List<T> findAll(List<String> codes);
     
    void save(T t);
     
    void update(T t, String[] params);
     
    void delete(T t);

    T objectToEntity(Object[] o);
	
    
}