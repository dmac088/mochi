package io.nzbee.entity.layout;

import io.nzbee.entity.IMapper;

public interface ILayoutMapper extends IMapper<Layout, io.nzbee.entity.layout.Layout> {

	Layout entityToDo(io.nzbee.entity.layout.Layout e);

	Layout entityToDo(io.nzbee.entity.layout.Layout e, String locale, String currency);
}
