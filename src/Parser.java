import com.google.gson.Gson;

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

    public void accessPersonPoints() {
        Person p = container.getPerson(0);
        System.out.println("First pose point is: " + p.getPosePoint(0));
    }




}
