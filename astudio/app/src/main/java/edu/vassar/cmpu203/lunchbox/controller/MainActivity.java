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
//import android.location.LocationRequest;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.location.LocationRequest;


import android.location.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//import edu.vassar.cmpu203.lunchbox.Manifest;
import android.Manifest;
import android.os.Looper;

import edu.vassar.cmpu203.lunchbox.model.IFilter;
import edu.vassar.cmpu203.lunchbox.model.LocFilter;
import edu.vassar.cmpu203.lunchbox.model.PriceFilter;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.model.RestaurantLibrary;
import edu.vassar.cmpu203.lunchbox.model.RestaurantNames;
import edu.vassar.cmpu203.lunchbox.model.Review;
import edu.vassar.cmpu203.lunchbox.model.ReviewsLibrary;
import edu.vassar.cmpu203.lunchbox.model.User;
import edu.vassar.cmpu203.lunchbox.model.data_repositories.FStoreRestaurantDataRepo;
import edu.vassar.cmpu203.lunchbox.model.data_repositories.FStoreReviewsDataRepo;
import edu.vassar.cmpu203.lunchbox.model.data_repositories.FirestoreCsvImporter;
import edu.vassar.cmpu203.lunchbox.model.data_repositories.IDataRepositoryCallback;
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
import edu.vassar.cmpu203.lunchbox.view.IUserProfileFragment;

import edu.vassar.cmpu203.lunchbox.model.*;
import edu.vassar.cmpu203.lunchbox.model.data_repositories.FirestoreCsvImporter;
import edu.vassar.cmpu203.lunchbox.view.*;

public class MainActivity extends AppCompatActivity implements IHomeView.Listener, IAddRestaurantView.Listener, ISearchView.Listener, IRestaurantView.Listener, IAddReviewView.Listener, IUserProfileFragment.Listener {
    private static RestaurantLibrary lib;
    private static ReviewsLibrary revLib;
    RestaurantNames restaurantNames;
    private User curUser;
    IMainView mainView;
    String email;
    String firebaseUid;
    String username;
    float latitude;
    float longitude;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private boolean locationUpdated;
    UserProfileFragment profileFragment;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    ActivityResultLauncher<Intent> loginActivityResultLauncher;

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFirebaseAuthListener();
        setupLoginActivityResultLauncher();

        lib = new RestaurantLibrary();
        revLib = new ReviewsLibrary();
        loadRestaurants();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        this.mainView = new MainView(this);
        HomeFragment homeFragment = new HomeFragment(this);
        this.mainView.displayFragment(homeFragment, false, "home", 0);

        setContentView(this.mainView.getRootView());
    }



    private void setupLocationService() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            fetchLastLocation();
        }
    }

//    private void fetchLastLocation() {
//        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
//            if (location != null) {
//                updateCurrentUserLocation((float) location.getLatitude(), (float) location.getLongitude());
//            } else {
//                System.out.println("Location is null from fetchLastLocation");
//            }
//        }).addOnFailureListener(this, e -> System.out.println("Failed to get location: " + e.getMessage()));
//    }

    private void fetchLastLocation() {

        // Check for permissions again (optional but recommended)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return; // Handle the lack of permission appropriately.
        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);
