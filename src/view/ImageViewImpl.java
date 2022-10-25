package view;

import model.Image;
import model.ImageProcessor;
import model.Pixel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents all the operations than can be used
 * to visualize an {@code ImageProcessor}.
 */
public class ImageViewImpl extends JFrame implements ImageView, ItemListener {

  private final Appendable destination;
  private JLabel textDisplay;
  private JLabel imageDisplay;
  private JButton loadButton;
  private JButton applyButton;
  private JComboBox applyOptions;
  private String selected = "red-component";
  private JButton saveButton;
  private JLabel loadDisplay;
  private JLabel saveDisplay;
  private JPanel south;
  private JTextField value;
  private JPanel red;
  private JPanel green;
  private JPanel blue;
  private JPanel intensity;

  /**
   * Constructs a new {@code ImageViewImpl} with the given image processor and appendable.
   *
   * @param processor the image processor
   * @throws IllegalArgumentException if either parameter is null
   */
  public ImageViewImpl(ImageProcessor processor, Appendable destination) throws
      IllegalArgumentException {
    super();
    if (processor == null || destination == null) {
      throw new IllegalArgumentException("Processor cannot be null");
    }
    this.destination = destination;

    this.setTitle("Image Processor");
    this.setSize(1000, 1000);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    JPanel north = new JPanel();
    north.setLayout(new BoxLayout(north, BoxLayout.PAGE_AXIS));
    JPanel text = new JPanel();
    text.setLayout(new FlowLayout());
    textDisplay = new JLabel("Load in an image to begin");
    text.add(textDisplay);
    JPanel load = new JPanel();
    load.setLayout(new FlowLayout());
    loadButton = new JButton("load");
    loadButton.setActionCommand("load button");
    loadDisplay = new JLabel("Loaded image path will appear here");
    load.add(loadButton);
    load.add(loadDisplay);
    JPanel apply = new JPanel();
    apply.setLayout(new FlowLayout());
    applyButton = new JButton("apply");
    applyButton.setActionCommand("apply button");
    String[] options = {"red-component", "green-component", "blue-component", "value", "intensity",
        "luma", "greyscale", "sepia", "blur", "sharpen", "flip-vertically", "flip-horizontally",
        "brighten", "darken"};
    applyOptions = new JComboBox(options);
    applyOptions.addItemListener(this);
    value = new JTextField("Enter value here to brighten or darken", 20);
    apply.add(applyButton);
    apply.add(applyOptions);
    apply.add(value);
    JPanel save = new JPanel();
    save.setLayout(new FlowLayout());
    saveButton = new JButton("save");
    saveButton.setActionCommand("save button");
    saveDisplay = new JLabel("Saved image path will appear here");
    save.add(saveButton);
    save.add(saveDisplay);
    north.add(text);
    north.add(load);
    north.add(apply);
    north.add(save);

    imageDisplay = new JLabel();
    Border black = BorderFactory.createLineBorder(Color.black);
    imageDisplay.setBorder(black);
    JScrollPane center = new JScrollPane(imageDisplay);
    center.setPreferredSize(new Dimension(800, 400));

    south = new JPanel();
    TitledBorder border = new TitledBorder("Image Component Histograms");
    border.setTitleJustification(TitledBorder.CENTER);
    border.setTitlePosition(TitledBorder.TOP);
    south.setBorder(border);
    red = new JPanel();
    red.setPreferredSize(new Dimension(262, 262));
    TitledBorder borderRed = new TitledBorder("Red");
    borderRed.setTitleJustification(TitledBorder.CENTER);
    borderRed.setTitlePosition(TitledBorder.TOP);
    red.setBorder(borderRed);
    green = new JPanel();
    green.setPreferredSize(new Dimension(260, 262));
    TitledBorder borderGreen = new TitledBorder("Green");
    borderGreen.setTitleJustification(TitledBorder.CENTER);
    borderGreen.setTitlePosition(TitledBorder.TOP);
    green.setBorder(borderGreen);
    blue = new JPanel();
    blue.setPreferredSize(new Dimension(262, 262));
    TitledBorder borderBlue = new TitledBorder("Blue");
    borderBlue.setTitleJustification(TitledBorder.CENTER);
    borderBlue.setTitlePosition(TitledBorder.TOP);
    blue.setBorder(borderBlue);
    intensity = new JPanel();
    intensity.setPreferredSize(new Dimension(262, 262));
    TitledBorder borderIntensity = new TitledBorder("Intensity");
    borderIntensity.setTitleJustification(TitledBorder.CENTER);
    borderIntensity.setTitlePosition(TitledBorder.TOP);
    intensity.setBorder(borderIntensity);
    south.add(red);
    south.add(green);
    south.add(blue);
    south.add(intensity);

    this.add(north, BorderLayout.NORTH);
    this.add(center, BorderLayout.CENTER);
    this.add(south, BorderLayout.SOUTH);
    this.pack();
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void changeTextDisplay(String text) {
    this.textDisplay.setText(text);
  }

  @Override
  public void changeLoadDisplay(String text) {
    this.loadDisplay.setText(text);
  }

  @Override
  public void changeSaveDisplay(String text) {
    this.saveDisplay.setText(text);
  }

  @Override
  public void changeImageDisplay(BufferedImage image) {
    this.imageDisplay.setIcon(new ImageIcon(image));
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public String selected() {
    return this.selected;
  }

  @Override
  public int value() {
    String input = this.value.getText();
    int inputValue = 0;
    try {
      inputValue = Integer.parseInt(input);
    } catch (NumberFormatException nfe) {
      this.changeTextDisplay("Please enter a numerical value");
      this.refresh();
    }
    return inputValue;
  }

  @Override
  public String load() {
    String path = "";
    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    int returnValue = jfc.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = jfc.getSelectedFile();
      path = selectedFile.getAbsolutePath();
    }
    return path;
  }

  @Override
  public void setCommandButtonListener(ActionListener actionEvent) {
    loadButton.addActionListener(actionEvent);
    applyButton.addActionListener(actionEvent);
    saveButton.addActionListener(actionEvent);
  }

  @Override
  public String save() {
    String path = "";
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showSaveDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      path = file.getAbsolutePath();
    }
    return path;
  }

