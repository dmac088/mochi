//package io.javabrains.springbootstarter.location;
//
//import java.util.Iterator;
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.json.JSONObject;
//import org.json.JSONArray;
//import org.json.simple.parser.JSONParser;
//
//
////@Controller
//public class LocationController {
//	
//	private double myLat, myLng;
//	private String myExp;
//	
//	//@Autowired
//	private LocationService poiService;	
//	
//	//@RequestMapping("/slidebars")
//    public String getSlidebars(HttpSession session) {
//		System.out.println("calling getSlidebars");
//        return "slidebars";
//    }
//	
//	//@RequestMapping("/Pois")
//    public String getAllPois(HttpSession session) {
//		System.out.println("calling getAllPois");
//		List<Location> Pois = poiService.getAllPois();
//		for(Iterator<Location> itr = Pois.iterator(); itr.hasNext();) {
//			Location p = itr.next();
//			p.setDist(this.myLat, this.myLng);
//		}
//		
//		JSONArray jArray = new JSONArray(Pois);
//        session.setAttribute("mySessionAttribute", jArray);
//        return "Pois";
//    }	
//	
//	//@ResponseBody
//	//@RequestMapping("/Pois/{id}")
//	public Location getPoi(@PathVariable String id) {
//		System.out.println("calling getPoi");
//		return poiService.getPoi(id);
//	}
//	
//	//we actually want this method to return a JSON string
//	//@ResponseBody
//	//@RequestMapping(method=RequestMethod.POST, value="/Pois")
//	public List<Location> addCurLoc(@RequestBody String jsonArray) {
//		System.out.println("calling addCurLoc");
//		org.json.simple.JSONObject json = null;
//		List<Location> Pois = null;
//		try {
//		JSONParser parser = new JSONParser();
//		json = (org.json.simple.JSONObject)parser.parse(jsonArray);
//		
//		Pois = poiService.getAllPois();
//		for(Iterator<Location> itr = Pois.iterator(); itr.hasNext();) {
//			Location p = itr.next();
//			p.setDist((double)json.get("lat"), (double)json.get("lng"));
//		}
//		
//		//System.out.println("Posted values: " + myLat + " " + myLng);
//		} catch (Exception e) {
//			System.err.print(e.toString());
//		}
//		return Pois;
//	}
//	
//	//@ResponseBody
//	//@RequestMapping(method=RequestMethod.PUT, value="/Pois/{id}")
//	public void updatePoi(@RequestBody Location Poi, @PathVariable String id) {
//		System.out.println("calling updatePoi");
////		poiService.updatePoi(id, Poi);
//	}
//
//	//@ResponseBody
//	@RequestMapping(method=RequestMethod.DELETE,value="/Pois/{id}")
//	public void deletePoi(@PathVariable String id) {
//		System.out.println("calling deletePoi");
//		poiService.deletePoi(id);
//	}
//	
//}
