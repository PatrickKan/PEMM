package ParseData;

import java.util.List;
import java.util.ArrayList;

public class Person
{
    private List<Float> pose_keypoints_2d;

    //Other data could be used for more precise movements
    private List<Float> face_keypoints_2d;
    private List<Float> hand_left_keypoints_2d;
    private List<Float> hand_right_keypoints_2d;
    private List<Float> pose_keypoints_3d;
    private List<Float> face_keypoints_3d;
    private List<Float> hand_left_keypoints_3d;
    private List<Float> hand_right_keypoints_3d;

    public List<Float> getPosePoints()
    {
        return pose_keypoints_2d;
    }

    public List<Point> createPoints() {
        List<Point> points = new ArrayList<Point>();
        for(int i = 0; i < pose_keypoints_2d.size(); i += 3) {
            Point currPoint = new Point(i/3);
            currPoint.x = pose_keypoints_2d.get(i);
            currPoint.y = pose_keypoints_2d.get(i+1);
            points.add(currPoint);
        }
        return points;
    }
}
