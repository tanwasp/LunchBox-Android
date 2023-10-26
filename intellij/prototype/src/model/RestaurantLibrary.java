package model;

import java.util.*;

/**
 * Represents all restaurants stored in the app
 */
public class RestaurantLibrary {

    private HashMap<String, Restaurant> data;

    /**
     * Constructor for RestaurantLibrary.
     * Initializes the data map and loads restaurants.
     */
    public RestaurantLibrary() {
        this.data = new HashMap<>();
        this.loadRestaurants();
    }

    /**
     * Adds a review to a specific restaurant's review list.
     *
     * @param restaurantId The ID of the restaurant to add the review to
     * @param reviewId     The ID of the review to be added from the ReviewsLibrary
     */
    public void addReviewToRest(String restaurantId, String reviewId) {
        Restaurant restaurant = data.get(restaurantId);
        restaurant.getReviewList().add(reviewId);
    }


    /**
     * Searches the Restaurant Library for restaurants that match a given search term, applying filters and sorting
     *
     * @param term     The search term to match against restaurant names
     * @param filters  The filters to apply to the search results
     * @param sort     The sorting method
     * @param curUser  The current user to consider for distance calculations
     *
     * @return An ArrayList of Restaurant objects that match the search criteria, filtered and sorted as specified.
     */
    public ArrayList<Restaurant> search(String term, Set<IFilter> filters, String sort, User curUser) {

        ArrayList<Restaurant> matches = new ArrayList<Restaurant>();

        // Assuming data is a HashMap<String, Restaurant>
        for (Restaurant res : data.values()){
            if (res.getName().toLowerCase().contains(term.toLowerCase())){
                res.setDistToUser(curUser);
                matches.add(res);
            }

        }

        for (IFilter f : filters){
            matches = (ArrayList<Restaurant>) f.filter(matches);
        }
        if ("prox".equals(sort)) {
            Collections.sort(matches, Comparator.comparingDouble(r -> (double) r.distanceToUser));
        } else {
            Collections.sort(matches, Comparator.comparingDouble(r -> (double) - r.getRating()));
        }
        return matches;
    }

