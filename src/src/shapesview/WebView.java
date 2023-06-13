package shapesview;

import shapesmodel.Album;
import shapesmodel.Oval;
import shapesmodel.Rectangle;
import shapesmodel.Shape;
import shapesmodel.Snapshot;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

/**
 * Class to paint the snapshots in a html file.
 */
public class WebView implements IView {

  public static final Color snapBoundColor = new Color(192, 72, 81);
  public static final Color bodyBackground = new Color(196, 215, 214);
  private final String out;

  /**
   * Constructor for the class.
   * @param album       the album of snapshots to be painted
   * @param snapWidth   the width of a single snapshot
   * @param snapHeight  the height of a single snapshot
   */
  public WebView(Album album, int snapWidth, int snapHeight) {
    this.out = this.buildWeb(album, snapWidth, snapHeight);
  }

  // **************** Below are empty methods for the graphical view *******
  /**
   * Getter for preferred window size.
   *
   * @return preferred window size
   */
  @Override
  public Dimension getPreferredSize() {
    return null;
  }

  /**
   * Create the panel of buttons at the bottom of the window.
   *
   * @return A JPanel of buttons
   */
  @Override
  public JPanel createButtonsPanel() {
    return null;
  }

  /**
   * Add the next snapshot onto the window (replacing the current snapshot).
   */
  @Override
  public void addNextSnapshot() {

  }

  /**
   * Add the previous snapshot onto the window (replacing the current snapshot).
   */
  @Override
  public void addPreviousSnapshot() {

  }

  /**
   * Add the selected snapshot at the given index of album onto the window.
   *
   * @param index index of the selected snapshot to be added
   */
  @Override
  public void addSelectedSnapshot(int index) {

  }

  /**
   * Create the select button, which allows user to select a snapshot based on ID to be displayed.
   *
   * @return (JButton) the "select" button
   */
  @Override
  public JButton createSelectButton() {
    return null;
  }

  // ************** Below are methods for web view ********************

  /**
   * Draw the circle.
   * @param circle  the circle to be drawn.
   * @return  html markup for drawing an oval
   */
  public String drawOval(Shape circle) {

    // parameter setup
    String name = circle.getName();
    double x = circle.getX();
    double y = circle.getY();
    int r = circle.getR();
    int g = circle.getG();
    int b = circle.getB();
    double xRadius = circle.getHorizontal();
    double yRadius = circle.getVertical();

    // format the output string and return
    String output = """
            <ellipse id='%s' cx='%f' cy='%f' rx='%f' ry='%f' fill='rgb(%d,%d,%d)'/>
            """;
    return output.formatted(name, x, y, xRadius, yRadius, r, g, b);
  }

  /**
   * Draw the rectangle.
   * @param rectangle  the rectangle to be drawn
   * @return  html markup for drawing a rectangle
   */
  public String drawRectangle(Shape rectangle) {

    // parameter setup
    String name = rectangle.getName();
    double x = rectangle.getX();
    double y = rectangle.getY();
    int r = rectangle.getR();
    int g = rectangle.getG();
    int b = rectangle.getB();
    double width = rectangle.getHorizontal();
    double height = rectangle.getVertical();

    // format the output string and return
    String output = """
            <rect id='%s' x='%f' y='%f' width='%f' height='%f' fill='rgb(%d,%d,%d)'/>
            """;
    return output.formatted(name, x, y, width, height, r, g, b);
  }

  /**
   * Draw a single snapshot containing shapes in the snapshot.
   * @param snap        the snapshot to be drawn
   * @param snapWidth   the width of a single snapshot
   * @param snapHeight  the height of a single snapshot
   * @return  the html markup for drawing a snapshot
   */
  public String drawSnapshot(Snapshot snap, int snapWidth, int snapHeight) {

    StringBuilder sb = new StringBuilder();

    // parameter setup
    String id = snap.getId().toString();
    String description = snap.getDescription();
    List<Shape> shapesList = snap.getCopiedList();

    // add snapshot's id and description (if description is not empty)
    String title = "<h2>%s</h2>";
    sb.append(String.format(title, id));
    if (!description.equals("")) {
      String des = "<p>Description: %s</p>";
      sb.append(String.format(des, description));
    }

    // add the beginning of svg tag
    String svgBegin = """
            <svg width='%d' height='%d'>
            <rect width='100%%' height='100%%' style='stroke:rgb(%d,%d,%d);stroke-width:3;fill-opacity:0'/>
            """;
    sb.append(svgBegin.formatted(
            snapWidth,
            snapHeight,
            snapBoundColor.getRed(),
            snapBoundColor.getGreen(),
            snapBoundColor.getBlue()));


    // add shapes
    for (Shape shape: shapesList) {
      if (shape instanceof Oval) {
        sb.append(drawOval(shape));
      }
      else if (shape instanceof Rectangle) {
        sb.append(drawRectangle(shape));
      }
    }

    // add closing tag and an empty line
    sb.append("</svg>").append("<br><br>");
    return sb.toString();
  }

  /**
   * Draw the snapshots on the album.
   * @param album       the album to be drawn
   * @param snapWidth   the width of a single snapshot
   * @param snapHeight  the height of a single snapshot
   * @return  the html markup for drawing the snapshots
   */
  public String buildWeb(Album album, int snapWidth, int snapHeight) {

    StringBuilder sb = new StringBuilder();

    // add header tags
    String beginning = """
            <!DOCTYPE html>
            <html>
            <body style="background-color:rgb(%d,%d,%d);">
            """;
    sb.append(beginning.formatted(bodyBackground.getRed(),
            bodyBackground.getGreen(),
            bodyBackground.getBlue()));

    // for each snapshot on the album, add to sb
    List<Snapshot> snapList = album.getAlbumList();
    for (Snapshot snap: snapList) {
      sb.append(drawSnapshot(snap, snapWidth, snapHeight));
    }

    // add ending tags
    String end = """
            </body>
            </html>
            """;
    sb.append(end);
    return sb.toString();
  }

  /**
   * Write the html markup output into the html file.
   * @param outputFileName  the name of the html output file
   * @throws IOException  when write fails
   */
  public void writeFile(String outputFileName) throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
      bw.write(this.out);
    } catch (IOException e) {
      System.out.println("Exception");
    }
  }
}
