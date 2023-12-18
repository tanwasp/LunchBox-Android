package edu.vassar.cmpu203.lunchbox.view.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.model.Review;
import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.view.IAddReviewView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder>{
    private LayoutInflater inflater;
    private List<Review> reviews;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onNavigateToReview(Review review);
    }

    /**
     * Constructor for ReviewAdapter
     * @param context
     * @param reviews
     */
    public ReviewAdapter(Context context, List<Review> reviews, ReviewAdapter.OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.reviews = reviews;
        this.listener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.usernameView.setText(review.getUsername());
        holder.ratingView.setText(String.valueOf(review.getRating()));
        holder.reviewBodyView.setText(review.getBody());
        String dateToString;
        try {
            DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            dateToString = df.format(review.getDate());
        } catch (Exception e) {
            dateToString = "";
        }
        holder.dateView.setText(dateToString);

        Review reviewSelect = reviews.get(position);
        ReviewViewHolder reviewHolder = (ReviewViewHolder) holder;
        reviewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onNavigateToReview(reviewSelect);
                }
            }
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return
     */
    @Override
    public int getItemCount() {
        return reviews.size();
    }

    /**
     * Sets the reviews to be displayed in the RecyclerView
     * @param newReviews
     */
    public void setReviews(List<Review> newReviews) {
        this.reviews = newReviews;
        notifyDataSetChanged(); // Notify the adapter to refresh the RecyclerView
    }



}
