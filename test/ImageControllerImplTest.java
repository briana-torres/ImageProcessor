import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageProcessor;
import model.ImageProcessorImpl;
import model.ImageImpl;
import model.Pixel;
import model.PixelImpl;
import org.junit.Before;
import org.junit.Test;
import view.ImageViewImpl;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * A class used to test the controller and all the methods it can use on a processor and view.
 */
public class ImageControllerImplTest {

  private StringBuilder log;
  private ImageProcessor processor;
  private ImageViewImpl view;
  private ImageController controller;
  private Reader in;

  @Before
  public void init() {
    Pixel p1 = new PixelImpl(255, 102, 178);
    Pixel p2 = new PixelImpl(153, 255, 255);
    Pixel p3 = new PixelImpl(178, 255, 102);
    Pixel p4 = new PixelImpl(255, 255, 153);
    List<List<Pixel>> pixels = new ArrayList<>(
        Arrays.asList(
            (new ArrayList<>(Arrays.asList(p1, p2))),
            (new ArrayList<>(Arrays.asList(p3, p4)))));
    ImageImpl image = new ImageImpl(2, 2, 255, pixels);
    log = new StringBuilder();
    processor = new ImageProcessorImpl();
    view = new ImageViewImpl(processor, log);
  }

  @Test
  public void testQuit() {
    in = new StringReader("quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:" + "\n" + "Program has quit", log.toString());
  }

  @Test
  public void testInvalidCommand() {
    in = new StringReader("hellooooo res/images/Test.ppm Test" + "\n" + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
        + "\n"
        + "Invalid command please try again"
        + "\n"
        + "Program has quit", log.toString());
  }

  @Test
  public void testLoadPPM() {
    in = new StringReader("load res/images/Test.ppm Test" + "\n" + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
            + "\n"
            + "Image loaded"
            + "\n"
            + "Program has quit", log.toString());
  }

  @Test
  public void testLoadPNG() {
    in = new StringReader("load res/images/Test.png Test" + "\n" + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
        + "\n"
        + "Image loaded"
        + "\n"
        + "Program has quit", log.toString());
  }

  @Test
  public void testLoadJPG() {
    in = new StringReader("load res/images/Test.jpg Test" + "\n" + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
        + "\n"
        + "Image loaded"
        + "\n"
        + "Program has quit", log.toString());
  }

  @Test
  public void testLoadBMP() {
    in = new StringReader("load res/images/Test.bmp Test" + "\n" + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
        + "\n"
        + "Image loaded"
        + "\n"
        + "Program has quit", log.toString());
  }

