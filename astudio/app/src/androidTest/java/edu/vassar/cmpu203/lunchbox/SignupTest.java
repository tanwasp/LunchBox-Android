package edu.vassar.cmpu203.lunchbox;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.vassar.cmpu203.lunchbox.controller.MainActivity;

@RunWith(AndroidJUnit4.class)
public class SignupTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    /**
     * Tests signing up for an account
     */
    @Test
    public void testSignUp(){
        // choose Log In
        onView(withId(R.id.btnSignup)).perform(click());

        int rand = ((int) (Math.random()*1000));
        String username = "John" + rand;
        String email = "john" + rand + "@gmail.com";
        // enter account info
        onView(withId(R.id.username)).perform(typeText(username), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(email), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc123!"), ViewActions.closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.sign_up_button)).perform(click());
        SystemClock.sleep(5500);

        //
        onView(withText("Welcome to LunchBox!!")).check(matches(isDisplayed()));
    }
}
