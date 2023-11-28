package edu.vassar.cmpu203.lunchbox.model.data_repositories;

import com.google.firebase.Timestamp;
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
    @Override
    public void getReviews(String restaurantId, IDataRepositoryCallback callback) {
        FirebaseFirestore db = FirestoreSingleton.getInstance().getFirestore();
        db.collection("reviews")
                .whereEqualTo("restaurantId", restaurantId)
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
                        callback.onSuccess(reviews);
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

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

        db.collection("reviews")
                .add(reviewData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(task.getResult());
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    @Override
    public void getReviewsByUser(String username, IDataRepositoryCallback callback) {
        FirebaseFirestore db = FirestoreSingleton.getInstance().getFirestore();
        db.collection("reviews")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Review> reviews = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Review review = document.toObject(Review.class);
                            Timestamp timestamp = document.getTimestamp("date");
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
}