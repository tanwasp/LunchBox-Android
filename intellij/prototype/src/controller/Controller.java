package controller;
import view.UserInterface;
import model.*;
import java.util.HashSet;

public class Controller{
    public static void main (String[] args){
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
                LocFilter lf = new LocFilter(Integer.parseInt(searchParams[2]));
                filters.add(lf);
            } catch (NumberFormatException e){}
        }
        String sort = searchParams[3];

        ui.printResults(lib.search(key, filters, sort));

    }
}