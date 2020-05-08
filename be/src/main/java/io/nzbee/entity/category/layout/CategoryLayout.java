package io.nzbee.entity.category.layout;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.layout.Layout;

@Entity
@Table(name = "layout_category", schema = "mochi")
public class CategoryLayout {

	@EmbeddedId
    private CategoryLayoutId id = new CategoryLayoutId();
 
    @ManyToOne
    @MapsId("categoryId")
    private Category category;
 
    @ManyToOne
    @MapsId("layoutId")
    private Layout layout;

    @Column(name="ord_num")
    private Long order;

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}
}
