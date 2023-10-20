package model;
import java.util.Collection;

public interface IFilter {
    public Collection<Restaurant> filter(Collection<Restaurant> restaurants);
}
