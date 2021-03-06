package io.javabrains.springbootstarter.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


public class RoleCustomerSpecs {


	  public static Specification<RoleCustomer> byCustomerNumber(String customerId) {
		    return new Specification<RoleCustomer>() {
		      public Predicate toPredicate(Root<RoleCustomer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		    	
		    	  return builder.equal(root.get("CustomerNumber"), customerId);
		    	 
		      }
		    };
		  }
	
}
