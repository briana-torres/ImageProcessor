package controller;

import model.colortransformation.GreyScale;
import model.colortransformation.Sepia;
import model.filter.Blur;
import model.filter.Sharpen;
import model.ImageProcessor;
import model.Image;
import model.ImageImpl;
import model.Pixel;
import model.PixelImpl;
import view.ImageView;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


/**
 * A class that represents all the methods in an ImageControllerImpl.
 */
public class ImageControllerImpl extends Converter implements
    ImageController, ActionListener {

  private final ImageProcessor processor;
  private final ImageView view;
  private final Readable input;
  private int imageCount = 0;
  private String loadedImage;
  private String lastImage;
  private String displayedImage;

  /**
   * Creates a new controller.
   *
   * @param processor the ImageProcessor object
   * @param input     the Readable object
   */
  public ImageControllerImpl(ImageProcessor processor, ImageView view, Readable input)
      throws IllegalArgumentException {
    if (processor == null || view == null || input == null) {
      throw new IllegalArgumentException("Inputs cannot be null");
    }
    this.processor = processor;
    this.view = view;
    this.input = input;
  }

  @Override
  public void load(String path, String name) {
    String fileType = path.substring(path.length() - 3).toLowerCase();
    switch (fileType) {
      case "ppm":
        this.loadPPM(path, name);
        break;
      case "jpg":
      case "png":
      case "bmp":
        this.loadHelper(path, name);
        break;
      default:
        try {
          this.view.changeTextDisplay("Error: File cannot be loaded");
          this.view.refresh();
          this.view.renderMessage("Cannot load file type");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        break;
    }

  }

  private void loadHelper(String path, String name) {
    try {
      File file = new File(path);
      BufferedImage bufferedImage = null;
      bufferedImage = ImageIO.read(file);
      Image image = this.toImage(bufferedImage);
      this.processor.saveImage(name, image);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void loadPPM(String path, String name) {
    Scanner sc;
    List<List<Pixel>> pixels = new ArrayList<>();
    Image image;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + path + " not found!");
    }

    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    for (int i = 0; i < height; i++) {
      pixels.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels.get(i).add(new PixelImpl(r, g, b));
      }
    }
    image = new ImageImpl(width, height, maxValue, pixels);
    this.processor.saveImage(name, image);
  }


  @Override
  public void save(Image image, String path) {
    String fileType = path.substring(path.length() - 3).toLowerCase();

    if (fileType.equals("ppm")) {
      this.savePPM(image, path);
    }
    try {
      if (fileType.equals("jpg")) {
        File file = new File(path);
        BufferedImage bufferedImage = this.toBufferedImage(image);
        ImageIO.write(bufferedImage, "jpg", file);
      }
      if (fileType.equals("png")) {
        File file = new File(path);
        BufferedImage bufferedImage = this.toBufferedImage(image);
        ImageIO.write(bufferedImage, "png", file);
      }
      if (fileType.equals("bmp")) {
        File file = new File(path);
        BufferedImage bufferedImage = this.toBufferedImage(image);
        ImageIO.write(bufferedImage, "bmp", file);
      }
    } catch (IOException e) {
      this.view.changeTextDisplay("Error: File cannot be saved");
      this.view.refresh();
      System.out.println("Error: File cannot be saved");
      throw new RuntimeException(e);
    }

  }

  private void savePPM(Image image, String path) {
    StringBuilder builder = new StringBuilder();
    builder.append("P3\n");
    builder.append(Integer.toString(image.getWidth())).append(" ");
    builder.append(Integer.toString(image.getHeight())).append("\n");
    builder.append(Integer.toString(image.getMaxPixelValue())).append("\n");
    List<List<Pixel>> pixels = image.getPixels();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel temp = pixels.get(i).get(j);
        builder.append(temp.getR()).append("\n");
        builder.append(temp.getG()).append("\n");
        builder.append(temp.getB()).append("\n");
      }
    }

    try {
      File blank = new File(path);
      if (blank.createNewFile()) {
        System.out.println("File created: " + blank.getName());
      } else {
        System.out.println("File has been overridden");
      }
      FileWriter file = new FileWriter(blank);
      file.write(builder.toString());
      file.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private void red(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = current.red();
    this.processor.saveImage(newImage, changed);
  }

  private void green(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = current.green();
    this.processor.saveImage(newImage, changed);
  }

  private void blue(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = current.blue();
    this.processor.saveImage(newImage, changed);
  }

  private void value(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = current.value();
    this.processor.saveImage(newImage, changed);
  }

  private void intensity(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = current.intensity();
    this.processor.saveImage(newImage, changed);
  }

  private void luma(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = current.luma();
    this.processor.saveImage(newImage, changed);
  }

  private void flipV(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = current.flipV();
    this.processor.saveImage(newImage, changed);
  }

  private void flipH(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = current.flipH();
    this.processor.saveImage(newImage, changed);
  }

  private void brighten(int value, String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = current.brighten(value);
    this.processor.saveImage(newImage, changed);
  }

  private void darken(int value, String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = current.darken(value);
    this.processor.saveImage(newImage, changed);
  }

  private void blur(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = new Blur().filter(current);
    this.processor.saveImage(newImage, changed);
  }

  private void sharpen(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = new Sharpen().filter(current);
    this.processor.saveImage(newImage, changed);
  }

  private void greyscale(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = new GreyScale().transform(current);
    this.processor.saveImage(newImage, changed);
  }

  private void sepia(String image, String newImage) {
    Image current = this.processor.getImage(image);
    Image changed = new Sepia().transform(current);
    this.processor.saveImage(newImage, changed);
  }

  private void saveHelper(String path, String name) {
    Image image = this.processor.getImage(name);
    this.save(image, path);
  }

  @Override
  public void process() throws IllegalStateException {
    Scanner s = new Scanner(this.input);
    String command1;
    String command2 = "";
    String command3 = "";
    int command4 = 0;

    try {
      this.view.renderMessage("Please enter commands below:");
      this.view.renderMessage("\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    while (s.hasNext()) {
      try {
        command1 = s.next().toLowerCase();
        if (command1.equals("quit")) {
          this.view.renderMessage("Program has quit");
          System.exit(0);
        }
        if (s.hasNextInt()) {
          command4 = s.nextInt();
        }
        if (s.hasNext()) {
          command2 = s.next();
        }
        if (s.hasNext()) {
          command3 = s.next();
        }
        switch (command1) {
          case "load":
            this.load(command2, command3);
            this.view.renderMessage("Image loaded");
            this.view.renderMessage("\n");
            break;
          case "red-component":
            this.red(command2, command3);
            this.view.renderMessage("Image changed to greyscale using the red component");
            this.view.renderMessage("\n");
            break;
          case "green-component":
            this.green(command2, command3);
            this.view.renderMessage("Image changed to greyscale using the green component");
            this.view.renderMessage("\n");
            break;
          case "blue-component":
            this.blue(command2, command3);
            this.view.renderMessage("Image changed to greyscale using the blue component");
            this.view.renderMessage("\n");
            break;
          case "value-component":
            this.value(command2, command3);
            this.view.renderMessage("Image changed to greyscale using the value");
            this.view.renderMessage("\n");
            break;
          case "intensity-component":
            this.intensity(command2, command3);
            this.view.renderMessage("Image changed to greyscale using the intensity");
            this.view.renderMessage("\n");
            break;
          case "luma-component":
            this.luma(command2, command3);
            this.view.renderMessage("Image changed to greyscale using the luma");
            this.view.renderMessage("\n");
            break;
          case "vertical-flip":
            this.flipV(command2, command3);
            this.view.renderMessage("Image flipped vertically");
            this.view.renderMessage("\n");
            break;
          case "horizontal-flip":
            this.flipH(command2, command3);
            this.view.renderMessage("Image flipped horizontally");
            this.view.renderMessage("\n");
            break;
          case "brighten":
            this.brighten(command4, command2, command3);
            this.view.renderMessage("Image brightened by " + Integer.toString(command4));
            this.view.renderMessage("\n");
            break;
          case "darken":
            this.darken(command4, command2, command3);
            this.view.renderMessage("Image darkened by " + Integer.toString(command4));
            this.view.renderMessage("\n");
            break;
          case "blur":
            this.blur(command2, command3);
            this.view.renderMessage("Image blurred");
            this.view.renderMessage("\n");
            break;
          case "sharpen":
            this.sharpen(command2, command3);
            this.view.renderMessage("Image sharpened");
            this.view.renderMessage("\n");
            break;
          case "greyscale":
            this.greyscale(command2, command3);
            this.view.renderMessage("Image has been transformed into a greyscale image");
            this.view.renderMessage("\n");
            break;
          case "sepia":
            this.sepia(command2, command3);
            this.view.renderMessage("Image has been sepia-toned");
            this.view.renderMessage("\n");
            break;
          case "save":
            this.saveHelper(command2, command3);
            this.view.renderMessage("Image has been saved");
            this.view.renderMessage("\n");
            break;
          default:
            this.view.renderMessage("Invalid command please try again");
            this.view.renderMessage("\n");
            break;
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public void openGUI() {
    this.view.setCommandButtonListener(this);
    this.view.makeVisible();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int amount = 0;
    switch (e.getActionCommand()) {
      case "load button":
        this.loadButtonHelper();
        break;
      case "apply button":
        this.applyHelper();
        break;
      case "save button":
        this.saveGUIHelper();
        break;
      default:
        //do nothing
        break;
    }
  }

  private void loadButtonHelper() {
    this.imageCount++;
    loadedImage = "image" + Integer.toString(imageCount);
    this.lastImage = loadedImage;
    String path = this.view.load();
    if (path.length() >= 5) {
      this.load(path, loadedImage);
      Image image = processor.getImage(loadedImage);
      this.view.changeHistograms(image);
      this.view.changeImageDisplay(this.toBufferedImage(image));
      this.view.changeTextDisplay("Image has been successfully loaded");
      this.view.changeLoadDisplay(path);
      this.view.refresh();
    }
  }

  private void applyHelper() {
    Image image;
    int value;
    switch (view.selected()) {
      case "red-component":
        this.displayedImage = loadedImage + "-red-component";
        this.red(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image changed to greyscale using the red component");
        this.view.refresh();
        break;
      case "green-component":
        this.displayedImage = loadedImage + "-green-component";
        this.green(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image changed to greyscale using the green component");
        this.view.refresh();
        break;
      case "blue-component":
        this.displayedImage = loadedImage + "-blue-component";
        this.blue(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image changed to greyscale using the blue component");
        this.view.refresh();
        break;
      case "value":
        this.displayedImage = loadedImage + "-value";
        this.value(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image changed to greyscale using the value");
        this.view.refresh();
        break;
      case "intensity":
        this.displayedImage = loadedImage + "-intensity";
        this.intensity(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image changed to greyscale using the intensity");
        this.view.refresh();
        break;
      case "luma":
        this.displayedImage = loadedImage + "-luma";
        this.luma(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image changed to greyscale using the luma");
        this.view.refresh();
        break;
      case "greyscale":
        this.displayedImage = loadedImage + "-greyscale";
        this.greyscale(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image changed to greyscale");
        this.view.refresh();
        break;
      case "sepia":
        this.displayedImage = loadedImage + "-sepia";
        this.sepia(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image has been sepia-toned");
        this.view.refresh();
        break;
      case "blur":
        this.displayedImage = loadedImage + "-blur";
        this.blur(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image has been blurred");
        this.view.refresh();
        break;
      case "sharpen":
        this.displayedImage = loadedImage + "-sharpen";
        this.sharpen(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image has been sharpened");
        this.view.refresh();
        break;
      case "flip-vertically":
        this.displayedImage = loadedImage + "-flip-vertical";
        this.flipV(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image has been flipped vertically");
        this.view.refresh();
        break;
      case "flip-horizontally":
        this.displayedImage = loadedImage + "-flip-horizontal";
        this.flipH(lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image has been flipped horizontally");
        this.view.refresh();
        break;
      case "brighten":
        value = this.view.value();
        this.displayedImage = loadedImage + "-brighten";
        this.brighten(value, lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image has been brightened by " + Integer.toString(value));
        this.view.refresh();
        break;
      case "darken":
        value = this.view.value();
        this.displayedImage = loadedImage + "-darken";
        this.darken(value, lastImage, displayedImage);
        this.lastImage = displayedImage;
        image = this.processor.getImage(displayedImage);
        this.view.changeHistograms(image);
        this.view.changeImageDisplay(this.toBufferedImage(image));
        this.view.changeTextDisplay("Image has been darkened by " + Integer.toString(value));
        this.view.refresh();
        break;
      default:
        //nothing happens
        break;
    }
  }

  private void saveGUIHelper() {
    String path = this.view.save();
    if (path.length() >= 5) {
      this.save(this.processor.getImage(displayedImage), path);
      this.view.changeTextDisplay("Image has been successfully saved");
      this.view.changeSaveDisplay(path);
      this.view.refresh();
    }
  }

}
