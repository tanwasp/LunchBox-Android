package edu.vassar.cmpu203.lunchbox.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class RestaurantLibraryTest {

    /**
     * Tests class's ability to load in a list of restaurants into the library
     */
    @Test
    public void testLoadRestaurants() {
        // set up restaurants
        RestaurantLibrary restaurantLibrary = new RestaurantLibrary();
        Restaurant r1 = new Restaurant("restaurant1", "Moreno Bakery", 3.25f, "737 W Brandon Blvd", "Brandon", "FL", "US", "33511", 27.9361395886f, -82.2950403264f, new ArrayList<>(Arrays.asList("review2", "review21", "review37", "review62", "review64")), 2); // <500 mi away
        Restaurant r2 = new Restaurant("restaurant2", "Britt’s Bakehouse", 3.0f, "137 W Jefferson Ave", "Kirkwood", "MO", "US", "63122", 38.5825641f, -90.4079162f, new ArrayList<>(Arrays.asList("review11", "review30", "review32", "review52", "review58")), 1); // >500 mi away
        Restaurant r3 = new Restaurant("restaurant3", "The Maple Creek Country Club", 4.5f, "10501 E 21st St", "Indianapolis", "IN", "US", "46229", 39.7963819f, -85.9817333f, new ArrayList<>(Arrays.asList("review7", "review35", "review40", "review46")), 2); // >500 mi away
        Restaurant r4 = new Restaurant("restaurant4", "McDonald's", 1.5f, "709 Terry Pkwy", "Gretna", "LA", "US", "70056", 29.8998883024f, -90.0298833754f, new ArrayList<>(Arrays.asList("review12", "review23", "review25", "review41")), 1); // <500 mi away
        Restaurant r5 = new Restaurant("restaurant5", "Little Greek Restaurant", 2.625f, "19022 Bruce B Downs Blvd", "Tampa", "FL", "US", "33647", 28.1445589708f, -82.3546334163f, new ArrayList<>(Arrays.asList("review20", "review24", "review29", "review33")), 2); // <500 mi away
        List<Restaurant> list = new ArrayList<>(Arrays.asList(r1,r2,r3,r4,r5));
        //load into library
        restaurantLibrary.loadRestaurants(list);

        // test restaurants are loaded properly
        for (int i = 1; i<=5; i++){
            String key = "restaurant" + i;
            assertTrue(restaurantLibrary.getLib().containsKey(key));
        }
        assertEquals("Little Greek Restaurant",restaurantLibrary.getLib().get("restaurant5").getName());
    }

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
        assertEquals(0, addedRest.getPriceRangeDisplay());

        //test side effects
        assertEquals(currSize+1, restLib.getNumRestaurants());
        assertNotNull(restLib.getRestaurant(addedRest.getRestaurantId()));

    }

    /**
     * Tests class's ability search restaurants and return only those matching the given criteria
     */
    @Test
    public void testSearch() {
        // set up restaurants
        RestaurantLibrary restaurantLibrary = new RestaurantLibrary();
        Restaurant r1 = new Restaurant("restaurant1", "Moreno Bakery", 3.25f, "737 W Brandon Blvd", "Brandon", "FL", "US", "33511", 27.9361395886f, -82.2950403264f, new ArrayList<>(Arrays.asList("review2", "review21", "review37", "review62", "review64")), 2); // <500 mi away
        Restaurant r2 = new Restaurant("restaurant2", "Britt’s Bakehouse", 3.0f, "137 W Jefferson Ave", "Kirkwood", "MO", "US", "63122", 38.5825641f, -90.4079162f, new ArrayList<>(Arrays.asList("review11", "review30", "review32", "review52", "review58")), 1); // >500 mi away
        Restaurant r3 = new Restaurant("restaurant3", "The Maple Creek Country Club", 4.5f, "10501 E 21st St", "Indianapolis", "IN", "US", "46229", 39.7963819f, -85.9817333f, new ArrayList<>(Arrays.asList("review7", "review35", "review40", "review46")), 2); // >500 mi away
        Restaurant r4 = new Restaurant("restaurant4", "McDonald's", 1.5f, "709 Terry Pkwy", "Gretna", "LA", "US", "70056", 29.8998883024f, -90.0298833754f, new ArrayList<>(Arrays.asList("review12", "review23", "review25", "review41")), 1); // <500 mi away
        Restaurant r5 = new Restaurant("restaurant5", "Little Greek Restaurant", 2.625f, "19022 Bruce B Downs Blvd", "Tampa", "FL", "US", "33647", 28.1445589708f, -82.3546334163f, new ArrayList<>(Arrays.asList("review20", "review24", "review29", "review33")), 2); // <500 mi away
        List<Restaurant> list = new ArrayList<>(Arrays.asList(r1,r2,r3,r4,r5));
        restaurantLibrary.loadRestaurants(list);

        // create a sample user
        User testUser = new User("default", 30, -90);

        // search for restaurants with a specific term and no filters
        String searchTerm = "Bake";
        Set<IFilter> filters = new HashSet<>();
        String sort = "prox";
        ArrayList<Restaurant> searchResults = restaurantLibrary.search(searchTerm, filters, sort, testUser);

        // assert that search results contain the expected restaurant
        assertEquals(2, searchResults.size());
        boolean match = true;
        for (int i = 0; i < searchResults.size(); i++){
            if (searchResults.get(i).getName().contains("Bake") == false){
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

        assertEquals(2, searchResults2.size()); // there are 4 restaurants within 500 miles with a $$ price tag
        match = true;
        for (int i = 0; i < searchResults2.size(); i++){
            if (searchResults2.get(i).getPriceRange() != 2 || searchResults2.get(i).distanceToUser > 500){
                match = false;
            }
        }
        assertTrue(match);

        // search for criteria with no results
        searchTerm = "";
        pf = new PriceFilter("$$$");
        filters = new HashSet<IFilter>();
        filters.add(pf);
        sort = "rating";
        ArrayList<Restaurant> searchResults3 = restaurantLibrary.search(searchTerm, filters, sort, testUser);

        assertEquals(0, searchResults3.size()); // there are no restaurants with the $$$ price tag

    }

}
