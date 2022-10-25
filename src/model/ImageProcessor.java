package model;

/**
 * An interface the represents all the operations that can be used on an image processor.
 */
public interface ImageProcessor {

  /**
   * Updates the {@code Image} if it already exists in the directory
   * or creates a new image object and adds it to the existing directory.
   * @param name the image name
   * @param image the image
   */
  public void saveImage(String name, Image image);

  /**
   * Returns the desired {@code Image} from the directory.
   * @param name the image name
   * @return the image
   */
  public Image getImage(String name);

}
