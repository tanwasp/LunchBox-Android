package edu.vassar.cmpu203.lunchbox.view;

public interface IManageReviewView {
    public interface Listener {
        void onEditReviewSelected();
        void onDeleteReviewSelected();
    }
}
