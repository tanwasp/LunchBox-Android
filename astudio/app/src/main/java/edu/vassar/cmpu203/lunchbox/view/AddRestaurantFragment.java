package edu.vassar.cmpu203.lunchbox.view;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;


import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentAddRestaurantBinding;

public class AddRestaurantFragment extends Fragment implements IAddRestaurantView{
    FragmentAddRestaurantBinding binding;
    Listener listener;
    public AddRestaurantFragment(@NonNull Listener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentAddRestaurantBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        this.binding.buttonAddRestaurant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listener.AddRestaurant();
            }
        });
    }
}
