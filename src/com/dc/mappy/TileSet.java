package com.dc.mappy;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class TileSet {
  private final Image image;
  private final int tileWidthPx;
  private final int tileHeightPx;
  private final int tilesWide;
  private final int tilesHigh;
  private final String filename;

  public TileSet(String filename, int tileWidthPx, int tileHeightPx) {
    this.filename = filename;
    this.tileWidthPx = tileWidthPx;
    this.tileHeightPx = tileHeightPx;
    image = new ImageIcon(filename).getImage();
    this.tilesWide = image.getWidth(null) / tileWidthPx;
    this.tilesHigh = image.getHeight(null) / tileHeightPx;
  }

  public void drawTile(Graphics g, int tileIndex, int dx, int dy) {
    int tileX = tileIndex % getTilesWide();
    int tileY = tileIndex / getTilesWide();
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

  public int getTilesWide() {
    return tilesWide;
  }

  public int getTilesHigh() {
    return tilesHigh;
  }

  public int getWidthPx() {
    return getTileWidthPx() * getTilesWide();
  }

  public int getHeightPx() {
    return getTileHeightPx() * getTilesHigh();
  }

  public Dimension getPreferredSize() {
    return new Dimension(getWidthPx(), getHeightPx());
  }

  public Point scalePoint(Point point) {
    return Util.scalePoint(point, tileWidthPx, tileHeightPx);
  }

  public int getIndex(Point point) {
    return point.y * tilesWide + point.x;
  }

  public TileSetJson toJson() {
    TileSetJson result = new TileSetJson();
    result.filename = filename;
    result.tileWidthPx = getTileWidthPx();
    result.tileHeightPx = getTileHeightPx();
    return result;
  }

  public static TileSet fromJson(TileSetJson json) {
    return new TileSet(json.filename, json.tileWidthPx, json.tileHeightPx);
  }
}
