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
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Restaurant restaurant);
    }

    public RestaurantAdapter(Context context, List<Restaurant> restaurants, OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.restaurants = restaurants;
        this.listener = listener;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make sure the listener is not null before invoking it
                if (listener != null) {
                    listener.onItemClick(restaurant);
                }
            }
        });
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
