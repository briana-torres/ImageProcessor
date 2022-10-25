package model.colortransformation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class to apply a sepia-toned color transformation to an image.
 */
public class Sepia extends AbstractColorTransformation {

  /**
   * Creates a new ColorTransformation object with a matrix that produces a sepia-toned image.
   * @throws IllegalArgumentException if the matrix is null
   */
  public Sepia() throws IllegalArgumentException {
    super(new ArrayList<>(
        Arrays.asList(
            (new ArrayList<>(Arrays.asList(0.393, 0.769, 0.189))),
            (new ArrayList<>(Arrays.asList(0.349, 0.686, 0.168))),
            (new ArrayList<>(Arrays.asList(0.272, 0.534, 0.131))))));
  }
}
