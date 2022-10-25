import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageProcessor;
import model.ImageProcessorImpl;
import view.ImageView;
import view.ImageViewImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A class used to run the main method.
 */
public class Main {

  /**
   * Runs the controller program to begin taking in user input.
   *
   * @param args String argument
   */
  public static void main(String[] args) throws FileNotFoundException {
    ImageProcessor processor = new ImageProcessorImpl();
    ImageView view = new ImageViewImpl(processor, System.out);
    ImageController controller =
        new ImageControllerImpl(processor, view, new InputStreamReader(getInputStream(args)));
    if (args != null && args.length == 1) {
      if ("-text".equals(args[0])) {
        controller.process();
      }
      else {
        System.out.println("Invalid command line argument");
      }
    }
    controller.openGUI();
  }

  private static InputStream getInputStream(String[] args) throws FileNotFoundException {

    if (args != null && args.length == 2) {
      if ("-file".equals(args[0])) {
        return new FileInputStream(args[1]);
      }
    }

    return System.in;
  }

}
