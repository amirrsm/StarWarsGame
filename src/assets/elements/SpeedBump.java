package assets.elements;

public class SpeedBump extends Elements {

    private int limit;
    static int numberOfSpeedBumps = 0;
    private static int fixed = 0;

    public SpeedBump(int i, int j, int limit) {
        super(i, j);
        setLimit(limit);
        fixed++;
    }

    public static int getNumberOfSpeedBumps() {
        return numberOfSpeedBumps;
    }

    public static void setNumberOfSpeedBumps(int numberOfSpeedBumps) {
        SpeedBump.numberOfSpeedBumps = numberOfSpeedBumps;
    }

    public void setLimit(int limit) {
        if (limit > 0)
            this.limit = limit;

    }

    public int getLimit() {
        return limit;
    }

    public static int getFixed() {
        return fixed;
    }

}
