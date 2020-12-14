package io.nzbee.entity.product.basic;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

import io.nzbee.entity.product.ProductEntity;

@Indexed
@Entity
@Table(name = "product_basic", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")
@DiscriminatorValue("2")
public class PhysicalProductEntity extends ProductEntity  {

	@Column(name="")
	private int widthDimension;
	
	private int lengthDimension;
	
	private int heightDimension;
	
	private int weightDimension;

	public int getWidthDimension() {
		return widthDimension;
	}

	public void setWidthDimension(int widthDimension) {
		this.widthDimension = widthDimension;
	}

	public int getLengthDimension() {
		return lengthDimension;
	}

	public void setLengthDimension(int lengthDimension) {
		this.lengthDimension = lengthDimension;
	}

	public int getHeightDimension() {
		return heightDimension;
	}

	public void setHeightDimension(int heightDimension) {
		this.heightDimension = heightDimension;
	}

	public int getWeightDimension() {
		return weightDimension;
	}

	public void setWeightDimension(int weightDimension) {
		this.weightDimension = weightDimension;
	}
	
}
