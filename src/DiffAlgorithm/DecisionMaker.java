package DiffAlgorithm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class DecisionMaker {

    private static int fileNum = 0;
    private static int frameCount = 0;

    public static void compareWithModel(List<List<Double>> expectedAng, List<List<Double>> actualAng)
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                decisionDecider(expectedAng.get(i).get(j), actualAng.get(i).get(j), i+1, j);
            }
        }
        decisionDecider(expectedAng.get(4).get(0), actualAng.get(4).get(0), 5, 0);
    }

    public static void decisionDecider(double expected, double actual, int frameNum, int jointNum) {
        String t1 = "";
        String t2 = "";

        if(frameNum == 1){
            //deals with joints 5, 6, 7
            if(jointNum == 0)
            {
                t1 = getFeedback(expected, actual, "holding the ball closer to your chest", "holding the ball away from your chest", "left elbow");
            }
            else if(jointNum == 1)//deals with joints 2, 3, 4
            {
                t2 = getFeedback(expected, actual, "holding the ball closer to your chest", "holding the ball away from your chest", "right elbow");
            }
        } else if(frameNum == 2){
            //Deals with joints 2, 3, 4
            if(jointNum == 0) {
                t1 = getFeedback(expected, actual,"bending your elbow more", "bending your elbow less", "right elbow");
            }
            //deals with joints 12, 13, 14
            else if(jointNum == 1) {
                t2 = getFeedback(expected, actual, "leaning forward and bending your front leg more", "straightening your front leg", "left leg");
            }
        } else if(frameNum == 3){
            //Joints 2, 3, 4
            if(jointNum == 0) {
                t1 = getFeedback(expected,actual, "bending your throwing arm more", "straightening out your throwing arm", "right elbow");
            }
            //Joints 12, 13, 14
            else if(jointNum == 1) {
                t2 = getFeedback(expected,actual, "bending your left leg more during the throw", "straightening your left leg more during the throw", "left leg");
            }
        } else if(frameNum == 4){
            //Joints 2, 3, 4
            if(jointNum == 0) {
                t1 = getFeedback(expected,actual, "bending your throwing arm more", "straightening out your throwing arm", "right elbow");
            }
            //Joints 9, 2, 3
            else if(jointNum == 1) {
                t2 = getFeedback(expected,actual, "leaning your body back slightly when you release the ball", "leaning your body forward slightly when you release the ball", "arm and torso");
            }
        } else if(frameNum == 5){
            //Joints 3, 2, 9
            t1 = getFeedback(expected,actual,"relaxing while throwing. You're using too much power", "putting more thrust into your throw", "arm and torso");
        }

        frameCount++;

        FileWriter fw;
        try {
            //Example of file name format is  1_decision_0_num_1
            String fileName = frameNum + "_decision_";
            if(t1.length() > 0)
            {
                fw = new FileWriter(new File("src/Data/",fileName + 1 + ".txt"));
                fw.write(t1);
                fw.close();
            }
            if(t2.length() > 0)
            {
                fw = new FileWriter(new File("src/Data/", fileName + 2 + ".txt"));
                fw.write(t2);
                fw.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getFeedback(double expected, double actual, String g, String l, String part) {
        int d = (int) (expected - actual);
        if (!(part.equals("arm and torso")))
        {
            if (d > 20)
                return "Not quite there yet, try " + l + ". \nYour " + part + " angle: " + (int)actual + " degrees \nExpected angle: " + (int)expected + " degrees";

            else if (d < -20)
                return "Not quite there yet, try " + g + ". \nYour " + part + " angle: " + (int)actual + " degrees \nExpected angle: " + (int)expected + " degrees";

            else if (d > 0) {
                return "Nice form! You could improve by " + l + ".\nYour " + part + " angle: " + (int)actual + " degrees \nExpected angle: " + (int)expected + " degrees";

            } else if (d < 0) {
                return "Nice form! You could improve by " + g + ". \nYour " + part + " angle: " + (int)actual + " degrees \nExpected angle: " + (int)expected + " degrees";

            } else if (d == 0) {
                return "Great! Your form perfectly matches that of an athlete!";
            } else {
                return "Oops, computer vision did not foresee this outcome, you might be from an alternate universe :(.";
            }
        }
        else
        {
            if (d > 20)
                return "Not quite there yet, try " + l + ". \nThe angle between your " + part + ": " + (int)actual + " degrees \nExpected angle: " + (int)expected + " degrees";

            else if (d < -20)
                return "Not quite there yet, try " + g + ". \nThe angle between your " + part + ": " + (int)actual + " degrees \nExpected angle: " + (int)expected + " degrees";

            else if (d > 0) {
                return "Nice form! You could improve by " + l + ". \nThe angle between your " + part + ": " + (int)actual + " degrees \nExpected angle: " + (int)expected + " degrees";

            } else if (d < 0) {
                return "Nice form! You could improve by " + g + ". \nThe angle between your " + part + ": " + (int)actual + " degrees \nExpected angle: " + (int)expected + " degrees";

            } else if (d == 0) {
                return "Great! Your form perfectly matches that of an athlete!";
            } else {
                return "Oops, computer vision did not foresee this outcome, you might be from an alternate universe :(.";
            }
        }
    }
}
