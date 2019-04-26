package io.javabrains.springbootstarter.entity;

import org.springframework.data.domain.Pageable;

public class PageableUtil {
	public int getStartPosition(Pageable pageable) {
		
		return (int) ((pageable.getPageNumber() * pageable.getPageSize()) /*+ pageable.getOffset()*/);
		
	}
	
}