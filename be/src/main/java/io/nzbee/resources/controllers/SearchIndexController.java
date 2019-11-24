package io.nzbee.resources.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.domain.services.SearchIndexService;


@RestController
@RequestMapping("/api")
public class SearchIndexController {
   
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private SearchIndexService searchIndexService;
	
    public SearchIndexController() {
    }
    
    @GetMapping("/CreateSearchIndex")
    public String createSearchIndex() {
 
    	LOGGER.info("Creating search index");
    	
    	searchIndexService.createSearchIndex();
    	return "Search Index Created!";
    } 
}
