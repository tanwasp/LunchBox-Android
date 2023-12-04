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
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String name) {
        this.restaurantName = name;
    }
    public String getUsername() {
        return username;
    }

    public String getReviewId() {
        return reviewId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public float getRating() {
        return rating;
    }

    public Date getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }
    public int getPriceRange() {
        return priceRange;
    }
    public String getUid() {
        return firebaseUid;
    }
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public void setUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

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

}

