package edu.vassar.cmpu203.lunchbox;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.google.common.base.Predicates.instanceOf;

import static org.hamcrest.Matchers.is;

import android.os.SystemClock;
import android.view.MenuItem;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.vassar.cmpu203.lunchbox.controller.MainActivity;
import edu.vassar.cmpu203.lunchbox.model.FirestoreSingleton;

@RunWith(AndroidJUnit4.class)
public class CheckProfileTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    /**
     * Tests ability to navigate to profile page and view profile info
     */
    @Test
    public void testCheckProfile() throws UiObjectNotFoundException {
        // Log in to account
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

        // Open the navigation drawer
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        SystemClock.sleep(1000); // Wait for drawer to open
        activityRule.getScenario().onActivity(activity -> {
            activity.getUserReviewsNavToProfile(); // Call the method here
        });
        onView(withText("john80")).check(matches(isDisplayed()));
    }

}
