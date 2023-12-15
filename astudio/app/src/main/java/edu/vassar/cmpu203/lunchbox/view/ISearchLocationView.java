package edu.vassar.cmpu203.lunchbox.view;

import java.util.List;

public interface ISearchLocationView {
    void updateLocationList(List<String> locationList);

    interface Listener {
        void onUseGivenLocation(String city);
        void onUseCurrentLocation();
    }
}
