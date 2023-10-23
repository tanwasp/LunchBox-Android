package controller;
import view.UserInterface;
import model.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Controller{
    public ArrayList<Restaurant> searchRestaurants(User curUser, UserInterface ui, RestaurantLibrary lib){
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

    public void postReview(User curUser, UserInterface ui, ReviewsLibrary revLib, String restaurantId){
        String[] reviewParams = ui.getReviewData();
        if (reviewParams == null){
            return;
        }
        float rating = Float.parseFloat(reviewParams[0]);
        String reviewText = reviewParams[1];
        revLib.addReview(curUser, restaurantId, rating, reviewText);
    }

    public static void main (String[] args){

        // For this prototype we assume the user's location is known. In the app, this will be collected using the device's GPS.
        User curUser = new User("Me", 30, -90);
        UserInterface ui = new UserInterface();
        RestaurantLibrary lib = new RestaurantLibrary();
        ReviewsLibrary revLib = new ReviewsLibrary();

        ui.welcome();

        while (true){
            Controller c = new Controller();
            ArrayList<Restaurant> results = c.searchRestaurants(curUser, ui, lib);
            if (!results.isEmpty()){
                int selected = ui.selectRestaurant(results);
                Restaurant selectedRes = results.get(selected - 1);
                ui.displayRestaurantInfo(selectedRes);
                if (selectedRes != null){
                    revLib.displayReviews(selectedRes.reviewList);
                }
                c.postReview(curUser, ui, revLib, selectedRes.restaurantId);

            }

        }

    }
}