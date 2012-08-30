package com.dc.mappy;

import java.awt.Point;

public class TilePickerController {
  private final TilePicker tilePicker;

  public TilePickerController(TilePicker tilePicker) {
    this.tilePicker = tilePicker;
  }

  public void onMouseClicked(Point point) {
    tilePicker.selectTile(point);
  }
}
