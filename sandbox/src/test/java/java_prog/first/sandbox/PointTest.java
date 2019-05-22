package java_prog.first.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance1() {
        Point p1 = new Point(1,11);
        Assert.assertEquals(p1.distance(2,22),11.045361017187261);
    }

    @Test
    public void testDistance2() {
        Point p2 = new Point(3,5);
        Assert.assertEquals(p2.distance(2,25),20.024984394500787);
    }

    @Test
    public void testDistance3() {
        Point p2 = new Point(10,10);
        Assert.assertEquals(p2.distance(2,5),9.433981132056603);
    }
}
