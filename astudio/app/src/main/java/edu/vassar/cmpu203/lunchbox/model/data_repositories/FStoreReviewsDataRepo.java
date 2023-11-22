package edu.vassar.cmpu203.lunchbox.model.data_repositories;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.vassar.cmpu203.lunchbox.model.FirestoreSingleton;
import edu.vassar.cmpu203.lunchbox.model.Review;

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
                            reviews.add(document.toObject(Review.class));
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
        reviewData.put("username", review.getUsername());
        reviewData.put("restaurantId", review.getRestaurantId());
        reviewData.put("rating", review.getRating());
        reviewData.put("body", review.getBody());
        reviewData.put("priceRange", review.getPriceRange());
        // Add other fields as necessary, but exclude reviewId

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
}
