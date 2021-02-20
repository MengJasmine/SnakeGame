import javax.swing.*;
// javax is an extension package to Java
// swing: Graphical visual package

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
//Abstract Window Toolkit=AWT

public class MainFrame extends JFrame {
    // member variable
    private Snake snake;
    private JPanel jPanel; // game board
    // Within the specified time, the snake is called to move
    private Timer timer;
    private Node food;
    public MainFrame() throws HeadlessException {
        // Initialize window parameters
        initFrame();
        // Initializes the game board
        initGameBoard();
        // Initializes the body of snake
        initSnake();
        // Initializes the food
        initFood();
        // Initializes the timer
        initTimer();
        // Set keyboard monitor： Up, Down, Left, Right
        setKeyListener();
    }
    private void setKeyListener() {
        addKeyListener(new KeyAdapter() {
            // when press the keyboard, it will run
            @Override
            public void keyPressed(KeyEvent e) {
                // There is a code for each key in the building plate
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: // UP
                        if (snake.getDirection() != Direction.DOWN) {
                            snake.setDirection(Direction.UP);
                        }

                        break;
                    case KeyEvent.VK_DOWN: // DOWN
                        if (snake.getDirection() != Direction.UP) {
                            snake.setDirection(Direction.DOWN);
                        }
                        break;
                    case KeyEvent.VK_LEFT: // LEFT
                        if (snake.getDirection() != Direction.RIGHT) {
                            snake.setDirection(Direction.LEFT);
                        }
                        break;
                    case KeyEvent.VK_RIGHT: // RIGHT
                        if (snake.getDirection() != Direction.LEFT) {
                            snake.setDirection(Direction.RIGHT);
                        }
                        break;
                }
            }
        });
    }
    private void initTimer() {
        // create the object
        timer = new Timer();
        // Initialize the timed task
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                snake.move();
                // check the snake eat the food
                Node head = snake.getBody().getFirst();
                if (head.getX() == food.getX() && head.getY() == food.getY()) {
                    snake.eat(food);
                    food.random();
                }
                // 每次动一下，就要重新绘制棋盘
                jPanel.repaint();
            }
        };
        // 每100毫秒，执行一次
        timer.scheduleAtFixedRate(timerTask, 0, 100);
    }
    private void initSnake() {
        snake = new Snake();
    }
    private void initFood() {
        food = new Node();
        food.random();
    }
    // Initializes the game board
    private void initGameBoard() {
        jPanel = new JPanel() {
            // Draw content in the game board

            @Override
            public void paint(Graphics g) {
                // We can see Graphics g as the pen
                // it offer some method to draw basic graphics like line, rectangle

                // clear the board
                g.clearRect(0, 0, 600, 600);
                // Draw 40 Horizontal lines
                for (int i = 0; i <= 40; i++) {
                    g.drawLine(0,i * 15, 600, i * 15);
                }
                // Draw 40 vertica lines
                for (int i = 0; i <= 40; i++) {
                    g.drawLine(i * 15, 0, i * 15, 600);
                }
                // Draw snake
                LinkedList<Node> body = snake.getBody();
                for (Node node: body) {
                    g.fillRect(node.getX() * 15, node.getY() * 15, 15, 15);
                }

                // Draw food
                g.fillRect(food.getX() * 15, food.getY() * 15, 15, 15);
            }
        };
        // Add the game board i nto the frame
        add(jPanel);
    }
    // Initialize window parameters
    private void initFrame() {
        // set size
        setSize(610, 610);
        // set location
        setLocation(400, 400);
        // set the button to close the program
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the frame's size is immutable
        setResizable(false);
    }

    public static void main(String[] args) {
        // creat the frame object and display
        new MainFrame().setVisible(true);
    }

}
