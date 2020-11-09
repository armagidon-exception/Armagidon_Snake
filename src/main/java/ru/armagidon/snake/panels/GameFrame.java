package ru.armagidon.snake.panels;


import ru.armagidon.snake.GameWindow;
import ru.armagidon.snake.gameObjects.Fruit;
import ru.armagidon.snake.gameObjects.Snake;
import ru.armagidon.snake.gameObjects.SnakeEvent;
import ru.armagidon.snake.utils.GameTimer;
import ru.armagidon.snake.utils.ImageLoader;
import ru.armagidon.snake.utils.KeyBoardHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameFrame extends Canvas implements GamePanel
{
    private final Dimension size;
    private BufferStrategy strategy;

    public final Snake snake;
    private final Fruit fruit;

    public boolean gameOver = false;

    private final KeyBoardHandler keyBoardHandler;
    private final GameTimer timer;
    private final int taskId;

    public GameFrame(Dimension size) {
        this.size =  size;
        this.timer = new GameTimer(50);

        this.keyBoardHandler = new KeyBoardHandler(this);
        this.taskId = timer.addTask(this::render);

        this.snake = createSnake();
        this.fruit = new Fruit(size.width, size.height);
    }

    private Snake createSnake(){
        Snake snake = new Snake(0, 0, Snake.Direction.EAST);

        snake.setSnakeEvent(new SnakeEvent() {
            @Override
            public void eatItSelf(Graphics2D g2) {
                timer.pauseTask(taskId);
                g2.setFont(new Font("Arial", Font.BOLD, 70));
                g2.setColor(Color.RED);
                g2.drawString("GAME OVER", (getWidth()/2) - (70 * 3), getHeight()/2);
                gameOver = true;
            }

            @Override
            public void moved(Snake snake) {
                if (snake.getX() < snake.getSize() / 4) {
                    snake.setX(getWidth() - snake.getSize() / 2);
                } else if (snake.getX() > getWidth() - snake.getSize()){
                    snake.setX(snake.getSize() / 4);
                } else if (snake.getY() < snake.getSize() / 4) {
                    snake.setY(getHeight() - snake.getSize() / 2);
                } else if (snake.getY() > getHeight() - snake.getSize()){
                    snake.setY(snake.getSize() / 4);
                }
            }

            @Override
            public void eaten(int newScore) {

            }
        });

        return snake;
    }

    private void render(){

        if (strategy == null){
            createBufferStrategy(2);
        }
        strategy = getBufferStrategy();


        Graphics2D graphics2D = (Graphics2D) strategy.getDrawGraphics();
        update(graphics2D);

        graphics2D.drawImage(ImageLoader.loadImage("floor1.png"), 0, 0, getWidth(), getHeight(), null);

        fruit.draw(graphics2D);
        snake.draw(graphics2D);


        graphics2D.setColor(Color.BLACK);

        graphics2D.setFont(new Font("Arial", Font.PLAIN, 30));
        graphics2D.drawString("Score: " + snake.getTails(), 50, 50);

        if (snake.checkCollision(fruit))
            snake.eat(fruit);

        graphics2D.dispose();
        strategy.show();
    }

    public void reset(){
        snake.reset();
        fruit.generate();
        timer.resumeTask(taskId);
    }

    @Override
    public void show(GameWindow window) {

        setPreferredSize(size);

        window.getFrame().add(this);

        window.getFrame().pack();

        createBufferStrategy(2);
        strategy = getBufferStrategy();

        timer.start();
    }

    @Override
    public void hide(GameWindow window) {
        timer.cancel();
        if (strategy != null)
            strategy.dispose();
        window.getFrame().remove(this);
    }
}
