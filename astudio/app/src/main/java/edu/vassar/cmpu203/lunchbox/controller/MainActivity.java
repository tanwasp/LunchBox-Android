package edu.vassar.cmpu203.lunchbox.controller;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.location.Location;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//import edu.vassar.cmpu203.lunchbox.Manifest;
import android.Manifest;
import edu.vassar.cmpu203.lunchbox.model.IFilter;
import edu.vassar.cmpu203.lunchbox.model.LocFilter;
import edu.vassar.cmpu203.lunchbox.model.PriceFilter;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.model.RestaurantLibrary;
import edu.vassar.cmpu203.lunchbox.model.Review;
import edu.vassar.cmpu203.lunchbox.model.ReviewsLibrary;
import edu.vassar.cmpu203.lunchbox.model.User;
import edu.vassar.cmpu203.lunchbox.model.data_repositories.FirestoreCsvImporter;
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
    String email;
    String firebaseUid;
    String username;
    float latitude;
    float longitude;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    ActivityResultLauncher<Intent> loginActivityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Check for location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION); // Replace with your request code
        }

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location androidLocation) {
                        if (androidLocation != null) {
                            latitude = (float) androidLocation.getLatitude();
                            longitude = (float) androidLocation.getLongitude();
                            initializeUser();
                        }
                    }
                });

        // Initialize login launcher

        loginActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        email = data.getStringExtra("email");
                        firebaseUid = data.getStringExtra("firebaseUserId");
                        username = data.getStringExtra("username");
                        curUser = new User(username, firebaseUid, email, latitude, longitude);

                        System.out.println(curUser);
                        SearchFragment searchFragment = new SearchFragment(this);
                        this.mainView.displayFragment(searchFragment, false, "search", 0);
                    }
                    System.out.println("Sign up failed in Main Activity");
                }
        );

        System.out.println((curUser));
        lib = new RestaurantLibrary();
        revLib = new ReviewsLibrary();


        // Initialize HomeView and SearchView
        this.mainView = new MainView(this);
        HomeFragment homeFragment = new HomeFragment(this);
        this.mainView.displayFragment(homeFragment, false, "home", 0);

        setContentView(this.mainView.getRootView());
    }

    private void initializeUser() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            username = currentUser.getDisplayName();
            email = currentUser.getEmail();
            firebaseUid = currentUser.getUid();
            curUser = new User(username, firebaseUid, email, latitude, longitude);
            System.out.println(curUser);  // Now curUser will have the updated location
        } else {
            System.out.println("User is not signed in");
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted
            } else {
                // Permission denied, handle the denial
            }
        }
    }

    // HomeView.Listener methods
    /**
     * navigates to search fragment
     */
    @Override
    public void onNavigateToSearch() {
        SearchFragment searchFragment = new SearchFragment(this);
        this.mainView.displayFragment(searchFragment, true, "search", 0);
    }


    // SearchView.Listener methods

    /**
     * performs search based on search term, price filter, distance filter, and sort option
     * @param searchTerm - they key word or phrase to search by with string contains
     * @param priceFilter - the price range to filter by
     * @param distanceFilter - the distance to filter by
     * @param sortOption - sort by proximity to the user or highest to lowest rating of the restaurants
     */
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

    // RestaurantAdapter.OnItemClickListener methods

    /**
     * navigates to restaurant fragment
     * @param restaurant
     * @param reversible
     * @param popCount
     */
    @Override
    public void onNavigateToRestaurant(Restaurant restaurant, boolean reversible, int popCount) {
        ArrayList<Review> reviewsList = revLib.getReviews(restaurant.getReviewList());
        RestaurantFragment restaurantFragment = new RestaurantFragment(this, restaurant, reviewsList);
        this.mainView.displayFragment(restaurantFragment, reversible, "restaurant", popCount);
    }

    // RestaurantView.Listener methods

    /**
     * navigates to add review fragment
     * @param restaurantId
     */
    @Override
    public void onNavigateToPostReview(String restaurantId) {
        AddReviewFragment addRevFragment = new AddReviewFragment(this, restaurantId);
        this.mainView.displayFragment(addRevFragment, true, "review form", 0);
    }

    /**
     * adds review to review library and adds review to restaurant
     * @param rating
     * @param comment
     * @param restaurantId
     * @param priceSymbol
     */
    public void onAddReview(float rating, String comment, String restaurantId, int priceSymbol){
        String reviewId = revLib.addReview(curUser, restaurantId, rating, comment, priceSymbol);
        lib.addReviewToRest(restaurantId, reviewId);
        Restaurant restaurant = lib.getRestaurant(restaurantId);
        restaurant.computeRating(revLib);
        restaurant.computePriceRange(revLib);
        onNavigateToRestaurant(lib.getRestaurant(restaurantId), true, 2);
    }

    /**
     * adds review to review library and adds review to restaurant
     */

    @Override
    public void onNavigateToAddRestaurant(){
        AddRestaurantFragment addRestaurantFragment = new AddRestaurantFragment(this);
        this.mainView.displayFragment(addRestaurantFragment, true, "add restaurant", 0);
    }

    /**
     * adds restaurant to restaurant library
     * @param name
     * @param address
     * @param city
     * @param state
     * @param country
     * @param postalCode
     * @param lat
     * @param lon
     */
    public void addRestaurant(String name, String address, String city, String state, String country, String postalCode, String lat, String lon){
        float floatLat = Float.parseFloat(lat);
        float floatLon = Float.parseFloat(lon);
        
        Restaurant r = lib.addRestaurant(name, address, city, state, country, postalCode, floatLat, floatLon);
        onNavigateToRestaurant(r, true, 1);
    }

    public void onNavigateToLogin() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.putExtra("action", "login");
        loginActivityResultLauncher.launch(loginIntent);
    }
    public void onNavigateToSignup() {
        Intent signupIntent = new Intent(this, LoginActivity.class);
        signupIntent.putExtra("action", "signup");
        loginActivityResultLauncher.launch(signupIntent);
    }

    public void onNavigateToHome(){
        HomeFragment homeFragment = new HomeFragment(this);
        this.mainView.displayFragment(homeFragment, false, "home", 0);
    }

}
