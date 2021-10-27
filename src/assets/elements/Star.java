package assets.elements;

public class Star extends Elements {

    private static int numberOfStars = 0;
    private static int fixed = 0;

    public Star(int i, int j) {
        super(i, j);
        fixed++;
    }

    public static int getNumberOfStars() {
        return numberOfStars;
    }

    public static void setNumberOfStars(int numberOfStars) {
        Star.numberOfStars = numberOfStars;
    }

    public static int getFixed() {
        return fixed;
    }

}