package edu.vassar.cmpu203.lunchbox.model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LocationTest {

    /**
     * Tests the distance calculator in the Location class
     */
    @Test
    public void testHaversine() {
        Location location1 = new Location(40.7128f, -74.006f);
        Location location2 = new Location(42.3601f, -71.0589f);

        // expected distance between the two locations (from online calculator)
        float expectedDistance = 190.212f;

        // calculated distance using the haversine formula
        float actualDistance = location1.haversine(location2);

        assertEquals(expectedDistance, actualDistance, 0.05f);

        // test same location is 0 distance
        Location location3 = new Location(42.3601f, -71.0589f);
        assertEquals(0, location2.haversine(location3), 0.05f);
    }
}

