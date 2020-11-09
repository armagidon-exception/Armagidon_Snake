package ru.armagidon.snake;

import ru.armagidon.snake.panels.MenuPanel;

import java.awt.*;

public class Main
{
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GameWindow window = new GameWindow("Snake", 800, 800);
            window.setGamePanel(new MenuPanel(window.getSize()));
            //window.setGamePanel(new GameFrame(new Dimension(window.getWidth(), window.getHeight())));
            window.showWindow();
        });
    }
}
