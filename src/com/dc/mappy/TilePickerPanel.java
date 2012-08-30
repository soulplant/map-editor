package com.dc.mappy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TilePickerPanel extends JPanel {

  private final TilePicker tilePicker;

  public TilePickerPanel(final TilePicker tilePicker, final TilePickerController controller) {
    this.tilePicker = tilePicker;
    setPreferredSize(tilePicker.getPreferredSize());
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        controller.onMouseClicked(tilePicker.scalePoint(e.getPoint()));
        repaint();
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int tilesWide = getWidth() / tilePicker.getTileWidthPx();
    int tilesHigh = getHeight() / tilePicker.getTileHeightPx();
    int index = 0;
    for (int y = 0; y < tilesHigh; y++) {
      for (int x = 0; x < tilesWide; x++) {
        tilePicker.drawTile(g, index, x, y);
        if (tilePicker.getCurrentTile() == index) {
          System.out.println("current tile = " + index);
          GridGraphics gg = new GridGraphics(g, tilePicker.getTileWidthPx(),
              tilePicker.getTileHeightPx());
          gg.drawRectAroundTile(Color.BLACK, x, y);
        }
        index++;
      }
    }
  }
}
