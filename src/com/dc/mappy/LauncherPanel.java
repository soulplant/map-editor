package com.dc.mappy;

import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LauncherPanel extends JPanel {
  public interface Listener {
    void onNewMap();
    void onLoadMap();
    void onQuit();
  }

  public LauncherPanel(List<Action> actions) {
    for (Action action : actions) {
      add(new JButton(action));
    }
  }
}
