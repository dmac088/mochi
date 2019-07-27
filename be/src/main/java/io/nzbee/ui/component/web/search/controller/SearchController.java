package io.nzbee.ui.component.web.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.ui.component.web.search.ISearchService;
import io.nzbee.ui.component.web.search.Search;
import io.nzbee.ui.component.web.sidebar.Sidebar;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping("/Product/{locale}/{currency}/category/{category}/maxPrice/{price}/page/{page}/size/{size}/sortBy/{sortBy}")
    public Search getProducts(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @PathVariable Double price, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy, @RequestBody final List<Sidebar> selectedFacets) {
    	return searchService.findAll(locale, currency, category, price, page, size, sortBy, selectedFacets);
    }
 

    @PostMapping("/Search/{locale}/{currency}/Category/{category}/SearchTerm/{term}/Page/{page}/Size/{size}/SortBy/{sortBy}")
    public Search search(@PathVariable String locale, @PathVariable String currency, @PathVariable String category,@PathVariable String term, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy, @RequestBody final List<Sidebar> selectedFacets) {
    	System.out.println("search");
    	return searchService.findAll(locale, currency, category, term, page, size, sortBy, selectedFacets);
    }
}
