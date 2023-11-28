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

public class UserProfileFragment extends Fragment implements IUserProfileFragment{
    private final IUserProfileFragment.Listener listener;
    private FragmentUserProfileBinding binding;
    private User user;
    private RecyclerView reviewsRecyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewsList;

    public UserProfileFragment(@NonNull IUserProfileFragment.Listener listener, User user, ReviewsLibrary revLib){
        this.listener = listener;
        this.user = user;
        this.reviewsList = revLib.getReviewsByUser(user);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentUserProfileBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // set profile details
        binding.usernameTextView.setText(user.getUsername());
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String dateToString = df.format(user.getJoinedDate());
        binding.dateJoinedTextView.setText(dateToString);

        // Initialize your adapter with an empty list or null
        reviewsRecyclerView = view.findViewById(R.id.reviewsRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        reviewAdapter = new ReviewAdapter(view.getContext(), new ArrayList<>());
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
