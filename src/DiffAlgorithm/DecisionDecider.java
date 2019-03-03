public class decisionMaker {

    public static void decisionDecider() {
        int test = 2;

        if(test == 1){
            //deals with joints 5, 6, 7
            String t1 = getFeedback(20,10,"holding the ball closer to your chest", "holding the ball away from your chester");
            //deals with joints 2, 3, 4
            String t2 = getFeedback(20,10, "holding the ball closer to the your chest", "holding the ball away from your chest");
        }else if(test == 2){
            //Deals with joints 2, 3, 4
            String t1 = getFeedback(20,10,"moving your throwing arm slightly forward", "moving your throwing arm slightly backwards");
            //deals with joints 12, 13, 14
            String t2 = getFeedback(20, 10, "leaning forward and bending your front leg more", "straightening your front leg less");
        }else if(test == 3){
            //Joints 2, 3, 4
            String t1 = getFeedback(20,10, "moving your throwing arm forward earlier", "moving your throwing arm forward later");
            //Joints 12, 13, 14
            String t2 = getFeedback(20,10, "leaving your leg a little more bent", "keeping your leg straighter during the throw");
        }else if(test == 4){
            //Joints 2, 3, 4
            String t1 = getFeedback(20,10, "keeping your center mass up with your throwing arm", "using your throwing arm earlier or swing faster");
            //Joints 9, 2, 3
            String t2 = getFeedback(20,10, "bending your throwing arm slightly", "to maintain your throwing arm moving above your shoulder");
        }else if(test == 5){
            //Joints 3, 2, 9
            String t1 = getFeedback(20,10,"relaxing while throwing. You're using too much power", "putting more thrust into your throw");
        }
    }

    public static String getFeedback(double expected, double actual, String g, String l){
        int d = (int)(expected-actual);
        if (d > 20)
            return "Not quite there yet, try " + l + ", adjust angle by" + d;

        else if (d < -20)
            return "Not quite there yet, try " + g + ", adjust angle by" + (-1*d);

        else if(d > 0) {
            return "Nice form! Your angle is within the range of (" + (expected - 20.0) + " < " + (int)actual + " < " + (expected + 20) + "). " +
                    "\nYour form is only " + d +" degrees bigger than professional players. " +
                    "\nTry" + l + ".";

        }
        else if(d < 0) {
            return "Nice form! Your angle is within the range of (" + (expected - 20.0) + " < " + (int)actual + " < " + (expected + 20) + "). " +
                    "\nYour form is only" + (-1*d) + " degrees smaller than professional players. " +
                    "\nTry" + g + ".";

        }else if(d == 0){
            return "Perfect! Your form here matches 100% with that of professionals!";
        }
        else
            return "Oops, computer vision did not foresee this outcome :(.";
    }
}
