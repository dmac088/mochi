//package io.nzbee.entity.adapters;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//import io.nzbee.domain.layout.Layout;
//import io.nzbee.domain.ports.ILayoutPortService;
//import io.nzbee.entity.layout.ILayoutMapper;
//import io.nzbee.entity.layout.ILayoutService;
//
//@Component
//public class PostgresLayoutAdapter implements ILayoutPortService {
//
//	@Autowired 
//	private ILayoutService layoutService;
//
//	@Autowired
//	@Qualifier(value = "layoutMapper")
//	private ILayoutMapper layoutMapper;
//	
//	@Override
//	public void save(Layout object) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(Layout object) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Layout findByCode(String layoutCode) {
//		// TODO Auto-generated method stub
//		return null;
//		//return layoutService.findByCode(layoutCode).get();
//	}
//
//
//}
