package model.filter;

import controller.Converter;
import model.Image;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Abstract class to reduce the code duplication across filters.
 */
public abstract class AbstractFilter extends Converter implements Filter {

  private final float[] matrix;
  private final int matrixWidth;
  private final int matrixHeight;

  /**
   * Super constructor that takes in a matrix that produces a filtered image.
   * @param matrixWidth the matrix width
   * @param matrixHeight the matrix height
   * @param matrix the float[] matrix
   * @throws IllegalArgumentException if the matrix does not have odd dimensions
   */
  public AbstractFilter(int matrixWidth, int matrixHeight, float[] matrix)
      throws IllegalArgumentException {
    if (matrix == null) {
      throw new IllegalArgumentException("Matrix cannot be null");
    }
    if (matrixWidth % 2 == 0 || matrixHeight % 2 == 0) {
      throw new IllegalArgumentException("Matrix must have odd dimensions");
    }
    this.matrix = matrix;
    this.matrixWidth = matrixWidth;
    this.matrixHeight = matrixHeight;
  }

  @Override
  public Image filter(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    BufferedImage bufferedImage = this.toBufferedImage(image);
    BufferedImage blank = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    BufferedImageOp op = new ConvolveOp( new Kernel(matrixWidth, matrixHeight, matrix) );
    BufferedImage filtered = op.filter(bufferedImage, blank);

    return this.toImage(filtered);
  }

  @Override
  public float[] getMatrix() {
    return this.matrix.clone();
  }

  @Override
  public int getMatrixWidth() {
    return matrixWidth;
  }

  @Override
  public int getMatrixHeight() {
    return matrixHeight;
  }
}
