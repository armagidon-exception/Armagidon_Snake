package ru.armagidon.snake;

import ru.armagidon.snake.panels.GamePanel;
import ru.armagidon.snake.utils.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class GameWindow
{
    private final JFrame frame;
    private final int width;
    private final int height;

    private GamePanel panel;


    public GameWindow(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.frame = new JFrame(title);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocationRelativeTo(null);
        frame.setLocation((screen.width - height) / 2, (screen.height - width) / 2);
        frame.setResizable(false); 
        frame.setIconImage(ImageLoader.loadImage("icon.png"));
    }

    public void setGamePanel(GamePanel panel){
        if (this.panel != null) this.panel.hide(this);
        this.panel = panel;
        this.panel.show(this);
    }

    public void showWindow(){
        frame.setVisible(true);
        frame.pack();
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Dimension getSize(){
        return new Dimension(width, height);
    }
}
