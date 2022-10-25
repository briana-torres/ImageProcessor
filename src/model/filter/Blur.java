package model.filter;

/**
 * A class to apply a blur filter to an image.
 */
public class Blur extends AbstractFilter {

  /**
   * Creates a new Filter object with a matrix that produces a blurred image.
   *
   * @throws IllegalArgumentException if the matrix does not have odd dimensions or is null
   */
  public Blur() throws IllegalArgumentException {
    super(3, 3, new float[]{
        1 / 16f, 1 / 8f, 1 / 16f,
        1 / 8f, 1 / 4f, 1 / 8f,
        1 / 16f, 1 / 8f, 1 / 16f});
  }

}
