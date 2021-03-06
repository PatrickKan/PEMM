import DiffAlgorithm.AlgMath;
import DiffAlgorithm.DecisionMaker;
import DiffAlgorithm.FootballModel;
import ParseData.Parser;
import ParseData.Point;

public class Main
{

    static String jsonContent;
    private static String exampleJson = "{\n" +
            "  \"version\": 1.2,\n" +
            "  \"people\": [\n" +
            "    {\n" +
            "      \"pose_keypoints_2d\": [\n" +
            "        185.61,\n" +
            "        51.905,\n" +
            "        0.874167,\n" +
            "        191.374,\n" +
            "        66.5743,\n" +
            "        0.768487,\n" +
            "        171.441,\n" +
            "        61.3395,\n" +
            "        0.625767,\n" +
            "        135.258,\n" +
            "        60.8158,\n" +
            "        0.63387,\n" +
            "        104.331,\n" +
            "        58.1804,\n" +
            "        0.806418,\n" +
            "        211.837,\n" +
            "        72.8657,\n" +
            "        0.67744,\n" +
            "        208.689,\n" +
            "        96.462,\n" +
            "        0.157843,\n" +
            "        0,\n" +
            "        0,\n" +
            "        0,\n" +
            "        167.246,\n" +
            "        112.195,\n" +
            "        0.433524,\n" +
            "        156.772,\n" +
            "        103.808,\n" +
            "        0.394004,\n" +
            "        103.806,\n" +
            "        103.28,\n" +
            "        0.272058,\n" +
            "        50.3236,\n" +
            "        93.3298,\n" +
            "        0.169639,\n" +
            "        176.697,\n" +
            "        119.543,\n" +
            "        0.347861,\n" +
            "        171.973,\n" +
            "        146.803,\n" +
            "        0.129229,\n" +
            "        142.614,\n" +
            "        158.856,\n" +
            "        0.378934,\n" +
            "        184.558,\n" +
            "        47.6797,\n" +
            "        0.889335,\n" +
            "        190.331,\n" +
            "        48.7356,\n" +
            "        0.933905,\n" +
            "        0,\n" +
            "        0,\n" +
            "        0,\n" +
            "        203.42,\n" +
            "        50.8444,\n" +
            "        0.892519,\n" +
            "        126.885,\n" +
            "        164.108,\n" +
            "        0.184385,\n" +
            "        0,\n" +
            "        0,\n" +
            "        0,\n" +
            "        137.368,\n" +
            "        157.295,\n" +
            "        0.197827,\n" +
            "        37.2019,\n" +
            "        94.8787,\n" +
            "        0.0594407,\n" +
            "        36.6952,\n" +
            "        93.3195,\n" +
            "        0.062143,\n" +
            "        0,\n" +
            "        0,\n" +
            "        0\n" +
            "      ],\n" +
            "      \"face_keypoints_2d\": [],\n" +
            "      \"hand_left_keypoints_2d\": [],\n" +
            "      \"hand_right_keypoints_2d\": [],\n" +
            "      \"pose_keypoints_3d\": [],\n" +
            "      \"face_keypoints_3d\": [],\n" +
            "      \"hand_left_keypoints_3d\": [],\n" +
            "      \"hand_right_keypoints_3d\": []\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static void main(String[] args)
    {
        Parser parseData = new Parser();
        parseData.parseJsonFile("src/example_data.json");
        parseData.accessPersonPoints();

        Parser frameParser = new Parser();
        frameParser.parseFrames();

        for(int i = 0; i < 5; i++) {
            frameParser.accessFramePoints(i);
        }

        frameParser.intializeAngles();

        FootballModel fm = new FootballModel();
        fm.trainModel();
        fm.setAverageAngles();

        DecisionMaker.compareWithModel(fm.getFrameAngles(), frameParser.getListAngles());
    }
}
