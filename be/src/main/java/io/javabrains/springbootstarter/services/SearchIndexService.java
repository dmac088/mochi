package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	public Page<ProductDTO> findProduct(String lcl, String searchTerm, int page, int size, String sortBy) {
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		
		QueryBuilder productAttributeQueryBuilder = fullTextEntityManager.getSearchFactory() 
				  .buildQueryBuilder()
				  .forEntity(ProductAttribute.class)
				  .get();
		
		
		org.apache.lucene.search.Query query = 
			productAttributeQueryBuilder.keyword()
		    .onFields("productDesc", "product.categories.productCategoryAttribute.categoryDesc")
		    .matching(searchTerm)
		    .createQuery();
		
		org.apache.lucene.search.Sort sort = new Sort(new SortField(sortBy, SortField.Type.DOUBLE));
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery
		  = fullTextEntityManager.createFullTextQuery(query, ProductAttribute.class);
		
		//filtering
		//jpaQuery.enableFullTextFilter("SelectedCategory").setParameter("categoryDesc", categoryDesc);
		
		//sorting
		jpaQuery.setSort(sort);
		
		List<ProductAttribute> results = jpaQuery.getResultList();
		
		List<ProductDTO> lp = new ArrayList<ProductDTO>();

		for(ProductAttribute pa : results) {
			lp.add(productDTOService.convertToProductDto(pa));
		}
		
		Page<ProductDTO> pp = new PageImpl<ProductDTO>(lp, PageRequest.of(page, size, org.springframework.data.domain.Sort.by(sortBy).descending()), lp.size());
		return pp;
	}	
	
}
