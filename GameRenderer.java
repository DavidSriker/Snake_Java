package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameRenderer extends JPanel{

    @Override
    public void paintComponent(Graphics elem) {
        System.out.println("Hello World !!");
        super.paintComponent(elem);
        elem.setColor(new Color(8804653));
        elem.fillRect(0, 0, SnakeLogic.WIDTH, SnakeLogic.HEIGHT);
        SnakeLogic snake = SnakeLogic.snake;

        elem.setColor(new Color(16724736));
        elem.fillRect(snake.treasure.x * SnakeLogic.SCALE,
                snake.treasure.y * SnakeLogic.SCALE,
                SnakeLogic.SCALE, SnakeLogic.SCALE);

        elem.setColor(new Color(39168));
        for (int i = 0; i < snake.snakeBody.size(); i++) {
            elem.fillRect(snake.snakeBody.get(i).x * SnakeLogic.SCALE,
                    snake.snakeBody.get(i).y * SnakeLogic.SCALE,
                    SnakeLogic.SCALE, SnakeLogic.SCALE);
        }

        elem.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 18));
        elem.setColor(new Color(3348992));
        if (snake.gameOver || snake.paused) {
            elem.drawString("Press Space to Start!", (SnakeLogic.WIDTH / 2) - 100, SnakeLogic.HEIGHT / 2);
        }

        elem.setFont(new Font("SansSerif", Font.ITALIC, 14));
        elem.drawString("Score: " + snake.score, 5, 20);
        elem.drawString("Time: " + snake.ticks  / 100, SnakeLogic.WIDTH - 100, 20);
    }
}
