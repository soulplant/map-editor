package com.dc.mappy;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class TileSet {
  private final Image image;
  private final int tileWidthPx;
  private final int tileHeightPx;
  private final int tilesWide;
  private final int tilesHigh;

  public TileSet(String filename, int tileWidthPx, int tileHeightPx) {
    this.tileWidthPx = tileWidthPx;
    this.tileHeightPx = tileHeightPx;
    image = new ImageIcon(filename).getImage();
    this.tilesWide = image.getWidth(null) / tileWidthPx;
    this.tilesHigh = image.getHeight(null) / tileHeightPx;
    System.out.println("tilesWide = " + tilesWide);
    System.out.println("tilesHigh = " + tilesHigh);
  }


  public void drawTile(Graphics g, int tileIndex, int dx, int dy) {
    int tileX = tileIndex % tilesWide;
    int tileY = tileIndex / tilesWide;
    int xPx = dx * getTileWidthPx();
    int yPx = dy * getTileHeightPx();
    drawTile(g, tileX, tileY, xPx, yPx);
  }

  public void drawTile(Graphics g, int tileX, int tileY, int xPx, int yPx) {
    int tileXPx = tileX * getTileWidthPx();
    int tileYPx = tileY * getTileHeightPx();
    g.drawImage(image, xPx, yPx, xPx + getTileWidthPx(), yPx + getTileHeightPx(),
        tileXPx, tileYPx, tileXPx + getTileWidthPx(), tileYPx + getTileHeightPx(),
        null);
  }

  public int getTileWidthPx() {
    return tileWidthPx;
  }

  public int getTileHeightPx() {
    return tileHeightPx;
  }
}
