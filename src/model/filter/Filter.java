package model.filter;

import model.Image;

/**
 * An interface used to create a filter to be applied to an image.
 */
public interface Filter {

  /**
   * Creates a new image by applying the filter.
   * @param image the given image to transform
   * @return a new transformed image
   */
  public Image filter(Image image);

  /**
   * Returns a copy of the matrix.
   * @return the float[] copy
   */
  public float[] getMatrix();

  /**
   * Returns the matrix width.
   * @return the matrix width
   */
  public int getMatrixWidth();

  /**
   * Returns the matrix height.
   * @return the matrix height
   */
  public int getMatrixHeight();

}
