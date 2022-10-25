package controller;

import model.Image;
import model.ImageImpl;
import model.Pixel;
import model.PixelImpl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that converts between Image and BufferedImage objects.
 */
public class Converter {

  /**
   * Converts from a BufferedImage to an Image.
   * @param bufferedImage the given BufferedImage
   * @return the new Image object
   */
  public Image toImage(BufferedImage bufferedImage) {
    int height = bufferedImage.getHeight();
    int width = bufferedImage.getWidth();
    List<List<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      pixels.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        Color color = new Color(bufferedImage.getRGB(j, i));
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        pixels.get(i).add(new PixelImpl(r, g, b));
      }
    }
    Image image = new ImageImpl(width, height, 255, pixels);
    image.changeMaxValue();
    return image;
  }

  /**
   * Converts from an Image to a BufferedImage.
   * @param image the given Image
   * @return the new BufferedImage object
   */
  public BufferedImage toBufferedImage(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    List<List<Pixel>> tempPixels = image.getPixels();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel temp = tempPixels.get(i).get(j);
        Color color = new Color(temp.getR(), temp.getG(), temp.getB());
        int rgb = color.getRGB();
        bufferedImage.setRGB(j, i, rgb);
      }
    }
    return bufferedImage;
  }

}
