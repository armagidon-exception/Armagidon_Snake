package ru.armagidon.snake.gameObjects;

import java.awt.*;

public interface SnakeEvent
{
    default void eatItSelf(Graphics2D g2){}

    default void eaten(int newScore){}

    default void moved(Snake snake) {}
}
