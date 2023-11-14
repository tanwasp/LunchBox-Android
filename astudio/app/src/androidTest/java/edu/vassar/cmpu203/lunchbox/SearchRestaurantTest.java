package edu.vassar.cmpu203.lunchbox;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.vassar.cmpu203.lunchbox.controller.MainActivity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.instanceOf;

import android.view.View;

@RunWith(AndroidJUnit4.class)
public class SearchRestaurantTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testSearchFunctionality() {
        // Assuming you have a search input field and a search button in your SearchFragment
        Espresso.onView(withId(R.id.btnNavigateToSearch)).perform(click());
        Espresso.onView(withId(R.id.searchTermText)).perform(typeText("Test Restaurant"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.searchButton)).perform(click());
        Espresso.onView(withId(R.id.searchResultsRecyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.searchTermText)).perform(typeText("Test Restaurant"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.searchResultsRecyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    public void testSpecificSearchFunctionality(){
        onView(withId(R.id.btnNavigateToSearch)).perform(click());
        onView(withId(R.id.searchTermText)).perform(typeText("SUSHI"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        onView(withText("Sushi Train")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withText("WIN Bubble Tea and Sushi")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withText("Sushi Kingdom")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testEmptySearchFunctionality(){
        Espresso.onView(ViewMatchers.withId(R.id.btnNavigateToSearch)).perform(ViewActions.click());
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.searchResultsRecyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testNoResultsSearchFunctionality(){
        onView(withId(R.id.btnNavigateToSearch)).perform(click());
        onView(withId(R.id.searchTermText)).perform(typeText("I just really really like food, dude"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        onView(withText("Sorry, no restaurants match the given criteria")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testSearchByDistance() {
        onView(withId(R.id.btnNavigateToSearch)).perform(click());
        onView(withId(R.id.distanceFilterEditText)).perform(typeText("10"), ViewActions.closeSoftKeyboard()); // Assuming '10' is a valid distance
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.searchResultsRecyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testSearchByPrice() {
        onView(withId(R.id.btnNavigateToSearch)).perform(click());
        onView(withId(R.id.priceFilterSpinner)).perform(click());
        onData(Matchers.allOf(is(instanceOf(String.class)), is("$$"))).perform(click());
        onView(withId(R.id.searchButton)).perform(click());
        onView(withText("$$")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Matchers.not(onView(withText("$$$")).check(ViewAssertions.matches(ViewMatchers.isDisplayed())));
        Matchers.not(onView(withText("$")).check(ViewAssertions.matches(ViewMatchers.isDisplayed())));
        onView(withId(R.id.searchResultsRecyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
