package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.domain.PageableUtil;
import io.javabrains.springbootstarter.domain.ProductAttribute;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.QueryParser;

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

	public Page<ProductDTO> findProduct(String lcl, String categoryName, String searchTerm, int page, int size, String sortBy) {
		
		PageableUtil pageableUtil = new PageableUtil();
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
				
		QueryBuilder productAttributeQueryBuilder = 
				fullTextEntityManager.getSearchFactory()
				  .buildQueryBuilder()
				  .forEntity(ProductAttribute.class)
				  .overridesForField("productDesc", lcl)
				  .overridesForField("product.categories.productCategoryAttribute.categoryDesc", lcl)
				  .overridesForField("product.categories.parent.productCategoryAttribute.categoryDesc", lcl)
				  .get();
		
		org.apache.lucene.search.Query searchQuery = productAttributeQueryBuilder.keyword()
													.fuzzy()
													.onFields("productDesc", "product.categories.productCategoryAttribute.categoryDesc")
													.matching(searchTerm)
													 .createQuery();
				
		
		org.apache.lucene.search.Query query = 
			productAttributeQueryBuilder
			.bool()
			.must(
				(!(searchTerm.equals("-Z")) ? searchQuery : null)
			)
			.must(productAttributeQueryBuilder.keyword()
			.onField("product.categories.parent.productCategoryAttribute.lclCd")
			.matching(lcl)
		    .createQuery())
			.must(productAttributeQueryBuilder.keyword()
			.onFields("product.categories.productCategoryAttribute.categoryDesc", "product.categories.parent.productCategoryAttribute.categoryDesc")
			.matching(categoryName)
			.createQuery())
			.must(productAttributeQueryBuilder.keyword()
			.onField("lclCd")
			.matching(lcl)
			.createQuery())
			.createQuery();
		
		org.apache.lucene.search.Sort sort = new Sort(new SortField(sortBy, SortField.Type.DOUBLE));
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery
		  = fullTextEntityManager.createFullTextQuery(query, ProductAttribute.class);
		
		Pageable pageable = PageRequest.of(page, size);
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());
	
		//sorting
		jpaQuery.setSort(sort);
		
		List<ProductAttribute> results = jpaQuery.getResultList();
		
		List<ProductDTO> lp = results.stream().map(pa -> productDTOService.convertToProductDto(pa)).collect(Collectors.toList());

		Page<ProductDTO> pp = new PageImpl<ProductDTO>(lp, pageable, jpaQuery.getResultSize());
		return pp;
	}	
	
}
