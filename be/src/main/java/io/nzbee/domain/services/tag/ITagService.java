package io.nzbee.domain.services.tag;

import java.util.List;

import io.nzbee.domain.Tag;
import io.nzbee.domain.services.IService;
import io.nzbee.ui.component.web.sidebar.Sidebar;

public interface ITagService extends IService<Tag> {

	//returns a list domain object
	List<Tag> findAll(String locale, String currency, String categoryDesc, List<Sidebar> selectedFacets);
	
	//returns a list of domain objects
	List<Tag> findAll(String locale, String currency, String categoryDesc, Double price, List<Sidebar> selectedFacets);
}
