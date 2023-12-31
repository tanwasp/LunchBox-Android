package edu.vassar.cmpu203.lunchbox.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an class representing all of the restaurant names for easier searching.
 * It is not used in this iteration.
 */
public class RestaurantNames {
    private List<String> names;

    public RestaurantNames(List<String> names) {
        this.names = names;
    }

    public List<String> searchNames(String term){
        List<String> results = new ArrayList<>();
        for (String name : names) {
            if (name.contains(term)) {
                results.add(name);
            }
        }
        return results;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
