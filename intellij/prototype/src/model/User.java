package model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    public String username;
    public Date joinedDate;
    public float lat;
    public float lon;

    public User(String name, float lat, float lon){
        this.username = name;
        // used: https://www.geeksforgeeks.org/java-program-to-get-todays-date/
        long millis = System.currentTimeMillis();
        this.joinedDate = new java.sql.Date(millis);
        this.lat = lat;
        this.lon = lon;
    }

    // Constructor, getters, setters, and other methods can be added as needed
}