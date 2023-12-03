package edu.vassar.cmpu203.lunchbox.model.data_repositories;

import edu.vassar.cmpu203.lunchbox.model.Review;

public interface IReviewsDataRepo {
    void getReviews(String field, String id, IDataRepositoryCallback callback);

    void addReview(Review review, IDataRepositoryCallback callback);

    void getAllReviews(IDataRepositoryCallback callback);
}
