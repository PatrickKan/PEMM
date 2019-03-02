package ParseData;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;


public class Parser
{
    public static final int NUM_FRAMES = 5;

    PersonContainer container;

    List<PersonContainer> framesContainer;

    public Parser()
    {
        framesContainer = new ArrayList<PersonContainer>();
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
            String fileName = "src/Data/" + i + "_keypoints.json";
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
}
