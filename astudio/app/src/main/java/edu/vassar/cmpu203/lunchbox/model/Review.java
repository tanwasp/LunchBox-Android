package edu.vassar.cmpu203.lunchbox.model;
import java.util.Date;

/**
 * Represents a review for a restaurant
 */
public class Review {
    public String reviewId;
    public String username;
    public String restaurantId;
    public float rating;
    private Date date;
    public String body;
    public int priceRange;

    public Review(String reviewId, String username, String restaurantId, float rating, String body, int price) {
        this.reviewId = reviewId;
        this.username = username;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.date = new Date();
        this.body = body;
        this.priceRange = price;
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

        return output.toString();
    }

}

