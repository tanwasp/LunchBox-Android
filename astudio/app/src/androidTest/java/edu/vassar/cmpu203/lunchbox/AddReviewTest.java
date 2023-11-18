package edu.vassar.cmpu203.lunchbox;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.controller.MainActivity;

@RunWith(AndroidJUnit4.class)
public class AddReviewTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests adding a review to a restaurant.
     */
    @Test
    public void testAddReview() {
        // Navigate to the search page
        onView(withId(R.id.btnNavigateToSearch)).perform(click());

        // Search for a restaurant
        onView(withId(R.id.searchTermText)).perform(typeText("McDonald"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        // Click on the top restaurant that appears
        onView(withId(R.id.searchResultsRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(1000);

        onView(withId(R.id.btnNavigateToPostReview)).perform(click());

        // Fill in the review details
        onView(withId(R.id.editTextComment)).perform(typeText("I absolutely hate the food here!!"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.ratingBar)).perform(ViewActions.click());

        // Submit the review
        onView(withId(R.id.buttonAddReview)).perform(click());

        Espresso.pressBack();

        // Verify submission
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.searchResultsRecyclerView)) .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withText("I absolutely hate the food here!!")).check(matches(isDisplayed()));
    }
}
