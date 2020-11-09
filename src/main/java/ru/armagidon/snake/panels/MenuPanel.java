package ru.armagidon.snake.panels;

import ru.armagidon.snake.GameWindow;
import ru.armagidon.snake.utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class MenuPanel extends JPanel implements GamePanel
{

    private final Dimension size;

    private final JButton startButton;
    private ActionListener listener;
    private MouseListener mouseListener;

    public MenuPanel(Dimension size) {
        this.size = size;
        this.startButton = new JButton("START");
    }

    @Override
    public void show(GameWindow window) {
        setPreferredSize(size);

        window.getFrame().add(this);

        window.getFrame().pack();

        initButtons(window);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageLoader.loadImage("logo.png"), 0, 0, size.width, size.height ,null);
    }

    private void initButtons(GameWindow window) {
        listener = (e) -> window.setGamePanel(new GameFrame(size));
        startButton.addActionListener(listener);
        startButton.addMouseListener(mouseListener);
        this.startButton.setPreferredSize(new Dimension(100, 50));
        add(startButton);
    }

    @Override
    public void hide(GameWindow window) {
        window.getFrame().remove(this);
        startButton.removeActionListener(listener);
        startButton.removeMouseListener(mouseListener);
    }
}
