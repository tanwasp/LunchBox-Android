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

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.instanceOf;

import android.os.SystemClock;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.controller.MainActivity;

@RunWith(AndroidJUnit4.class)
public class AddReviewTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);


    /**
     * Tests adding a review to a restaurant.
     */
    @Test
    public void testAddReview() {
        // log in to app
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        SystemClock.sleep(1000);

        // Navigate to the search page
        onView(withId(R.id.btnNavigateToSearch)).perform(click());

        // Search for a restaurant
        onView(withId(R.id.searchTermText)).perform(typeText("cafe"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        // Click on the top restaurant that appears
        onView(withId(R.id.searchResultsRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(1000);

        onView(withId(R.id.btnNavigateToPostReview)).perform(click());

        // Fill in the review details
        onView(withId(R.id.editTextComment)).perform(typeText("Cozy atmosphere and good food"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.ratingBar)).perform(ViewActions.click());
        onView(withId(R.id.spinnerPrice)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("$$"))).perform(click());

        // Submit the review
        onView(withId(R.id.buttonAddReview)).perform(click());

        Espresso.pressBack();

        // Verify submission
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.searchResultsRecyclerView)) .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withText("Cozy atmosphere and good food")).check(matches(isDisplayed()));
        onView(withText("$$")).check(matches(isDisplayed()));
    }
}
