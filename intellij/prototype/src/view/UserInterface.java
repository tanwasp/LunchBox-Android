package view;
import model.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface for the Lunch Box app.
 */
public class UserInterface{

    public void welcome(){
        System.out.println("Welcome to Lunch Box!");
    }

    /**
     * Gets the search parameters from the user.
     *
     * @return an array of strings containing the search parameters
     */
    public String[] getSearchData(){

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a search term to find a restaurant by name.");
        String term = in.nextLine();

        String priceFilter = askPriceFilter();
        String locFilter = askLocationFilter();
        String sort = askSort();

        String[] res = {term, priceFilter, locFilter, sort};
        return res;
    }
    /**
     * Gets the desired price filter from the user.
     *
     * @return a String representing the price filter (null if none desired)
     */
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

    /**
     * Gets the desired location filter from the user.
     *
     * @return a String representing the location filter (null if none desired)
     */
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

    /**
     * Gets the desired sorting algorithm from the user.
     *
     * @return a String representing the sort type
     */
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

    /**
     * Displays the results of the search.
     *
     * @param results an array of restaurants that match the search criteria
     */
    public void displayRestaurants(ArrayList<Restaurant> results){
        StringBuilder output = new StringBuilder();

        if (results.isEmpty()) {
            System.out.println("No restaurants match the given criteria.");
            return;
        }

        output.append("=====================================\n");
        output.append("           RESTAURANT LIST           \n");
        output.append("=====================================\n");

        int count = 1;

        for (Restaurant restaurant : results) {
            output.append("Restaurant ").append(count).append("\n");
            output.append(restaurant.toString());
            output.append("-------------------------------------\n");
            count++;

        }

        System.out.println(output);
    }

    /**
     * Displays the reviews of a restaurant.
     *
     * @param reviews an array of reviews for a restaurant
     */
    public void displayReviews(ArrayList<Review> reviews){
        StringBuilder output = new StringBuilder();

        if (reviews.isEmpty()) {
            System.out.println("No reviews for this restaurant.");
            return;
        }

        output.append("=====================================\n");
        output.append("           REVIEWS           \n");
        output.append("=====================================\n");

        int count = 1;

        for (Review review : reviews) {
            output.append(review.toString());
            output.append("-------------------------------------\n");

        }

        System.out.println(output);
    }

    /**
     * Gets the review parameters from the user.
     *
     * @return an array of strings containing the review parameters
     */
    public String[] getReviewData() {
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to post a review? (y,n)");
        while (true) {
            String resp = in.nextLine();
            if (resp.equals("y")) {
                break;
            } else if (resp.equals("n")) {
                return null;
            }
            System.out.println("Invalid input. Please try again.");
        }
        System.out.println("Please enter a rating for this restaurant (1-5 only integers).");
        String rating;
        while (true) {
            rating = in.nextLine();
            if (!(rating.equals("1") || rating.equals("2") || rating.equals("3") || rating.equals("4") || rating.equals("5"))) {
                System.out.println("Invalid rating. Please try again.");
            } else {
                break;
            }
        }
        System.out.println("Please enter a review for this restaurant.");
        String reviewText = in.nextLine();
        String[] res = {rating, reviewText};
        return res;
    }

    /**
     * Displays the information for a restaurant.
     *
     * @param r the restaurant to display
     */
    public void displayRestaurantInfo(Restaurant r){
        System.out.println(r.toString());
    }

    /**
     * Gets desired restaurant selection from the user
     *
     * @return the number corresponding to the desired restaurant
     */
    public int selectRestaurant(ArrayList<Restaurant> results){
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter the number of the restaurant you would like to select.");
            try {
                int num = in.nextInt();
                if (num > 0 && num <= results.size()) {
                    return num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number. Please try again.");
            }
            System.out.println("Invalid input. Please try again.");
        }
    }

    /**
     * Gets the user's next action after searching
     *
     * @return a String representing what the user wants to do next
     */
    public String getNextAction(){
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("What would you like to do next? (Enter 1 to search again search, Enter 2 to select restaurant)");
            String resp = in.nextLine();
            if (resp.equals("1") || resp.equals("2")) {
                return resp;
            }
            System.out.println("Invalid input. Please try again.");
        }
    }


}