package edu.vassar.cmpu203.lunchbox.model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LocationTest {

    @Test
    public void testHaversine() {
        Location location1 = new Location(40.7128f, -74.006f);
        Location location2 = new Location(42.3601f, -71.0589f);

        // expected distance between the two locations
        float expectedDistance = 190.212f;

        // calculated distance using the haversine formula
        float actualDistance = location1.haversine(location2);

        assertEquals(expectedDistance, actualDistance, 0.05f);
    }
}

