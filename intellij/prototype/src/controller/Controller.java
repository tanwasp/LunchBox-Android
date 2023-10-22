package controller;
import view.UserInterface;
import model.*;
import java.util.HashSet;

public class Controller{
    public static void main (String[] args){

        // For this prototype we assume the user's location is known. In the app, this will be collected using the device's GPS.
        User curUser  = new User("default", 30, -90);
        UserInterface ui = new UserInterface();
        RestaurantLibrary lib = new RestaurantLibrary();

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

        ui.printResults(lib.search(key, filters, sort));

    }
}