package edu.vassar.cmpu203.lunchbox.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a user in the system.
 */
public class User implements Serializable {

    /** The username of the user. */
    public String username;

    /** The date the user joined. */
    private Date joinedDate;

    private Coordinate loc;

    private String firebaseUid;

    private String email;
    private Coordinate defaultCoordinate;
    private Coordinate lastCoordinate;

    /**
     * Constructs a new User with the given name and location.
     *
     * @param name The username of the user.
     * @param lat The latitude of the user's location.
     * @param lon The longitude of the user's location.
     */
    public User(String name, float lat, float lon){
        this.username = name;
        this.loc = new Coordinate(lat, lon);
        long millis = System.currentTimeMillis();
        joinedDate = new java.sql.Date(millis);
    }

    /**
     * Constructs a new User object with the specified details.
     *
     * @param name        The username of the user.
     * @param firebaseUid The Firebase UID of the user.
     * @param email       The email address of the user.
     */
    public User(String name, String firebaseUid, String email){
        this.username = name;
        this.firebaseUid = firebaseUid;
        this.email = email;
        long millis = System.currentTimeMillis();
        joinedDate = new java.sql.Date(millis);
    }

    /**
     * Constructs a new User object with additional location details.
     *
     * @param name        The username of the user.
     * @param firebaseUid The Firebase UID of the user.
     * @param email       The email address of the user.
     * @param lat         The latitude coordinate of the user's location.
     * @param lon         The longitude coordinate of the user's location.
     */
    public User(String name, String firebaseUid, String email, float lat, float lon){
        this.username = name;
        this.firebaseUid = firebaseUid;
        this.email = email;
        this.loc = new Coordinate(lat, lon);
        long millis = System.currentTimeMillis();
        joinedDate = new java.sql.Date(millis);
    }

    /**
     * Sets the geographical location of the user using latitude and longitude.
     *
     * @param lat The new latitude coordinate of the user's location.
     * @param lon The new longitude coordinate of the user's location.
     */
    public void setLoc(float lat, float lon){
        this.loc = new Coordinate(lat, lon);
    }

    /**
     * Sets the geographical location of the user.
     *
     * @param loc The Location object representing the user's geographical coordinates.
     */
    public void setLoc(Coordinate loc){
        this.loc = loc;
    }

    /**
     * Retrieves the Firebase UID of the user.
     *
     * @return The Firebase UID of the user.
     */
    public String getUid(){
        return this.firebaseUid;
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The new email address of the user.
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Sets the Firebase UID of the user.
     *
     * @param firebaseUid The new Firebase UID of the user.
     */
    public void setFirebaseUid(String firebaseUid){
        this.firebaseUid = firebaseUid;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username of the user.
     */
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
    public Coordinate getLoc() {
        return loc;
    }

    /**
     * Formats the user information into a string
     *
     * @return String representing User info
     */
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

    /**
     * Gets the default coordinate of the user.
     *
     * @return The default coordinate of the user.
     */
    public Coordinate getDefaultCoordinate() {
        return defaultCoordinate;
    }
    public void setDefaultCoordinate(Coordinate defaultCoordinate) {
        this.defaultCoordinate = defaultCoordinate;
    }
    /**
     * Gets the last coordinate of the user.
     *
     * @return The last coordinate of the user.
     */
    public Coordinate getLastCoordinate() {
        return lastCoordinate;
    }
    public void setLastCoordinate(Coordinate lastCoordinate) {
        this.lastCoordinate = lastCoordinate;
    }
}
