package java_prog.first.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance1() {
        Point p1 = new Point(1,11);
        Point p2 = new Point(2,22);
        Assert.assertEquals(p1.distance(p2),11.045361017187261);
    }

    @Test
    public void testDistance2() {
        Point p1 = new Point(3,5);
        Point p2 = new Point(2,25);
        Assert.assertEquals(p1.distance(p2),20.024984394500787);
    }

    @Test
    public void testDistance3() {
        Point p1 = new Point(10,10);
        Point p2 = new Point(2,5);
        Assert.assertEquals(p1.distance(p2),9.433981132056603);
    }
}
