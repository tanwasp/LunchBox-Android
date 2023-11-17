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

    /**
     * Constructor for RestaurantAdapter
     * @param context
     * @param restaurants
     * @param listener
     */
    public RestaurantAdapter(Context context, List<Restaurant> restaurants, OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.restaurants = restaurants;
        this.listener = listener;
    }

    /**
     * Creates a new RecyclerView.ViewHolder and initializes some private fields to be used by RecyclerView.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
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

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
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

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    @Override
    public int getItemCount() {
        return restaurants.size()+1;
    }

    /**
     * Sets the restaurants list to the new list of restaurants and notifies the adapter to refresh the RecyclerView.
     * @param newRestaurants
     */
    public void setRestaurants(List<Restaurant> newRestaurants) {
        this.restaurants = newRestaurants;
        notifyDataSetChanged(); // Notify the adapter to refresh the RecyclerView
    }


    /**
     * Returns the view type of the item at position for the purposes of view recycling.
     * @param position position to query
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == restaurants.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }


}
