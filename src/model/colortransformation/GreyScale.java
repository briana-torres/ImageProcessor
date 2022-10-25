package model.colortransformation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class to apply a greyscale color transformation to an image.
 */
public class GreyScale extends AbstractColorTransformation {

  /**
   * Creates a new ColorTransformation object with a matrix that produces a greyscale image.
   * @throws IllegalArgumentException if the given matrix is null
   */
  public GreyScale() throws IllegalArgumentException {
    super(new ArrayList<>(
        Arrays.asList(
            (new ArrayList<>(Arrays.asList(0.2126, 0.7152, 0.0722))),
            (new ArrayList<>(Arrays.asList(0.2126, 0.7152, 0.0722))),
            (new ArrayList<>(Arrays.asList(0.2126, 0.7152, 0.0722))))));
  }
}
