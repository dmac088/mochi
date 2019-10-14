package io.nzbee.entity;

import java.util.List;
import java.util.Optional;


public interface ILocalizedDao<T> {
    
    Optional<T> findById(String locale, String currency, long id);
    
    Optional<T> findByCode(String locale, String currency, String code);
    
    Optional<T> findByDesc(String locale, String currency, String desc);
    
    List<T> findAll(String locale, String currency);
    
    List<T> findAll(String locale, String currency, List<String> codes);
     
    void save(T t);
     
    void update(T t, String[] params);
     
    void delete(T t);

	
    
}