package DiffAlgorithm;

public class DiffAlg
{
    private String sportName;

    public DiffAlg(String sportName)
    {
        sportName = sportName.toLowerCase();
    }

    public double computeDiff()
    {
        switch(sportName)
        {
            case "football":
                //Football throwing alg
                break;
            case "basketball":
                //Basketball throwing form
                break;
            case "taekwondo":
                //Taekwondo kicking form
                break;
            default:
                System.out.println("That sport is currently not in our database.");
        }
        return 0;
    }

    public double computeFootball() {
        return 0;
    }

}
