package com.dc.mappy;

import java.awt.Frame;
import java.awt.event.KeyEvent;

public class GridController {
  private final Grid<Integer> grid;

  public GridController(Grid<Integer> grid) {
    this.grid = grid;
  }

  public void onClick(int x, int y) {
    grid.set(x, y, 1);
  }

  public void onKeyPressed(int keyCode) {
    if (keyCode == KeyEvent.VK_Q) {
      for (Frame frame : Frame.getFrames()) {
        frame.dispose();
      }
    }
  }

  public void onClickRight(int x, int y) {
    grid.set(x, y, 0);
  }
}