//        locationRequest.setNumUpdates(1);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    System.out.println("Location result is null");
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        updateCurrentUserLocation((float) location.getLatitude(), (float) location.getLongitude());
                        break; // Update with the first valid location
                    }
                }
            }
        };
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }


    private void setupFirebaseAuthListener() {
        authStateListener = firebaseAuth -> {
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            if (currentUser != null) {
                updateCurrentUser(currentUser);
                setupLocationService();
            }
        };
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }

    private void setupLoginActivityResultLauncher() {
        loginActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                updateCurrentUser(data);
            } else {
                System.out.println("Sign up failed in Main Activity");
            }
        });
    }

    private void updateCurrentUser(FirebaseUser firebaseUser) {
        if (curUser == null || curUser.getLoc() == null) {
            curUser = new User(firebaseUser.getDisplayName(), firebaseUser.getUid(), firebaseUser.getEmail(), 0, 0);
        } else {
            curUser = new User(firebaseUser.getDisplayName(), firebaseUser.getUid(), firebaseUser.getEmail());
        }
        System.out.println("User is signed in: " + curUser);
    }


    private void updateCurrentUser(Intent data) {
        String email = data.getStringExtra("email");
        String firebaseUid = data.getStringExtra("firebaseUserId");
        String username = data.getStringExtra("username");
        curUser = new User(username, firebaseUid, email);
        System.out.println("Current user logged in or signed up is " + curUser);
        SearchFragment searchFragment = new SearchFragment(this);
        this.mainView.displayFragment(searchFragment, true, "search", 0);
    }

    private void updateCurrentUserLocation(float latitude, float longitude) {
        if (curUser != null) {
            curUser.setLoc(latitude, longitude);
        }
//        System.out.println("Location updated: " + latitude + ", " + longitude);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            fetchLastLocation();
        } else {
            System.out.println("Location permission denied");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
        setupLocationService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fusedLocationClient != null && locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
        if (authStateListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
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
     *
     * @param searchTerm     - they key word or phrase to search by with string contains
     * @param priceFilter    - the price range to filter by
     * @param distanceFilter - the distance to filter by
     * @param sortOption     - sort by proximity to the user or highest to lowest rating of the restaurants
     */
    @Override
    public void onPerformSearch(String searchTerm, String priceFilter, String distanceFilter, String sortOption) {
        System.out.println("Latitude " + curUser.getLoc().getLat() + " Longitude " + curUser.getLoc().getLon());
        HashSet<IFilter> filters = new HashSet<IFilter>();
        if (priceFilter != null && !priceFilter.equals("Price")) {
            PriceFilter pf = new PriceFilter(priceFilter);
            filters.add(pf);
        }
        if (distanceFilter != null) {
            try {
                LocFilter lf = new LocFilter(Integer.parseInt(distanceFilter), curUser);
                filters.add(lf);
            } catch (NumberFormatException e) {
                System.out.println("Something went wrong. Invalid integer.");
            }
        }
        ArrayList<Restaurant> matches = lib.search(searchTerm, filters, sortOption, curUser);
        this.mainView.displaySearchResults(matches);
    }


    // RestaurantAdapter.OnItemClickListener methods

    /**
     * navigates to restaurant fragment
     *
     * @param restaurant
     * @param reversible
     * @param popCount
     */
    @Override
    public void onNavigateToRestaurant(Restaurant restaurant, boolean reversible, int popCount) {
        FStoreReviewsDataRepo repo = new FStoreReviewsDataRepo();
        repo.getReviews("restaurantId", restaurant.getRestaurantId(), new IDataRepositoryCallback() {
            @Override
            public void onSuccess(Object result) {
                List<Review> reviewsList = (List<Review>) result;
//                revLib.addReviews(reviews);
                onNavigateToRestaurant(restaurant, reversible, popCount, reviewsList);
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("Failed to get reviews from Firestore" + e.getMessage());
            }
        });

//        ArrayList<Review> reviewsList = revLib.getReviews(restaurant.getReviewList());
//        RestaurantFragment restaurantFragment = new RestaurantFragment(this, restaurant, reviewsList);
//        this.mainView.displayFragment(restaurantFragment, reversible, "restaurant", popCount);
    }

    public void onNavigateToRestaurant(Restaurant restaurant, boolean reversible, int popCount, List<Review> reviewsList) {
        RestaurantFragment restaurantFragment = new RestaurantFragment(this, restaurant, reviewsList);
        this.mainView.displayFragment(restaurantFragment, reversible, "restaurant", popCount);
    }

    // RestaurantView.Listener methods

    /**
     * navigates to add review fragment
     *
     * @param restaurantId
     */
    @Override
    public void onNavigateToPostReview(String restaurantId) {
        AddReviewFragment addRevFragment = new AddReviewFragment(this, restaurantId);
        this.mainView.displayFragment(addRevFragment, true, "review form", 0);
    }

    /**
     * adds review to review library and adds review to restaurant
     *
     * @param rating
     * @param comment
     * @param restaurantId
     * @param priceSymbol
     */
    public void onAddReview(float rating, String comment, String restaurantId, int priceSymbol) {
        // Create the Review object
        Review newReview = new Review(curUser.getUid(), curUser.getUsername(), restaurantId, rating, comment, priceSymbol, null);
        System.out.println("New review: " + newReview);
        // Use FStoreReviewsDataRepo to add the review
        FStoreReviewsDataRepo repo = new FStoreReviewsDataRepo();
        repo.addReview(newReview, new IDataRepositoryCallback() {
            @Override
            public void onSuccess(Object result) {
                // retrieve the reviewId from the DocumentReference if necessary
                // String reviewId = ((DocumentReference) result).getId();

                // Update restaurant details after adding the review
                Restaurant restaurant = lib.getRestaurant(restaurantId);
                if (restaurant != null) {
                    restaurant.computeRating(revLib);
                    restaurant.computePriceRange(revLib);
                    onNavigateToRestaurant(restaurant, true, 2);
                }
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("Failed to add review to Firestore" + e.getMessage());
            }
        });
    }


    /**
     * adds review to review library and adds review to restaurant
     */

    @Override
    public void onNavigateToAddRestaurant() {
        AddRestaurantFragment addRestaurantFragment = new AddRestaurantFragment(this);
        this.mainView.displayFragment(addRestaurantFragment, true, "add restaurant", 0);
    }

    /**
     * adds restaurant to restaurant library
     *
     * @param name
     * @param address
     * @param city
     * @param state
     * @param country
     * @param postalCode
     * @param lat
     * @param lon
     */
    public void addRestaurant(String name, String address, String city, String state, String country, String postalCode, String lat, String lon) {
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

    public void onNavigateToHome() {
        HomeFragment homeFragment = new HomeFragment(this);
        this.mainView.displayFragment(homeFragment, false, "home", 0);
    }

    public void onNavigateToMyProfile(List<Review> reviewsList) {
        profileFragment = new UserProfileFragment(this, curUser, reviewsList);
        this.mainView.displayFragment(profileFragment, true, "profile", 0);
    }

    public void onNavigateToMyFriends() {
        FriendsFragment friendsFragment = new FriendsFragment();
        this.mainView.displayFragment(friendsFragment, true, "friends", 0);
    }

    public void getUserReviewsNavToProfile() {
        FStoreReviewsDataRepo repo = new FStoreReviewsDataRepo();
        repo.getReviews("username", curUser.getUsername(), new IDataRepositoryCallback() {
            @Override
            public void onSuccess(Object result) {
                List<Review> reviewsList = (List<Review>) result;
                onNavigateToMyProfile(reviewsList);
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("Failed to get reviews from Firestore" + e.getMessage());
            }
        });
    }

    private void loadRestaurants(){
        FStoreRestaurantDataRepo repo = new FStoreRestaurantDataRepo();
        repo.getAllRestaurants(new IDataRepositoryCallback() {
            @Override
            public void onSuccess(Object result) {
                List<Restaurant> restaurants = (List<Restaurant>) result;
                lib.loadRestaurants(restaurants);
                System.out.println("Loaded " + restaurants.size() + " restaurants from Firestore");
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("Failed to get restaurants from Firestore" + e.getMessage());
            }
        });
    }
}
