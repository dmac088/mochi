package io.nzbee.search;

import java.util.List;
import java.util.Set;


public interface ISearchDimensionService<T> {

	String tokenToCode(String token);

	List<T> findAll(String lcl, String currency, Set<String> codes);

} 
