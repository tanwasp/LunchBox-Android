package edu.vassar.cmpu203.lunchbox.view;

import edu.vassar.cmpu203.lunchbox.model.Review;

public interface IReviewView {
    interface Listener {
        public void onNavigateToEditReview(Review rev);
        public void onDeleteReview(Review rev);
    }
}
