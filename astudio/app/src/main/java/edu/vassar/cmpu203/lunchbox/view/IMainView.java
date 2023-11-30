package edu.vassar.cmpu203.lunchbox.view;

import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import java.util.ArrayList;
import java.util.List;

import edu.vassar.cmpu203.lunchbox.controller.MainActivity;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.model.Review;

/**
 * An interface for the application screen template.
 */
public interface IMainView {

    /**
     * Retrieve the graphical widget (android view) at the root of the screen hierarchy
     *
     * @return the screen's root android view (widget)
     */
    View getRootView();

    /**
     * Replaces the contents of the screen's fragment container with the one passed in as an argument.
     *
     * @param fragment The fragment to be displayed.
     * @param reversible true if this transaction should be reversible, false otherwise
     * @param name the name this transaction can be referred by.
     */
    void displayFragment(Fragment fragment, boolean reversible, String name, int popCount);

    void displaySearchResults(List<Restaurant> searchResults);

    void clearBackStack();

    void setupNavigationDrawer(MainActivity mainActivity);

    NavController getNavController();

    AppBarConfiguration getAppBarConfiguration();

    DrawerLayout getDrawerLayout();

    void showAppBar();

    void hideAppBar();
}
