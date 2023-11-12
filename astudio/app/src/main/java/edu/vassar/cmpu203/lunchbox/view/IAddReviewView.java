package edu.vassar.cmpu203.lunchbox.view;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.model.Restaurant;

public interface IAddReviewView {

    interface Listener{
        void onAddReview(float rating, String comment, String id, int priceSymbol);
    }

}
