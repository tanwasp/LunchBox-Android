package model;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a price filter
 */
public class PriceFilter implements IFilter{
    private int price;

    public PriceFilter(String price){
        switch (price) {
            case "$" -> this.price = 1;
            case "$$" -> this.price = 2;
            case "$$$" -> this.price = 3;
            default -> throw new IllegalStateException("Unexpected value: " + price);
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
