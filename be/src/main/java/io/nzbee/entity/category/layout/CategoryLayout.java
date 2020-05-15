package io.nzbee.entity.category.layout;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.entity.category.Category;

@Entity
@Table(name = "category_layout", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
@DiscriminatorValue("4")
@JsonTypeName("categorylayout")
public class CategoryLayout  extends Category {

	@Transient
	private int layoutCount;
	
	
	@Field(analyze = Analyze.NO, store=Store.YES)
	@Column(name="cat_ord_num")
	private Long orderNumber;

	
	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	@JsonIgnore
	public String getType() {
		return this.getClass().getSimpleName().toLowerCase();
	}

	@Override
	public void setType(String type) {
		
	}

	@Override
	public String getCode() {
		return this.getCategoryCode();
	}

	@Override
	public String getDesc() {
		return this.getCategoryAttribute().getCategoryDesc();
	}

	@Override
	public int getCount() {
		return this.getObjectCount();
	}

	@Override
	public boolean isHierarchical() {
		return false;
	}

	@Override
	public int getObjectCount() {
		
		return 0;
	}

	@Override
	public void setObjectCount(int count) {
		this.layoutCount = count;
		
	}
	
	
}
