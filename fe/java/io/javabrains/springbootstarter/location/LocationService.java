//package io.javabrains.springbootstarter.location;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
////@Service
//public class LocationService {
//
//	//@Autowired
//	private LocationRepository poiRepository; 
//	
//	
//	public List<Location> getAllPois(double mylat, double mylng) {
//		List<Location> Pois = new ArrayList<>();
//		  
//		for(Iterator<Location> it = poiRepository.findAll().iterator(); it.hasNext();) {
//			  Location currPoi = it.next();
//			  currPoi.setDist(mylat, mylng);
//		}
//		return Pois;
//	}
//	
//	public List<Location> getAllPois() {
//		List<Location> Pois = new ArrayList<>();
//		  poiRepository.findAll()
//		  .forEach(Pois::add);
//		  return Pois;
//	}
//	
//	public Location getPoi(String id) {
//		return poiRepository.findOne(id);
//	}
//	
//	public void addPoi(Location Poi) {
//		poiRepository.save(Poi);
//	}
//	
//	public void updatePoi(String id, Location Poi) {
//		poiRepository.save(Poi);
//	}
//	
//	public void deletePoi(String id) {
//		poiRepository.delete(id);
//	}
//	
//}
