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
}
