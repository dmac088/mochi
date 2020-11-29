package io.nzbee.entity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Tuple;

public interface ILocalizedDao<T, Z> extends IDao<Z> {
    
    Optional<T> findById(String locale, Long id);
    
    Optional<T> findByCode(String locale, String code);
    
    Optional<T> findByDesc(String locale, String desc);
    
    List<T> findAll(String locale);
    
    List<T> findAll(String locale, Set<String> codes);
     
	T objectToDTO(Tuple t, String locale, String currency);

	T objectToDTO(Object[] o, String locale);

	T objectToDTO(Tuple t, String locale);

	T objectToDTO(Object[] o, String locale, String currency);

	
}