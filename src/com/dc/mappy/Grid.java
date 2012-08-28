package com.dc.mappy;

import java.util.ArrayList;
import java.util.List;

public class Grid<T> {
  private final List<T> tiles;
  private final int width;
  private final int height;

  public Grid(int width, int height) {
    this.width = width;
    this.height = height;
    tiles = new ArrayList<T>(width * height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        tiles.add(null);
      }
    }
  }

  public T get(int x, int y) {
    return tiles.get(getIndex(x, y));
  }

  public void set(int x, int y, T t) {
    tiles.set(getIndex(x, y), t);
  }

  private int getIndex(int x, int y) {
    return y * getWidth() + x;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
