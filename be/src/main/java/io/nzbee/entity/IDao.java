package io.nzbee.entity;

import java.util.List;
import java.util.Optional;

import io.nzbee.dto.category.CategoryWithName;

public interface IDao<T> {
    
    Optional<T> findById(long id);
     
    List<T> getAll();
    
    List<T> findAll();
     
    void save(T t);
     
    void update(T t, String[] params);
     
    void delete(T t);

    
}