package edu.vassar.cmpu203.lunchbox.view.recyclerview;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import edu.vassar.cmpu203.lunchbox.R;

public class RestaurantFooterViewHolder extends RecyclerView.ViewHolder {
    public Button addRestaurantFooterButton;

    public RestaurantFooterViewHolder(View view) {
        super(view);
        addRestaurantFooterButton = view.findViewById(R.id.addRestaurantFooterButton);
    }
}