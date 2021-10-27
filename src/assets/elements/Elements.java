package assets.elements;

public abstract class Elements {
    private int x;
    private int y;

    public Elements(int i, int j) {
        setX(i);
        setY(j);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
