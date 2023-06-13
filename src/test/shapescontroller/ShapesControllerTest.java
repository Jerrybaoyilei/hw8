package shapescontroller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Class to test methods in ShapesController.
 */
public class ShapesControllerTest {

  /**
   * Test readCommandLine().
   */
  @Test
  public void readCommandLine() {
    String[] args = {"-in", "buildings.txt", "-view", "graphical"};
    ShapesController c = new ShapesController();
    c.readCommandLine(args);
    assertEquals("buildings.txt", c.getIn());
    assertEquals("graphical", c.getView());

    args = new String[]{"-view", "web", "-in", "buildings.txt", "-out", "test.html", "123", "456"};
    c.readCommandLine(args);
    assertEquals("web", c.getView());
    assertEquals("test.html", c.getOut());
    assertEquals(123, c.getXmax());
    assertEquals(456, c.getYmax());
  }

  /**
   * Test displayView().
   */
  @Test
  public void displayView() {

    String[] args = {"-in", "buildings.txt", "-view", "wrong"};
    ShapesController c = new ShapesController();
    c.readCommandLine(args);
    assertThrows(IllegalArgumentException.class, c::displayView);

    args = new String[]{"-in", "buildings.txt", "-view", "web"};
    c.readCommandLine(args);
    assertThrows(IllegalArgumentException.class, c::displayView);
    args = new String[]{"-in", "buildings.txt", "-view", "web", "out", "wrongType.txt"};
    c.readCommandLine(args);
    assertThrows(IllegalArgumentException.class, c::displayView);
  }
}