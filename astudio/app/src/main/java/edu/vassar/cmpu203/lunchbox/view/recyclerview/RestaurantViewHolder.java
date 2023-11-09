package edu.vassar.cmpu203.lunchbox.view.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantViewHolder extends RecyclerView.ViewHolder{
    TextView nameView;
    TextView ratingView;
    TextView priceRangeView;
    TextView addressView;

    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = (TextView) itemView.findViewById(R.id.restaurant_name);
        ratingView = (TextView) itemView.findViewById(R.id.restaurant_rating);
        priceRangeView = (TextView) itemView.findViewById(R.id.restaurant_price_range);
        addressView = (TextView) itemView.findViewById(R.id.restaurant_address);
    }
}
