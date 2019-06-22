package io.nzbee.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jayway.jsonpath.Criteria;

import io.nzbee.entity.Category;
import io.nzbee.entity.CategoryAttribute;
import io.nzbee.entity.CategoryAttribute_;
import io.nzbee.entity.Category_;
import io.nzbee.entity.Product;
import io.nzbee.entity.Product_;


@Repository
public class ProductDAO {

	EntityManager em;
	
	public Page<Product> findByCategoryIdInAndLclCdAndPriceBetweenAndPricesTypeAndCurrencyCodeAndPriceStartAndPriceEndAndBrandIdIn(List<Long> categoryIds, String productlcl, String brandlcl, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Category, CategoryAttribute> category = root.join(Product_.categories).join(Category_.attributes);
		
		
		cq.select(root).where(cb.equal(category.get(CategoryAttribute_.categoryDesc), "Fruit"));
		
		Query<Product> query = (Query<Product>) em.createQuery(cq);
        //Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");

		return new PageImpl<Product>(query.getResultList(), pageable, query.getResultList().size());
		
		
    }
	
}
