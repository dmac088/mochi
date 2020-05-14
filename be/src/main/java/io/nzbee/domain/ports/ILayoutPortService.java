package io.nzbee.domain.ports;

import io.nzbee.domain.IService;
import io.nzbee.domain.layout.Layout;

public interface ILayoutPortService  extends IService<Layout> {

	Layout findByCode(String layoutCode);
	
}
