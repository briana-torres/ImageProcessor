package model.colortransformation;

import model.Image;
import model.ImageImpl;
import model.Pixel;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class to reduce the code duplication across color transformations.
 */
public abstract class AbstractColorTransformation implements ColorTransformation {

  private final List<List<Double>> matrix;

  /**
   * Super constructor that takes in a matrix that produces a transformed image.
   * @param matrix a 2D List of double values that must be a 3x3 matrix
   * @throws IllegalArgumentException if the matrix is null
   */
  public AbstractColorTransformation(List<List<Double>> matrix)
      throws IllegalArgumentException {
    if (matrix == null) {
      throw new IllegalArgumentException("Matrix cannot be null");
    }
    this.matrix = matrix;
  }

  @Override
  public Image transform(Image image) {
    List<List<Pixel>> tempPixels = image.getPixels();
    List<List<Double>> tempMatrix = this.getMatrix();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel temp = tempPixels.get(i).get(j);
        int r = temp.getR();
        int g = temp.getG();
        int b = temp.getB();
        int changedR = (int) ((tempMatrix.get(0).get(0) * r)
            + (tempMatrix.get(0).get(1) * g) + (tempMatrix.get(0).get(2) * b));
        if (changedR > 255) {
          changedR = 255;
        }
        if (changedR < 0) {
          changedR = 0;
        }
        int changedG = (int) ((tempMatrix.get(1).get(0) * r)
            + (tempMatrix.get(1).get(1) * g) + (tempMatrix.get(1).get(2) * b));
        if (changedG > 255) {
          changedG = 255;
        }
        if (changedG < 0) {
          changedG = 0;
        }
        int changedB = (int) ((tempMatrix.get(2).get(0) * r)
            + (tempMatrix.get(2).get(1) * g) + (tempMatrix.get(2).get(2) * b));
        if (changedB > 255) {
          changedB = 255;
        }
        if (changedB < 0) {
          changedB = 0;
        }
        temp.changeColor(changedR, changedG, changedB);
      }
    }
    Image changed = new ImageImpl(image.getWidth(), image.getHeight(), image.getMaxPixelValue(),
        tempPixels);
    changed.changeMaxValue();
    return changed;
  }

  @Override
  public List<List<Double>> getMatrix() {
    List<List<Double>> copy = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      copy.add(new ArrayList<>());
      for (int j = 0; j < 3; j++) {
        Double temp = this.matrix.get(i).get(j);
        copy.get(i).add(temp);
      }
    }
    return copy;
  }
}
