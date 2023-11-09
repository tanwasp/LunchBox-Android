package edu.vassar.cmpu203.lunchbox.view.recyclerview;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.vassar.cmpu203.lunchbox.model.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder>{
    private Context context;
    private ArrayList<Restaurant> restaurants;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.nameView.setText(restaurant.getName());
        holder.ratingView.setText(restaurant.getRating());
        holder.priceRangeView.setText(restaurant.getPriceRange());
        holder.addressView.setText(restaurant.getAddress());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

}
