package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.domain.ProductAttribute;

@Service
public class SearchIndexService {
	
	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;

	public void createSearchIndex() {
		FullTextEntityManager fullTextEntityManager 
		  = Search.getFullTextEntityManager(em);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		//will change this to a page later
	public List<ProductAttribute> findProduct(String lcl, String searchTerm) {
		FullTextEntityManager fullTextEntityManager 
		  = Search.getFullTextEntityManager(em);
		
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory() 
				  .buildQueryBuilder()
				  .forEntity(ProductAttribute.class)
				  .get();
		
		org.apache.lucene.search.Query query = queryBuilder
				  .keyword()
				  .onField("productDesc")
				  .matching("carrot")
				  .createQuery();
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery
		  = fullTextEntityManager.createFullTextQuery(query, ProductAttribute.class);
		
		List<ProductAttribute> results = jpaQuery.getResultList();
		return results;
	}	
	
}
