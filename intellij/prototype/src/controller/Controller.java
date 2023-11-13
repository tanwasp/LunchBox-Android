package controller;
import view.UserInterface;
import model.*;

import java.util.ArrayList;
import java.util.HashSet;
/**
 * This class is the controller for the prototype. It is responsible for creating the user interface, the restaurant library, and the reviews library.
 * It also handles the flow of the program, calling the appropriate methods in the user interface and libraries.
 */
public class Controller{
    private static RestaurantLibrary lib;
    private static ReviewsLibrary revLib;
    private static UserInterface ui;

    /**
     * This method is called when the user wants to search for restaurants. It gets the search parameters from the user interface,
     * formats them to the correct data types, and calls the search method in the restaurant library.
     *
     * @param curUser The user who is searching for restaurants.
     * @return An ArrayList of restaurants that match the search parameters.
     */
    public ArrayList<Restaurant> searchRestaurants(User curUser){
        String[] searchParams = ui.getSearchData();

        String key = searchParams[0];
        HashSet<IFilter> filters = new HashSet<IFilter>();
        if(searchParams[1] != null){
            PriceFilter pf = new PriceFilter(searchParams[1]);
            filters.add(pf);
        }
        if(searchParams[2] != null){
            try {
                LocFilter lf = new LocFilter(Integer.parseInt(searchParams[2]), curUser);
                filters.add(lf);
            } catch (NumberFormatException e){
                System.out.println("Something went wrong. Invalid integer.");
            }
        }
        String sort = searchParams[3];
        ArrayList<Restaurant> matches = lib.search(key, filters, sort, curUser);
        ui.displayRestaurants(matches);
        return matches;
    }

    /**
     * This method is called when the user wants to post a review. It gets the review parameters from the user interface, and calls
     * the addReview method in the reviews library.
     *
     * @param curUser The user who is posting the review.
     * @param restaurantId The id of the restaurant that the review is for.
     * @return True if the review was successfully posted, false otherwise.
     */
    public boolean postReview(User curUser, String restaurantId){
        String[] reviewParams = ui.getReviewData();
        if (reviewParams == null){
            return false;
        }
        float rating = Float.parseFloat(reviewParams[0]);
        String reviewText = reviewParams[1];
        String newReviewId = revLib.addReview(curUser, restaurantId, rating, reviewText);
        lib.addReviewToRest(restaurantId, newReviewId);
        return true;
    }


    public static void main (String[] args){

        // For this prototype we assume the user's location is known. In the app, this will be collected using the device's GPS.
        User curUser = new User("Me", 30, -90);
        ui = new UserInterface();
        lib = new RestaurantLibrary();
        revLib = new ReviewsLibrary();

        ui.welcome();
        Controller c = new Controller();


        while (true){

            ArrayList<Restaurant> results = c.searchRestaurants(curUser);
            if (!results.isEmpty()){
                while(true) {
                    String nextAction = ui.getNextAction();
                    if (nextAction.toLowerCase().equals("1")) {
                        break;
                    }
                    else {
                        int selected = ui.selectRestaurant(results);
                        Restaurant selectedRes = results.get(selected - 1);
                        ui.displayRestaurantInfo(selectedRes);
                        if (selectedRes != null) {
                            ArrayList<Review> reviews = revLib.getReviews(selectedRes.getReviewList());
                            ui.displayReviews(reviews);
                        }
                        if (c.postReview(curUser, selectedRes.getRestaurantId())){
                            selectedRes.computeRating(revLib);
                        }
                        ui.displayRestaurants(results);
                    }
                }
            }

        }

    }
}