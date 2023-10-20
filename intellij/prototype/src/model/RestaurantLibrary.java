package model;

import java.util.ArrayList;

public class RestaurantLibrary {
    public ArrayList<Restaurant> allRestaurants = new ArrayList<Restaurant>();

    public ArrayList<Restaurant> search(String criteria) {
        // Implement search logic here, for now, return an empty list
        return new ArrayList<Restaurant>();
    }



    public Review postReview(User user, Restaurant restaurant, float rating, String reviewText) {
        // Implement review posting logic here, for now, return a dummy review
        return new Review();
    }
}
