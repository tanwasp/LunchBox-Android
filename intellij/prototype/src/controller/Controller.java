package controller;
import view.UserInterface;
import model.RestaurantLibrary;

public class Controller{
    public static void main (String[] args){
        UserInterface ui = new UserInterface();
        RestaurantLibrary lib = new RestaurantLibrary();

        String[] searchParams = ui.getSearchData();
        System.out.println(searchParams);

    }
}