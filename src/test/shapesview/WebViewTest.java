package shapesview;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import shapesmodel.Album;
import shapesmodel.Oval;
import shapesmodel.Rectangle;
import shapesmodel.Shape;

import java.util.ArrayList;
import java.util.List;


/**
 * Class to test methods in WebView.
 */
public class WebViewTest {
  Album album = new Album();
  WebView view = new WebView(album, 800, 800);

  /**
   * Test drawOval().
   */
  @Test
  public void testDrawOval() {
    String expected = """
            <ellipse id='o' cx='20.000000' cy='38.000000' rx='48.000000' ry='10.000000' fill='rgb(56,78,90)'/>
            """;
    Shape oval = new Oval("o", 20, 38, 56, 78, 90, 48, 10);
    assertEquals(expected, view.drawOval(oval));
  }

  /**
   * Test drawRectangle().
   */
  @Test
  public void testDrawRectangle() {
    String expected = """
            <rect id='r' x='20.800000' y='124.700000' width='82.910000' height='19.480000' fill='rgb(152,160,33)'/>
            """;
    Shape rect = new Rectangle("r", 20.8, 124.7,152,160,33, 82.91, 19.48);
    assertEquals(expected, view.drawRectangle(rect));
  }

  /**
   * Test drawSnapshot().
   */
  @Test
  public void testDrawSnapshot() {

    // album prep
    List<Shape> shapesList = new ArrayList<>();
    Shape oval = new Oval("o", 20, 38, 56, 78, 90, 48, 10);
    Shape rect = new Rectangle("r", 20.8, 124.7,152,160,33, 82.91, 19.48);
    shapesList.add(oval);
    shapesList.add(rect);
    album.takeSnapshot("1st snap", shapesList);

    // expected string
    String id = album.getAlbumList().get(0).getId().toString();
    String expected = """
            <h2>%s</h2><p>Description: 1st snap</p><svg width='800' height='800'>
            <rect width='100%%' height='100%%' style='stroke:rgb(192,72,81);stroke-width:3;fill-opacity:0'/>
            <ellipse id='o' cx='20.000000' cy='38.000000' rx='48.000000' ry='10.000000' fill='rgb(56,78,90)'/>
            <rect id='r' x='20.800000' y='124.700000' width='82.910000' height='19.480000' fill='rgb(152,160,33)'/>
            </svg><br><br>""".formatted(id);

    assertEquals(expected, view.drawSnapshot(album.getAlbumList().get(0), 800, 800));
  }

  /**
   * Method to test buildWeb().
   */
  @Test
  public void testBuildWeb() {

    // album prep
    List<Shape> shapesList = new ArrayList<>();
    Shape oval = new Oval("o", 20, 38, 56, 78, 90, 48, 10);
    Shape rect = new Rectangle("r", 20.8, 124.7,152,160,33, 82.91, 19.48);
    shapesList.add(oval);
    shapesList.add(rect);
    album.takeSnapshot("1st snap", shapesList);

    // expected string
    String id = album.getAlbumList().get(0).getId().toString();
    String expected = """
            <!DOCTYPE html>
            <html>
            <body style="background-color:rgb(196,215,214);">
            <h2>%s</h2><p>Description: 1st snap</p><svg width='800' height='800'>
            <rect width='100%%' height='100%%' style='stroke:rgb(192,72,81);stroke-width:3;fill-opacity:0'/>
            <ellipse id='o' cx='20.000000' cy='38.000000' rx='48.000000' ry='10.000000' fill='rgb(56,78,90)'/>
            <rect id='r' x='20.800000' y='124.700000' width='82.910000' height='19.480000' fill='rgb(152,160,33)'/>
            </svg><br><br></body>
            </html>
            """.formatted(id);

    assertEquals(expected, view.buildWeb(album, 800, 800));
  }
}