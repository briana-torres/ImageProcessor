package model;

import java.util.List;

/**
 * An interface representing all the operations that can be done to an {@code Image} object.
 */
public interface Image {

  /**
   * Returns the width of the image.
   * @return the width
   */
  public int getWidth();

  /**
   * Returns the height of the image.
   * @return the height
   */
  public int getHeight();

  /**
   * Returns the max pixel value in the image.
   * @return the max pixel value
   */
  public int getMaxPixelValue();


  /**
   * Returns all the pixels in an image.
   * @return a 2D ArrayList copy of all the pixels
   */
  public List<List<Pixel>> getPixels();


  /**
   * Converts the image to greyscale using only the red components.
   * @return the changed image
   */
  public Image red();

  /**
   * Converts the image to greyscale using only the green components.
   * @return the changed image
   */
  public Image green();

  /**
   * Converts the image to greyscale using only the blue components.
   * @return the changed image
   */
  public Image blue();

  /**
   * Converts the image to greyscale using the value.
   * @return the changed image
   */
  public Image value();

  /**
   * Converts the image to greyscale using the intensity.
   * @return the changed image
   */
  public Image intensity();

  /**
   * Converts the image to greyscale using the luma.
   * @return the changed image
   */
  public Image luma();


  /**
   * Modifies the x and y of the pixels in the image to flip it horizontally.
   * @return the changed image
   */
  public Image flipH();

  /**
   * Modifies the x and y of the pixels in the image to flip it vertically.
   * @return the changed image
   */
  public Image flipV();

  /**
   * Modifies the x and y of the pixels in the image to brighten the image.
   * @param value the given value to brighten the image
   * @return the changed image
   */
  public Image brighten(int value);

  /**
   * Modifies the x and y of the pixels in the image to darken the image.
   * @param value the given value to darken the image
   * @return the changed image
   */
  public Image darken(int value);

  /**
   * Updates the max RGB value.
   */
  public void changeMaxValue();

}
