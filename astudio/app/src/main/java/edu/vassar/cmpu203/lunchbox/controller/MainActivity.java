package edu.vassar.cmpu203.lunchbox.controller;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import edu.vassar.cmpu203.lunchbox.model.IFilter;
import edu.vassar.cmpu203.lunchbox.model.LocFilter;
import edu.vassar.cmpu203.lunchbox.model.PriceFilter;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.model.RestaurantLibrary;
import edu.vassar.cmpu203.lunchbox.model.Review;
import edu.vassar.cmpu203.lunchbox.model.ReviewsLibrary;
import edu.vassar.cmpu203.lunchbox.model.User;
import edu.vassar.cmpu203.lunchbox.view.AddRestaurantFragment;
import edu.vassar.cmpu203.lunchbox.view.HomeFragment;
import edu.vassar.cmpu203.lunchbox.view.IAddRestaurantView;
import edu.vassar.cmpu203.lunchbox.view.IHomeView;
import edu.vassar.cmpu203.lunchbox.view.IMainView;
import edu.vassar.cmpu203.lunchbox.view.IRestaurantView;
import edu.vassar.cmpu203.lunchbox.view.MainView;
import edu.vassar.cmpu203.lunchbox.view.ISearchView;
import edu.vassar.cmpu203.lunchbox.view.RestaurantFragment;
import edu.vassar.cmpu203.lunchbox.view.SearchFragment;
import edu.vassar.cmpu203.lunchbox.view.IAddReviewView;
import edu.vassar.cmpu203.lunchbox.view.AddReviewFragment;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.RestaurantAdapter;

public class MainActivity extends AppCompatActivity implements IHomeView.Listener,  IAddRestaurantView.Listener, ISearchView.Listener, IRestaurantView.Listener, IAddReviewView.Listener {
    private static RestaurantLibrary lib;
    private static ReviewsLibrary revLib;
    private User curUser;
    IMainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        curUser = new User("Me", 30, -90);
        lib = new RestaurantLibrary();
        revLib = new ReviewsLibrary();

        // Initialize HomeView and SearchView
        this.mainView = new MainView(this);
        HomeFragment homeFragment = new HomeFragment(this);
        this.mainView.displayFragment(homeFragment, false, "home");

        setContentView(this.mainView.getRootView());
    }

    // HomeView.Listener methods
    @Override
    public void onNavigateToSearch() {
        SearchFragment searchFragment = new SearchFragment(this);
        this.mainView.displayFragment(searchFragment, true, "search");
    }

    // SearchView.Listener methods
    @Override
    public void onPerformSearch(String searchTerm, String priceFilter, String distanceFilter, String sortOption) {
        HashSet<IFilter> filters = new HashSet<IFilter>();
        if(priceFilter != null && !priceFilter.equals("Price")){
            PriceFilter pf = new PriceFilter(priceFilter);
            filters.add(pf);
        }
        if(distanceFilter != null){
            try {
                LocFilter lf = new LocFilter(Integer.parseInt(distanceFilter), curUser);
                filters.add(lf);
            } catch (NumberFormatException e){
                System.out.println("Something went wrong. Invalid integer.");
            }
        }
        ArrayList<Restaurant> matches = lib.search(searchTerm, filters, sortOption, curUser);
        this.mainView.displaySearchResults(matches);
    }

    @Override
    public void onNavigateToRestaurant(Restaurant restaurant, Boolean reversible) {
        ArrayList<Review> reviewsList = revLib.getReviews(restaurant.getReviewList());
        RestaurantFragment restaurantFragment = new RestaurantFragment(this, restaurant, reviewsList);
        this.mainView.displayFragment(restaurantFragment, reversible, "restaurant");
    }

    @Override
    public void onNavigateToPostReview(String restaurantId) {
        AddReviewFragment addRevFragment = new AddReviewFragment(this, restaurantId);
        this.mainView.displayFragment(addRevFragment, true, "review form");
    }

    public void onAddReview(float rating, String comment, String restaurantId, int priceSymbol){
        String reviewId = revLib.addReview(curUser, restaurantId, rating, comment, priceSymbol);
        lib.addReviewToRest(restaurantId, reviewId);
        Restaurant restaurant = lib.getRestaurant(restaurantId);
        restaurant.computeRating(revLib);
        restaurant.computePriceRange(revLib);
        onNavigateToRestaurant(lib.getRestaurant(restaurantId), true);
    }

    @Override
    public void onNavigateToAddRestaurant(){
        AddRestaurantFragment addRestaurantFragment = new AddRestaurantFragment(this);
        this.mainView.displayFragment(addRestaurantFragment, true, "add restaurant");
    }

    public void addRestaurant(String name, String address, String city, String state, String country, String postalCode, String lat, String lon){
        float floatLat = Float.parseFloat(lat);
        float floatLon = Float.parseFloat(lon);
        
        Restaurant r = lib.addRestaurant(name, address, city, state, country, postalCode, floatLat, floatLon);
        onNavigateToRestaurant(r, true);
    }


}
