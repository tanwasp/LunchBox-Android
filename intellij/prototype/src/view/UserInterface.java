package view;
import model.Restaurant;
import model.RestaurantLibrary;

import java.util.ArrayList;
import java.util.Scanner;
public class UserInterface{
    public String[] getSearchData(){
        System.out.println("Welcome to Lunch Box!");

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a search term to find a restaurant by name.");
        String term = in.nextLine();

        String priceFilter = askPriceFilter();
        String locFilter = askLocationFilter();
        String sort = askSort();

        String[] res = {term, priceFilter, locFilter, sort};
        return res;
    }

    public String askPriceFilter(){
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Would you like to filter by price? (y,n)");
            String resp = in.nextLine();
            if (resp.equals("y")) {
                System.out.println("Please enter a valid price category: $, $$, or $$$");
                String price = in.nextLine();
                if (price.equals("$") || price.equals("$$") || price.equals("$$$")){
                    return price;
                }
            } else if (resp.equals("n")) {
                return null;
            }
            System.out.println("Invalid input. Please try again.");
        }
    }

    public String askLocationFilter(){
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Would you like to filter by distance? (y,n)");
            String resp = in.nextLine();
            if (resp.equals("y")) {
                System.out.println("Please enter the max distance (in miles) the restaurant can be from you.");
                String dist = in.nextLine();
                try{
                    Float.parseFloat(dist);
                    return dist;
                } catch (NumberFormatException e){}
            } else if (resp.equals("n")) {
                return null;
            }
            System.out.println("Invalid input. Please try again.");
        }
    }

    public String askSort(){
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Would you like to sort your results? (y,n)");
            String resp = in.nextLine();
            if (resp.equals("y")) {
                System.out.println("How would you like to sort? (proximity, rating)");
                String sort = in.nextLine();
                if (sort.equalsIgnoreCase("proximity")){
                    return "prox";
                } else if (sort.equalsIgnoreCase("rating")){
                    return "rating";
                }
            } else if (resp.equals("n")) {
                return null;
            }
            System.out.println("Invalid input. Please try again.");
        }
    }

    public void displayRestaurants(ArrayList<Restaurant> results){
        StringBuilder output = new StringBuilder();

        if (results.isEmpty()) {
            System.out.println("No restaurants match the given criteria.");
            return;
        }

        output.append("=====================================\n");
        output.append("           RESTAURANT LIST           \n");
        output.append("=====================================\n");

        for (Restaurant restaurant : results) {
            output.append("Name: ").append(restaurant.name).append("\n");
            output.append("Rating: ").append(restaurant.rating).append("\n");
            output.append("Price: ").append(getDollarSigns(restaurant.priceRange)).append("\n");
            output.append("-------------------------------------\n");
        }

        System.out.println(output.toString());
    }

    private String getDollarSigns(int priceRange) {
        StringBuilder dollarSigns = new StringBuilder();
        for (int i = 0; i < priceRange; i++) {
            dollarSigns.append("$");
        }
        return dollarSigns.toString();

    }
}