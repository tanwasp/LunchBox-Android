package edu.vassar.cmpu203.lunchbox.model;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReviewsLibraryTest {

    @Test
    public void testAddReview() {
        ReviewsLibrary reviewsLibrary = new ReviewsLibrary();
        User testUser = new User("default", 30, -90);

        // Add a review
        String reviewId = reviewsLibrary.addReview(testUser, "restaurant0", 4.0f, "Delicious!", 2);

        // Retrieve the added review
        Review addedReview = reviewsLibrary.getReviews();

        // Assert that the retrieved review matches the added review
        assertNotNull(addedReview);
        assertEquals("testuser", addedReview.getUsername());
        assertEquals("restaurantX", addedReview.getRestaurantId());
        assertEquals(4.0f, addedReview.getRating(), 0.01);
        assertEquals("Great food!", addedReview.getReviewText());
        assertEquals(2, addedReview.getPriceRange());
    }

    @Test
    public void testGetReviews() {
        ReviewsLibrary reviewsLibrary = new ReviewsLibrary();

        // Create a sample user for testing
        User testUser = new User("testuser", "Test User");

        // Add a review
        String reviewId = reviewsLibrary.addReview(testUser, "restaurantX", 4.0f, "Great food!", 2);

        // Retrieve the added review
        ArrayList<Review> retrievedReviews = reviewsLibrary.getReviews(new ArrayList<>(Collections.singletonList(reviewId)));

        // Assert that the retrieved reviews list is not null and has one element
        assertNotNull(retrievedReviews);
        assertEquals(1, retrievedReviews.size());

        // Assert that the retrieved review matches the added review
        Review addedReview = retrievedReviews.get(0);
        assertEquals("testuser", addedReview.getUsername());
        assertEquals("restaurantX", addedReview.getRestaurantId());
        assertEquals(4.0f, addedReview.getRating(), 0.01);
        assertEquals("Great food!", addedReview.getReviewText());
        assertEquals(2, addedReview.getPriceRange());
    }
}
