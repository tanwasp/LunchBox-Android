package model;

import java.util.ArrayList;
import java.util.Collection;

public class LocFilter implements IFilter{

    int distance;

    public LocFilter(int dist){
        this.distance = dist;
    }

    public Collection<Restaurant> filter(Collection<Restaurant> restaurants){
        Collection<Restaurant> filtered = new ArrayList<Restaurant>();
        for (Restaurant res : restaurants){
            if (distance <= res.distToUser(u)){
                filtered.add(res);
            }
        }
        return filtered;
    }
}