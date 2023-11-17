package edu.vassar.cmpu203.lunchbox.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class PriceFilterTest {

    /**
     * Tests filtering by price option
     */
    @Test
    public void testFilter() {
        Restaurant r1 = new Restaurant("restaurant1", "Restaurant 1", 3.0f, "Address 1", "City 1", "State 1", "Country 1", "11111", 30.0f, -90.0f, new ArrayList<>(Arrays.asList("review1")), 1); // 0 miles away
        Restaurant r2 = new Restaurant("restaurant2", "Restaurant 2", 4.0f, "Address 2", "City 2", "State 2", "Country 2", "22222", 31.0f, -91.0f, new ArrayList<>(Arrays.asList("review2")), 2); // 147 miles away
        Restaurant r3 = new Restaurant("restaurant3", "Restaurant 3", 4.5f, "Address 3", "City 3", "State 3", "Country 3", "33333", 32.0f, -92.0f, new ArrayList<>(Arrays.asList("review3")), 3); // 293 miles away
        Collection<Restaurant> allRestaurants = new ArrayList<>(Arrays.asList(r1, r2, r3));

        PriceFilter priceFilter = new PriceFilter("$$");

        // filter the restaurants using the location filter
        Collection<Restaurant> filteredRestaurants = priceFilter.filter(allRestaurants);

        // check whether filtered correctly
        assertFalse(filteredRestaurants.contains(r1));
        assertTrue(filteredRestaurants.contains(r2));
        assertFalse(filteredRestaurants.contains(r3));
    }
}
