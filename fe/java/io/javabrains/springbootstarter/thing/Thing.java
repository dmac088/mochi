package io.javabrains.springbootstarter.thing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Thing {

	@Id 
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "thing_generator")
	@SequenceGenerator(name = "thing_generator", sequenceName = "thing_id_seq")
	private int id;

	
	public Thing() {
		
	}
	
	public Thing(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
