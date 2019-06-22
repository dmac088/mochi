package io.nzbee.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import io.nzbee.entity.Category;
import io.nzbee.entity.Product;


@Repository
public class ProductDAO {

	EntityManager em;
	
	public Page<Product> findByCategoryIdInAndLclCdAndPriceBetweenAndPricesTypeAndCurrencyCodeAndPriceStartAndPriceEndAndBrandIdIn(List<Long> categoryIds, String productlcl, String brandlcl, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		//Product_.
		
		Root<Product> product = cq.from(Product.class);
//		Join<Product, Category> category = product.join(Product.class..categories);
//		Predicate categoryIdPredicate = cb.equal(product.join("").get("author"), authorName);
//        Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
		
		return null;
    }
	
}
