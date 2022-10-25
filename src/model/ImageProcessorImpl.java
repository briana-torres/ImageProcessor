package model;

import java.util.HashMap;

/**
 * A class that represents all the operations that can be used on an {@code ImageProcessorImpl}.
 */
public class ImageProcessorImpl implements ImageProcessor {

  HashMap<String, Image> directory;

  /**
   * Creates a new ImageProcessorImpl with a {@code HashMap} to store all the created images.
   */
  public ImageProcessorImpl() {
    this.directory = new HashMap<>();
  }

  @Override
  public void saveImage(String name, Image image) {
    this.directory.put(name, image);
  }

  @Override
  public Image getImage(String name) {
    return this.directory.get(name);
  }
}
