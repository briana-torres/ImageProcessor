package controller;

import model.Image;

import java.awt.event.ActionListener;

/**
 * An interface that represents all the operations a controller can do, with the information given
 * from the model, directed by the user.
 */
public interface ImageController extends ActionListener {

  /**
   * Creates a new image object and adds it to existing directory.
   * @param path the image path
   * @param name the image name
   */
  public void load(String path, String name);

  /**
   * Creates a new PPM file to the given path.
   * @param image the image to be converted
   * @param path the file path
   */
  public void save(Image image, String path);

  /**
   * Allows the user to begin processing images.
   */
  public void process();

  /**
   * Start the program, i.e. give control to the controller.
   */
  public void openGUI();

}
