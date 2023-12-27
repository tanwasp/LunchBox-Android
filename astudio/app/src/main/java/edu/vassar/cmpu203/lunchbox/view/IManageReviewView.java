package edu.vassar.cmpu203.lunchbox.view;

import edu.vassar.cmpu203.lunchbox.model.Review;

public interface IManageReviewView {
    public interface Listener {
        public void onUpdateReview(Review existingReview, float updatedRating, String updatedComment, int updatedPriceSymbol);
    }
}
