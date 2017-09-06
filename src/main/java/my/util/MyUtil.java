package my.util;

import java.awt.image.BufferedImage;

public class MyUtil
{
    /**
     * Converts color image to grayscaled.
     * 
     * @param image color image
     * @return gray scaled image
     */
    public static BufferedImage grayScale(BufferedImage image)
    {
        //get image width and height
        int width = image.getWidth();
        int height = image.getHeight();

        //convert to grayscale
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int p = image.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                //calculate average
                int avg = (r + g + b) / 3;

                //replace RGB value with avg
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                image.setRGB(x, y, p);
            }
        }
        return image;
    }
}
