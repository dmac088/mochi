package io.javabrains.springbootstarter.domain;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecs {

	  public static Specification<Product> byProductLclCd(String lcl) {
		    return new Specification<Product>() {
		      public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
		            CriteriaBuilder builder) {
		    	 // return builder.equal(root.get("productUPC"), "12345678");
		    	  return builder.equal(root.get("lclCd"), lcl);
		    	 
		      }
		    };
		  }
	
}
