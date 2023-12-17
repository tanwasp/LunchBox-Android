package edu.vassar.cmpu203.lunchbox.model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CoordinateTest {

    /**
     * Tests the distance calculator in the Location class
     */
    @Test
    public void testHaversine() {
        Coordinate coordinate1 = new Coordinate(40.7128f, -74.006f);
        Coordinate coordinate2 = new Coordinate(42.3601f, -71.0589f);

        // expected distance between the two locations (from online calculator)
        float expectedDistance = 190.212f;

        // calculated distance using the haversine formula
        float actualDistance = coordinate1.haversine(coordinate2);

        assertEquals(expectedDistance, actualDistance, 0.05f);

        // test same location is 0 distance
        Coordinate coordinate3 = new Coordinate(42.3601f, -71.0589f);
        assertEquals(0, coordinate2.haversine(coordinate3), 0.05f);
    }
}

