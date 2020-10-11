package io.nzbee.entity.bag;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@JsonIgnore
	private Party party;

	@OneToMany(	mappedBy="bag",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<BagItem> bagItems = new HashSet<BagItem>();
		
	@Column(name="bag_crd_dt")
	private LocalDateTime bagCreatedDateTime;

	@Column(name="bag_upd_dt")
	private LocalDateTime bagUpdatedDateTime;

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
		bi.setBag(this);
	}
	
	public void removeItem(BagItem bi) {
		this.getBagItems().remove(bi);
		bi.setBag(null);
	}
	
	public LocalDateTime getBagCreatedDateTime() {
		return bagCreatedDateTime;
	}

	public void setBagCreatedDateTime(LocalDateTime bagCreatedDateTime) {
		this.bagCreatedDateTime = bagCreatedDateTime;
	}

	public LocalDateTime getBagUpdatedDateTime() {
		return bagUpdatedDateTime;
	}

	public void setBagUpdatedDateTime(LocalDateTime bagUpdatedDateTime) {
		this.bagUpdatedDateTime = bagUpdatedDateTime;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bag)) return false;
        return bagId != null && bagId.equals(((Bag) o).getBagId());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getBagId());
    }
	
}
