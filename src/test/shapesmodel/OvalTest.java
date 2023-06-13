package shapesmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import shapesmodel.Oval;

/**
 * Test methods in the Oval class, including methods inherited from AbstractShape.
 */
public class OvalTest {

  Oval o1 = new Oval("o1", 38, 405, 255, 204, 0, 89, 64);
  Oval o2 = new Oval("circle", 76, 20, 0, 36, 32, 57, 57);

  /**
   * Method to test move().
   */
  @Test
  public void testMove() {

    o1.move(20, 850);
    assertEquals(20, o1.getX(), 0.0);
    assertEquals(850, o1.getY(), 0.0);

    o1.move(2.2, 3.1415);
    assertEquals(2.2, o1.getX(), 0.0);
    assertEquals(3.1415, o1.getY(), 0.0000);

    assertThrows(IllegalArgumentException.class, () -> {
      o1.move(-0.01, -0.1);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      o1.move(-2384, -1930);
    });
  }

  /**
   * Test changeColor().
   */
  @Test
  public void testChangeColor() {

    o2.changeColor(0, 21, 18);
    assertEquals(0, o2.getR(), 0.0);
    assertEquals(21, o2.getG(), 0.0);
    assertEquals(18, o2.getB(), 0.0);

    assertThrows(IllegalArgumentException.class, () -> {
      o2.changeColor(-1, -1, 0);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      o2.changeColor(-193, -8132, 255);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      o2.changeColor(256, 0, 257);
    });
  }

  /**
   * Test rename().
   */
  @Test
  public void testRename() {

    o1.rename("new oval");
    assertEquals("new oval", o1.getName());

    assertThrows(IllegalArgumentException.class, () -> {
      o1.rename("");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      o1.rename(null);
    });
  }

  /**
   * test scale().
   */
  @Test
  public void testScale() {

    double oldXRadius = o1.getHorizontal();
    double oldYRadius = o1.getVertical();

    o1.scale(1.4);

    double newXRadius = oldXRadius * 1.4;
    double newYRadius = oldYRadius * 1.4;

    assertEquals(newXRadius, o1.getHorizontal(), 0.00);
    assertEquals(newYRadius, o1.getVertical(), 0.00);

    assertThrows(IllegalArgumentException.class, () -> {
      o2.scale(0);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      o2.scale(-0.01);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      o2.scale(-203.230);
    });
  }

  /**
   * Test changeHorizontal().
   */
  @Test
  public void testChangeHorizontal() {

    o1.changeHorizontal(2.0);
    assertEquals(2.0, o1.getHorizontal(), 0.0);

    assertThrows(IllegalArgumentException.class, () -> {
      o2.changeHorizontal(-203.230);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      o2.changeHorizontal(-0.001);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      o2.changeHorizontal(0);
    });
  }

  /**
   * Test changeVertical().
   */
  @Test
  public void testChangeVertical() {

    o1.changeVertical(340.103);
    assertEquals(340.103, o1.getVertical(), 0.000);

    assertThrows(IllegalArgumentException.class, () -> {
      o2.changeVertical(-203.230);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      o2.changeVertical(-0.001);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      o2.changeVertical(0);
    });
  }
}