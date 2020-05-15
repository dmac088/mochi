package io.nzbee.entity.category.layout;

import io.nzbee.domain.category.LayoutCategory;
import io.nzbee.entity.IMapper;

public interface ICategoryLayoutMapper extends IMapper<LayoutCategory, io.nzbee.entity.category.layout.CategoryLayout> { 

	LayoutCategory entityToDo(io.nzbee.entity.category.layout.CategoryLayout e);
	
}
