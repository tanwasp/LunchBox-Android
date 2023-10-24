package model;
import java.util.Date;
public class Review {
    public String reviewId;
    public String username;
    public String restaurantId;
    public float rating;
    public Date date;
    public String body;

    @Override
    public String toString(){
            StringBuilder output = new StringBuilder();
            output.append("Username: ").append(username).append("\n");
            output.append("Rating: ").append(rating).append("\n");
            output.append("Review: ").append(body).append("\n");

        return output.toString();
    }

    public Review(String reviewId, String username, String restaurantId, Float rating, String body) {
        this.reviewId = reviewId;
        this.username = username;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.date = new Date();
        this.body = body;
    }


}

