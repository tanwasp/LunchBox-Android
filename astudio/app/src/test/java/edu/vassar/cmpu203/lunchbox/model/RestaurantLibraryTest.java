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
        RestaurantLibrary restaurantLibrary = new RestaurantLibrary();

        restaurantLibrary.addReviewToRest("restaurant1", reviewId);

        // Retrieve the restaurant and check if the review is added to its review list
        Restaurant restaurant = restaurantLibrary.getRestaurant(restaurantId);
        assertNotNull(restaurant);
        assertTrue(restaurant.getReviewList().contains(reviewId));
    }

    @Test
    public void testSearch() {
        RestaurantLibrary restaurantLibrary = new RestaurantLibrary();

        // Create a sample user for distance calculations
        User testUser = new User("testuser", "Test User");

        // Search for restaurants with a specific term and no filters
        String searchTerm = "Bakehouse";
        Set<IFilter> filters = new HashSet<>();
        String sort = "prox";
        ArrayList<Restaurant> searchResults = restaurantLibrary.search(searchTerm, filters, sort, testUser);

        // Assert that search results are not null and contain the expected restaurant
        assertNotNull(searchResults);
        assertFalse(searchResults.isEmpty());

        boolean found = false;
        for (Restaurant restaurant : searchResults) {
            if (restaurant.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }
}
