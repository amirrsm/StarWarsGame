package assets.elements;

public class Wall extends Elements {

    private static int numberOfWalls = 0;
    private static int fixed = 0;

    public Wall(int i, int j) {
        super(i, j);
        fixed++;
    }

    public static void setNumberOfWalls(int numberOfWalls) {
        Wall.numberOfWalls = numberOfWalls;
    }

    public static int getFixed() {
        return fixed;

    }

}