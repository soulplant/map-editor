package com.dc.mappy;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class Main {
  private static final String DEFAULT_MAP = "default.map";

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        guiMain();
      }
    });
  }

  @SuppressWarnings("serial")
  private static void guiMain() {
    final JFrame frame = new JFrame("mappy");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    List<Action> actions = new ArrayList<Action>();
    actions.add(new AbstractAction("New map") {
      @Override
      public void actionPerformed(ActionEvent event) {
        System.out.println("New map");
      }
    });
    actions.add(new AbstractAction("Load map") {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        FileDialog fileDialog = new FileDialog(frame);
        fileDialog.setDirectory(".");
        fileDialog.setVisible(true);
        String filename = fileDialog.getFile();
        if (filename != null) {
          openMap(filename);
        }
      }
    });
    actions.add(new AbstractAction("Quit") {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        frame.setVisible(false);
        frame.dispose();
      }
    });
    frame.add(new LauncherPanel(actions));
    frame.pack();
    frame.setVisible(true);
  }

  private static void openMap(final String filename) {
    File file = new File(filename);
    GameMap map;
    try {
      Gson gson = new Gson();
      GameMapJson json = gson.fromJson(new FileReader(file), GameMapJson.class);
      map = GameMap.fromJson(json);
    } catch (Exception e) {
      Grid<Integer> grid = new Grid<Integer>(640 / 16, 480 / 16, 0);
      TileSet tileSet = new TileSet("tiles.png", 16, 16);
      map = new GameMap(grid, tileSet);
    }
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    Grid<Integer> grid = map.getTiles();
    TileSet tileSet = map.getTileSet();
    TilePicker tilePicker = new TilePicker(tileSet);
    TilePickerController tilePickerController = new TilePickerController(tilePicker);

    GridController gridController = new GridController(grid, tilePicker);

    GridPanel gridPanel = new GridPanel(gridController, tileSet, grid);
    frame.add(gridPanel, BorderLayout.CENTER);

    TilePickerPanel tileSetPanel = new TilePickerPanel(tilePicker, tilePickerController);
    JScrollPane jScrollPane = new JScrollPane(tileSetPanel);
    jScrollPane.setPreferredSize(new Dimension(16 * 10, 16 * 10));
    frame.add(jScrollPane, BorderLayout.LINE_END);

    frame.setFocusable(true);
    final GameMap fmap = map;
    frame.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_S:
          Gson gson = new Gson();
          try {
            FileWriter fileWriter = new FileWriter(new File(filename));
            gson.toJson(fmap.toJson(), fileWriter);
            fileWriter.close();
            System.out.println("saved " + filename);
          } catch (JsonIOException ex) {
            ex.printStackTrace();
          } catch (IOException ex) {
            ex.printStackTrace();
          }
          break;
        }
      }
    });

    frame.pack();
    frame.setVisible(true);

  }
}
