package edu.vassar.cmpu203.lunchbox.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a restaurant with its details and reviews.
 */
public class Restaurant implements Serializable {

    /**
     * Unique identifier for the restaurant.
     */
    private String restaurantId;

    /**
     * Name of the restaurant.
     */
    private String name;

    /**
     * Average rating of the restaurant.
     */
    private float rating;

    /**
     * Address of the restaurant.
     */
    private String address;

    /**
     * City where the restaurant is located.
     */
    private String city;

    /**
     * State where the restaurant is located.
     */
    private String state;

    /**
     * Country where the restaurant is located.
     */
    private String country;

    /**
     * Postal code of the restaurant.
     */
    private String postalCode;

    /**
     * Latitude of the restaurant's location.
     */
    private Location loc;

//    /**
//     * List of review IDs associated with the restaurant.
//     */
//    private ArrayList<String> reviewList;

    /**
     * Distance of the restaurant from the user.
     */
    public float distanceToUser;

    /**
     * Price range of the restaurant (represented by the number of dollar signs).
     */
    public int priceRange;

    public Restaurant() {
        // Default constructor required for calls to DataSnapshot.getValue(Restaurant.class)
    }

    /**
     * Constructs a new Restaurant with the given parameters.
     *
     * @param restaurantId Unique identifier for the restaurant.
     * @param name         Name of the restaurant.
     * @param rating       Average rating of the restaurant.
     * @param address      Address of the restaurant.
     * @param city         City where the restaurant is located.
     * @param state        State where the restaurant is located.
     * @param country      Country where the restaurant is located.
     * @param postalCode   Postal code of the restaurant.
     * @param lat          Latitude of the restaurant's location.
     * @param lon          Longitude of the restaurant's location.
     * @param reviewList   List of review IDs associated with the restaurant.
     * @param priceRange   Price range of the restaurant.
     */
    public Restaurant(String restaurantId, String name, float rating, String address, String city, String state, String country, String postalCode, float lat, float lon, ArrayList<String> reviewList, int priceRange) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.loc = new Location (lat, lon);
//        this.reviewList = reviewList;
        this.priceRange = priceRange;
        distanceToUser = -1.0f;
    }

    /**
     * Constructs a new Restaurant from Add Restaurant form
     *
     * @param restaurantId Unique identifier for the restaurant.
     * @param name         Name of the restaurant.
     * @param address      Address of the restaurant.
     * @param city         City where the restaurant is located.
     * @param state        State where the restaurant is located.
     * @param country      Country where the restaurant is located.
     * @param postalCode   Postal code of the restaurant.
     * @param lat          Latitude of the restaurant's location.
     * @param lon          Longitude of the restaurant's location.
     */
    public Restaurant(String restaurantId, String name, String address, String city, String state, String country, String postalCode, float lat, float lon){
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.loc = new Location(lat, lon);
//        this.reviewList = new ArrayList<String>();
        this.priceRange = 0;
//        distanceToUser = -1.0f;
    }

    /**
     * Computes the average rating of the restaurant based on its reviews.
     *
     * @param revLib The reviews library containing all reviews.
     */
    public void computeRating(ReviewsLibrary revLib) {
        ArrayList<Review> reviews = revLib.getReviewsByRestaurant(this);
        int count = 0;
        float sum = 0;
        for (Review rev : reviews) {
            sum += rev.rating;
            count ++;
        }
        if (count == 0){
            this.rating = -1; // Set to -1 if there are no reviews
        } else {
            this.rating = sum / count; // Average rating
        }

    }

    /**
     * Computes the average price range of the restaurant based on its reviews.
     *
     * @param revLib The reviews library containing all reviews.
     */
    public void computePriceRange(ReviewsLibrary revLib){
        ArrayList<Review> reviews = revLib.getReviewsByRestaurant(this);
        int sum = 0;
        int count = 0;
        for (Review rev : reviews){
            if (rev.getPriceRange() != 0){
                sum += rev.getPriceRange();
                count++;
            }
        }
        if (count == 0){
            this.priceRange = -1; // Set to -1 if there are no reviews with a price range
        } else {
            this.priceRange = sum / count; // Average price range
        }
    }


    /**
     * Returns a string representation of the restaurant.
     *
     * @return A formatted string containing restaurant details.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Restaurant ID: ").append(restaurantId).append("\n");
        output.append("Name: ").append(name).append("\n");
        output.append("Rating: ").append(String.format("%.1f", rating)).append("\n");
        output.append("Price: ").append(getDollarSigns(priceRange)).append("\n");
        output.append("Address: ").append(address).append("\n");
        output.append("City: ").append(city).append("\n");
        output.append("State: ").append(state).append("\n");
        output.append("Country: ").append(country).append("\n");
        output.append("Postal Code: ").append(postalCode).append("\n");
        output.append("Price Range: ").append(priceRange).append("\n");
        if (loc != null){
        output.append("Location: ").append(loc.toString()).append("\n");
        }
        if (distanceToUser < 10) {
            output.append("Distance: ")
                    .append(String.format("%.1f", distanceToUser));
        } else {
            output.append("Distance: ")
                    .append(String.format("%.0f", distanceToUser));

        }
        output.append(" miles\n");
        return output.toString();
    }

    /**
     * Converts the price range of the restaurant to a string of dollar signs to increase readability.
     *
     * @param priceRange Price range of the restaurant.
     *
     * @return A string of dollar signs representing the price range.
     */
    public String getDollarSigns(int priceRange) {
        if (priceRange == 0){
            return "No price info";
        }

        StringBuilder dollarSigns = new StringBuilder();
        for (int i = 0; i < priceRange; i++) {
            dollarSigns.append("$");
        }
        return dollarSigns.toString();
    }

    /**
     * Sets the distance of the restaurant from the user.
     *
     * @param u The user for whom the distance is to be computed.
     */
    public void setDistToUser(User u) {
        System.out.println(this.getName()+" coordinates are"+ this.loc.getLat() + " " + this.loc.getLon());
        if (distanceToUser == -1.0f) {
            distanceToUser = u.getLoc().haversine(this.loc);
        }
    }

    /**
     * Gets the average rating of the restaurant.
     *
     * @return The average rating of the restaurant.
     */
    public float getRating() {
        return rating;
    }

    /**
     * Gets the name of the restaurant.
     *
     * @return The name of the restaurant.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the unique identifier of the restaurant.
     *
     * @return The unique identifier of the restaurant.
     */
    public String getRestaurantId() {
        return restaurantId;
    }

    /**
     * Gets the list of review IDs associated with the restaurant.
     *
     * @return The list of review IDs associated with the restaurant.
     */
