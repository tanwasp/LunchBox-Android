package edu.vassar.cmpu203.lunchbox.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.model.RestaurantLibrary;
import edu.vassar.cmpu203.lunchbox.model.ReviewsLibrary;
import edu.vassar.cmpu203.lunchbox.model.User;
import edu.vassar.cmpu203.lunchbox.view.HomeView;
import edu.vassar.cmpu203.lunchbox.view.SearchView;

public class MainActivity extends AppCompatActivity implements HomeView.Listener, SearchView.Listener {

    private RestaurantLibrary lib;
    private ReviewsLibrary revLib;
    private User curUser;
    private HomeView homeView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        curUser = new User("Me", 30, -90);
        lib = new RestaurantLibrary();
        revLib = new ReviewsLibrary();

        // Initialize HomeView and SearchView
        homeView = new HomeView(findViewById(R.id.homeViewContainer), this);
        searchView = new SearchView(findViewById(R.id.searchViewContainer), this);
    }

    // HomeView.Listener methods
    @Override
    public void onNavigateToSearch() {
        // Switch to SearchView
    }

    // SearchView.Listener methods
    @Override
    public void onPerformSearch(String searchTerm, String priceFilter, String distanceFilter, String sortOption) {
        // Perform search and update UI
    }
}