  @Override
  public void changeHistograms(Image image) {
    List<List<Pixel>> pixels = image.getPixels();
    List<Integer> redValues = new ArrayList<>();
    List<Integer> greenValues = new ArrayList<>();
    List<Integer> blueValues = new ArrayList<>();
    List<Integer> intensityValues = new ArrayList<>();
    int height = image.getHeight();
    int width = image.getWidth();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel temp = pixels.get(i).get(j);
        redValues.add(temp.getR());
        greenValues.add(temp.getG());
        blueValues.add(temp.getB());
        intensityValues.add(temp.intensity());
      }
    }
    this.south.remove(red);
    this.south.remove(blue);
    this.south.remove(green);
    this.south.remove(intensity);
    this.red = new Histogram(redValues, "red");
    red.setPreferredSize(new Dimension(262, 262));
    TitledBorder borderRed = new TitledBorder("Red");
    borderRed.setTitleJustification(TitledBorder.CENTER);
    borderRed.setTitlePosition(TitledBorder.TOP);
    red.setBorder(borderRed);
    this.green = new Histogram(greenValues, "green");
    green.setPreferredSize(new Dimension(262, 262));
    TitledBorder borderGreen = new TitledBorder("Green");
    borderGreen.setTitleJustification(TitledBorder.CENTER);
    borderGreen.setTitlePosition(TitledBorder.TOP);
    green.setBorder(borderGreen);
    this.blue = new Histogram(blueValues, "blue");
    blue.setPreferredSize(new Dimension(262, 262));
    TitledBorder borderBlue = new TitledBorder("Blue");
    borderBlue.setTitleJustification(TitledBorder.CENTER);
    borderBlue.setTitlePosition(TitledBorder.TOP);
    blue.setBorder(borderBlue);
    this.intensity = new Histogram(intensityValues, "intensity");
    intensity.setPreferredSize(new Dimension(262, 262));
    TitledBorder borderIntensity = new TitledBorder("Intensity");
    borderIntensity.setTitleJustification(TitledBorder.CENTER);
    borderIntensity.setTitlePosition(TitledBorder.TOP);
    intensity.setBorder(borderIntensity);
    this.south.add(red);
    this.south.add(green);
    this.south.add(blue);
    this.south.add(intensity);
  }


  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getSource() == applyOptions) {
      this.selected = (String) applyOptions.getSelectedItem();
    }
  }
}
