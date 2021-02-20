import java.util.LinkedList;

// linked list to build a snake
// when the game start, there are 5 nodes
public class Snake {
    // snake body
    private LinkedList<Node> body;
    // snake head's movement
    // default: the direction is left
    private Direction direction = Direction.LEFT;
    // snake is alive
    private boolean isLiving = true;
    // Constructor, executed when the object is created.
    public Snake() {
        // initial body
        initSnake();
    }
    private void initSnake(){
        body = new LinkedList<>();
        // put the snake in the middle
        body.add(new Node(16,20));
        body.add(new Node(17,20));
        body.add(new Node(18,20));
        body.add(new Node(19,20));
        body.add(new Node(20,20));
    }
    // snake move forward by the direction of head
    // Add a node in the direction of the snake head's movement,
    // Remove the node in the snake's tail
    public void move(){

        if (isLiving) {
            // get the head
            Node head = body.getFirst();
            switch (direction) {
                case UP:
                    // add one node at (x, y - 1)
                    body.addFirst(new Node(head.getX(), head.getY() - 1));
                    break;
                case DOWN:
                    // add one node at (x, y + 1)
                    body.addFirst(new Node(head.getX(), head.getY() + 1));
                    break;
                case LEFT:
                    // add one node at (x - 1, y)
                    body.addFirst(new Node(head.getX() - 1, head.getY()));
                    break;
                case RIGHT:
                    // add one node at (x + 1, y)
                    body.addFirst(new Node(head.getX() + 1, head.getY()));
                    break;
            }
            // remove tail
            body.removeLast();

            // check the snake out of bounds
            head = body.getFirst();
            if (head.getX() < 0  || head.getY() < 0 || head.getX() >= 40 || head.getY() >= 40) {
                isLiving = false;
            }
            // check the snake touch itself
            for (int i = 1; i < body.size(); i++) {
                Node node = body.get(i);
                if (head.getX() == node.getX() && head.getY() == node.getY()) {
                    isLiving = false;
                }
            }
        }
    }
    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    // eat food: Add a node along the direction of the snake head
    public void eat(Node food) {
        Node head = body.getFirst();
        switch (direction) {
            case UP:
                // add one node at (x, y - 1)
                body.addFirst(new Node(head.getX(), head.getY() - 1));
                break;
            case DOWN:
                // add one node at (x, y + 1)
                body.addFirst(new Node(head.getX(), head.getY() + 1));
                break;
            case LEFT:
                // add one node at (x - 1, y)
                body.addFirst(new Node(head.getX() - 1, head.getY()));
                break;
            case RIGHT:
                // add one node at (x + 1, y)
                body.addFirst(new Node(head.getX() + 1, head.getY()));
                break;
        }
    }
}

