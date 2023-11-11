package edu.vassar.cmpu203.lunchbox.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.vassar.cmpu203.lunchbox.databinding.FragmentRestaurantBinding;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;

public class RestaurantFragment extends Fragment implements IRestaurantView{
    private final Listener listener;
    private FragmentRestaurantBinding binding;
    private final Restaurant restaurant;

    public RestaurantFragment(@NonNull Listener listener, Restaurant restaurant){
        this.listener = listener;
        this.restaurant = restaurant;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentRestaurantBinding.inflate(inflater);
        return this.binding.getRoot();
    }


    public void OnViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.restaurantName.setText(restaurant.getName());
        binding.restaurantRating.setText(String.valueOf(restaurant.getRating()));
        binding.priceRange.setText(restaurant.getDollarSigns(restaurant.getPriceRange()));
        binding.address.setText(restaurant.getAddress());
        this.binding.btnNavigateToPostReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listener.onNavigateToPostReview();
            }
        });
    }

}