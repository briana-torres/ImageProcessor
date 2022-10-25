package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents all the operations that can be used on an {@code Image} object.
 */
public class ImageImpl implements Image {

  private final int width;
  private final int height;
  private int maxValue;
  private final List<List<Pixel>> pixels;

  /**
   * Constructs a new Image object.
   *
   * @param width    the image width
   * @param height   the image height
   * @param maxValue the max RGB value in the image
   * @param pixels   the list of pixels in the image
   * @throws IllegalArgumentException if the given values are null or negative
   */
  public ImageImpl(int width, int height, int maxValue,
                   List<List<Pixel>> pixels) throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (width < 0 || height < 0 || maxValue < 0) {
      throw new IllegalArgumentException("Dimensions cannot be negative");
    }
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.pixels = pixels;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getMaxPixelValue() {
    return this.maxValue;
  }

  @Override
  public List<List<Pixel>> getPixels() {
    List<List<Pixel>> copy = new ArrayList<>();
    for (int i = 0; i < this.height; i++) {
      copy.add(new ArrayList<Pixel>());
      for (int j = 0; j < this.width; j++) {
        Pixel temp = this.pixels.get(i).get(j);
        copy.get(i).add(temp);
      }
    }
    return copy;
  }

  @Override
  public Image red() {
    List<List<Pixel>> tempPixels = this.getPixels();
    Image changed = new ImageImpl(this.getWidth(), this.getHeight(), this.getMaxPixelValue(),
        tempPixels);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Pixel temp = tempPixels.get(i).get(j);
        temp.changeColor(temp.getR(), temp.getR(), temp.getR());
      }
    }
    changed.changeMaxValue();
    return changed;
  }

  @Override
  public Image green() {
    List<List<Pixel>> tempPixels = this.getPixels();
    Image changed = new ImageImpl(this.getWidth(), this.getHeight(), this.getMaxPixelValue(),
        tempPixels);

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Pixel temp = tempPixels.get(i).get(j);
        temp.changeColor(temp.getG(), temp.getG(), temp.getG());
      }
    }
    changed.changeMaxValue();
    return changed;
  }

  @Override
  public Image blue() {
    List<List<Pixel>> tempPixels = this.getPixels();
    Image changed = new ImageImpl(this.getWidth(), this.getHeight(), this.getMaxPixelValue(),
        tempPixels);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Pixel temp = tempPixels.get(i).get(j);
        temp.changeColor(temp.getB(), temp.getB(), temp.getB());
      }
    }
    changed.changeMaxValue();
    return changed;
  }

  @Override
  public Image value() {
    int max = 0;
    List<List<Pixel>> tempPixels = this.getPixels();
    Image changed = new ImageImpl(this.getWidth(), this.getHeight(), this.getMaxPixelValue(),
        tempPixels);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Pixel temp = tempPixels.get(i).get(j);
        max = temp.value();
        temp.changeColor(max, max, max);
      }
    }
    changed.changeMaxValue();
    return changed;
  }

  @Override
  public Image intensity() {
    int average = 0;
    List<List<Pixel>> tempPixels = this.getPixels();
    Image changed = new ImageImpl(this.getWidth(), this.getHeight(), this.getMaxPixelValue(),
        tempPixels);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Pixel temp = tempPixels.get(i).get(j);
        average = temp.intensity();
        temp.changeColor(average, average, average);
      }
    }
    changed.changeMaxValue();
    return changed;
  }

  @Override
  public Image luma() {
    int luma = 0;
    List<List<Pixel>> tempPixels = this.getPixels();
    Image changed = new ImageImpl(this.getWidth(), this.getHeight(), this.getMaxPixelValue(),
        tempPixels);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Pixel temp = tempPixels.get(i).get(j);
        luma = temp.luma();
        temp.changeColor(luma, luma, luma);
      }
    }
    changed.changeMaxValue();
    return changed;
  }

  @Override
  public Image flipH() {
    List<List<Pixel>> tempPixels = this.getPixels();
    for (int i = 0; i < this.height; i++) {
      for (int j = this.width - 1; j >= this.width / 2 - 1; j--) {
        int flipped = this.width - j - 1;
        Pixel temp = this.pixels.get(i).get(j).clone();
        Pixel temp2 = this.pixels.get(i).get(flipped).clone();
        tempPixels.get(i).set(flipped, temp);
        tempPixels.get(i).set(j, temp2);
      }
    }
    return new ImageImpl(this.getWidth(), this.getHeight(), this.getMaxPixelValue(),
        tempPixels);
  }

  @Override
  public Image flipV() {
    List<List<Pixel>> tempPixels = this.getPixels();
    for (int i = this.height - 1; i >= this.height / 2 - 1; i--) {
      for (int j = 0; j < this.width; j++) {
        int flipped = this.height - i - 1;
        Pixel temp = this.pixels.get(i).get(j).clone();
        Pixel temp2 = this.pixels.get(flipped).get(j).clone();
        tempPixels.get(i).set(j, temp2);
        tempPixels.get(flipped).set(j, temp);
      }
    }
    return new ImageImpl(this.getWidth(), this.getHeight(), this.getMaxPixelValue(),
        tempPixels);
  }

  @Override
  public void changeMaxValue() {
    int max = 0;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Pixel temp = this.pixels.get(i).get(j);
        int current = temp.value();
        if (current > max) {
          max = current;
        }
      }
    }
    this.maxValue = max;
  }

  @Override
  public Image brighten(int value) {
    List<List<Pixel>> tempPixels = this.getPixels();
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Pixel temp = tempPixels.get(i).get(j);
        int r = temp.getR() + value;
        if (r > 255) {
          r = 255;
        }
        int g = temp.getG() + value;
        if (g > 255) {
          g = 255;
        }
        int b = temp.getB() + value;
        if (b > 255) {
          b = 255;
        }
        temp.changeColor(r, g, b);
      }
    }
    Image changed = new ImageImpl(this.getWidth(), this.getHeight(), this.getMaxPixelValue(),
        tempPixels);
    changed.changeMaxValue();
    return changed;
  }

  @Override
  public Image darken(int value) {
    List<List<Pixel>> tempPixels = this.getPixels();
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Pixel temp = tempPixels.get(i).get(j);
        int r = temp.getR() - value;
        if (r < 0) {
          r = 0;
        }
        int g = temp.getG() - value;
        if (g < 0) {
          g = 0;
        }
        int b = temp.getB() - value;
        if (b < 0) {
          b = 0;
        }
        temp.changeColor(r, g, b);
      }
    }
    Image changed = new ImageImpl(this.getWidth(), this.getHeight(), this.getMaxPixelValue(),
        tempPixels);
    changed.changeMaxValue();
    return changed;
  }

}
