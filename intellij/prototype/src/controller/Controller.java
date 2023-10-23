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
//            if (!results.isEmpty()){
//                Restaurant selected = ui.selectRestaurant(results);
//                if (selected != null){
//                    ui.displayReviews(selected, revLib);
//                    ui.askReview(selected, curUser, revLib);
//                }
//            }

        }

    }
}