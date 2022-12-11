package shapesalbum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

/**
 * Class to test the methods in the Rectangle class, including methods inherited from AbstractShape.
 */
public class RectangleTest {

  private Rectangle r1 = new Rectangle("r1", 20, 30, 50, 40, 78, 160, 90);
  private Rectangle r2 = new Rectangle("not square", 0, 0, 0, 0, 0, 64, 640);

  /**
   * Test move().
   */
  @Test
  public void testMove() {

    r1.move(60, 92);
    assertEquals(60, r1.getX(), 0);
    assertEquals(92, r1.getY(), 0);

    r1.move(0, 0);
    assertEquals(0, r1.getX(), 0);
    assertEquals(0, r1.getY(), 0);

    r1.move(6.203, 459.839);
    assertEquals(6.203, r1.getX(), 0.001);
    assertEquals(459.839, r1.getY(), 0.001);

    assertThrows(IllegalArgumentException.class, () -> {
      r1.move(-0.01, -1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      r1.move(-1293.5843, -1830.8109);
    });
  }

  /**
   * Test changeColor().
   */
  @Test
  public void testChangeColor() {

    r1.changeColor(219, 10, 64);
    assertEquals(219, r1.getR(), 0);
    assertEquals(10, r1.getG(), 0);
    assertEquals(64, r1.getB(), 0);

    assertThrows(IllegalArgumentException.class, () -> {
      r1.changeColor(-1, 0, 0);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      r1.changeColor(-0.01, -28, -9130);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      r1.changeColor(255.1, 256, 257);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      r1.changeColor(7138934, 18390, 283);
    });
  }

  /**
   * Test rename().
   */
  @Test
  public void testRename() {

    r1.rename("renamed r1");
    assertEquals("renamed r1", r1.getName());

    r1.rename("aklsjdfkladj&8sdslfd");

    assertEquals("aklsjdfkladj&8sdslfd", r1.getName());

    assertThrows(IllegalArgumentException.class, () -> {
      r1.rename(null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      r1.rename("");
    });
  }

  /**
   * Test scale().
   */
  @Test
  public void scale() {

    double oldWidth = r1.getHorizontal();
    double oldHeight = r1.getVertical();

    double newWidth = r1.getHorizontal() * 1.1;
    double newHeight = r1.getVertical() * 1.1;

    r1.scale(1.1);

    assertEquals(newWidth, r1.getHorizontal(), 0.0);
    assertEquals(newHeight, r1.getVertical(), 0.0);

    oldWidth = r1.getHorizontal();
    oldHeight = r1.getVertical();

    r1.scale(27392.2930);
    newWidth = oldWidth * 27392.2930;
    newHeight = oldHeight * 27392.2930;

    assertEquals(newWidth, r1.getHorizontal(), 0.0);
    assertEquals(newHeight, r1.getVertical(), 0.0);
  }

  /**
   * Test getWidth().
   */
  @Test
  public void testGetWidth() {
    assertEquals(64, r2.getHorizontal(), 0.0);
  }

  /**
   * Test getHeight().
   */
  @Test
  public void testGetHeight() {
    assertEquals(640, r2.getVertical(), 0.0);
  }

  /**
   * Test changeHorizontal().
   */
  @Test
  public void testChangeHorizontal() {

    r1.changeHorizontal(2.0);
    assertEquals(2.0, r1.getHorizontal(), 0.0);

    assertThrows(IllegalArgumentException.class, () -> {
      r2.changeHorizontal(-203.230);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      r2.changeHorizontal(-0.001);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      r2.changeHorizontal(0);
    });
  }

  /**
   * Test changeVertical().
   */
  @Test
  public void testChangeVertical() {

    r1.changeVertical(340.103);
    assertEquals(340.103, r1.getVertical(), 0.000);

    assertThrows(IllegalArgumentException.class, () -> {
      r2.changeVertical(-203.230);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      r2.changeVertical(-0.001);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      r2.changeVertical(0);
    });
  }
}