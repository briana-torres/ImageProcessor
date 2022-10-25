package model;

/**
 * An interface that represents the operations that can be used on a {@code Pixel} object.
 */
public interface Pixel {

  /**
   * Returns the red value of the pixel.
   * @return an int representing the red value
   */
  public int getR();

  /**
   * Returns the green value of the pixel.
   * @return an int representing the green value
   */
  public int getG();

  /**
   * Returns the blue value of the pixel.
   * @return an int representing the blue value
   */
  public int getB();

  /**
   * Modifies the RGB values of a pixel.
   * @param r the red value
   * @param g the green value
   * @param b the blue value
   */
  public void changeColor(int r, int g, int b);

  /**
   * Calculates the average of the three components for a pixel.
   * @return the average
   */
  public int intensity();

  /**
   * Calculates the max value of the three components for a pixel.
   * @return the max value
   */
  public int value();

  /**
   * Calculates the luma using the three components of a pixel.
   * @return the luma
   */
  public int luma();

  /**
   * Creates a copy of the pixel.
   * @return the copy
   */
  public Pixel clone();

}
