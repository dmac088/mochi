package io.javabrains.springbootstarter.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    
    @GetMapping("/Search/{lcl}/SearchTerm/{term}")
    public Page<ProductDTO> search(@PathVariable String lcl, @PathVariable String term) {
    	return searchIndexService.findProduct(lcl, term);
    }
}
