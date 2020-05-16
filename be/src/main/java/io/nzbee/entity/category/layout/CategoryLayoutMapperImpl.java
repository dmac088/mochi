package io.nzbee.entity.category.layout;

import org.springframework.stereotype.Component;

import io.nzbee.domain.category.LayoutCategory;

@Component
public class CategoryLayoutMapperImpl implements ICategoryLayoutMapper {

	@Override
	public LayoutCategory entityToDo(CategoryLayout e) {
		return new LayoutCategory(
				e.getCategoryCode(),
				e.getCategoryAttribute().getCategoryDesc(),
				e.getCategoryLevel(),
				e.getLocale(), 
				e.getCurrency(),
				e.getObjectCount()
				);
		
	}

	@Override
	public CategoryLayout doToEntity(LayoutCategory d) {
		// TODO Auto-generated method stub
		return null;
	}

}
