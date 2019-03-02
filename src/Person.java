import java.util.List;
import java.util.ArrayList;

public class Person
{
    private List<Float> pose_keypoints_2d;
    private List<Float> face_keypoints_2d;
    private List<Float> hand_left_keypoints_2d;
    private List<Float> hand_right_keypoints_2d;
    private List<Float> pose_keypoints_3d;
    private List<Float> face_keypoints_3d;
    private List<Float> hand_left_keypoints_3d;
    private List<Float> hand_right_keypoints_3d;

    public float getPosePoint(int index) {
        return pose_keypoints_2d.get(index);
    }
    public int getPoseSize() {
        return pose_keypoints_2d.size();
    }
}
