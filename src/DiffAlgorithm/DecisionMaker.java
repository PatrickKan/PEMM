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
                t1 = getFeedback(expected, actual, "holding the ball closer to your chest", "holding the ball away from your chester");
            }
            else if(jointNum == 1)//deals with joints 2, 3, 4
            {
                t2 = getFeedback(expected, actual, "holding the ball closer to the your chest", "holding the ball away from your chest");
            }
        } else if(frameNum == 2){
            //Deals with joints 2, 3, 4
            if(jointNum == 0) {
                t1 = getFeedback(expected, actual,"moving your throwing arm slightly forward", "moving your throwing arm slightly backwards");
            }
            //deals with joints 12, 13, 14
            else if(jointNum == 1) {
                t2 = getFeedback(expected, actual, "leaning forward and bending your front leg more", "straightening your front leg less");
            }
        } else if(frameNum == 3){
            //Joints 2, 3, 4
            if(jointNum == 0) {
                t1 = getFeedback(expected,actual, "moving your throwing arm forward earlier", "moving your throwing arm forward later");
            }
            //Joints 12, 13, 14
            else if(jointNum == 1) {
                t2 = getFeedback(expected,actual, "leaving your leg a little more bent", "keeping your leg straighter during the throw");
            }
        } else if(frameNum == 4){
            //Joints 2, 3, 4
            if(jointNum == 0) {
                t1 = getFeedback(expected,actual, "keeping your center mass up with your throwing arm", "using your throwing arm earlier or swing faster");
            }
            //Joints 9, 2, 3
            else if(jointNum == 1) {
                t2 = getFeedback(expected,actual, "bending your throwing arm slightly", "to maintain your throwing arm moving above your shoulder");
            }
        } else if(frameNum == 5){
            //Joints 3, 2, 9
            t1 = getFeedback(expected,actual,"relaxing while throwing. You're using too much power", "putting more thrust into your throw");
        }

        frameCount++;

        if(frameCount % 5==0) {
            fileNum++;
        }

        FileWriter fw;
        try {
            //Example of file name format is  1_decision_0_num_1
            String fileName = frameNum + "_decision_" + fileNum + "_num_";
            if(t1.length() > 0)
            {
                fw = new FileWriter(new File("src/Data/",fileName + 1));
                fw.write(t1);
                fw.close();
            }
            if(t2.length() > 0)
            {
                fw = new FileWriter(new File("src/Data/", fileName + 2));
                fw.write(t2);
                fw.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getFeedback(double expected, double actual, String g, String l){
        int d = (int)(expected-actual);
        if (d > 20)
            return "Not quite there yet, try " + l + ", adjust angle by " + d;

        else if (d < -20)
            return "Not quite there yet, try " + g + ", adjust angle by " + (-1*d);

        else if(d > 0) {
            return "Nice form! Your angle is within the range of (" + (int)(expected - 20.0) + " < " + (int)actual + " < " + (int)(expected + 20) + "). " +
                    "\nYour form is only " + d +" degrees bigger than professional players. " +
                    "\nTry " + l + ".";

        }
        else if(d < 0) {
            return "Nice form! Your angle is within the range of (" + (int)(expected - 20.0) + " < " + (int)actual + " < " + (int)(expected + 20) + "). " +
                    "\nYour form is only " + (-1*d) + " degrees smaller than professional players. " +
                    "\nTry " + g + ".";

        }else if(d == 0){
            return "Perfect! Your form here matches 100% with that of professionals!";
        }
        else
            return "Oops, computer vision did not foresee this outcome :(.";
    }
}
