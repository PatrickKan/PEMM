import java.util.List;

public class PersonContainer
{
    private float version;
    private List<Person> people;

    public float getVersion()
    {
        return version;
    }

    public Person getPerson(int index)
    {
        return people.get(index);
    }

}
