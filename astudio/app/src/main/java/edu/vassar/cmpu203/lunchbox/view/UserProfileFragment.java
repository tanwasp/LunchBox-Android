package edu.vassar.cmpu203.lunchbox.view;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
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
//    private final IUserProfileFragment.Listener listener;
    private FragmentUserProfileBinding binding;
    private User user;
    private RecyclerView reviewsRecyclerView;
    private ReviewAdapterUserProf reviewAdapter;
    private List<Review> reviewsList;
    private Listener listener;

    public UserProfileFragment(){

    }

    public static UserProfileFragment newInstance(User user, ArrayList<Review> reviewsList) {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", (Serializable) user);
        args.putSerializable("reviewsList", reviewsList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable("user");
            reviewsList = (ArrayList<Review>) getArguments().getSerializable("reviewsList");
        }
    }

//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        this.binding = FragmentUserProfileBinding.inflate(inflater);
//        return this.binding.getRoot();
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        if (user != null) {
//            binding.usernameTextView.setText(user.getUsername());
            String dateToString = formatDate(user.getJoinedDate());
            binding.dateJoinedTextView.setText("Member since "+dateToString);
        }

        // Initialize the RecyclerView
        setupRecyclerView(view);

        // Set up button click listeners
        this.binding.button.setOnClickListener(v -> listener.onNavigateToMyFriends());


    }

    private String formatDate(Date date) {
        try {
            DateFormat df = new SimpleDateFormat("MMMM yyyy");
            return df.format(date);
        } catch (Exception e) {
            return "";
        }
    }


    private void setupRecyclerView(View view) {
        reviewsRecyclerView = view.findViewById(R.id.reviewsRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        reviewAdapter = new ReviewAdapterUserProf(view.getContext(), reviewsList != null ? reviewsList : new ArrayList<>());
        reviewsRecyclerView.setAdapter(reviewAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IUserProfileFragment.Listener) {
            listener = (IUserProfileFragment.Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement IUserProfileFragment.Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
