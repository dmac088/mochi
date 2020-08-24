package io.nzbee.entity.bag;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import io.nzbee.entity.bag.item.BagItem;
import io.nzbee.entity.party.Party;

@Entity
@Table(name = "bag", schema = "mochi")
public class Bag {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bag_id")
	private Long bagId;
	
	@OneToOne
	@JoinColumn(name="pty_id")
	private Party party;

	@OneToMany(	mappedBy="bag",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<BagItem> bagItems;

	public Long getBagId() {
		return bagId;
	}
	
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Set<BagItem> getBagItems() {
		return bagItems;
	}

	public void setBagItems(Set<BagItem> bagItems) {
		this.bagItems = bagItems;
	}
	
	public void addItem(BagItem bi) {
		this.getBagItems().add(bi);
	}
	
	public void removeItem(BagItem bi) {
		this.getBagItems().remove(bi);
	}
	
}
