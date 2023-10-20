package model;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantLibrary {

    private HashMap<String, Restaurant> data;

    public RestaurantLibrary() {
        this.data = new HashMap<>();
    }

    // Method to add a restaurant to the library
    public void addRestaurant(Restaurant restaurant) {
        this.data.put(restaurant.restaurantId, restaurant);
    }

    // Method to get a restaurant by its ID
    public Restaurant getRestaurant(String restaurantId) {
        return this.data.get(restaurantId);
    }

    // Other utility methods can be added as needed

    public HashMap<String, Restaurant> getAllRestaurants() {
        return this.data;
    }

    public ArrayList<Restaurant> search(String criteria) {
        // Implement search logic here, for now, return an empty list
        return new ArrayList<Restaurant>();
    }

    public Review postReview(User user, Restaurant restaurant, float rating, String reviewText) {
        // Implement review posting logic here, for now, return a dummy review
        return new Review();
    }
}
