package io.nzbee.entity.ports;

import java.util.Set;
import io.nzbee.domain.category.Category;

public interface ICategoryPortService  extends IPortService<Category> {

	Set<Category> findAllForLevel(String locale, String currency, Long level);

	Set<Category> findByParent(String parentCategoryCode, String currency, String locale);

	

}
