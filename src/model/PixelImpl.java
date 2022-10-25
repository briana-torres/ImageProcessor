package model;

/**
 * This class represents all the operations than can be used on
 * a singular pixel object.
 */
public class PixelImpl implements Pixel {

  private int r;
  private int g;
  private int b;

  /**
   * Constructs a new pixel with the given parameters.
   * @param r the red value
   * @param g the green value
   * @param b the blue value
   * @throws IllegalArgumentException if the position is negative or the color is not in RGB format
   */
  public PixelImpl(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("RBG values cannot be larger than 255 or smaller than 0");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getG() {
    return this.g;
  }

  @Override
  public int getB() {
    return this.b;
  }

  @Override
  public void changeColor(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public int intensity() {
    return (r + g + b) / 3;
  }

  @Override
  public int value() {

    int max = r;

    if (g > max) {
      max = g;
    }

    if (b > max) {
      max = b;
    }

    return max;
  }

  @Override
  public int luma() {
    return (int) ((0.2126 * r) + (0.7152 * g) + (0.0722 * b));
  }

  @Override
  public Pixel clone() {
    return new PixelImpl(this.getR(), this.getG(), this.getB());
  }

}
