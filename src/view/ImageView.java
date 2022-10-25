package view;

import model.Image;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * An interface that represents all the operations used to visualize an
 * {@code ImageProcessor} without changing it.
 */
public interface ImageView {

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   */
  public void renderMessage(String message) throws IOException;

  /**
   * Make the view visible.
   */
  public void makeVisible();

  /**
   * Sets the text display to the given text.
   * @param text the given text
   */
  public void changeTextDisplay(String text);

  /**
   * Sets the load display to the given text.
   * @param text the given text
   */
  public void changeLoadDisplay(String text);

  /**
   * Sets the save display to the given text.
   * @param text the given text
   */
  public void changeSaveDisplay(String text);

  /**
   * Sets the image display to the given image.
   * @param image the given BufferedImage
   */
  public void changeImageDisplay(BufferedImage image);

  /**
   * Updates the view.
   */
  public void refresh();

  /**
   * Returns the option currently selected by the user.
   * @return the String option
   */
  public String selected();

  /**
   * Returns the value the user would like to brighten or darken the image by.
   * @return the value
   */
  public int value();

  /**
   * Returns the file path of the loaded in image.
   * @return the file path
   */
  public String load();

  /**
   * Provide the view with an action listener.
   * @param actionEvent the action event
   */
  void setCommandButtonListener(ActionListener actionEvent);

  /**
   * Returns the file path of the image that was saved.
   * @return the file path
   */
  public String save();

  /**
   * Updates all the histograms to match the current image.
   * @param image the displayed image
   */
  public void changeHistograms(Image image);

}
