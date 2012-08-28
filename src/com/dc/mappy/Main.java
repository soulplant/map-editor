package com.dc.mappy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        guiMain();
      }
    });
  }

  private static void guiMain() {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    Grid<Integer> grid = new Grid<Integer>(10, 10);

    frame.add(new GridPanel(new GridController(grid), new TileSet("tiles.png", 16, 16), grid));
    frame.pack();
    frame.setVisible(true);
  }
}
