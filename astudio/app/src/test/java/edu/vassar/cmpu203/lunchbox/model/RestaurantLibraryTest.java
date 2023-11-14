package edu.vassar.cmpu203.lunchbox.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RestaurantLibraryTest {

    @Test
    public void testAddReviewToRest() {
        RestaurantLibrary restLib = new RestaurantLibrary();
        ReviewsLibrary revLib = new ReviewsLibrary();
        User testUser = new User("default", 30, -90);

        String reviewId = revLib.addReview(testUser, "restaurant1", 1.0f, "Bad", 1);
        restLib.addReviewToRest("restaurant1", reviewId);

        // Retrieve the restaurant and check if the review is added to its review list
        Restaurant restaurant = restLib.getRestaurant("restaurant1");
        assertTrue(restaurant.getReviewList().contains(reviewId));
    }

    @Test
    public void testAddRestaurant() {
        RestaurantLibrary restLib = new RestaurantLibrary();
        int currSize = restLib.getNumRestaurants();

        Restaurant addedRest = restLib.addRestaurant("name", "address", "city", "state", "country", "00000", 0.0f, 0.0f);

        //test return value
        assertEquals(0.0, addedRest.getRating(), 0.01f);
        assertEquals("name", addedRest.getName());
        assertEquals("address", addedRest.getAddress());
        assertEquals(0, addedRest.getPriceRange());
        //test side effects
        assertEquals(currSize+1, restLib.getNumRestaurants());
        assertNotNull(restLib.getRestaurant(addedRest.getRestaurantId()));

    }

    @Test
    public void testSearch() {
        RestaurantLibrary restaurantLibrary = new RestaurantLibrary();

        // create a sample user
        User testUser = new User("default", 30, -90);

        // Search for restaurants with a specific term and no filters
        String searchTerm = "Bakehouse";
        Set<IFilter> filters = new HashSet<>();
        String sort = "prox";
        ArrayList<Restaurant> searchResults = restaurantLibrary.search(searchTerm, filters, sort, testUser);

        // Assert that search results are not null and contain the expected restaurant
        assertTrue(searchResults.size() == 1);
        boolean match = true;
        for (int i = 0; i < searchResults.size(); i++){
            if (searchResults.get(i).getName().contains("Bakehouse") == false){
                match = false;
            }
        }
        assertTrue(match);

    }
}
