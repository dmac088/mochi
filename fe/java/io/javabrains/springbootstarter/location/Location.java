//package io.javabrains.springbootstarter.location;
//
//import io.javabrains.springbootstarter.thing.Thing;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Transient;
//
////@Entity
//public class Location extends Thing {
//	
//
//	private String name;
//	private String type;
//	private Double lat;
//	private Double lng;
//	
//	//@Transient
//	private double dist;
//	
//	public Location() {
//		super();
//	}
//	
//	public Location(int id) {
//		super(id);
//	}
//	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	
//
//	
//	public Double getLat() {
//		return lat;
//	}
//	public void setLat(Double lat) {
//		this.lat = lat;
//	}
//	
//	public Double getLng() {
//		return lng;
//	}
//	
//	public void setLng(Double lng) {
//		this.lng = lng;
//	}
//	
//	public void setDist(double mylat, double mylng) {
//		dist = distTo(mylat, mylng, this.lat, this.lng, 'K');
//	}
//	
//	public double getDist() {
//		return dist;
//	}
//	
//	public static Double distTo(double mylat, double mylng, double poiLat, double poiLng, char unit) {
//		double theta = mylng - poiLng;
//	      double dist = Math.sin(deg2rad(mylat)) * Math.sin(deg2rad(poiLat)) + Math.cos(deg2rad(mylat)) * Math.cos(deg2rad(poiLat)) * Math.cos(deg2rad(theta));
//	      dist = Math.acos(dist);
//	      dist = rad2deg(dist);
//	      dist = dist * 60 * 1.1515;
//	      if (unit == 'K') {
//	        dist = dist * 1.609344;
//	      } else if (unit == 'N') {
//	        dist = dist * 0.8684;
//	        }
//	      return (dist);
//	}
//	
//	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
//    /*::  This function converts decimal degrees to radians             :*/
//    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
//    private static double deg2rad(double deg) {
//      return (deg * Math.PI / 180.0);
//    }
//
//    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
//    /*::  This function converts radians to decimal degrees             :*/
//    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
//    private static double rad2deg(double rad) {
//      return (rad * 180.0 / Math.PI);
//    }
//	
//	
//	
//}
