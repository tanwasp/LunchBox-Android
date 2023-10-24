package controller;
import view.UserInterface;
import model.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Controller{
    private static RestaurantLibrary lib;
    private static ReviewsLibrary revLib;
    private static UserInterface ui;

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

    public void postReview(User curUser, String restaurantId){
        String[] reviewParams = ui.getReviewData();
        if (reviewParams == null){
            return;
        }
        float rating = Float.parseFloat(reviewParams[0]);
        String reviewText = reviewParams[1];
        String newReviewId = revLib.addReview(curUser, restaurantId, rating, reviewText);
        lib.addReviewToRest(restaurantId, newReviewId);
    }

    public void computeRating(Restaurant r){

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
                            revLib.displayReviews(selectedRes.reviewList);
                        }
                        c.postReview(curUser, selectedRes.restaurantId);
                        ui.displayRestaurants(results);
                    }


                }
            }

        }

    }
}