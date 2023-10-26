package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a user in the system.
 */
public class User {

    /** The username of the user. */
    public String username;

    /** The date the user joined. */
    private Date joinedDate;

    /** The latitude of the user's location. */
    public float lat;

    /** The longitude of the user's location. */
    public float lon;

    /**
     * Constructs a new User with the given name and location.
     *
     * @param name The username of the user.
     * @param lat The latitude of the user's location.
     * @param lon The longitude of the user's location.
     */
    public User(String name, float lat, float lon){
        this.username = name;
        long millis = System.currentTimeMillis();
        this.joinedDate = new java.sql.Date(millis);
        this.lat = lat;
        this.lon = lon;
    }

}
