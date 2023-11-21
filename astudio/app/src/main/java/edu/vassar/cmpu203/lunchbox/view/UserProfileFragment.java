package edu.vassar.cmpu203.lunchbox.view;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.databinding.FragmentRestaurantBinding;
import edu.vassar.cmpu203.lunchbox.model.*;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.ReviewAdapter;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentUserProfileBinding;

public class UserProfileFragment extends Fragment implements IUserProfileFragment{
    private final IUserProfileFragment.Listener listener;
    private FragmentUserProfileBinding binding;
    private User user;
    private RecyclerView reviewsRecyclerView;

    public UserProfileFragment(@NonNull IUserProfileFragment.Listener listener, User user){
        this.listener = listener;
        this.user = user;
    }
}
