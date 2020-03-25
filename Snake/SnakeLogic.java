package Snake;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;


public class SnakeLogic implements ActionListener, KeyListener {

    public static SnakeLogic snake;
    public static final int WIDTH = 400, HEIGHT = 400, UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
    public Point treasure;
    public ArrayList<Point> snakeBody = new ArrayList<Point>();
    public boolean gameOver, paused=true;
    public int clockTicks, score;
    public GameRenderer renderer;
    public JFrame jframe;
    public int snakeLength;
    public int direction = DOWN;
    public Point head;
    public Random randGen = new Random();
    public Timer timer = new Timer(10, this);

    public SnakeLogic(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake Game");
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setLocation(dim.width/2-jframe.getWidth()/2, dim.height/2-jframe.getHeight()/2);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(renderer = new GameRenderer());
        renderer.repaint();
        jframe.setVisible(true);
        jframe.addKeyListener(this);
        startGame();
        timer.start();
    }

    private void startGame() {
        gameOver = false;
        clockTicks = 0;
        score = 0;
        snakeBody.clear();
        head = new Point(0, -1);
        direction = DOWN;
        treasure = new Point(WIDTH/SCALE/2, HEIGHT/SCALE/2);
        snakeLength = 5;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && (direction != DOWN)) { // up
            direction = UP;
        } else if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && (direction != UP)) { // down
            direction = DOWN;
        } else if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && (direction != RIGHT)) { // left
            direction = LEFT;
        } else if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && (direction != LEFT)) { // right
            direction = RIGHT;
        } else if (key == KeyEvent.VK_SPACE) { // space event
            if (gameOver) {
                startGame();
            } else {
                paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderer.repaint();
        if (!paused && !gameOver) {
            clockTicks++;
            if (clockTicks % 10 == 0) {
                score++;
                if (direction == UP) {
                    if (head.y - 1 > -1 && !snakeBody.contains(new Point(head.x, head.y - 1))) {
                        head = new Point(head.x, head.y - 1);
                    } else {
                        gameOver = true;
                    }
                } else if (direction == DOWN) {
                    if (head.y + 1 < 38 && !snakeBody.contains(new Point(head.x, head.y + 1))) {
                        head = new Point(head.x, head.y + 1);
                    } else {
                        gameOver = true;
                    }
                } else if (direction == LEFT) {
                    if (head.x - 1 > -1 && !snakeBody.contains(new Point(head.x - 1, head.y))) {
                        head = new Point(head.x - 1, head.y);
                    } else {
                        gameOver = true;
                    }
                } else if (direction == RIGHT) {
                    if (head.x + 1 < 40 && !snakeBody.contains(new Point(head.x + 1, head.y))) {
                        head = new Point(head.x + 1, head.y);
                    } else {
                        gameOver = true;
                    }
                }
                if (!gameOver) {
                    snakeBody.add(head);
                }

                if (snakeBody.size() > snakeLength) {
                    snakeBody.remove(0);
                }

                if (head.equals(treasure)) {
                    score += 100;
                    snakeLength++;
                    treasure = new Point(randGen.nextInt(38), randGen.nextInt(36));
                }
            }
        }
    }

    public static void main(String[] args) {
        snake = new SnakeLogic();
    }
}
