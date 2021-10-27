package assets;

public class Player {

    final private String username;
    private int point;
    private int[] speedBump;
    private int findSpeedBump = 0; //find which block of array is without speedBump
    public boolean isSelected = false;
    private int lastX, lastY;

    //constructor
    public Player(String name1) {

        this.username = name1;
    }

    public String getUsername() {
        return username;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public int getFindSpeedBump() {
        return findSpeedBump;
    }

    public void setFindSpeedBump(int findSpeedBump) {
        this.findSpeedBump = findSpeedBump;
    }

    public int[] getSpeedBump() {
        return speedBump;
    }

    public void setSpeedBump(int[] speedBump) {
        this.speedBump = speedBump;
    }
}