package ru.armagidon.snake.gameObjects;

import lombok.Getter;
import lombok.Setter;
import ru.armagidon.snake.utils.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Snake
{

    private @Getter final int SIZE = 40;

    private Direction direction;
    private final Direction initialDirection;

    private @Setter @Getter int x;
    private @Setter @Getter int y;

    private @Setter @Getter int tails = 0;

    private final LinkedList<Point> points = new LinkedList<>();
    private final LinkedList<Direction> directions = new LinkedList<>();

    private @Setter
    SnakeEvent snakeEvent;



    public Snake(int x, int y, Direction direction) {
        this.direction = this.initialDirection = direction ;
        this.x = x;
        this.y = y;
        this.snakeEvent = new SnakeEvent() {};
    }

    public void draw(Graphics2D g2){

        //Adding directions and points
        points.add(new Point(x, y));
        directions.add(direction);

        //Movement
        x += (direction.getMx() * (SIZE / 2));
        y += (direction.getMy() * (SIZE / 2));

        //Map coordinates
        snakeEvent.moved(this);

        Point point = new Point(x, y);

        //If there are tails
        if (tails > 0){

            // If snake eats itself, stop the game
            if (points.contains(point))
                snakeEvent.eatItSelf(g2);

            //Iterate them all and render them
            for (int i = 0; i < tails; i++) {
                int index = (points.size() - i) - 1;

                Point p = points.get(index);
                Direction dir = directions.get(index);

                BufferedImage TAIL = ImageLoader.loadImage("snake_body.png");

                g2.drawImage(ImageLoader.rotate(TAIL, dir.directionToDegrees()), p.x, p.y, SIZE, SIZE,null);
            }
            //Clear old tails
            int shouldContain = (points.size() - tails) - 1;
            if (shouldContain >= 0) {
                points.subList(0, shouldContain + 1).clear();
                directions.subList(0, shouldContain + 1).clear();
            }
        }

        //Render snake head
        BufferedImage HEAD = ImageLoader.loadImage("snake_head.png");
        g2.drawImage(ImageLoader.rotate(HEAD, direction.directionToDegrees()), x, y, SIZE, SIZE, null);
    }


    public void eat(Fruit fruit){
        //Generate new fruit
        fruit.generate();
        //Add new tail
        tails++;
        //Call event
        snakeEvent.eaten(tails);
    }

    public void turn(Direction direction){
        this.direction = direction;
    }

    public boolean checkCollision(Fruit fruit){
        int dX = x - fruit.getPosition().x;
        int dY = y - fruit.getPosition().y;

        int hypo = (int) Math.sqrt( Math.pow(dX, 2) + Math.pow(dY, 2));

        return hypo <= (fruit.getSize() + SIZE/2);
    }

    public void reset(){
        tails = 0;
        points.clear();
        directions.clear();
        direction = initialDirection;
        x = 0;
        y = 0;
    }

    public int getSize(){
        return SIZE;
    }






    public enum Direction {
        NORTH(0, -1),
        SOUTH(0,1),
        EAST(1,0),
        WEST(-1,0);

        private final int mX;
        private final int mY;

        Direction(int mX, int mY) {
            this.mX = mX;
            this.mY = mY;
        }

        public int getMx() {
            return mX;
        }

        public int getMy() {
            return mY;
        }

        public int directionToDegrees(){
/*            switch (this) {
                case NORTH:
                    return 270;
                case SOUTH:
                    return 90;
                case EAST:
                    return 0;
                case WEST:
                    return 180;
                default:
                    return -1;
            }*/
            return switch (this){
                case NORTH -> 270;
                case SOUTH -> 90;
                case EAST -> 0;
                case WEST -> 180;
            };
        }

    }
}
