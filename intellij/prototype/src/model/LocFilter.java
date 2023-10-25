package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a location filter
 */
public class LocFilter implements IFilter{

    private int distance;
    private User u;

    public LocFilter(int dist, User u){
        this.distance = dist;
        this.u = u;
    }

    public Collection<Restaurant> filter(Collection<Restaurant> restaurants){
        Collection<Restaurant> filtered = new ArrayList<Restaurant>();
        for (Restaurant res : restaurants){
            res.setDistToUser(u);
            if (distance >= res.distanceToUser){
                filtered.add(res);
            }
        }
        return filtered;
    }
}
