package com.dc.mappy;

import java.awt.Color;
import java.awt.Graphics;

public class GridGraphics {

  private final Graphics g;
  private final int tileWidthPx;
  private final int tileHeightPx;

  public GridGraphics(Graphics g, int tileWidthPx, int tileHeightPx) {
    this.g = g;
    this.tileWidthPx = tileWidthPx;
    this.tileHeightPx = tileHeightPx;
  }

  public void drawRectAroundTile(Color color, int x, int y) {
    g.setColor(color);
    g.drawRect(x * tileWidthPx, y * tileHeightPx, tileWidthPx - 1, tileHeightPx - 1);
  }
}
