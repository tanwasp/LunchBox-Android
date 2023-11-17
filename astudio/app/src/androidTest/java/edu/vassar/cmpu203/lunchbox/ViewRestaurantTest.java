package edu.vassar.cmpu203.lunchbox;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.vassar.cmpu203.lunchbox.controller.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;

@RunWith(AndroidJUnit4.class)
public class ViewRestaurantTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testViewRestaurantDetails() {
        // Navigate to Search
        onView(withId(R.id.btnNavigateToSearch)).perform(click());

        onView(withId(R.id.searchTermText)).perform(typeText("Moreno"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());

        onView(withId(R.id.searchResultsRecyclerView)) .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(1000);

        onView(withId(R.id.restaurantName)).check(matches(withText("Moreno Bakery")));
        onView(withId(R.id.restaurantRating)).check(matches(isDisplayed()));
        onView(withId(R.id.priceRange)).check(matches(isDisplayed()));
        onView(withId(R.id.address)).check(matches(isDisplayed()));

        onView(withId(R.id.reviewRecyclerView)).check(matches(isDisplayed()));

    }

    @Test
    public void testNavigateToPostReview() {
        onView(withId(R.id.btnNavigateToSearch)).perform(click());

        onView(withId(R.id.searchTermText)).perform(typeText("Moreno"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.searchResultsRecyclerView)) .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(1000);
        onView(withId(R.id.btnNavigateToPostReview)).check(matches(isClickable()));
    }

    @Test
    public void testTextViewDisplays() {
        onView(withId(R.id.btnNavigateToSearch)).perform(click());
        onView(withId(R.id.searchTermText)).perform(typeText("Moreno"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());

        onView(withId(R.id.searchResultsRecyclerView)) .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(1000);

        onView(withText("Moreno Bakery")).check(matches(isDisplayed()));
        onView(withText("737 W Brandon Blvd")).check(matches(isDisplayed()));
        onView(withText("$$")).check(matches(isDisplayed()));
        onView(withText("3.3")).check(matches(isDisplayed()));

    }
}