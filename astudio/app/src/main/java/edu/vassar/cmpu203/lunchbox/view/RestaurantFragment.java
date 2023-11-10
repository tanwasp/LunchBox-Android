package edu.vassar.cmpu203.lunchbox.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.vassar.cmpu203.lunchbox.databinding.FragmentRestaurantBinding;

public class RestaurantFragment extends Fragment implements IRestaurantView{
    private final Listener listener;
    private FragmentRestaurantBinding binding;

    public RestaurantFragment(Listener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentRestaurantBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void OnViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        this.binding.btnNavigateToPostReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listener.onNavigateToPostReview();
            }
        });
    }

}