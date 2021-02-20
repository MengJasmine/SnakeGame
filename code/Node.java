import java.util.Random;

public class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // randomly get location
    // for food
    public void random() {
        Random r = new Random();
        // row:
        this.x = r.nextInt(40);
        // col:
        this.y = r.nextInt(40);
    }
}
