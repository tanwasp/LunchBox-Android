package edu.vassar.cmpu203.lunchbox.view.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.vassar.cmpu203.lunchbox.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder{
    TextView usernameView;
    TextView ratingView;
    TextView reviewBodyView;
    public ReviewViewHolder(@NonNull View itemView){
        super(itemView);
        usernameView = (TextView) itemView.findViewById(R.id.usernameTextView);
        ratingView = (TextView) itemView.findViewById(R.id.ratingTextView);
        reviewBodyView = (TextView) itemView.findViewById(R.id.reviewBodyTextView);

    }
}
