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

    }

    @Test
    public void testComputeRating(){

    }
}
