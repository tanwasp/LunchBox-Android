package edu.vassar.cmpu203.lunchbox;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.espresso.NoMatchingViewException;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.vassar.cmpu203.lunchbox.controller.MainActivity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.clearText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.instanceOf;

import android.os.SystemClock;
import android.view.View;

@RunWith(AndroidJUnit4.class)
public class SearchRestaurantTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);


    /**
     * Tests the search restaurants functionality of the app
     */
    @Test
    public void testSearchFunctionality() {
        // log in if necessary
        try {
            onView(withText("Log In")).check(matches(isDisplayed()));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.password)).perform(typeText("abc123"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.login_button)).perform(click());
            SystemClock.sleep(1000);
        } catch (NoMatchingViewException e) {}

        // Assuming you have a search input field and a search button in your SearchFragment
        Espresso.onView(withId(R.id.btnNavigateToSearch)).perform(click());
        SystemClock.sleep(1000);
        Espresso.onView(withId(R.id.searchTermText)).perform(typeText("Test Restaurant"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.searchButton)).perform(click());
        Espresso.onView(withId(R.id.searchResultsRecyclerView)).check(matches(isDisplayed()));
    }

    /**
     * Tests the search restaurants functionality of the app by searching for the restaurant by the search term "Sushi"
     */
    @Test
    public void testSpecificSearchFunctionality(){
        // log in if necessary
        try {
            onView(withText("Log In")).check(matches(isDisplayed()));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.password)).perform(typeText("abc123"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.login_button)).perform(click());
            SystemClock.sleep(1000);
        } catch (NoMatchingViewException e) {}

        onView(withId(R.id.btnNavigateToSearch)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.searchTermText)).perform(typeText("p"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        SystemClock.sleep(1000);
        // Check if all restaurants with 'p' in the name appear
        onView(withText("Maggies Place")).check(matches(isDisplayed()));
        onView(withText("Le Perigord")).check(matches(isDisplayed()));
        onView(withText("Parkside Restaurant")).check(matches(isDisplayed()));
    }

    /**
     * Tests the empty search functionality of the app by searching for the restaurant by the search term "" which should return all restaurants
     */
    @Test
    public void testEmptySearchFunctionality(){
        // log in if necessary
        try {
            onView(withText("Log In")).check(matches(isDisplayed()));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.password)).perform(typeText("abc123"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.login_button)).perform(click());
            SystemClock.sleep(1000);
        } catch (NoMatchingViewException e) {}

        Espresso.onView(ViewMatchers.withId(R.id.btnNavigateToSearch)).perform(ViewActions.click());
        SystemClock.sleep(1000);
        onView(withId(R.id.searchButton)).perform(click());

        onView(withId(R.id.searchResultsRecyclerView)).check(matches(isDisplayed()));
    }

    /**
     * Tests the no results message functionality of the app by searching for the restaurant by the search term "I just really really like food, dude"
     */

    @Test
    public void testNoResultsSearchFunctionality(){
        // log in if necessary
        try {
            onView(withText("Log In")).check(matches(isDisplayed()));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.password)).perform(typeText("abc123"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.login_button)).perform(click());
            SystemClock.sleep(1000);
        } catch (NoMatchingViewException e) {}

        onView(withId(R.id.btnNavigateToSearch)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.searchTermText)).perform(typeText("I just really really like food, dude"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        // Testing for no results message
        onView(withText("Sorry, no restaurants match the given criteria")).check(matches(isDisplayed()));
    }

    /**
     * Tests the search by price functionality of the app
     */
    @Test
    public void testSearchByPrice() {
        // log in if necessary
        try {
            onView(withText("Log In")).check(matches(isDisplayed()));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.password)).perform(typeText("abc123"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.login_button)).perform(click());
            SystemClock.sleep(1000);
        } catch (NoMatchingViewException e) {}

        // Navigate to search
        onView(withId(R.id.btnNavigateToSearch)).perform(click());
        SystemClock.sleep(2000);

        // Open spinner and select "$$"
        onView(withId(R.id.priceFilterSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("$$"))).perform(click());

        onView(withId(R.id.searchButton)).perform(click());

        // Check if restaurants with "$$" are displayed and those with "$$$", "$" are not
        onView(withText("Mezzaluna")).check(matches(isDisplayed()));
        onView(withText("cafe")).check(doesNotExist());

        // Check if RecyclerView is displayed
        onView(withId(R.id.searchResultsRecyclerView)).check(matches(isDisplayed()));
    }

    /**
     * Tests the search by distance functionality of the app
     */
    @Test
    public void testSearchByDistance(){
        // log in if necessary
        try {
            onView(withText("Log In")).check(matches(isDisplayed()));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.password)).perform(typeText("abc123"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.login_button)).perform(click());
            SystemClock.sleep(1000);
        } catch (NoMatchingViewException e) {}

        onView(withId(R.id.btnNavigateToSearch)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.distanceFilterEditText)).perform(typeText("5380"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        onView(withText("Tov Kosher Kitchen")).check(matches(isDisplayed()));

        onView(withId(R.id.distanceFilterEditText)).perform(clearText());
        onView(withId(R.id.distanceFilterEditText)).perform(typeText("5385"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        onView(withText("Viand Cafe")).check(matches(isDisplayed()));
    }

    /**
     * Tests the sort by proximity functionality of the app
     */
    @Test
    public void testSearchSortByProximity(){
        // log in if necessary
        try {
            onView(withText("Log In")).check(matches(isDisplayed()));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.password)).perform(typeText("abc123"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.login_button)).perform(click());
            SystemClock.sleep(1000);
        } catch (NoMatchingViewException e) {}

        onView(withId(R.id.btnNavigateToSearch)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.proximityRadioButton)).perform(click());
        onView(withId(R.id.searchButton)).perform(click());
        SystemClock.sleep(1000);

        onView(withText("Great Kills Yacht Club")).check(matches(isDisplayed()));
    }




}
