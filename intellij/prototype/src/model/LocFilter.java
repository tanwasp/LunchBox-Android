package model;

import java.util.ArrayList;
import java.util.Collection;

public class LocFilter implements IFilter{
    private float lat;
    private float lon;

    public LocFilter(int dist){

    }

    public Collection<Restaurant> filter(Collection<Restaurant> restaurants){
        //returns restaurants within given distance (math to implement)
        return null;
    }
}
