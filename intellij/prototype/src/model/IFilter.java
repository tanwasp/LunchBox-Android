package model;
import java.util.Collection;

/**
 * Template for different types of filters
 */
public interface IFilter {
    public Collection<Restaurant> filter(Collection<Restaurant> restaurants);
}
