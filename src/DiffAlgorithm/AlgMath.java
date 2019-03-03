package DiffAlgorithm;
import ParseData.Point;
public class AlgMath
{
    public static double getAngle(Point base, Point center, Point top)
    {
        double calc = Math.abs(Math.toDegrees(Math.atan2((double) (top.x - center.x), (double) (top.y - center.y)) - Math.atan2((double) (base.x - center.x), (double) (base.y - center.y))));
        if (calc > 180)
        {
            calc -= 90;
        }
        return calc;
    }
}
