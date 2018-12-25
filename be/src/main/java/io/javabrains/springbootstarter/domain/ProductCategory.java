package io.javabrains.springbootstarter.domain;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cateogry", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
public class ProductCategory {

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(						//the table that manages the many to many relationship
    								name = "product_category", schema="mochi", 
    								joinColumns 		= @JoinColumn(name = "cat_id"), 
    								inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
	@OrderBy
    @JsonIgnore
    private Collection<Product> products;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_id")
	private Long categoryId;

	@Column(name="cat_cd")
	private String categoryCode;
	
	@Column(name="cat_sht_desc")
	private Date categoryDesc;
	
	public Long getCategoryId() {
		return categoryId;
	}
	
	public Collection<Product> getProducts() {
		return products;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Date getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(Date categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
}
