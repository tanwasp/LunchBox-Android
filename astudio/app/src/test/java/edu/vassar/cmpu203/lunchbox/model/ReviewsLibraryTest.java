package edu.vassar.cmpu203.lunchbox.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class ReviewsLibraryTest {

    /**
     * Tests class's ability to add a review (to the review library and corresponding restaurant)
     */
    @Test
    public void testAddReview() {
        ReviewsLibrary revLib= new ReviewsLibrary();
        User testUser = new User("default", 30, -90);

        // Add a review to the review library
        String reviewId = revLib.addReview(testUser, "restaurant0", 4.0f, "Delicious!", 2, "Molly's Restaurant");
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

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Tests class's ability to add a review object to the review library
     */
    @Test
    public void testAddReviewToReviewsLibrary() {
        ReviewsLibrary revLib= new ReviewsLibrary();
        User testUser = new User("default", 30, -90);

        // Add a review to the review library
        Review rev = new Review("review0", "123456", "default", "restaurant100", 4.0f, "Delicious!", 2, parseDate("26-09-2022"), "Molly's Restaurant");
        revLib.addReviewToReviewsLibrary(rev);
        ArrayList<String> reviews = new ArrayList<>();
        reviews.add("review0");

        // Retrieve the added review from revlib using ID
        ArrayList<Review> reviewsForRestaurant0 = revLib.getReviews(reviews);
        Review addedReview = reviewsForRestaurant0.get(0);

        // Assert that the retrieved review matches the added review
        assertEquals("default", addedReview.getUsername());
        assertEquals("restaurant100", addedReview.getRestaurantId());
        assertEquals(4.0f, addedReview.getRating(), 0.01);
        assertEquals("Delicious!", addedReview.getBody());
        assertEquals(2, addedReview.priceRange);
    }

    /**
     * Tests class's ability get reviews for a specific restaurant
     */
    @Test
    public void testGetReviews() {
        ReviewsLibrary revLib = new ReviewsLibrary();
        RestaurantLibrary restLib = new RestaurantLibrary();
        User testUser = new User("default", 30, -90);

        //Specify reviews to get (will be all reviews associated with a restaurant)
        String reviewId1 = revLib.addReview(testUser, "restaurant0", 4.0f, "Delicious!", 2, "Molly's Restaurant");
        String reviewId2 = revLib.addReview(testUser, "restaurant1", 2.5f, "ok...", 1, "Mike's Pizza");
        ArrayList<String> reviewIDs = new ArrayList<>(Arrays.asList(reviewId1, reviewId2));
        // Retrieve the reviews
        ArrayList<Review> retrievedReviews = revLib.getReviews(reviewIDs);

        // Assert that the reviews retrieved from revLib match the ones specified by the Ids
        assertEquals(2, retrievedReviews.size());

        Review review1 = retrievedReviews.get(0);
        assertEquals("default", review1.getUsername());
        assertEquals("Molly's Restaurant", review1.getRestaurantName());
        assertEquals("restaurant0", review1.getRestaurantId());
        assertEquals(4.0f, review1.getRating(), 0.01);
        assertEquals("Delicious!", review1.getBody());
        assertEquals(2, review1.priceRange);

        Review review2 = retrievedReviews.get(1);
        assertEquals("default", review2.getUsername());
        assertEquals("Mike's Pizza", review2.getRestaurantName());
        assertEquals("restaurant1", review2.getRestaurantId());
        assertEquals(2.5f, review2.getRating(), 0.01);
        assertEquals("ok...", review2.getBody());
        assertEquals(1, review2.priceRange);
    }

    /**
     * Tests class's ability get all reviews made by a specific user
     */
    @Test
    public void testGetReviewsByUser() {
        ReviewsLibrary revLib = new ReviewsLibrary();
        RestaurantLibrary restLib = new RestaurantLibrary();
        User testUser1 = new User("Tom", "12345", "user1@gmail.com", 30, -90);
        User testUser2 = new User("Brady", "67890", "user2@gmail.com",30, -90);

        String reviewId1 = revLib.addReview(testUser1, "restaurant0", 4.0f, "Delicious!", 2, "Molly's Restaurant");
        String reviewId2 = revLib.addReview(testUser2, "restaurant1", 2.5f, "ok...", 1, "Mike's Pizza");

        // get the reviews by Tom
        ArrayList<Review> retrievedReviews = revLib.getReviewsByUser(testUser1);

        Review review1 = retrievedReviews.get(0);
        assertEquals(1, retrievedReviews.size());
        assertEquals("Tom", review1.getUsername());
        assertEquals("Molly's Restaurant", review1.getRestaurantName());
        assertEquals("restaurant0", review1.getRestaurantId());
        assertEquals(4.0f, review1.getRating(), 0.01);
        assertEquals("Delicious!", review1.getBody());
        assertEquals(2, review1.priceRange);
    }

    /**
     * Tests class's ability get all reviews for a specific restaurant
     */
    @Test
    public void testGetReviewsByRestaurant() {
        ReviewsLibrary revLib = new ReviewsLibrary();
        RestaurantLibrary restLib = new RestaurantLibrary();
        User testUser = new User("default", "12345", "user1@gmail.com", 30, -90);

        String reviewId1 = revLib.addReview(testUser, "restaurant0", 4.0f, "Delicious!", 2, "Molly's Restaurant");
        String reviewId2 = revLib.addReview(testUser, "restaurant1", 2.5f, "ok...", 1, "Mike's Pizza");

        // get the reviews of Molly's Restaurant
        Restaurant m = new Restaurant("restaurant0", "Molly's Restaurant", "123 Baker St", "NYC", "NY", "USA", "12345", -30, 90);
        ArrayList<Review> retrievedReviews = revLib.getReviewsByRestaurant(m);
        assertEquals(1, retrievedReviews.size());
    }

    /**
     * Tests class's ability get load reviews into the library
     */
    @Test
    public void testLoadReviews() {
        ReviewsLibrary revLib = new ReviewsLibrary();
        // Mock data for testing
        Review mockReview1 = new Review("review1", "user1", "username1", "restaurant1", 4.0f, "Good review", 2, null, "Restaurant One");
        Review mockReview2 = new Review("review2", "user2", "username2", "restaurant2", 3.5f, "Okay review", 3, null, "Restaurant Two");
        List<Review> mockReviewList = Arrays.asList(mockReview1, mockReview2);

        // Call the loadReviews function
        revLib.loadReviews(mockReviewList);

        // Verify that the reviews are loaded into the library
        assertEquals(mockReviewList.size(), revLib.getReviews(new ArrayList<>(Arrays.asList("review1", "review2"))).size());
    }
}
