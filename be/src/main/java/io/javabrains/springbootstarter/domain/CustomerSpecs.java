package io.javabrains.springbootstarter.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


public class CustomerSpecs {


	  public static Specification<Customer> byCustomerID(String customerId) {
		    return new Specification<Customer>() {
		      public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		    	
		    	  return builder.equal(root.get("CustomerId"), customerId);
		    	 
		      }
		    };
		  }
	
}
