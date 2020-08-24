package io.nzbee.entity.bag;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "bag_item", schema = "mochi")
public class BagItem {

	@ManyToOne
	private Bag bag;
	
	
	
}
