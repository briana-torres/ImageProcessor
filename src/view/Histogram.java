package view;


import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class used to create histograms.
 */
public class Histogram extends JPanel {

  private List<Integer> values;
  private String type;

  /**
   * Creates a histogram based on the given values.
   *
   * @param values the given values
   * @param type   the histogram type
   * @throws IllegalArgumentException if the values are null
   */
  public Histogram(List<Integer> values, String type) throws IllegalArgumentException {
    if (values == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    this.values = values;
    this.type = type;
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Map<Integer, Integer> map = new HashMap<>();
    String histogram = this.type;

    for (int i = 0; i < 256; i++) {
      map.put(i, 0);
    }

    for (int value : this.values) {
      int amount = 0;
      if (map.containsKey(value)) {
        amount = map.get(value);
        amount++;
      }
      map.put(value, amount);
    }

    for (int i = 0; i < 256; i++) {
      int current = map.get(i);
      if (current > 255) {
        current = current / 20;
      }
      map.put(i, current);
    }

    for (int i = 0; i < 256; i++) {
      int current = map.get(i);
      if (current > 255) {
        current = 255;
      }
      map.put(i, current);
    }

    if (histogram.equals("red")) {
      for (int i = 0; i < 256; i++) {
        g.setColor(Color.RED);
        g.fillRect(i + 3, map.get(i) + 8, 1, 255 - map.get(i));
        g.drawRect(i + 3, map.get(i) + 8, 1, 255 - map.get(i));
      }
    }
    if (histogram.equals("green")) {
      for (int i = 0; i < 256; i++) {
        g.setColor(Color.GREEN);
        g.fillRect(i + 3, map.get(i) + 8, 1, 255 - map.get(i));
        g.drawRect(i + 3, map.get(i) + 8, 1, 255 - map.get(i));
      }
    }
    if (histogram.equals("blue")) {
      for (int i = 0; i < 256; i++) {
        g.setColor(Color.BLUE);
        g.fillRect(i + 3, map.get(i) + 8, 1, 255 - map.get(i));
        g.drawRect(i + 3, map.get(i) + 8, 1, 255 - map.get(i));
      }
    }
    if (histogram.equals("intensity")) {
      for (int i = 0; i < 256; i++) {
        g.setColor(Color.GRAY);
        g.fillRect(i + 3, map.get(i) + 8, 1, 255 - map.get(i));
        g.drawRect(i + 3, map.get(i) + 8, 1, 255 - map.get(i));
      }
    }

  }

}
