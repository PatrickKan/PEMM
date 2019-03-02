package ParseData;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;


public class Parser
{
    PersonContainer container;
    public Parser()
    {

    }

    //Parse json using Gson
    public void parseJson(String jsonContent)
    {
        Gson gson = new Gson();
        container = gson.fromJson(jsonContent, PersonContainer.class);
    }

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

    public void accessPersonPoints()
    {
        Person p = container.getPerson(0);
        List<Float> posePoints = p.getPosePoints();

        List<Point> points = p.createPoints();
//
//        for(float num: posePoints) {
//            System.out.println("Pose Point: " + num);
//        }
//
        for(Point point: points) {
            System.out.println("Point " + point.num + ": (" + point.x + ", " + point.y + ")");
        }
    }
}
