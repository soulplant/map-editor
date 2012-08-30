package com.dc.mappy;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
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
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setLayout(new BorderLayout());

    TileSet tileSet = new TileSet("tiles.png", 16, 16);
    TilePicker tilePicker = new TilePicker(tileSet);
    TilePickerController tilePickerController = new TilePickerController(tilePicker);

    Grid<Integer> grid = new Grid<Integer>(640 / 16, 480 / 16);
    GridController gridController = new GridController(grid, tilePicker);

    GridPanel gridPanel = new GridPanel(gridController, tileSet, grid);
    frame.add(gridPanel, BorderLayout.CENTER);

    TilePickerPanel tileSetPanel = new TilePickerPanel(tilePicker, tilePickerController);
    JScrollPane jScrollPane = new JScrollPane(tileSetPanel);
    jScrollPane.setPreferredSize(new Dimension(16 * 10, 16 * 10));
    frame.add(jScrollPane, BorderLayout.LINE_END);

    frame.pack();
    frame.setVisible(true);
  }
}
