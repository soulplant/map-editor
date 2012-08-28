package com.dc.mappy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {
  private final GridController controller;
  private final TileSet tileSet;
  private final Grid<Integer> grid;

  public GridPanel(GridController controller, TileSet tileSet, Grid<Integer> grid) {
    this.controller = controller;
    this.tileSet = tileSet;
    this.grid = grid;

    setPreferredSize(new Dimension(640, 480));
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        onMouseClicked(e);
      }
    });

    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        onKeyPressed(e);
      }
    });
    setFocusable(true);
  }

  private void onKeyPressed(KeyEvent e) {
    controller.onKeyPressed(e.getKeyCode());
    repaint();
  }

  private void onMouseClicked(MouseEvent e) {
    int x = e.getX() / tileSet.getTileWidthPx();
    int y = e.getY() / tileSet.getTileHeightPx();

    if (e.getButton() == MouseEvent.BUTTON1) {
      controller.onClick(x, y);
    } else {
      controller.onClickRight(x, y);
    }
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, getWidth(), getHeight());
    for (int y = 0; y < grid.getHeight(); y++) {
      for (int x = 0; x < grid.getWidth(); x++) {
        Integer tileIndex = grid.get(x, y);
        if (tileIndex != null) {
          tileSet.drawTile(g, tileIndex, x, y);
        }
      }
    }
  };
}
