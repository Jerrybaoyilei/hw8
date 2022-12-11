package shapesview;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import shapesmodel.Album;
import shapesmodel.Shape;
import shapesmodel.Snapshot;

/**
 * Interface for the two views: graphical (MainFrame) and web (WebView).
 */
public interface IView {

  /**
   * Getter for preferred window size.
   * @return  preferred window size
   */
  Dimension getPreferredSize();

  /**
   * Create the panel of buttons at the bottom of the window.
   * @return  A JPanel of buttons
   */
  JPanel createButtonsPanel();

  /**
   * Add the next snapshot onto the window (replacing the current snapshot).
   */
  void addNextSnapshot();

  /**
   * Add the previous snapshot onto the window (replacing the current snapshot).
   */
  void addPreviousSnapshot();

  /**
   * Add the selected snapshot at the given index of album onto the window.
   * @param index  index of the selected snapshot to be added
   */
  void addSelectedSnapshot(int index);

  /**
   * Create the select button, which allows user to select a snapshot based on ID to be displayed.
   * @return (JButton) the "select" button
   */
  JButton createSelectButton();

  // **************************** WEB VIEW *****************************

  /**
   * Draw the circle.
   * @param circle  the circle to be drawn.
   * @return  html markup for drawing an oval
   */
  String drawOval(Shape circle);

  /**
   * Draw the rectangle.
   * @param rectangle  the rectangle to be drawn
   * @return  html markup for drawing a rectangle
   */
  String drawRectangle(Shape rectangle);

  /**
   * Draw a single snapshot containing shapes in the snapshot.
   * @param snap        the snapshot to be drawn
   * @param snapWidth   the width of a single snapshot
   * @param snapHeight  the height of a single snapshot
   * @return  the html markup for drawing a snapshot
   */
  String drawSnapshot(Snapshot snap, int snapWidth, int snapHeight);

  /**
   * Draw the snapshots on the album.
   * @param album       the album to be drawn
   * @param snapWidth   the width of a single snapshot
   * @param snapHeight  the height of a single snapshot
   * @return  the html markup for drawing the snapshots
   */
  String buildWeb(Album album, int snapWidth, int snapHeight);

  /**
   * Write the html markup output into the html file.
   * @param outputFileName  the name of the html output file
   * @throws IOException  when write fails
   */
  void writeFile(String outputFileName) throws IOException;
}
