package edu.vassar.cmpu203.lunchbox.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RestaurantLibraryTest {

    /**
     * Tests class's ability to add a new restaurant
     */
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

    /**
     * Tests class's ability search restaurants and return only those matching the given criteria
     */
    @Test
    public void testSearch() {
        RestaurantLibrary restaurantLibrary = new RestaurantLibrary();

        // create a sample user
        User testUser = new User("default", 30, -90);

        // search for restaurants with a specific term and no filters
        String searchTerm = "Bakehouse";
        Set<IFilter> filters = new HashSet<>();
        String sort = "prox";
        ArrayList<Restaurant> searchResults = restaurantLibrary.search(searchTerm, filters, sort, testUser);

        // assert that search results contain the expected restaurant
        assertTrue(searchResults.size() == 1);
        boolean match = true;
        for (int i = 0; i < searchResults.size(); i++){
            if (searchResults.get(i).getName().contains("Bakehouse") == false){
                match = false;
            }
        }
        assertTrue(match);

        // search for restaurants with a no specific term but apply filters
        searchTerm = "";
        PriceFilter pf = new PriceFilter("$$");
        LocFilter lf = new LocFilter(500, testUser);
        filters = new HashSet<IFilter>();
        filters.add(pf);
        filters.add(lf);
        sort = "rating";
        ArrayList<Restaurant> searchResults2 = restaurantLibrary.search(searchTerm, filters, sort, testUser);

        assertTrue(searchResults2.size() == 4); // there are 4 restaurants within 500 miles with a $$ price tag
        match = true;
        for (int i = 0; i < searchResults2.size(); i++){
            if (searchResults2.get(i).getPriceRange() != 2 || searchResults2.get(i).distanceToUser > 500){
                match = false;
            }
        }
        assertTrue(match);

    }
}
