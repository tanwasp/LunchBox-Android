package edu.vassar.cmpu203.lunchbox;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

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

import android.os.SystemClock;

@RunWith(AndroidJUnit4.class)
public class AddRestaurantTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);


    /**
     * Tests the functionality of adding a restaurant.
     */
    @Test
    public void testAddRestaurantFunctionality() {
        // log in to app
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        SystemClock.sleep(1000);

        // Navigate to Add Restaurant
        onView(withId(R.id.btnNavigateToSearch)).perform(click());

        onView(withId(R.id.searchResultsRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(1000);


        String randomNumber = String.valueOf((int) (Math.random() * 10000));
        String restaurantName = "Magic Bowl" + randomNumber;

        // Input restaurant details
        onView(withId(R.id.editTextRestaurantName)).perform(typeText(restaurantName), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("123 MG Street"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextCity)).perform(typeText("Test City"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextState)).perform(typeText("Test State"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextCountry)).perform(typeText("Test Country"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextPostalCode)).perform(typeText("12345"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextLatitude)).perform(typeText("40.7128"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextLongitude)).perform(typeText("-74.0060"), ViewActions.closeSoftKeyboard());

        // Click the Add Restaurant button
        onView(withId(R.id.buttonAddRestaurant)).perform(click());
        onView(withText(restaurantName)).check(matches(isDisplayed()));

        Espresso.pressBack();

        onView(withId(R.id.searchTermText)).perform(typeText("magic"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        SystemClock.sleep(1000);

      // Check that the restaurant was added
        onView(withText(restaurantName)).check(matches(isDisplayed()));
    }
}
