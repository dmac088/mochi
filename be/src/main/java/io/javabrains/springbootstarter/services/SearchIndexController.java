package io.javabrains.springbootstarter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class SearchIndexController {
   
    @Autowired
    private SearchIndexService searchIndexService;
	
    public SearchIndexController() {
        super();
    }
    
    @GetMapping("/CreateSearchIndex")
    public String createSearchIndex() {
    	searchIndexService.createSearchIndex();
    	return "Search Index Created!";
    }
    
    
}
