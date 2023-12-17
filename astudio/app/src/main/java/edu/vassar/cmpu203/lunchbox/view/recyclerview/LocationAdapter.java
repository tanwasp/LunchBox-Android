package edu.vassar.cmpu203.lunchbox.view.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.util.Listener;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;

public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {
    private List<String> locations;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onUseGivenLocation(String location);
        void onUseCurrentLocation();
    }
    public LocationAdapter(Context context, List<String> locations, OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.locations = locations;
        this.listener = listener;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_location, parent, false);
        return new LocationViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(LocationViewHolder holder, int position) {
//        String location = locations.get(position);
//        holder.locationView.setText(location);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onUseGivenLocation(location);
//            }
//        });
//    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        String location = locations.get(position);
        boolean isCurrentLocation = position == 0; // True if it's the first item

        holder.bind(location, isCurrentLocation);

        holder.itemView.setOnClickListener(v -> {
            if (isCurrentLocation) {
                listener.onUseCurrentLocation();
            } else {
                listener.onUseGivenLocation(location);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

//    public void setLocations(List<String> locations) {
//        this.locations.clear();
//        this.locations.addAll(locations);
//        notifyDataSetChanged();
//    }

    public void setLocations(List<String> locations) {
        this.locations.clear();
        this.locations.add(0, "Current Location"); // Always keep this at the top
        this.locations.addAll(locations);
        notifyDataSetChanged();
    }

}

