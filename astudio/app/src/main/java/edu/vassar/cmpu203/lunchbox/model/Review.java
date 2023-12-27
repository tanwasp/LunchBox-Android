package edu.vassar.cmpu203.lunchbox.model;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents a review for a restaurant
 */
public class Review implements Serializable {
    public String reviewId;
    public String firebaseUid;
    public String restaurantId;
    public float rating;
    private Date date;
    public String body;
    public int priceRange;
    public String username;
    private String restaurantName;

//    public Review(String reviewId, String username, String restaurantId, float rating, String body, int price) {
//        this.reviewId = reviewId;
//        this.username = username;
//        this.restaurantId = restaurantId;
//        this.rating = rating;
//        this.date = new Date();
//        this.body = body;
//        this.priceRange = price;
//    }
    public Review() {}

//    public Review(String firebaseUid, String username, String restaurantId, float rating, String body, int price) {
//        this.firebaseUid = firebaseUid;
//        this.username = username;
//        this.restaurantId = restaurantId;
//        this.rating = rating;
//        this.date = new Date();
//        this.body = body;
//        this.priceRange = price;
//    }

    /**
     * Constructs a new Review object with the specified details.
     *
     * @param reviewId       The unique identifier of the review.
     * @param firebaseUid    The Firebase UID of the user submitting the review.
     * @param username       The username of the user submitting the review.
     * @param restaurantId   The unique identifier of the reviewed restaurant.
     * @param rating         The numeric rating given in the review.
     * @param body           The textual content of the review.
     * @param price          The price range rating given in the review.
     * @param date           The date when the review was submitted.
     * @param restaurantName The name of the reviewed restaurant.
     */
    public Review(String reviewId, String firebaseUid, String username, String restaurantId, float rating, String body, int price, Date date, String restaurantName) {
        this.reviewId = reviewId;
        this.firebaseUid = firebaseUid;
        this.username = username;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.body = body;
        this.priceRange = price;
        this.date = date;
        if (date == null) {
            this.date = new Date();
        }
        this.restaurantName = restaurantName;
    }

    /**
     * Retrieves the name of the restaurant associated with the review.
     *
     * @return The name of the reviewed restaurant.
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Sets the name of the restaurant associated with the review.
     *
     * @param name The new name of the restaurant in the review
     */
    public void setRestaurantName(String name) {
        this.restaurantName = name;
    }

    /**
     * Retrieves the username of the user who made the review.
     *
     * @return The username of the reviewer
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the ID of the review.
     *
     * @return The review ID
     */
    public String getReviewId() {
        return reviewId;
    }

    /**
     * Retrieves the ID of the restaurant the review is made for
     *
     * @return The restaurant ID associated with the review
     */
    public String getRestaurantId() {
        return restaurantId;
    }

    /**
     * Retrieves the rating given in the review
     *
     * @return The review rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * Retrieves the date the review was made
     *
     * @return The review date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Retrieves the written comment given in the review
     *
     * @return The text body of the review
     */
    public String getBody() {
        return body;
    }

    public void setBody(String newBody){
        this.body = newBody;
    }

    /**
     * Retrieves the price assignment given in the review
     *
     * @return The price assignment
     */
    public int getPriceRange() {
        return priceRange;
    }

    /**
     * Retrieves the Firebase UID of the user who submitted the review.
     *
     * @return The Firebase UID of the reviewer.
     */
    public String getUid() {
        return firebaseUid;
    }

    /**
     * Sets the unique identifier of the review.
     *
     * @param reviewId The new unique identifier for the review.
     */
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * Sets the Firebase UID of the user who submitted the review.
     *
     * @param firebaseUid The new Firebase UID for the reviewer.
     */
    public void setUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    /**
     * Sets the date when the review was submitted.
     *
     * @param date The new date for the review.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Generates a string representation of the review for UI to print
     *
     * @return A string representing the review
     */
    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append("Username: ").append(username).append("\n");
        output.append("Rating: ").append(rating).append("\n");
        output.append("Review: ").append(body).append("\n");
        output.append("Price Range: ").append(priceRange).append("\n");
        output.append("Date: ").append(date).append("\n");
        output.append("User ID: ").append(firebaseUid).append("\n");
        output.append("Review ID: ").append(reviewId).append("\n");
        output.append("Restaurant ID: ").append(restaurantId).append("\n");

        return output.toString();
    }

    public void setRating(float updatedRating) {
        this.rating = updatedRating;
    }

    public void setPriceRange(int updatedPrice) {
        this.priceRange = updatedPrice;
    }
}

