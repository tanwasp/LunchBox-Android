package edu.vassar.cmpu203.lunchbox.view.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.model.Review;
import edu.vassar.cmpu203.lunchbox.R;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder>{
    private LayoutInflater inflater;
    private List<Review> reviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        this.inflater = LayoutInflater.from(context);
        this.reviews = reviews;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.usernameView.setText(review.getUsername());
        holder.ratingView.setText(String.valueOf(review.getRating()));
        holder.reviewBodyView.setText(review.getBody());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviews(List<Review> newReviews) {
        this.reviews = newReviews;
        notifyDataSetChanged(); // Notify the adapter to refresh the RecyclerView
    }



}
