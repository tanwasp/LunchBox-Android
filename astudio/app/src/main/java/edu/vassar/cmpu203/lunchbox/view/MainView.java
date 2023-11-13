package edu.vassar.cmpu203.lunchbox.view;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import edu.vassar.cmpu203.lunchbox.databinding.MainBinding;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.model.Review;

public class MainView implements IMainView{

    FragmentManager fmanager; // lets us perform fragment transactions
    MainBinding binding; // gives us access to all the graphical components in res/layout/main.xml

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

    /**
     * Replaces the contents of the screen's fragment container with the one passed in as an argument.
     *
     * @param fragment The fragment to be displayed.
     * @param addToStack true if this transaction should be reversible, false otherwise
     * @param tag the name this transaction can be referred by.
     */

    @Override
    public void displayFragment(Fragment fragment, boolean addToStack, String tag) {
        FragmentTransaction ft = fmanager.beginTransaction();
        ft.replace(this.binding.fragmentContainer.getId(), fragment, tag);
        if (addToStack) ft.addToBackStack(tag);
        ft.commit();
    }

    public void displaySearchResults(ArrayList<Restaurant> searchResults) {
        Fragment currentFragment = fmanager.findFragmentByTag("search");
        if (currentFragment instanceof SearchFragment) {
            ((SearchFragment) currentFragment).updateSearchResults(searchResults);
            ((SearchFragment)currentFragment).showNoResultsMessage(searchResults.isEmpty());
        }
    }




}
