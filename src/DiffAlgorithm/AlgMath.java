package DiffAlgorithm;
import ParseData.Point;
public class AlgMath {
    public double getAngle(Point base, Point center, Point top) {
        double calc = Math.atan2((double) (top.y - center.y), (double) (top.x - center.x)) - Math.atan2((double) (base.y - center.y), (double) (base.x - center.x));
        return Math.abs(Math.toDegrees(calc));
    }
}
