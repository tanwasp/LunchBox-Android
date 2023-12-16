package edu.vassar.cmpu203.lunchbox.view.recyclerview;

import android.graphics.Color;
import android.graphics.Typeface;
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

    public void bind(String location, boolean isCurrentLocation) {
        locationView.setText(location);
        if (isCurrentLocation) {
            // Change font, size, color, etc. for "Current Location"
            locationView.setTypeface(locationView.getTypeface(), Typeface.BOLD);
            locationView.setTextColor(Color.BLUE); // Example color
            // Set any other styling you need
        } else {
            // Reset to default styling for other locations
            locationView.setTypeface(null, Typeface.NORMAL);
            locationView.setTextColor(Color.BLACK); // Example color
        }
    }

}
