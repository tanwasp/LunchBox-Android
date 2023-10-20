package controller;

import view.UserInterface;
public class Controller{
    public static void main (String[] args){
        UserInterface ui = new UserInterface();
        RestaurantLibrary lib = new RestaurantLibrary();

        String[] criteria = ui.getSearchData();



    }
}