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
    private List<PersonContainer> containers;

    public FootballModel()
    {
        containers = new ArrayList<PersonContainer>();
    }

    public void trainModel()
    {
        Gson gson = new Gson();
        try
        {
            PersonContainer currContainer = new PersonContainer();
            FileReader readFile = new FileReader(""); //TODO: PUT FILE NAME HERE
            JsonReader reader = new JsonReader(readFile);
            currContainer = gson.fromJson(reader, PersonContainer.class);
            containers.add(currContainer);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
            System.exit(1);
        }
        createModel();
    }

    private void createModel()
    {
        AlgMath alg = new AlgMath();
        for(int i = 0; i < containers.size(); i++)
        {
            PersonContainer container = containers.get(i);
            List<Point> points = container.getPerson(0).createPoints();

            if(i % 5 == 0)
            {
                //First frame
                frameOneFeedback();
            }
            else if(i % 5 == 1)
            {
                //Second frame
                frameTwoFeedback();
            }
            else if(i % 5 == 2)
            {
                //Third frame
                frameThreeFeedback();
            }
            else if(i % 5 == 3)
            {
                //Fourth Frame
                frameFourFeedback();
            }
            else if(i % 5 == 4)
            {
                //Fifth frame
                frameFiveFeedback();
            }

            if((i+1)%5 == 0) {
               //Write file with data every 5 images
            }
        }
    }

    public void frameOneFeedback()
    {
        //Holding ball initially
        //6 angle with 7 and 5
        //3 angle with 2 and 4
    }
    public void frameTwoFeedback()
    {
        //3 is at 90ish degree angle with 2 and 4
        //6 angle with 5 and 7 (slight bend, close to 180 degrees)
        //Left leg planted 12, 13(center), 14
    }
    public void frameThreeFeedback()
    {
        //Right arm kind of behind head
        //2, 3, 4 (right arm)

        //Left leg bent
        //12, 13, 14 (left leg)
    }
    public void frameFourFeedback()
    {
        //Right hand, angle with 3, 4 and 2 is close to straight
        //9 should be about 180 degrees with 2
    }
    public void frameFiveFeedback()
    {
        //Angle between 2, 3 and 9
    }
}
