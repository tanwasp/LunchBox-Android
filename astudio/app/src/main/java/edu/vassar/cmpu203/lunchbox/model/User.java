package edu.vassar.cmpu203.lunchbox.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;

/**
 * Represents a user in the system.
 */
public class User {

    /** The username of the user. */
    public String username;

    /** The date the user joined. */
    private Date joinedDate;

    private Location loc;

    private String firebaseUid;

    private String email;

    /**
     * Constructs a new User with the given name and location.
     *
     * @param name The username of the user.
     * @param lat The latitude of the user's location.
     * @param lon The longitude of the user's location.
     */
    public User(String name, float lat, float lon){
        this.username = name;
        this.loc = new Location(lat, lon);
        long millis = System.currentTimeMillis();
        joinedDate = new java.sql.Date(millis);
    }

    public User(String name, String firebaseUid, String email){
        this.username = name;
        this.firebaseUid = firebaseUid;
        this.email = email;
        long millis = System.currentTimeMillis();
        joinedDate = new java.sql.Date(millis);
    }

    public User(String name, String firebaseUid, String email, float lat, float lon){
        this.username = name;
        this.firebaseUid = firebaseUid;
        this.email = email;
        this.loc = new Location(lat, lon);
        long millis = System.currentTimeMillis();
        joinedDate = new java.sql.Date(millis);
    }

    public void setLoc(float lat, float lon){
        this.loc = new Location(lat, lon);
    }

    public void setLoc(Location loc){
        this.loc = loc;
    }

    public String getUid(){
        return this.firebaseUid;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setFirebaseUid(String firebaseUid){
        this.firebaseUid = firebaseUid;
    }

    public void setUsername(String username){
        this.username = username;
    }


    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the date the user joined.
     *
     * @return The date the user joined.
     */
    public Date getJoinedDate() {
        return joinedDate;
    }

    /**
     * Gets the user's location.
     *
     * @return The Location object representing the user's location
     */
    public Location getLoc() {
        return loc;
    }

    public String toString(){
        if (this.loc == null){
            return "Username: " + this.username + "\n" +
                    "Firebase UID: " + this.firebaseUid + "\n" +
                    "Email: " + this.email + "\n" +
                    "Location: null";
        }
        else{
            return "Username: " + this.username + "\n" +
                    "Firebase UID: " + this.firebaseUid + "\n" +
                    "Email: " + this.email + "\n" +
                    "Location: " + this.loc.toString();
        }
    }
}