  @Test
  public void testRed() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "red-component Test Test-GreyScale-Red"
        + "\n"
        + "save res/images/Test-GreyScale-Red.ppm Test-GreyScale-Red"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
            + "\n"
            + "Image loaded"
            + "\n"
            + "Image changed to greyscale using the red component"
            + "\n"
            + "Image has been saved"
            + "\n"
            + "Program has quit", log.toString());
  }

  @Test
  public void testGreen() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "green-component Test Test-GreyScale-Green"
        + "\n"
        + "save res/images/Test-GreyScale-Green.ppm Test-GreyScale-Green"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
            + "\n"
            + "Image loaded"
            + "\n"
            + "Image changed to greyscale using the green component"
            + "\n"
            + "Image has been saved"
            + "\n"
            + "Program has quit", log.toString());
  }

  @Test
  public void testBlue() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "blue-component Test Test-GreyScale-Blue"
        + "\n"
        + "save res/images/Test-GreyScale-Blue.ppm Test-GreyScale-Blue"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
            + "\n"
            + "Image loaded"
            + "\n"
            + "Image changed to greyscale using the blue component"
            + "\n"
            + "Image has been saved"
            + "\n"
            + "Program has quit", log.toString());
  }

  @Test
  public void testValue() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "value-component Test Test-GreyScale-Value"
        + "\n"
        + "save res/images/Test-GreyScale-Value.ppm Test-GreyScale-Value"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
            + "\n"
            + "Image loaded"
            + "\n"
            + "Image changed to greyscale using the value"
            + "\n"
            + "Image has been saved"
            + "\n"
            + "Program has quit", log.toString());
  }

  @Test
  public void testIntensity() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "intensity-component Test Test-GreyScale-Intensity"
        + "\n"
        + "save res/images/Test-GreyScale-Intensity.ppm Test-GreyScale-Intensity"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
            + "\n"
            + "Image loaded"
            + "\n"
            + "Image changed to greyscale using the intensity"
            + "\n"
            + "Image has been saved"
            + "\n"
            + "Program has quit", log.toString());
  }

  @Test
  public void testLuma() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "luma-component Test Test-GreyScale-Luma"
        + "\n"
        + "save res/images/Test-GreyScale-Luma.ppm Test-GreyScale-Luma"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
            + "\n"
            + "Image loaded"
            + "\n"
            + "Image changed to greyscale using the luma"
            + "\n"
            + "Image has been saved"
            + "\n"
            + "Program has quit", log.toString());
  }

  @Test
  public void testFlipV() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "vertical-flip Test Test-Flip-Vertical"
        + "\n"
        + "save res/images/Test-Flip-Vertical.ppm Test-Flip-Vertical"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
            + "\n"
            + "Image loaded"
            + "\n"
            + "Image flipped vertically"
            + "\n"
            + "Image has been saved"
            + "\n"
            + "Program has quit", log.toString());
  }

  @Test
  public void testFlipH() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "horizontal-flip Test Test-Flip-Horizontal"
        + "\n"
        + "save res/images/Test-Flip-Horizontal.ppm Test-Flip-Horizontal"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
            + "\n"
            + "Image loaded"
            + "\n"
            + "Image flipped horizontally"
            + "\n"
            + "Image has been saved"
            + "\n"
            + "Program has quit", log.toString());
  }

  @Test
  public void testBrighten() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "brighten 50 Test Test-Brighten"
        + "\n"
        + "save res/images/Test-Brighten.ppm Test-Brighten"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
            + "\n"
            + "Image loaded"
            + "\n"
            + "Image brightened by 50"
            + "\n"
            + "Image has been saved"
            + "\n"
            + "Program has quit", log.toString());
  }

  @Test
  public void testDarken() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "darken 50 Test Test-Darken"
        + "\n"
        + "save res/images/Test-Darken.ppm Test-Darken"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
            + "\n"
            + "Image loaded"
            + "\n"
            + "Image darkened by 50"
            + "\n"
            + "Image has been saved"
            + "\n"
            + "Program has quit", log.toString());
  }

  @Test
  public void testBlur() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "blur Test Test-Blur"
        + "\n"
        + "save res/images/Test-Blur.png Test-Blur"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
        + "\n"
        + "Image loaded"
        + "\n"
        + "Image blurred"
        + "\n"
        + "Image has been saved"
        + "\n"
        + "Program has quit", log.toString());
  }

  @Test
  public void testSharpen() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "sharpen Test Test-Sharpen"
        + "\n"
        + "save res/images/Test-Sharpen.jpg Test-Sharpen"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
        + "\n"
        + "Image loaded"
        + "\n"
        + "Image sharpened"
        + "\n"
        + "Image has been saved"
        + "\n"
        + "Program has quit", log.toString());
  }

  @Test
  public void testGreyscale() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "Greyscale Test Test-Greyscale"
        + "\n"
        + "save res/images/Test-Greyscale.bmp Test-Greyscale"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
        + "\n"
        + "Image loaded"
        + "\n"
        + "Image has been transformed into a greyscale image"
        + "\n"
        + "Image has been saved"
        + "\n"
        + "Program has quit", log.toString());
  }

  @Test
  public void testSepia() {
    in = new StringReader("load res/images/Test.ppm Test"
        + "\n"
        + "Sepia Test Test-Sepia"
        + "\n"
        + "save res/images/Test-Sepia.ppm Test-Sepia"
        + "\n"
        + "quit");
    controller = new ImageControllerImpl(processor, view, in);
    controller.process();
    assertEquals("Please enter commands below:"
        + "\n"
        + "Image loaded"
        + "\n"
        + "Image has been sepia-toned"
        + "\n"
        + "Image has been saved"
        + "\n"
        + "Program has quit", log.toString());
  }

}