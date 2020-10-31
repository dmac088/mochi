package io.nzbee.search;

import java.util.Set;


public interface ISearchDimensionService<T> {

	String tokenToCode(String token);

	Set<T> findAll(String lcl, String currency, Set<String> codes);

} 
