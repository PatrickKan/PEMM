package ParseData;

import DiffAlgorithm.AlgMath;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;


public class Parser
{
    public static final int NUM_FRAMES = 5;

    private PersonContainer container;

    private List<PersonContainer> framesContainer;

    private List<Double>  frame1;
    private List<Double>  frame2;
    private List<Double>  frame3;
    private List<Double>  frame4;
    private List<Double>  frame5;


    public Parser()
    {
        framesContainer = new ArrayList<PersonContainer>();
        frame1 = new ArrayList<>();
        frame2 = new ArrayList<>();
        frame3 = new ArrayList<>();
        frame4 = new ArrayList<>();
        frame5 = new ArrayList<>();

        for(int i = 0; i < 5; i++)
        {
            frame1.add(0.0);
            frame2.add(0.0);
            frame3.add(0.0);
            frame4.add(0.0);
            frame5.add(0.0);
        }
    }

    //Parse json using string
    public void parseJson(String jsonContent)
    {
        Gson gson = new Gson();
        container = gson.fromJson(jsonContent, PersonContainer.class);
    }

    //Parse json using file name
    public void parseJsonFile(String fileName)
    {
        Gson gson = new Gson();
        try
        {
            FileReader readFile = new FileReader(fileName);
            JsonReader reader = new JsonReader(readFile);
            container = gson.fromJson(reader, PersonContainer.class);
        }
        catch(FileNotFoundException e) {
            System.out.println("JSon File Not Found");
            System.exit(1);
        }
    }

    public void parseFrames()
    {
        Gson gson = new Gson();
        for(int i = 1; i <= NUM_FRAMES; i++)
        {
            String fileName = "src/Data/Actual/patork" + i + "_keypoints.json";
            System.out.println(fileName);
            try
            {
                FileReader readFile = new FileReader(fileName);
                JsonReader reader = new JsonReader(readFile);
                PersonContainer frame = gson.fromJson(reader, PersonContainer.class);
                System.out.println("adding");
                framesContainer.add(frame);
                System.out.println("after");
            }
            catch(FileNotFoundException e)
            {
                System.out.println("JSon File Not Found");
                System.exit(1);
            }
        }
    }

    public PersonContainer getFrame(int index) {
        return framesContainer.get(index);
    }

    public List<Point> accessPersonPoints()
    {
        Person p = container.getPerson(0);
        List<Float> posePoints = p.getPosePoints();

        List<Point> points = p.createPoints();

        for(Point point: points)
        {
            System.out.println("Point " + point.num + ": (" + point.x + ", " + point.y + ")");
        }
        return points;
    }

    public List<Point> accessFramePoints(int index)
    {
        Person p = framesContainer.get(index).getPerson(0);

        List<Point> points = p.createPoints();

        for(Point point: points)
        {
            System.out.println("Point " + point.num + ": (" + point.x + ", " + point.y + ")");
        }
        return points;
    }

    public void intializeAngles()
    {
        frameOne(accessFramePoints(0));
        frameTwo(accessFramePoints(1));
        frameThree(accessFramePoints(2));
        frameFour(accessFramePoints(3));
        frameFive(accessFramePoints(4));
    }

    //Add angles for person
    private void frameOne(List<Point> points)
    {
        //Holding ball initially

        //6 angle with 7 and 5
        double angle = AlgMath.getAngle(points.get(5), points.get(6), points.get(7));
        frame1.set(0, frame1.get(0) + angle);

        //3 angle with 2 and 4
        angle = AlgMath.getAngle(points.get(2), points.get(3), points.get(4));
        frame1.set(1, frame1.get(1) + angle);
    }

    private void frameTwo(List<Point> points)
    {
        double angle = AlgMath.getAngle(points.get(2), points.get(3), points.get(4));
        frame2.set(0, frame2.get(0) + angle);

        angle = AlgMath.getAngle(points.get(12), points.get(13), points.get(14));
        frame2.set(1, frame2.get(1) + angle);

        //3 is at 90ish degree angle with 2 and 4
        //REMOVED: 6 angle with 5 and 7 (slight bend, close to 180 degrees)
        //Left leg planted 12, 13(center), 14
    }
    private void frameThree(List<Point> points)
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
    private void frameFour(List<Point> points)
    {
        double angle = AlgMath.getAngle(points.get(2), points.get(3), points.get(4));
        frame4.set(0, frame4.get(0) + angle);

        angle = AlgMath.getAngle(points.get(9), points.get(2), points.get(3));
        frame4.set(1, frame4.get(1) + angle);

        //Right hand, angle with 2, 3 and 4 is close to straight
        //9 should be about 180 degrees with 2
    }
    private void frameFive(List<Point> points)
    {
        double angle = AlgMath.getAngle(points.get(3), points.get(2), points.get(9));
        frame5.set(0, frame5.get(0) + angle);
        //Angle between 2, 3 and 9
    }

    public List<List<Double>> getListAngles()
    {
        List<List<Double>> angleLists = new ArrayList<List<Double>>();

        angleLists.add(frame1);
        angleLists.add(frame2);
        angleLists.add(frame3);
        angleLists.add(frame4);
        angleLists.add(frame5);

        return angleLists;
    }
}
