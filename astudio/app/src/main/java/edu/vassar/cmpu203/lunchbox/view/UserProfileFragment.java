package edu.vassar.cmpu203.lunchbox.view;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentRestaurantBinding;
import edu.vassar.cmpu203.lunchbox.model.*;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.ReviewAdapter;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentUserProfileBinding;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.ReviewAdapterUserProf;

public class UserProfileFragment extends Fragment implements IUserProfileFragment{
    private final IUserProfileFragment.Listener listener;
    private FragmentUserProfileBinding binding;
    private User user;
    private RecyclerView reviewsRecyclerView;
    private ReviewAdapterUserProf reviewAdapter;
    private List<Review> reviewsList;

    public UserProfileFragment(@NonNull IUserProfileFragment.Listener listener, User user, List<Review> reviewsList){
        this.listener = listener;
        this.user = user;
        this.reviewsList = reviewsList;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentUserProfileBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // set profile details
        binding.usernameTextView.setText(user.getUsername());
        String dateToString;
        try {
            DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            dateToString = df.format(user.getJoinedDate());
        } catch (Exception e){
            dateToString = "";
        }
        binding.dateJoinedTextView.setText(dateToString);

        // Initialize your adapter with an empty list or null
        reviewsRecyclerView = view.findViewById(R.id.reviewsRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        reviewAdapter = new ReviewAdapterUserProf(view.getContext(), new ArrayList<>());
        reviewsRecyclerView.setAdapter(reviewAdapter);

        // Sets restaurant data in the restaurant fragment
//        binding.restaurantName.setText(restaurant.getName());
//        binding.restaurantRating.setText(String.valueOf(restaurant.getRatingDisplay()));
//        binding.priceRange.setText(restaurant.getDollarSigns(restaurant.getPriceRangeDisplay()));
//        binding.address.setText(restaurant.getAddress());

        if (reviewsList != null) {
            reviewAdapter.setReviews(reviewsList);
            reviewAdapter.notifyDataSetChanged();
        }

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNavigateToMyFriends();
            }
        });

    }
}
