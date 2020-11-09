package ru.armagidon.snake.utils;

import ru.armagidon.snake.Main;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader
{
    public static BufferedImage loadImage(String name)  {
        try {
            InputStream image = Main.class.getClassLoader().getResourceAsStream(name);
            if (image == null) return new BufferedImage(0,0,1);
            return ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
            return new BufferedImage(0,0,1);
        }
    }

    public static BufferedImage rotate(BufferedImage image, int degrees){
        final double rads = Math.toRadians(degrees);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w >> 1, h >> 1);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() >> 1, -image.getHeight() >> 1);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return rotateOp.filter(image,rotatedImage);
    }
}