    /**
     * Loads a set of sample restaurants into the library.
     */
     private void loadRestaurants() {

        Restaurant r1 = new Restaurant("restaurant1", "Moreno Bakery", 3.25f, "737 W Brandon Blvd", "Brandon", "FL", "US", "33511", 27.9361395886f, -82.2950403264f, new ArrayList<>(Arrays.asList("review2", "review21", "review37", "review62", "review64")), 2);
        this.data.put("restaurant1", r1);

        Restaurant r2 = new Restaurant("restaurant2", "Britt’s Bakehouse", 3.0f, "137 W Jefferson Ave", "Kirkwood", "MO", "US", "63122", 38.5825641f, -90.4079162f, new ArrayList<>(Arrays.asList("review11", "review30", "review32", "review52", "review58")), 1);
        this.data.put("restaurant2", r2);

        Restaurant r3 = new Restaurant("restaurant3", "The Maple Creek Country Club", 4.5f, "10501 E 21st St", "Indianapolis", "IN", "US", "46229", 39.7963819f, -85.9817333f, new ArrayList<>(Arrays.asList("review7", "review35", "review40", "review46")), 1);
        this.data.put("restaurant3", r3);

        Restaurant r4 = new Restaurant("restaurant4", "McDonald's", 1.5f, "709 Terry Pkwy", "Gretna", "LA", "US", "70056", 29.8998883024f, -90.0298833754f, new ArrayList<>(Arrays.asList("review12", "review23", "review25", "review41")), 1);
        this.data.put("restaurant4", r4);

        Restaurant r5 = new Restaurant("restaurant5", "Little Greek Restaurant", 2.625f, "19022 Bruce B Downs Blvd", "Tampa", "FL", "US", "33647", 28.1445589708f, -82.3546334163f, new ArrayList<>(Arrays.asList("review20", "review24", "review29", "review33")), 1);
        this.data.put("restaurant5", r5);

        Restaurant r13 = new Restaurant("restaurant13", "Biscuit Love: Hillsboro Village", 3.83f, "2001 Belcourt Ave", "Nashville", "TN", "US", "37212", 36.1367494339f, -86.7997314667f, new ArrayList<>(Arrays.asList("review10", "review43", "review61")), 2);
        this.data.put("restaurant13", r13);

        Restaurant r19 = new Restaurant("restaurant19", "Sushi Train", 3.5f, "94 White Bridge Rd", "Nashville", "TN", "US", "37205", 35.1302006f, -87.93207f, new ArrayList<>(Arrays.asList("review18", "review57", "review63")), 1);
        this.data.put("restaurant19", r19);

        Restaurant r18 = new Restaurant("restaurant18", "WIN Bubble Tea and Sushi", 4.5f, "99 White Bridge Pike", "Nashville", "TN", "US", "37205", 36.1316071f, -86.8581153f, new ArrayList<>(Arrays.asList("review17", "review28", "review42")), 1);
        this.data.put("restaurant18", r18);

        Restaurant r17 = new Restaurant("restaurant17", "El Merkury", 2.5f, "2104 Chestnut St", "Philadelphia", "PA", "US", "19103", 39.952185f, -75.1758693f, new ArrayList<>(Arrays.asList("review16", "review45", "review66")), 2);
        this.data.put("restaurant17", r17);

        Restaurant r16 = new Restaurant("restaurant16", "Sushi Kingdom", 3.5f, "148 NJ-73", "Marlton", "NJ", "US", "08053", 39.8973486233f, -74.9302179453f, new ArrayList<>(Arrays.asList("review15", "review36", "review67")), 3);
        this.data.put("restaurant16", r16);

        Restaurant r15 = new Restaurant("restaurant15", "Hot Dogs on Main", 3.75f, "505 Main St", "Dunedin", "FL", "US", "34698", 28.0124356f, -82.7855034f, new ArrayList<>(Arrays.asList("review14", "review47", "review60")), 3);
        this.data.put("restaurant15", r15);

        Restaurant r14 = new Restaurant("restaurant14", "The Truckee Bagel Company - Midtown", 3.5f, "538 S Virginia St, Ste B", "Reno", "NV", "US", "89501", 39.5202401f, -119.810022f, new ArrayList<>(Arrays.asList("review13", "review54", "review65")), 3);
        this.data.put("restaurant14", r14);

        Restaurant r11 = new Restaurant("restaurant11", "Hunters Station", 1.25f, "975 Main St", "Nashville", "TN", "US", "37206", 36.1782943f, -86.7522834f, new ArrayList<>(Arrays.asList("review8", "review34", "review56")), 2);
        this.data.put("restaurant11", r11);

        Restaurant r12 = new Restaurant("restaurant12", "The Sidecar Bar & Grille", 3.5f, "2201 Christian St", "Philadelphia", "PA", "US", "19146", 39.9418939f, -75.1794584f, new ArrayList<>(Arrays.asList("review9", "review48", "review53")), 3);
        this.data.put("restaurant12", r12);

        Restaurant r10 = new Restaurant("restaurant10", "Dingho Cafe", 1.666f, "7900 Watson Rd", "Saint Louis", "MO", "US", "63119", 38.5689735f, -90.3378223f, new ArrayList<>(Arrays.asList("review6", "review44", "review50")), 2);
        this.data.put("restaurant10", r10);

        Restaurant r9 = new Restaurant("restaurant9", "Fat Cat Tavern", 2.5f, "3665 East Bay Dr, Ste 100", "Largo", "FL", "US", "33771", 27.915055f, -82.7491266f, new ArrayList<>(Arrays.asList("review5", "review27", "review59")), 2);
        this.data.put("restaurant9", r9);

        Restaurant r8 = new Restaurant("restaurant8", "Cafe Rista", 3.5f, "14213 103 Ave NW", "Edmonton", "AB", "US", "T5N 0S8", 53.5438013f, -113.5666839f, new ArrayList<>(Arrays.asList("review4", "review38", "review39")), 3);
        this.data.put("restaurant8", r8);

        Restaurant r7 = new Restaurant("restaurant7", "Daredevil Brewing", 1.0f, "1151 N Main St", "Speedway", "IN", "US", "46224", 39.7824059f, -86.2405885f, new ArrayList<>(Arrays.asList("review3", "review22", "review26")), 3);
        this.data.put("restaurant7", r7);

        Restaurant r6 = new Restaurant("restaurant6", "The Clubhouse Cafe", 3.75f, "9420 Gravois Rd", "Affton", "MO", "US", "63123", 38.5533355784f, -90.3206312656f, new ArrayList<>(Arrays.asList("review1", "review49", "review55")), 1);
        this.data.put("restaurant6", r6);

        Restaurant r20 = new Restaurant("restaurant20", "Chipotle Mexican Grill", 1.75f, "2618 W Moreland Rd", "Willow Grove", "PA", "US", "19090", 40.1435860245f, -75.1217490172f, new ArrayList<>(Arrays.asList("review19", "review31", "review51")), 3);
        this.data.put("restaurant20", r20);
    }
}
