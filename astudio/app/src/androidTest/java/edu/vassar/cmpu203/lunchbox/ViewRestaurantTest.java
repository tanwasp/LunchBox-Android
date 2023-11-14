package edu.vassar.cmpu203.lunchbox;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.vassar.cmpu203.lunchbox.controller.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ViewRestaurantTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testViewRestaurantDetails() {
        // Navigate to Search
        onView(withId(R.id.btnNavigateToSearch)).perform(click());

        // Perform a search (assuming "Restaurant Name" is a valid name)
        onView(withId(R.id.searchTermText)).perform(typeText("Restaurant Name"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());

        // Select a restaurant from the search results
        // Assuming "Restaurant Name" is in the RecyclerView and is clickable
        onView(withText("Restaurant Name")).perform(click());

        // Now in RestaurantFragment, verify the details are displayed
        onView(withId(R.id.restaurantName)).check(matches(withText("Restaurant Name")));
        onView(withId(R.id.restaurantRating)).check(matches(isDisplayed()));
        onView(withId(R.id.priceRange)).check(matches(isDisplayed()));
        onView(withId(R.id.address)).check(matches(isDisplayed()));

        // If you have a RecyclerView for reviews, you can check its presence
        onView(withId(R.id.reviewRecyclerView)).check(matches(isDisplayed()));
    }
}
