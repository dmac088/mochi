package io.javabrains.springbootstarter.services;
import java.util.Collections;
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

	public Page<ProductDTO> findProduct(String lcl, String categoryCode, String searchTerm, int page, int size, String sortBy) {
		
		PageableUtil pageableUtil = new PageableUtil();
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
				
		QueryBuilder productAttributeQueryBuilder = 
				fullTextEntityManager.getSearchFactory()
				  .buildQueryBuilder()
				  .forEntity(ProductAttribute.class)
				  .overridesForField("productDesc", lcl)
				  .overridesForField("product.categories.productCategoryAttribute.categoryDesc", lcl)
				  .overridesForField("product.categories.parent.productCategoryAttribute.categoryDesc", lcl)
				  .overridesForField("product.categories.parent.parent.productCategoryAttribute.categoryDesc", lcl)
				  .get();
		
		org.apache.lucene.search.Query searchQuery = productAttributeQueryBuilder.keyword()
												
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
			.onFields("product.categories.categoryCode", "product.categories.parent.categoryCode", "product.categories.parent.parent.categoryCode")
			.matching(categoryCode)
			.createQuery())
			.must(productAttributeQueryBuilder.keyword()
			.onField("lclCd")
			.matching(lcl)
			.createQuery())
			.createQuery();
		
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery
		  = fullTextEntityManager.createFullTextQuery(query, ProductAttribute.class);
		
		Pageable pageable = PageRequest.of(page, size);
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());
	
		//sorting
		org.apache.lucene.search.Sort sort = new Sort(new SortField(getSortField(sortBy), getSortFieldType(sortBy)));
		jpaQuery.setSort(sort);
		
		@SuppressWarnings("unchecked")
		List<ProductAttribute> results =  Collections.checkedList(jpaQuery.getResultList(), ProductAttribute.class);
		
		System.out.println("result size = " + results.size());
		for(ProductAttribute pa: results) {
			System.out.println(pa.getProductDesc());
		}
		
		List<ProductDTO> lp = results.stream().map(pa -> ProductDTOService.convertToProductDto(pa)).collect(Collectors.toList());

		Page<ProductDTO> pp = new PageImpl<ProductDTO>(lp, pageable, jpaQuery.getResultSize());
		return pp;
	}
	
	private String getSortField(String field) {
		switch(field) {
		case "description":
			return "productDesc";
		case "price":
			return "productRrp";
		case "productDesc":
			return "productDesc";
		case "productRrp":
			return "productRrp";
		default: 
			return "productDesc";
		}
	}
	
	private SortField.Type getSortFieldType(String field) {
		switch(field) {
		case "productDesc":
			return SortField.Type.STRING;
		case "description":
			return SortField.Type.STRING;
		case "price":
			return SortField.Type.DOUBLE;
		case "productRrp":
			return SortField.Type.DOUBLE;
		default: 
			return SortField.Type.STRING;
		}
	}

	
}
