package ru.armagidon.snake.utils;

import ru.armagidon.snake.panels.GameFrame;

import java.awt.event.KeyEvent;

public class KeyBoardHandler
{

    private final GameFrame gameFrame;

    public KeyBoardHandler(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void registerKeys() {

    }

    public void keyPressed(KeyEvent e) {
/*        if (e.getKeyCode() == KeyEvent.VK_UP){
            gameFrame.snake.turn(Snake.Direction.NORTH);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            gameFrame.snake.turn(Snake.Direction.SOUTH);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gameFrame.snake.turn(Snake.Direction.WEST);
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            gameFrame.snake.turn(Snake.Direction.EAST);
        } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if (gameFrame.gameOver){
                gameFrame.reset();
                gameFrame.gameOver = false;
            }
        }*/
    }
}
