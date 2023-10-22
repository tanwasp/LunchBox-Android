package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class Restaurant {
    public String restaurantId;
    public String name;
    public float rating;
    public String address;
    public String city;
    public String state;
    public String country;
    public String postalCode;
    public float lat;
    public float lon;
    public ArrayList<String> reviewList = new ArrayList<>();
    public int priceRange;

    public Restaurant(String restaurantId, String name, float rating, String address, String city, String state, String country, String postalCode, float lat, float lon, ArrayList<String> reviewList, int priceRange) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.lat = lat;
        this.lon = lon;
        this.reviewList = reviewList;
        this.priceRange = priceRange;
    }

    public float distToUser(User u){
        return haversine(u.lat, u.lon, this.lat, this.lon);
    }

    // help source: https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
    public float haversine(float long1, float lat1, float long2, float lat2){
        double earthRadius = 6371.0;

        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double long1Rad = Math.toRadians(long1);
        double lat2Rad = Math.toRadians(lat2);
        double long2Rad = Math.toRadians(long2);

        // Haversine formula
        double dlong = long2Rad - long1Rad;
        double dlat = lat2Rad - lat1Rad;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dlong / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return (float) distance;
    }

    // Constructor, getters, setters, and other methods can be added as needed
}