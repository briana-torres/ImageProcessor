package model.colortransformation;

import model.Image;

import java.util.List;

/**
 * An interface used to create a color transformation to be applied to an image.
 */
public interface ColorTransformation {

  /**
   * Creates a new image by applying the color transformation to each pixel.
   * @param image the given image to transform
   * @return a new transformed image
   */
  public Image transform(Image image);

  /**
   * Returns a copy of the matrix.
   * @return a 2D List copy
   */
  public List<List<Double>> getMatrix();

}
