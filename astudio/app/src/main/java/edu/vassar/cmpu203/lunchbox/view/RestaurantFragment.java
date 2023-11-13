package edu.vassar.cmpu203.lunchbox.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentRestaurantBinding;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.model.Review;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.ReviewAdapter;

public class RestaurantFragment extends Fragment implements IRestaurantView{
    private final Listener listener;
    private FragmentRestaurantBinding binding;
    private final Restaurant restaurant;
    private RecyclerView reviewsRecyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewsList;

    public RestaurantFragment(@NonNull Listener listener, Restaurant restaurant, List<Review> reviewsList){
        this.listener = listener;
        this.restaurant = restaurant;
        this.reviewsList = reviewsList;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentRestaurantBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        reviewsRecyclerView = view.findViewById(R.id.reviewRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // Initialize your adapter with an empty list or null
        // Inside SearchFragment onViewCreated method
        reviewAdapter = new ReviewAdapter(view.getContext(), new ArrayList<>());
        reviewsRecyclerView.setAdapter(reviewAdapter);


        binding.restaurantName.setText(restaurant.getName());
        binding.restaurantRating.setText(String.valueOf(restaurant.getRatingDisplay()));
        binding.priceRange.setText(restaurant.getDollarSigns(restaurant.getPriceRangeDisplay()));
        binding.address.setText(restaurant.getAddress());
        this.binding.btnNavigateToPostReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listener.onNavigateToPostReview(restaurant.getRestaurantId());
            }
        });

        if (reviewsList != null) {
            reviewAdapter.setReviews(reviewsList);
            reviewAdapter.notifyDataSetChanged();

        }
    }



}