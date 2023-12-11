package edu.vassar.cmpu203.lunchbox.model.data_repositories;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.vassar.cmpu203.lunchbox.model.FirestoreSingleton;
import edu.vassar.cmpu203.lunchbox.model.Review;
import edu.vassar.cmpu203.lunchbox.model.User;

public class FStoreReviewsDataRepo implements IReviewsDataRepo{
    /**
     * Gets all reviews for a given restaurant
     * @param field the field to search by
     * @param id the id of the restaurant
     * @param callback the callback to call when the operation is complete
     */

    @Override
    public void getReviews(String field, String id, IDataRepositoryCallback callback) {
        FirebaseFirestore db = FirestoreSingleton.getInstance().getFirestore();
        db.collection("reviews")
                .whereEqualTo(field, id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Review> reviews = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Review review = document.toObject(Review.class);
                            Timestamp timestamp = document.getTimestamp("Date");
                            if (timestamp != null) {
                                review.setDate(timestamp.toDate());
                            } else {
                                review.setDate(new Date());
                            }
                            reviews.add(review);
                        }
                        Collections.sort(reviews, (r1, r2) -> r2.getDate().compareTo(r1.getDate()));
                        callback.onSuccess(reviews);
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    /**
     * Gets all reviews for a given user
     * @param callback the callback to call when the operation is complete
     */
    @Override
    public void getAllReviews(IDataRepositoryCallback callback) {
        FirebaseFirestore db = FirestoreSingleton.getInstance().getFirestore();
        db.collection("reviews")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Review> reviews = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Review review = document.toObject(Review.class);
                            review.setReviewId(document.getId());
                            Timestamp timestamp = document.getTimestamp("Date");
                            if (timestamp != null) {
                                review.setDate(timestamp.toDate());
                            } else {
                                review.setDate(new Date());
                            }
                            reviews.add(review);
                        }
                        Collections.sort(reviews, (r1, r2) -> r2.getDate().compareTo(r1.getDate()));
                        callback.onSuccess(reviews);
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }
    /**
     * adds review to database
     * @param review the review to add
     * @param callback the callback to call when the operation is complete
     */
    @Override
    public void addReview(Review review, IDataRepositoryCallback callback) {
        FirebaseFirestore db = FirestoreSingleton.getInstance().getFirestore();
        Map<String, Object> reviewData = new HashMap<>();
        reviewData.put("firebaseUid", review.getUid());
        reviewData.put("username", review.getUsername());
        reviewData.put("restaurantId", review.getRestaurantId());
        reviewData.put("rating", review.getRating());
        reviewData.put("body", review.getBody());
        reviewData.put("priceRange", review.getPriceRange());
        reviewData.put("Date", review.getDate());
        reviewData.put("restaurantName", review.getRestaurantName());

        DocumentReference reviewDocRef = db.collection("reviews").document(review.getReviewId());

        reviewDocRef.set(reviewData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(task.getResult());
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }
}
