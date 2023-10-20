package model;
import java.util.Date;
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

//    reviewId: 	- string
//    username: 	- string
//    restaurantId: 	- string
//    rating: 		- float
//    date: 		- Date
//    body	- String

    // Constructor, getters, setters, and other methods can be added as needed
}

