package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class Restaurant {
    public String restaurantId;
    public String name;
    public Location location;
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

    // Constructor, getters, setters, and other methods can be added as needed
}
