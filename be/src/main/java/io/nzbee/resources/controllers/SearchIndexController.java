package io.nzbee.resources.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.CacheMode;
import org.hibernate.search.batchindexing.impl.SimpleIndexingProgressMonitor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.entity.product.physical.PhysicalProductEntity;


@RestController
@RequestMapping("/api")
public class SearchIndexController {
   
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;

    public SearchIndexController() {
    }
    
    @GetMapping("/CreateSearchIndex")
    public String createSearchIndex() {
 
    	LOGGER.debug("Creating search index");
    	
    	FullTextEntityManager fullTextEntityManager 
		  = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		try {
			fullTextEntityManager
			.createIndexer( PhysicalProductEntity.class )
			.batchSizeToLoadObjects( 25 )
			.cacheMode( CacheMode.IGNORE )
			.threadsToLoadObjects( 12 )
			.idFetchSize( 150 )
			.transactionTimeout( 1800 )
			.progressMonitor( new SimpleIndexingProgressMonitor() ) //a MassIndexerProgressMonitor implementation
			.startAndWait();	
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return "Search Index Created!";
    } 
}
