package com.dc.mappy;

import java.util.List;

import com.google.gson.Gson;

public class GameMap {
  private final Grid<Integer> tiles;
  private final TileSet tileSet;

  public GameMap(Grid<Integer> tiles, TileSet tileSet) {
    this.tiles = tiles;
    this.tileSet = tileSet;
  }

  static public GameMap fromJson(GameMapJson json) {
    System.out.println("fromJson");
    System.out.println((new Gson()).toJson(json));
    Grid<Integer> grid = new Grid<Integer>(json.width, json.height, 0);
    for (int y = 0; y < json.height; y++) {
      for (int x = 0; x < json.width; x++) {
        grid.set(x, y, json.tileIndices[y * json.width + x]);
      }
    }
    TileSet tileSet = TileSet.fromJson(json.tileSet);
    return new GameMap(grid, tileSet);
  }

  public GameMapJson toJson() {
    GameMapJson json = new GameMapJson();
    json.width = tiles.getWidth();
    json.height = tiles.getHeight();
    List<Integer> tileIndices = tiles.getTileIndices();
    json.tileIndices = new int[tileIndices.size()];
    int i = 0;
    for (int index : tileIndices) {
      json.tileIndices[i++] = index;
    }

    json.tileSet = tileSet.toJson();
    return json;
  }

  public Grid<Integer> getTiles() {
    return tiles;
  }

  public TileSet getTileSet() {
    return tileSet;
  }
}
