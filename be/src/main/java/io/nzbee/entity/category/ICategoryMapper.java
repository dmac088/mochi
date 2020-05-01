package io.nzbee.entity.category;

import io.nzbee.entity.IMapper;
import java.util.Optional;

import io.nzbee.domain.category.Category;

public interface ICategoryMapper extends IMapper<Category, io.nzbee.entity.category.Category> {

	Optional<Category> entityToDo(Optional<?> optional, String locale, String currency);

	
	
}
