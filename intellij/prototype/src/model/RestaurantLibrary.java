
package model;
import java.util.HashSet;
import java.util.ArrayList;

public class RestaurantLibrary {
    public ArrayList<Restaurant> allRestaurants = new ArrayList<Restaurant>();

    public RestaurantLibrary search(String term, HashSet<IFilter> filters, String sort) {
        // Implement search logic here, for now, return an empty list
        return new RestaurantLibrary();
    }

    public String toString(){
        // needs implementation of how to print the array of restaurants
        return "";
    }

    public Review postReview(User user, Restaurant restaurant, float rating, String reviewText) {
        // Implement review posting logic here, for now, return a dummy review
        return new Review();
    }
}

//package model;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import Restaurant;
//
//public class RestaurantLibrary {
//
//    private HashMap<String, Restaurant> data;
//
//    public RestaurantLibrary() {
//        this.data = new HashMap<>();
//    }
//
//    // Method to add a restaurant to the library
//    public void addRestaurant(Restaurant restaurant) {
//        this.data.put(restaurant.restaurantId, restaurant);
//    }
//
//    // Method to get a restaurant by its ID
//    public Restaurant getRestaurant(String restaurantId) {
//        return this.data.get(restaurantId);
//    }
//
//    // Other utility methods can be added as needed
//
//    public HashMap<String, Restaurant> getAllRestaurants() {
//        return this.data;
//    }
//
//    public ArrayList<Restaurant> search(String criteria) {
//        // Implement search logic here, for now, return an empty list
//        return new ArrayList<Restaurant>();
//    }
//
//    public Review postReview(User user, Restaurant restaurant, float rating, String reviewText) {
//        // Implement review posting logic here, for now, return a dummy review
//        return new Review();
//    }
//}