//    public ArrayList<String> getReviewList() {
//        return reviewList;
//    }

    /**
     * Gets the price range of the restaurant.
     *
     * @return The price range of the restaurant.
     */
    public int getPriceRange() {
        return priceRange;
    }

    /**
     * Gets the address of the restaurant.
     *
     * @return The restaurant's address attribute
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the city of the restaurant.
     *
     * @return The restaurant's city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the state of the restaurant.
     *
     * @return The restaurant's state
     */
    public String getState() {
        return state;
    }

    /**
     * Gets the country of the restaurant.
     *
     * @return The restaurant's country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the postal code of the restaurant.
     *
     * @return The restaurant's postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets the location of the restaurant.
     *
     * @return The restaurant's location attribute
     */
    public Location getLoc() {
        return loc;
    }

    /**
     * Retrieves the display value for the distance to the user.
     *
     * @return A formatted string representing the distance to the user
     */
    public String getDistDisplay(){
        if (distanceToUser < 10){
            return String.format("%.1f", Math.abs(distanceToUser));
        }
        return String.format("%.0f", distanceToUser);
    }

    /**
     * Makes a formatted address string for display purposes.
     *
     * @return A formatted address string
     */
    public String addressDisplay(){
        String a = address + ", " + city;
        if (!state.equals("")){
            a+= ", " + state;
        }
        return a;
    }

    /**
     * Retrieves the display value for the price range.
     *
     * @return An integer representing the price range display (0 for unknown, 1 for low, 2 for medium, 3 for high)
     */
    public int getPriceRangeDisplay(){
        if (priceRange <= 0){
            return 0;
        }
        else if (priceRange < 1.5 ){
            return 1;
        } else if (priceRange < 2.5){
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Retrieves the display value for the restaurant's rating.
     *
     * @return A formatted string representing the rating or "NA" if the rating is unknown
     */
    public String getRatingDisplay(){
        if (rating < 0){
            return "NA";
        }
        return String.format("%.1f", rating);
    }

    /**
     * Sets the rating of the restaurant
     *
     * @param rating The new rating to set
     */
    public void setRating(float rating){
        this.rating = rating;
    }

    /**
     * Sets the address where the restaurant is located
     *
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the price range of the restaurant
     *
     */
    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    /**
     * Sets the city where the restaurant is located
     *
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the restaurant's country
     *
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets the restaurant's Location
     *
     */
    public void setLoc(Location loc) {
        this.loc = loc;
    }

    /**
     * Sets the restaurant's postal code
     *
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Sets the restaurant's state
     *
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets the restaurant's ID
     *
     */
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Sets the restaurant's distance to a given user
     *
     */
    public void setDistanceToUser(float distanceToUser) {
        this.distanceToUser = distanceToUser;
    }
}


