package DiffAlgorithm;

public class FootballDiff
{
    //AlgMath calc;
    public FootballDiff()
    {
       //calc = new AlgMath();
    }

    public void calculateByFrame(int frameNum)
    {
        switch(frameNum)
        {
            case 0:
                frameOneFeedback();
                break;
            case 1:
                frameTwoFeedback();
                break;
            case 2:
                frameThreeFeedback();
                break;
            case 3:
                frameFourFeedback();
                break;
            case 4:
                frameFiveFeedback();
                break;
            default:
                System.out.println("No frame to give feedback on");
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
