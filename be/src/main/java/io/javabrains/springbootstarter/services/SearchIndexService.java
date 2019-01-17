package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.domain.ProductAttribute;
import io.javabrains.springbootstarter.domain.ProductCategoryAttribute;

@Service
public class SearchIndexService {
	
	private ProductDTOService productDTOService;
	
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
	public Page<ProductDTO> findProduct(String lcl, String categoryDesc, String searchTerm, int page, int size, String sortBy) {
		FullTextEntityManager fullTextEntityManager 
		  = Search.getFullTextEntityManager(em);
		
		QueryBuilder productAttributeQueryBuilder = fullTextEntityManager.getSearchFactory() 
				  .buildQueryBuilder()
				  .forEntity(ProductAttribute.class)
				  .get();
		
		QueryBuilder productCategoryAttributeQueryBuilder = fullTextEntityManager.getSearchFactory() 
				  .buildQueryBuilder()
				  .forEntity(ProductCategoryAttribute.class)
				  .get();
		
		/*org.apache.lucene.search.Query query = queryBuilder
				  .keyword()
				  .onField("productDesc").matching(searchTerm)
				  .createQuery();*/
		
		//System.out.println("the categoryId is = " + categoryId);
		
		Query query = productAttributeQueryBuilder
				  .bool()
				  .must(productAttributeQueryBuilder.keyword().onField("productDesc").matching(searchTerm).createQuery())
				  .must(productCategoryAttributeQueryBuilder.keyword().onField("categoryDesc").matching(categoryDesc).createQuery())
				  .createQuery();
		
		org.apache.lucene.search.Sort sort = new Sort(new SortField(sortBy, SortField.Type.DOUBLE));
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery
		  = fullTextEntityManager.createFullTextQuery(query, ProductAttribute.class);
		
		//sorting
		jpaQuery.setSort(sort);
		
		List<ProductAttribute> results = jpaQuery.getResultList();		
		
		System.out.println(results.size());
		
		for(ProductAttribute pca : results) {
			System.out.println(pca.getProductDesc());
		}
		
		List<ProductDTO> lp = new ArrayList<ProductDTO>();

		for(ProductAttribute pa : results) {
			lp.add(productDTOService.convertToProductDto(pa));
		}
		
		Page<ProductDTO> pp = new PageImpl<ProductDTO>(lp, PageRequest.of(page, size, org.springframework.data.domain.Sort.by(sortBy).descending()), lp.size());
		return pp;
	}	
	
}
