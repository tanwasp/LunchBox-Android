package edu.vassar.cmpu203.lunchbox.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class RestaurantTest {

    @Test
    public void testSetDistToUser(){
        User testUser = new User("default", 30f, -90f);
        Location loc = new Location(40.7128f, -74.006f);
        Restaurant r = new Restaurant("restaurant1", "Moreno Bakery", 3.25f, "737 W Brandon Blvd", "Brandon", "FL", "US", "33511", 40.7128f, -74.006f, new ArrayList<>(Arrays.asList("review2", "review21", "review37", "review62", "review64")), 2);

        // find distance between locations
        float expected = loc.haversine(testUser.getLoc());
        r.setDistToUser(testUser);

        // test correct distance was assigned to the restaurant's field
        assertEquals(expected, r.distanceToUser, 0.05f);
    }

    @Test
    public void testComputePriceRange(){
        ReviewsLibrary revLib = new ReviewsLibrary();
        User testUser = new User("default", 30f, -90f);

        Restaurant r = new Restaurant("restaurant0", "Moreno Bakery", 3.25f, "737 W Brandon Blvd", "Brandon", "FL", "US", "33511", 40.7128f, -74.006f, new ArrayList<>(Arrays.asList()), 2);

        // add reviews to restaurant
        revLib.addReview(testUser, "restaurant0", 3.0f, "Okay", 1);
        revLib.addReview(testUser, "restaurant0", 4.0f, "Good", 2);
        revLib.addReview(testUser, "restaurant0", 5.0f, "Excellent", 3);

        // compute the price assignment for restaurant
        r.computePriceRange(revLib);

        // check if the correct price category is computed
        assertEquals(2, r.getPriceRange());
    }

    @Test
    public void testComputeRating(){
        ReviewsLibrary revLib = new ReviewsLibrary();
        User testUser = new User("default", 30f, -90f);

        Restaurant r = new Restaurant("restaurant0", "Moreno Bakery", 3.25f, "737 W Brandon Blvd", "Brandon", "FL", "US", "33511", 40.7128f, -74.006f, new ArrayList<>(Arrays.asList()), 2);

        // add reviews to restaurant
        revLib.addReview(testUser, "restaurant0", 3.0f, "Okay", 1);
        revLib.addReview(testUser, "restaurant0", 4.0f, "Good", 2);
        revLib.addReview(testUser, "restaurant0", 5.0f, "Excellent", 3);

        // compute the rating
        r.computeRating(revLib);

        // check if the correct rating is computed
        assertEquals(4.0f, r.getRating(), 0.01f);
    }
}
