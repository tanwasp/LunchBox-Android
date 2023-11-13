package edu.vassar.cmpu203.lunchbox.model;

import java.util.ArrayList;

/**
 * Represents a restaurant with its details and reviews.
 */
public class Restaurant {

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

    /**
     * List of review IDs associated with the restaurant.
     */
    private ArrayList<String> reviewList;

    /**
     * Distance of the restaurant from the user.
     */
    public float distanceToUser;

    /**
     * Price range of the restaurant (represented by the number of dollar signs).
     */
    public int priceRange;

    /**
     * Computes the average rating of the restaurant based on its reviews.
     *
     * @param revLib The reviews library containing all reviews.
     */
    public void computeRating(ReviewsLibrary revLib) {
        if (this != null) {
            ArrayList<Review> reviews = revLib.getReviews(reviewList);
            float sum = 0;
            for (Review rev : reviews) {
                sum += rev.rating;
            }
            rating = sum / reviews.size();
        }
    }

    /**
     * Computes the average price range of the restaurant based on its reviews.
     *
     * @param revLib The reviews library containing all reviews.
     */

    public void computePriceRange(ReviewsLibrary revLib){
        if (this != null){
            ArrayList<Review> reviews = revLib.getReviews(reviewList);
            int sum = 0;
            int count = 0;
            for (Review rev : reviews){
                if (rev.priceRange != 0){
                    count++;
                }
                sum += rev.priceRange;
            }
            priceRange = sum / count;
        }
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
        this.reviewList = reviewList;
        this.priceRange = priceRange;
        distanceToUser = -1.0f;
    }

    public Restaurant(String restaurantId, String name, String address, String city, String state, String country, String postalCode, float lat, float lon){
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.loc = new Location(lat, lon);
        this.reviewList = new ArrayList<String>();
        this.priceRange = 0;
        distanceToUser = -1.0f;
    }

    /**
     * Returns a string representation of the restaurant.
     *
     * @return A formatted string containing restaurant details.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Name: ").append(name).append("\n");
        output.append("Rating: ").append(String.format("%.1f", rating)).append("\n");
        output.append("Price: ").append(getDollarSigns(priceRange)).append("\n");
        output.append("Address: ").append(address).append("\n");
        output.append("City: ").append(city).append("\n");
        output.append("State: ").append(state).append("\n");
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
    public ArrayList<String> getReviewList() {
        return reviewList;
    }

    /**
     * Gets the price range of the restaurant.
     *
     * @return The price range of the restaurant.
     */
    public int getPriceRange() {
        return priceRange;
    }
    public String getAddress() {
        return address;
    }

    public int getPriceRangeDisplay(){
        if (priceRange  < 1.5 ){
            return 1;
        } else if (priceRange < 2.5){
            return 2;
        } else {
            return 3;
        }
    }

    public String getRatingDisplay(){
        return String.format("%.1f", rating);
    }
}


