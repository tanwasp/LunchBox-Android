package model;
import java.util.Date;
public class Review {
    public String reviewId;
    public String username;
    public String restaurantId;
    public float rating;
    public Date date;
    public String body;

    public Review(){
        this.reviewId = "";
        this.username = "";
        this.restaurantId = "";
        this.rating = 0;
        this.date = new Date();
        this.body = "";
    }

//    reviewId: 	- string
//    username: 	- string
//    restaurantId: 	- string
//    rating: 		- float
//    date: 		- Date
//    body	- String

    // Constructor, getters, setters, and other methods can be added as needed
}
