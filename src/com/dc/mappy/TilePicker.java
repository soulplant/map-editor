package com.dc.mappy;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class TilePicker {
  private final TileSet tileSet;
  private Point selectedTile = new Point(0, 0);

  public TilePicker(TileSet tileSet) {
    this.tileSet = tileSet;
  }

  public int getCurrentTile() {
    return tileSet.getIndex(selectedTile);
  }

  public void selectTile(Point point) {
    this.selectedTile = point;
  }

  public Dimension getPreferredSize() {
    return tileSet.getPreferredSize();
  }

  public Point scalePoint(Point point) {
    return tileSet.scalePoint(point);
  }

  public int getTileWidthPx() {
    return tileSet.getTileWidthPx();
  }

  public int getTileHeightPx() {
    return tileSet.getTileHeightPx();
  }

  public void drawTile(Graphics g, int index, int x, int y) {
    tileSet.drawTile(g, index, x, y);
  }
}
