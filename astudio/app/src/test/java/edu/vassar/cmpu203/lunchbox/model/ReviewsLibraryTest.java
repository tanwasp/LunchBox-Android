package edu.vassar.cmpu203.lunchbox.model;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

public class ReviewsLibraryTest {

    @Test
    public void testAddReview() {
        ReviewsLibrary revLib= new ReviewsLibrary();
        User testUser = new User("default", 30, -90);

        // Add a review to the review library
        String reviewId = revLib.addReview(testUser, "restaurant0", 4.0f, "Delicious!", 2);
        ArrayList<String> reviews = new ArrayList<>();
        reviews.add(reviewId);

        // Retrieve the added review from revlib using ID
        ArrayList<Review> reviewsForRestaurant0 = revLib.getReviews(reviews);
        Review addedReview = reviewsForRestaurant0.get(0);

        // Assert that the retrieved review matches the added review
        assertEquals("default", addedReview.getUsername());
        assertEquals("restaurant0", addedReview.getRestaurantId());
        assertEquals(4.0f, addedReview.getRating(), 0.01);
        assertEquals("Delicious!", addedReview.getBody());
        assertEquals(2, addedReview.priceRange);
    }

    @Test
    public void testGetReviews() {
        ReviewsLibrary revLib = new ReviewsLibrary();
        RestaurantLibrary restLib = new RestaurantLibrary();

        //Specify reviews to get (will be all reviews associated with a restaurant)
        ArrayList<String> reviewIDs = new ArrayList<>(Arrays.asList("review1", "review20"));
        // Retrieve the reviews
        ArrayList<Review> retrievedReviews = revLib.getReviews(reviewIDs);

        // Assert that the reviews retrieved from revLib match the ones specified by the Ids
        assertEquals(2, retrievedReviews.size());

        Review review1 = retrievedReviews.get(0);
        assertEquals("burchdanielle", review1.getUsername());
        assertEquals("restaurant6", review1.getRestaurantId());
        assertEquals(2.5f, review1.getRating(), 0.01);
        String body = "A me try writer off enough. Road hope wall onto foot. Better require until peace. Half official always why who body take. That rate region over task.";
        assertEquals(body, review1.getBody());
        assertEquals(1, review1.priceRange);

        Review review2 = retrievedReviews.get(1);
        assertEquals("vbennett", review2.getUsername());
        assertEquals("restaurant5", review2.getRestaurantId());
        assertEquals(1.5f, review2.getRating(), 0.01);
        body = "Population weight company thank assume loss I. Practice decade left memory. Per structure get prevent store. ank east arm store. White nation or police. Where information process pull long.";
        assertEquals(body, review2.getBody());
        assertEquals(3, review2.priceRange);
    }
}
