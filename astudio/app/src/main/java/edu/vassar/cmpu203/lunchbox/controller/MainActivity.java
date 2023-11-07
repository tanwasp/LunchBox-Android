package edu.vassar.cmpu203.lunchbox.controller;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.model.RestaurantLibrary;
import edu.vassar.cmpu203.lunchbox.model.ReviewsLibrary;
import edu.vassar.cmpu203.lunchbox.model.User;
import edu.vassar.cmpu203.lunchbox.view.HomeFragment;
import edu.vassar.cmpu203.lunchbox.view.HomeView;
import edu.vassar.cmpu203.lunchbox.view.IHomeView;
import edu.vassar.cmpu203.lunchbox.view.IMainView;
import edu.vassar.cmpu203.lunchbox.view.MainView;
import edu.vassar.cmpu203.lunchbox.view.SearchFragment;
import edu.vassar.cmpu203.lunchbox.view.ISearchView;

public class MainActivity extends AppCompatActivity implements IHomeView.Listener, ISearchView.Listener {

    private RestaurantLibrary lib;
    private ReviewsLibrary revLib;
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
        setContentView(this.mainView.getRootView());
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
