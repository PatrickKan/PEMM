package DiffAlgorithm;

import ParseData.Person;
import ParseData.PersonContainer;
import ParseData.Point;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class FootballModel
{
    public static final int JOINTS_ANALYZED = 5;
    public static final int NUM_TRAINING_DATA = 3;
    public static final int NUM_FRAMES = 5;

    private List<PersonContainer> containers;

    private List<Double>  frame1;
    private List<Double>  frame2;
    private List<Double>  frame3;
    private List<Double>  frame4;
    private List<Double>  frame5;

    public FootballModel()
    {
        containers = new ArrayList<PersonContainer>();
        frame1 = new ArrayList<>();
        frame2 = new ArrayList<>();
        frame3 = new ArrayList<>();
        frame4 = new ArrayList<>();
        frame5 = new ArrayList<>();

        for(int i = 0; i < JOINTS_ANALYZED; i++)
        {
            frame1.add(0.0);
            frame2.add(0.0);
            frame3.add(0.0);
            frame4.add(0.0);
            frame5.add(0.0);
        }
    }

    public void trainModel()
    {
        Gson gson = new Gson();
        for(int i = 0; i < NUM_TRAINING_DATA; i++)
        {
            for(int j = 0; j < NUM_FRAMES; j++)
            {
                try
                {
                    PersonContainer currContainer;
                    String fileName = "[" + i + "]FootballReference" + j + "_keypoints.json";
                    FileReader readFile = new FileReader("src/Data/" + fileName);
                    JsonReader reader = new JsonReader(readFile);
                    currContainer = gson.fromJson(reader, PersonContainer.class);
                    containers.add(currContainer);
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("File Not Found");
                    System.exit(1);
                }
            }
        }
        createModel();
    }

    private void createModel()
    {
        for(int i = 0; i < containers.size(); i++)
        {
            PersonContainer container = containers.get(i);
            List<Point> points = container.getPerson(0).createPoints();

            if(i % 5 == 0)
            {
                //First frame
                frameOne(points);
            }
            else if(i % 5 == 1)
            {
                //Second frame
                frameTwo(points);
            }
            else if(i % 5 == 2)
            {
                //Third frame
                frameThree(points);
            }
            else if(i % 5 == 3)
            {
                //Fourth Frame
                frameFour(points);
            }
            else if(i % 5 == 4)
            {
                //Fifth frame
                frameFive(points);
            }

            if((i+1)%5 == 0) {
               //Write file with data every 5 images
            }
        }
    }

    //Train for frame one
    public void frameOne(List<Point> points)
    {
        //Holding ball initially

        //6 angle with 7 and 5
        double angle = AlgMath.getAngle(points.get(5), points.get(6), points.get(7));
        frame1.set(0, frame1.get(0) + angle);

        System.out.println("ANGLE IS:" + angle);

        //3 angle with 2 and 4
        angle = AlgMath.getAngle(points.get(2), points.get(3), points.get(4));
        frame1.set(1, frame1.get(1) + angle);


        System.out.println("ANGLE IS:" + angle);
    }
    public void frameTwo(List<Point> points)
    {
        double angle = AlgMath.getAngle(points.get(2), points.get(3), points.get(4));
        frame2.set(0, frame2.get(0) + angle);

        angle = AlgMath.getAngle(points.get(5), points.get(6), points.get(7));
        frame2.set(1, frame2.get(1) + angle);

        angle = AlgMath.getAngle(points.get(12), points.get(13), points.get(13));
        frame2.set(2, frame2.get(2) + angle);

        //3 is at 90ish degree angle with 2 and 4
        //6 angle with 5 and 7 (slight bend, close to 180 degrees)
        //Left leg planted 12, 13(center), 14
    }
    public void frameThree(List<Point> points)
    {
        double angle = AlgMath.getAngle(points.get(2), points.get(3), points.get(4));
        frame3.set(0, frame3.get(0) + angle);

        angle = AlgMath.getAngle(points.get(12), points.get(13), points.get(14));
        frame3.set(1, frame3.get(1) + angle);

        //Right arm kind of behind head
        //2, 3, 4 (right arm)

        //Left leg bent
        //12, 13, 14 (left leg)

    }
    public void frameFour(List<Point> points)
    {
        double angle = AlgMath.getAngle(points.get(2), points.get(3), points.get(4));
        frame4.set(0, frame4.get(0) + angle);

        angle = AlgMath.getAngle(points.get(9), points.get(2), points.get(3));
        frame4.set(1, frame4.get(1) + angle);

        //Right hand, angle with 2, 3 and 4 is close to straight
        //9 should be about 180 degrees with 2
    }
    public void frameFive(List<Point> points)
    {
        double angle = AlgMath.getAngle(points.get(3), points.get(2), points.get(9));
        frame5.set(0, frame5.get(0) + angle);
        //Angle between 2, 3 and 9
    }

    public void setAverageAngles()
    {
        for(int i = 0; i < JOINTS_ANALYZED; i++)
        {
           frame1.set(i, frame1.get(i)/NUM_TRAINING_DATA);
           frame2.set(i, frame2.get(i)/NUM_TRAINING_DATA);
           frame3.set(i, frame3.get(i)/NUM_TRAINING_DATA);
           frame4.set(i, frame4.get(i)/NUM_TRAINING_DATA);
           frame5.set(i, frame5.get(i)/NUM_TRAINING_DATA);
        }
        System.out.println(frame1);
        System.out.println(frame2);
        System.out.println(frame3);
        System.out.println(frame4);
        System.out.println(frame5);
    }

    //TODO: Actual - Expected for  diff
}
