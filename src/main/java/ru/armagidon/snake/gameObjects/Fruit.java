package ru.armagidon.snake.gameObjects;

import ru.armagidon.snake.utils.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fruit implements Drawable
{
    private Point position = new Point(0,0);

    private final int SIZE = 30;

    private final int SCREEN_W;
    private final int SCREEN_H;

    public Fruit(int SCREEN_W, int SCREEN_H) {
        this.SCREEN_W = SCREEN_W;
        this.SCREEN_H = SCREEN_H;
    }

    public void generate(){
        int x = (int) (Math.random() * SCREEN_W);
        int y = (int) (Math.random() * SCREEN_H);
        position = new Point(x,y);
    }

    private final BufferedImage fruit = ImageLoader.loadImage("fruit.png");

    @Override
    public void draw(Graphics2D graphics2D) {
        Color color = new Color(5, 4, 4, 225);
        graphics2D.setColor(color);
        graphics2D.fillOval(position.x, (int) (position.y + (SIZE / 1.5)), SIZE, SIZE / 2);
        graphics2D.drawImage(fruit,position.x, position.y, SIZE, SIZE, null);
    }

    public Point getPosition() {
        return position;
    }


    public int getSize() {
        return SIZE;
    }
}
