package com.dc.mappy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {
  private final GridController controller;
  private final TileSet tileSet;
  private final Grid<Integer> grid;

  public GridPanel(final GridController controller, final TileSet tileSet, Grid<Integer> grid) {
    this.controller = controller;
    this.tileSet = tileSet;
    this.grid = grid;

    setPreferredSize(new Dimension(640, 480));
    addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
      }

      @Override
      public void mouseMoved(MouseEvent e) {
        controller.onMouseMoved(getLogicalPointFromScreenPoint(e.getPoint()));
        repaint();
      }

      private Point getLogicalPointFromScreenPoint(Point point) {
        int x = point.x / tileSet.getTileWidthPx();
        int y = point.y / tileSet.getTileHeightPx();
        return new Point(x, y);
      }
    });
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        controller.onMousePressed();
        repaint();
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        controller.onMouseReleased();
        repaint();
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
