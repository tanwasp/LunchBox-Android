package edu.vassar.cmpu203.lunchbox.view.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.vassar.cmpu203.lunchbox.R;
public class RestaurantViewHolder extends RecyclerView.ViewHolder{
    TextView nameView;
    TextView ratingView;
    TextView priceRangeView;
    TextView addressView;

    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = (TextView) itemView.findViewById(R.id.restaurantNameTextView);
        ratingView = (TextView) itemView.findViewById(R.id.ratingTextView);
        priceRangeView = (TextView) itemView.findViewById(R.id.priceRangeTextView);
        addressView = (TextView) itemView.findViewById(R.id.locationTextView);
    }
}
