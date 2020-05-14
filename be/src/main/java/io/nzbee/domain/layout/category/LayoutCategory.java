package io.nzbee.domain.layout.category;

import java.util.ArrayList;
import java.util.List;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.layout.Layout;

public class LayoutCategory extends Layout {
	
	private List<Category> categories;
	
	public LayoutCategory(String layoutCode, String layoutDesc, Category category) {
		super(layoutCode, layoutDesc);
		categories = new ArrayList<Category>();
		categories.add(category);
	}

	public void addCategory(Category category) {
		this.categories.add(category);
	}
}

