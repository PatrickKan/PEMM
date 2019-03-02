import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

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

    public void parseJsonFile(String fileName) {
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

    public void accessPersonPoints() {
        Person p = container.getPerson(0);
        System.out.println("First pose point is: " + p.getPosePoint(0));
    }




}
