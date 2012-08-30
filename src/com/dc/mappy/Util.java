package com.dc.mappy;

import java.awt.Point;

public class Util {
  public static Point scalePoint(Point point, int tileWidthPx, int tileHeightPx) {
    int x = point.x / tileWidthPx;
    int y = point.y / tileHeightPx;
    return new Point(x, y);
  }
}
