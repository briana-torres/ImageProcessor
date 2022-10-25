package model.filter;

/**
 * A class to apply a filter to sharpen an image.
 */
public class Sharpen extends AbstractFilter {

  /**
   * Creates a new Filter object with a matrix that produces a sharpened image.
   *
   * @throws IllegalArgumentException if the matrix does not have odd dimensions or is null
   */
  public Sharpen() throws IllegalArgumentException {
    super(5, 5, new float[]{
        -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f,
        -1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f,
        -1 / 8f, 1 / 4f, 1f, 1 / 4f, -1 / 8f,
        -1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f,
        -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f});
  }
}
