
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
    public int postalCode;
    public float lat;
    public float lon;
    public ArrayList<String> reviewList = new ArrayList<>();
    public int priceRange;

    public Restaurant(String restaurantId, String name, float rating, String address, String city, String state, String country, int postalCode, float lat, float lon, ArrayList<String> reviewList, int priceRange) {
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

    public float distToUser(){
        return -1;
    }

    // Constructor, getters, setters, and other methods can be added as needed
}
