package Snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;

public class SnakeLogic {
    public static SnakeLogic snake;
    public static final int WIDTH = 395, HEIGHT = 400, UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
    public Point treasure;
    public ArrayList<Point> snakeBody = new ArrayList<Point>();
    public boolean gameOver, paused=true;
    public int ticks, score;
    public GameRenderer renderer;
    public JFrame jframe;
    public int tailLength;
    public int direction = DOWN;
    public Point head;

    public SnakeLogic(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake Game");
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setLocation(dim.width/2-jframe.getWidth()/2, dim.height/2-jframe.getHeight()/2);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(renderer = new GameRenderer());
        renderer.repaint();
        jframe.setVisible(true);
        startGame();
    }

    private void startGame() {
        gameOver = false;
        ticks = 0;
        score = 0;
        snakeBody.clear();
        head = new Point(0, -1);
        direction = DOWN;
        treasure = new Point(WIDTH/SCALE/2, HEIGHT/SCALE/2);
        tailLength = 5;
    }

    public static void main(String[] args) {
        snake = new SnakeLogic();
    }
}
