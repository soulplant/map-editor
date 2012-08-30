package com.dc.mappy;

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class GridController {
  private final Grid<Integer> grid;
  private Point cursorPosition = new Point(0, 0);
  private boolean isDrawing = false;

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

  public void onMousePressed() {
    isDrawing = true;
    grid.set(cursorPosition.x, cursorPosition.y, 1);
  }

  public void onMouseReleased() {
    isDrawing = false;
  }

  public void onMouseMoved(Point point) {
    this.cursorPosition = point;
    if (isDrawing) {
      grid.set(cursorPosition.x, cursorPosition.y, 1);
    }
  }
}
