package edu.vassar.cmpu203.lunchbox.controller;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import androidx.appcompat.app.ActionBar;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
//import android.location.LocationRequest;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.location.LocationRequest;


import android.location.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

//import edu.vassar.cmpu203.lunchbox.Manifest;
import android.Manifest;
import android.os.Looper;
import android.util.Log;

import edu.vassar.cmpu203.lunchbox.R;
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
import edu.vassar.cmpu203.lunchbox.view.IUserProfileFragment;

import edu.vassar.cmpu203.lunchbox.view.*;

public class MainActivity extends AppCompatActivity implements IHomeView.Listener, IAddRestaurantView.Listener, ISearchView.Listener, IRestaurantView.Listener, IAddReviewView.Listener, IUserProfileFragment.Listener, ILandingView.Listener {
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
    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    ActivityResultLauncher<Intent> loginActivityResultLauncher;

    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFirebaseAuthListener();
        setupLoginActivityResultLauncher();

        lib = new RestaurantLibrary();
        revLib = new ReviewsLibrary();
        loadReviews();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        this.mainView = new MainView(this);

        setContentView(this.mainView.getRootView());
        mainView.setupNavigationDrawer(this);

        navigateBasedOnAuthenticationStatus();
    }

    public void updateUIBasedOnCurrentFragment() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        System.out.println("currentFragment is " + currentFragment);
        if (currentFragment != null) {
            DrawerLayout drawerLayout = mainView.getDrawerLayout();

            if (currentFragment instanceof HomeFragment || currentFragment instanceof UserProfileFragment) {
                mainView.showAppBar();
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            } else {
                mainView.hideAppBar();
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(this.mainView.getNavController(), this.mainView.getAppBarConfiguration())
                || super.onSupportNavigateUp();
    }


    private void navigateBasedOnAuthenticationStatus() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // User is signed in, navigate to HomeFragment
            updateCurrentUser(currentUser);
            onNavigateToHome();
        } else {
            // No user is signed in, navigate to LandingView
            LandingFragment landingFragment = new LandingFragment(this);
            navigateToFragment(landingFragment, false, "land", 0);
        }
    }

    public void onLogout() {
        FirebaseAuth.getInstance().signOut();
        navigateBasedOnAuthenticationStatus();
        this.mainView.clearBackStack();
        LandingFragment landingFragment = new LandingFragment(this);
        this.mainView.displayFragment(landingFragment, false, "land", 0);
        getSupportFragmentManager().executePendingTransactions();
        updateUIBasedOnCurrentFragment();
    }

    private void setupLocationService() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            fetchLastLocation();
        }
    }

    @Override
    public void onBackPressed() {
        // Call the super method to handle the back button press as usual
        super.onBackPressed();
        getSupportFragmentManager().executePendingTransactions();
        // Now update the UI based on the current fragment
        updateUIBasedOnCurrentFragment();
    }

    private void fetchLastLocation() {

        // Check for permissions again (optional but recommended)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return; // Handle the lack of permission appropriately.
        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);

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
        onNavigateToHome();
    }

    private void updateCurrentUserLocation(float latitude, float longitude) {
        if (curUser != null) {
            curUser.setLoc(latitude, longitude);
        }
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
        navigateToFragment(searchFragment, true, "search", 0);
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


    @Override
    public void onNavigateToRestaurant(Restaurant restaurant, boolean reversible, int popCount) {
        List<Review> reviewsList = revLib.getReviewsByRestaurant(restaurant);
        onNavigateToRestaurant(restaurant, reversible, popCount, reviewsList);
    }

    public void onNavigateToRestaurant(Restaurant restaurant, boolean reversible, int popCount, List<Review> reviewsList) {
        RestaurantFragment restaurantFragment = new RestaurantFragment(this, restaurant, reviewsList);
        navigateToFragment(restaurantFragment, reversible, "restaurant", popCount);
    }

    // RestaurantView.Listener methods

    /**
     * navigates to add review fragment
     *
     * @param restaurantId
     */
    @Override
    public void onNavigateToPostReview(String restaurantId, String restaurantName) {
        AddReviewFragment addRevFragment = new AddReviewFragment(this, restaurantId, restaurantName);
        navigateToFragment(addRevFragment, true, "review form", 0);
    }

    /**
     * adds review to review library and adds review to restaurant
     *
     * @param rating
     * @param comment
     * @param restaurantId
     * @param priceSymbol
     */
    public void onAddReview(float rating, String comment, String restaurantId, int priceSymbol, String restaurantName) {
        // Create the Review object
        UUID uuid = UUID.randomUUID();
        String reviewId = uuid.toString();
        Review newReview = new Review(reviewId, curUser.getUid(), curUser.getUsername(), restaurantId, rating, comment, priceSymbol, null, restaurantName);
        System.out.println("New review: " + newReview);
        // Use FStoreReviewsDataRepo to add the review
        addReviewToFirestore(newReview);
        // Update restaurant details after adding the review
        revLib.addReviewToReviewsLibrary(newReview);
        Restaurant restaurant = lib.getRestaurant(newReview.getRestaurantId());
        if (restaurant != null) {
            restaurant.computeRating(revLib);
            restaurant.computePriceRange(revLib);
            onNavigateToRestaurant(restaurant, true, 2);
        }
    }

    public void addReviewToFirestore(Review newReview) {
        FStoreReviewsDataRepo repo = new FStoreReviewsDataRepo();
        repo.addReview(newReview, new IDataRepositoryCallback() {
            @Override
            public void onSuccess(Object result) {
                System.out.println("Successfully added review to Firestore");
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
        navigateToFragment(addRestaurantFragment, true, "add restaurant", 0);
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
        HomeFragment homeFragment = new HomeFragment();
        navigateToFragment(homeFragment, false, "home",0);
        closeNavigationDrawer();
        updateActionBarTitle("Home");
    }

    public void onNavigateToMyProfile(List<Review> reviewsList) {
        logUserProfileNavigation(reviewsList);
        UserProfileFragment profileFragment = UserProfileFragment.newInstance(curUser, new ArrayList<>(reviewsList));
        navigateToFragment(profileFragment,  false, "profile",0);
        closeNavigationDrawer();
        updateActionBarTitle(curUser.getUsername());
    }

    public void updateActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            SpannableString spannableTitle = new SpannableString(title);
            spannableTitle.setSpan(new ForegroundColorSpan(Color.WHITE), 0, title.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            actionBar.setTitle(spannableTitle);
        }
    }

    private void logUserProfileNavigation(List<Review> reviewsList) {
        Log.d(TAG, "Navigating to user profile for user: " + curUser.getUsername() + " with " + reviewsList.size() + " reviews");
    }

    private void navigateToFragment(Fragment fragment, boolean addToBackStack, String tag, int popCount) {
        this.mainView.displayFragment(fragment, addToBackStack, tag, popCount);
        getSupportFragmentManager().executePendingTransactions();
        updateUIBasedOnCurrentFragment();
    }

    private void closeNavigationDrawer() {
        mainView.getDrawerLayout().closeDrawer(GravityCompat.START);
    }

    public void onNavigateToMyFriends() {
        FriendsFragment friendsFragment = new FriendsFragment();
        navigateToFragment(friendsFragment,  true, "friends",0);
    }

    public void getUserReviewsNavToProfile() {
        List<Review> reviewsList = revLib.getReviewsByUser(curUser);
        onNavigateToMyProfile(reviewsList);
    }

    private void computePriceAndRating(List<Restaurant> restaurants) {
        for (Restaurant r : restaurants) {
            r.computePriceRange(revLib);
            r.computeRating(revLib);
        }
    }

    private void loadRestaurants() {
        FStoreRestaurantDataRepo repo = new FStoreRestaurantDataRepo();
        repo.getAllRestaurants(new IDataRepositoryCallback() {
            @Override
            public void onSuccess(Object result) {
                List<Restaurant> restaurants = (List<Restaurant>) result;
                computePriceAndRating(restaurants);
                lib.loadRestaurants(restaurants);
                System.out.println("Loaded " + restaurants.size() + " restaurants from Firestore");
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("Failed to get restaurants from Firestore" + e.getMessage());
            }
        });
    }

    public void loadReviews() {
        FStoreReviewsDataRepo repo = new FStoreReviewsDataRepo();
        repo.getAllReviews(new IDataRepositoryCallback() {
            @Override
            public void onSuccess(Object result) {
                List<Review> reviews = (List<Review>) result;
                revLib.loadReviews(reviews);
                System.out.println("Loaded " + reviews.size() + " reviews from Firestore");
                loadRestaurants();
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("Failed to get reviews from Firestore" + e.getMessage());
            }
        });
    }
}
