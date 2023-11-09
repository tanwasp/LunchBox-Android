package edu.vassar.cmpu203.lunchbox.view.recyclerview;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.R;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder>{
    private LayoutInflater inflater;
    private List<Restaurant> restaurants;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
        this.inflater = LayoutInflater.from(context);
        this.restaurants = restaurants;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.nameView.setText(restaurant.getName());
        holder.ratingView.setText(String.valueOf(restaurant.getRating()));
        holder.priceRangeView.setText(restaurant.getDollarSigns(restaurant.getPriceRange()));
        holder.addressView.setText(restaurant.getAddress());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void setRestaurants(List<Restaurant> newRestaurants) {
        this.restaurants = newRestaurants;
        notifyDataSetChanged(); // Notify the adapter to refresh the RecyclerView
    }

}
