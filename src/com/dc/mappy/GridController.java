package com.dc.mappy;

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class GridController {
  private final Grid<Integer> grid;
  private Point cursorPosition = new Point(0, 0);
  private boolean isDrawing = false;
  private final TilePicker tilePicker;

  public GridController(Grid<Integer> grid, TilePicker tilePicker) {
    this.grid = grid;
    this.tilePicker = tilePicker;
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
    drawTileAtCursor();
  }

  public void onMouseReleased() {
    isDrawing = false;
  }

  public void onMouseMoved(Point point) {
    this.cursorPosition = point;
    if (isDrawing) {
      drawTileAtCursor();
    }
  }

  private void drawTileAtCursor() {
    grid.set(cursorPosition.x, cursorPosition.y, tilePicker.getCurrentTile());
  }
}
