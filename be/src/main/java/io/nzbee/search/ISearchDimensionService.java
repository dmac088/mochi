package io.nzbee.search;

import java.util.List;
import io.nzbee.entity.StringCollectionWrapper;


public interface ISearchDimensionService<T> {

	String tokenToCode(String token);

	List<T> findAll(String lcl, String currency, String rootCategory, StringCollectionWrapper codes);

} 
