package io.nzbee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.services.SearchIndexService;


@RestController
@RequestMapping("/api")
public class SearchIndexController {
   
    @Autowired
    private SearchIndexService searchIndexService;
	
    public SearchIndexController() {
    }
    
    @GetMapping("/CreateSearchIndex")
    public String createSearchIndex() {
    	searchIndexService.createSearchIndex();
    	return "Search Index Created!";
    }
    
    
}
