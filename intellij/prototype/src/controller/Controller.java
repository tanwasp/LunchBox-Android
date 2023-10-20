package controller;
import view.UserInterface;
public class Controller{
    public static void main (String[] args){
        UserInterface ui = new UserInterface();

        String[] searchParams = ui.getSearchData();
        System.out.println(searchParams);
    }
}