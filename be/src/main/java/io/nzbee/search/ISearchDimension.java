package io.nzbee.search;


public interface ISearchDimension {

	String getCode();
	
	String getDesc();
	
	String getLocale();
	
	Long getCount();
	
	boolean isHierarchical();

}
