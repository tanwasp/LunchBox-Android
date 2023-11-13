package edu.vassar.cmpu203.lunchbox.view.recyclerview;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.RestaurantFooterViewHolder;

public class RestaurantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater inflater;
    private List<Restaurant> restaurants;
    private OnItemClickListener listener;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public interface OnItemClickListener {
        void onNavigateToRestaurant(Restaurant restaurant);

        void onNavigateToAddRestaurant();
    }

    public RestaurantAdapter(Context context, List<Restaurant> restaurants, OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.restaurants = restaurants;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = inflater.inflate(R.layout.resto_list_footer_layout, parent, false);
            return new RestaurantFooterViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_restaurant, parent, false);
            return new RestaurantViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            Restaurant restaurant = restaurants.get(position);
            RestaurantViewHolder restaurantHolder = (RestaurantViewHolder) holder;

            restaurantHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onNavigateToRestaurant(restaurant);
                    }
                }
            });

            restaurantHolder.nameView.setText(restaurant.getName());
            restaurantHolder.ratingView.setText(String.valueOf(restaurant.getRating()));
            restaurantHolder.priceRangeView.setText(restaurant.getDollarSigns(restaurant.getPriceRange()));
            restaurantHolder.addressView.setText(restaurant.getAddress());
        }
        else {
            RestaurantFooterViewHolder footerHolder = (RestaurantFooterViewHolder) holder;
            footerHolder.addRestaurantFooterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onNavigateToAddRestaurant();
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return restaurants.size()+1;
    }

    public void setRestaurants(List<Restaurant> newRestaurants) {
        this.restaurants = newRestaurants;
        notifyDataSetChanged(); // Notify the adapter to refresh the RecyclerView
    }


    @Override
    public int getItemViewType(int position) {
        if (position == restaurants.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }


}
