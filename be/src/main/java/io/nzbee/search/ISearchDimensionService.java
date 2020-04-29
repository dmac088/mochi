package io.nzbee.search;

import java.util.List;
import java.util.Set;

public interface ISearchDimensionService<T> {

	List<T> findAll(String lcl, String currency, Set<String> codes);
	
	String tokenToCode(String token);
	
} 
