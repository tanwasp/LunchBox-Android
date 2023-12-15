package edu.vassar.cmpu203.lunchbox.view.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.vassar.cmpu203.lunchbox.R;

public class LocationViewHolder extends RecyclerView.ViewHolder {
    TextView locationView;
    public LocationViewHolder(@NonNull View itemView) {
        super(itemView);
        locationView = itemView.findViewById(R.id.locationTextView);
    }
}
