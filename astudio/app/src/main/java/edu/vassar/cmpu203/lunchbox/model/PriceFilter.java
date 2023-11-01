package edu.vassar.cmpu203.lunchbox.model;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a price filter
 */
public class PriceFilter implements IFilter{
    private int price;

    public PriceFilter(String price) {
        if ("$".equals(price)) {
            this.price = 1;
        } else if ("$$".equals(price)) {
            this.price = 2;
        } else if ("$$$".equals(price)) {
            this.price = 3;
        } else {
            throw new IllegalStateException("Unexpected value: " + price);
        }
    }


    public Collection<Restaurant> filter(Collection<Restaurant> restaurants){
        Collection<Restaurant> filtered = new ArrayList<Restaurant>();
        for (Restaurant res : restaurants){
            if (res.priceRange == price){
                filtered.add(res);
            }
        }
        return filtered;
    }
}
