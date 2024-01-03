package edu.vassar.cmpu203.lunchbox.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents all users stored in the app.
 */
public class UserLibrary {

    private HashMap<String, User> data;

    /**
     * Constructor for UserLibrary.
     * Initializes the data map and loads users.
     */
    public UserLibrary() {
        this.data = new HashMap<>();
        this.loadUsers();
    }

    /**
     * Adds a new user to the User Library with the specified details.
     *
     * @param name The username of the user.
     * @param lat  The latitude of the user's location.
     * @param lon  The longitude of the user's location.
     * @return The new User object added to the library.
     */
    public User addUser(String name, float lat, float lon) {
        String uid = "user" + (data.size() + 1);
        User user = new User(name, lat, lon);
        data.put(uid, user);
        return user;
    }

    /**
     * Searches the User Library for users that match a given search term.
     *
     * @param term     The search term to match against user names.
     * @return An ArrayList of User objects that match the search criteria.
     */
    public ArrayList<User> search(String term) {
        ArrayList<User> matches = new ArrayList<>();

        for (User user : data.values()) {
            if (user.getUsername().toLowerCase().contains(term.toLowerCase())) {
                matches.add(user);
            }
        }

        return matches;
    }

    /**
     * Gets a user from the User Library that matches the ID.
     *
     * @param uid The unique identifier of the user.
     * @return The User object corresponding to the given identifier.
     */
    public User getUser(String uid) {
        return data.get(uid);
    }

    /**
     * Retrieves the total number of users in the User Library.
     *
     * @return The number of users in the library.
     */
    public int getNumUsers() {
        return data.size();
    }

    /**
     * Retrieves the entire user library.
     *
     * @return The user library.
     */
    public HashMap<String, User> getLib() {
        return data;
    }

    /**
     * Loads sample users into the User Library.
     *
     */
     private void loadUsers() {
         User u1 = new User("tantan", "8RnivNWBJaXuuJuxmjUBDs135k22", "tantan@vassar.edu");
         this.data.put("user1", u1);

         User u2 = new User("john315", "3JArusqULKYOhCNymKnEQl1fW343", "john315@gmail.com");
         this.data.put("user2", u2);

         User u3 = new User("caroline", "9QglEotpA9M3WFusfVnQ2HZsZFT2", "caroak@gmail.com");
         this.data.put("user3", u3);

         User u4 = new User("sexy", "mW9ywJovzpeW65xfhRo0NBbwCDp1", "sexy@gmail.com");
         this.data.put("user4", u4);

         User u5 = new User("kitty", "m3RvxYAgpKMGrTukwzhgvmN3HwU2", "kittykat@gmail.com");
         this.data.put("user5", u5);
     }

}
