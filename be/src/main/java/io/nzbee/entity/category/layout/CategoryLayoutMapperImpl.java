package io.nzbee.entity.category.layout;

import io.nzbee.domain.category.LayoutCategory;

public class CategoryLayoutMapperImpl implements ICategoryLayoutMapper {

	@Override
	public LayoutCategory entityToDo(CategoryLayout e) {
		return new LayoutCategory(
				e.getCategoryCode(),
				e.getCategoryAttribute().getCategoryDesc(),
				e.getCategoryLevel(),
				e.getLocale(), 
				e.getCurrency(),
				e.getObjectCount(),
				e.getOrderNumber()
				);
		
	}

}
