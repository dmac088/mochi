package io.nzbee.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface Dao<T> {
    
    Optional<T> findById(long id);
     
    List<T> getAll();
    
    Page<T> findAll();
     
    void save(T t);
     
    void update(T t, String[] params);
     
    void delete(T t);
    
}