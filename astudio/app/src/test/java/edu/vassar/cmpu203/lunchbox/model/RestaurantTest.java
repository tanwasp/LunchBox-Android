package edu.vassar.cmpu203.lunchbox.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class RestaurantTest {
User testUser;
Location loc;
Restaurant r;
RestaurantLibrary lib;
ReviewsLibrary revLib;

    /**
     * Sets up the libraries for use in the tests
     */
    public Restaurant setupDatabase(){
        testUser = new User("default", 30f, -90f);
        loc = new Location(40.7128f, -74.006f);
        lib = new RestaurantLibrary();
        revLib = new ReviewsLibrary();

        r = new Restaurant("restaurant1", "Moreno Bakery", 3.25f, "737 W Brandon Blvd", "Brandon", "FL", "US", "33511", 40.7128f, -74.006f, new ArrayList<>(Arrays.asList("review2", "review21", "review37", "review62", "review64")), 2);

        float floatLat = Float.parseFloat("123");
        float floatLon = Float.parseFloat("123");
        Restaurant r = lib.addRestaurant("Gordon Commons", "124 Raymond Ave", "Poughkeepsie", "NY", "India", "12604", floatLat, floatLon);


        revLib.addReview(testUser, r.getRestaurantId(), 3.0f, "Okay", 1, "Gordon Commons");
        revLib.addReview(testUser, r.getRestaurantId(), 4.0f, "Good", 1, "Gordon Commons");
        revLib.addReview(testUser, r.getRestaurantId(), 5.0f, "Excellent", 3, "Gordon Commons");
        return r;

    }

    /**
     * Test calculating restaurant's distance to the user
     */
    @Test
    public void testSetDistToUser(){
        testUser = new User("default", 30f, -90f);
        loc = new Location(40.7128f, -74.006f);
        r = new Restaurant("restaurant1", "Moreno Bakery", 3.25f, "737 W Brandon Blvd", "Brandon", "FL", "US", "33511", 40.7128f, -74.006f, new ArrayList<>(Arrays.asList("review2", "review21", "review37", "review62", "review64")), 2);

        // find distance between locations
        float expected = loc.haversine(testUser.getLoc());
        r.setDistToUser(testUser);

        // test correct distance was assigned to the restaurant's field
        assertEquals(expected, r.distanceToUser, 0.05f);

        r = new Restaurant("restaurant2", "Charlie's Pizza", 4f, "737 W Brandon Blvd", "Brandon", "FL", "US", "33511", 30f, -90f, new ArrayList<>(Arrays.asList("review2", "review21", "review37", "review62", "review64")), 2);
        r.setDistToUser(testUser);

        // test distance to a resraurant with the same coordinates is 0
        assertEquals(0.0f, r.distanceToUser, 0.05f);
    }

    /**
     * Test calculating all restaurants' price tag
     */
    @Test
    public void testComputePriceRange(){
        Restaurant restaurant = setupDatabase();

        // compute the price assignment for restaurant
        restaurant.computePriceRange(revLib);

        // check if the correct price category is computed
        assertEquals(2, restaurant.getPriceRangeDisplay());

        // add new reviews and recalculate
        revLib.addReview(testUser, r.getRestaurantId(), 2.0f, "Pretty Bad", 1, "Gordon Commons");
        revLib.addReview(testUser, r.getRestaurantId(), 4.0f, "", 1, "Gordon Commons");

        restaurant.computePriceRange(revLib);
        assertEquals(1, restaurant.getPriceRangeDisplay());
    }

    /**
     * Test calculating all restaurants' rating
     */
    @Test
    public void testComputeRating(){
        Restaurant restaurant = setupDatabase();

        // compute the rating
        restaurant.computeRating(revLib);

        // check if the correct rating is computed
        assertEquals(4.0f, restaurant.getRating(), 0.01f);

        // add a new review and recalculate
        revLib.addReview(testUser, r.getRestaurantId(), 2.0f, "Pretty Bad", 1, "Gordon Commons");

        restaurant.computeRating(revLib);
        assertEquals(3.5f, restaurant.getRating(), 0.01f);
    }
}
