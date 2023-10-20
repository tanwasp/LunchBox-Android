package model;
import java.util.Date;
public class Review {
    public String reviewId;
    public String username;
    public String restaurantId;
    public float rating;
    public Date date;
    public String comment;

    public Review(){
        this.reviewId = "";
        this.username = "";
        this.restaurantId = "";
        this.rating = 0;
        this.date = new Date();
        this.comment = "";
    }

    // Constructor, getters, setters, and other methods can be added as needed
}
