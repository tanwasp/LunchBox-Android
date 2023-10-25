package model;
import java.util.Date;

/**
 * Represents a review for a restaurant
 */
public class Review {
    public String reviewId;
    public String username;
    public String restaurantId;
    public float rating;
    public Date date;
    public String body;

    public Review(String reviewId, String username, String restaurantId, Float rating, String body) {
        this.reviewId = reviewId;
        this.username = username;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.date = new Date();
        this.body = body;
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

