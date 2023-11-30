package edu.vassar.cmpu203.lunchbox.view;

import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.controller.MainActivity;
import edu.vassar.cmpu203.lunchbox.databinding.MainBinding;
import edu.vassar.cmpu203.lunchbox.databinding.ContentMainBinding;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.model.Review;

public class MainView implements IMainView{

    FragmentManager fmanager; // lets us perform fragment transactions
    MainBinding binding; // gives us access to all the graphical components in res/layout/main.xml
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    /**
     * Constructor method.
     * @param activity The android activity the screen is associated with.
     */
    public MainView(FragmentActivity activity){
        this.fmanager = activity.getSupportFragmentManager();
        this.binding = MainBinding.inflate(activity.getLayoutInflater());
    }

    /**
     * Retrieve the graphical widget (android view) at the root of the screen hierarchy/
     * @return the screen's root android view (widget)
     */
    @Override
    public View getRootView() {
        return this.binding.getRoot();
    }


    public void setupNavigationDrawer(MainActivity activity) {
        // Setup the Toolbar
        activity.setSupportActionBar(binding.appBarMain.toolbar);

        // Initialize DrawerLayout and NavigationView using binding
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Setup AppBarConfiguration
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile)
                .setOpenableLayout(drawer)
                .build();


        // Setup NavController
        System.out.println("fragment container id is: " + R.id.nav_host_fragment_content_main);

        NavHostFragment navHostFragment = (NavHostFragment) activity.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
        } else {
            throw new IllegalStateException("NavHostFragment not found!");
        }

        // Setup ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, binding.appBarMain.toolbar,
                R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_profile){
                    activity.getUserReviewsNavToProfile();
                    return true;
                // handle other cases
            }
            return false;
        });

    }

    public AppBarConfiguration getAppBarConfiguration() {
        return appBarConfiguration;
    }

    public NavController getNavController(){
        return navController;
    }

    public DrawerLayout getDrawerLayout() {
        return binding.drawerLayout;
    }



    /**
     * Replaces the contents of the screen's fragment container with the one passed in as an argument.
     *
     * @param fragment The fragment to be displayed.
     * @param addToStack true if this transaction should be reversible, false otherwise
     * @param tag the name this transaction can be referred by.
     * @param popCount the number of times the the fragment should pop
     */

    @Override
    public void displayFragment(Fragment fragment, boolean addToStack, String tag, int popCount) {
        FragmentTransaction ft = fmanager.beginTransaction();
//        ft.replace(this.binding.fragmentContainer.getId(), fragment, tag);
        ft.replace(R.id.nav_host_fragment_content_main, fragment, tag);
        for (int count = 0; count < popCount; count++) fmanager.popBackStack();

        if (addToStack) ft.addToBackStack(tag);
        ft.commit();
    }

    public void showAppBar() {
        // Make the AppBarLayout visible
        binding.appBarMain.toolbar.setVisibility(View.VISIBLE);
    }

    public void hideAppBar() {
        // Hide the AppBarLayout
        binding.appBarMain.toolbar.setVisibility(View.GONE);
    }


    /**
     * Displays the restaurant details screen.
     * @param searchResults
     */
    public void displaySearchResults(List<Restaurant> searchResults) {
        Fragment currentFragment = fmanager.findFragmentByTag("search");
        if (currentFragment instanceof SearchFragment) {
            ((SearchFragment) currentFragment).updateSearchResults(searchResults);
            ((SearchFragment)currentFragment).showNoResultsMessage(searchResults.isEmpty());
        }
    }

    /**
     * Clears the back stack.
     */
    public void clearBackStack() {
        fmanager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
