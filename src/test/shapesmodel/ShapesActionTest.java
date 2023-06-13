package shapesmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import shapesmodel.Oval;
import shapesmodel.Rectangle;
import shapesmodel.Shape;
import shapesmodel.ShapesAction;

/**
 * Class to test methods in shapesList class.
 */
public class ShapesActionTest {

  private final List<Shape> list1 = new ArrayList<>();
  private final Oval O = new Oval("O", 500.0, 100.0, 0, 0, 1, 60.0, 30.0);
  private final Rectangle R = new Rectangle("R", 200.0, 200.0, 1, 0, 0, 50.0, 100.0);

  /**
   * Test static method createList().
   */
  @Test
  public void testCreateList() {
    List<Shape> list2 = new ArrayList<>();
    assertEquals(list1, list2);
  }

  /**
   * Test adding to the list of shapes.
   */
  @Test
  public void add() {
    list1.add(O);
    list1.add(R);
    assertEquals(2, list1.size());
    assertEquals(list1.get(0), new Oval("O", 500.0, 100.0, 0, 0, 1, 60.0, 30.0));
    assertEquals(list1.get(1), new Rectangle("R", 200.0, 200.0, 1, 0, 0, 50.0, 100.0));
  }

  /**
   * Test removing from the list of shapes.
   */
  @Test
  public void testRemove() {
    list1.add(O);
    list1.add(R);
    list1.remove(1);
    assertEquals(1, list1.size());
    assertEquals(list1.get(0), new Oval("O", 500.0, 100.0, 0, 0, 1, 60.0, 30.0));


    assertThrows(IndexOutOfBoundsException.class, () -> {
      list1.remove(1);
    });
  }

  /**
   * Test move().
   */
  @Test
  public void testMove() {

    list1.add(O);
    list1.add(R);

    ShapesAction.move(list1, 0, 35, 40);
    assertEquals(35, list1.get(0).getX(), 0.0);
    assertEquals(40, list1.get(0).getY(), 0.0);

    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.move(list1, 2, 0, 0);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.move(list1, 1, -1, -2);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.move(list1, 0, -8239, -0.001);
    });
  }

  /**
   * Test changeColor().
   */
  @Test
  public void testChangeColor() {

    list1.add(O);
    list1.add(R);

    ShapesAction.changeColor(list1, 0, 206, 220, 0);
    assertEquals(206, list1.get(0).getR(), 0.0);
    assertEquals(220, list1.get(0).getG(), 0.0);
    assertEquals(0, list1.get(0).getB(), 0.0);

    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.changeColor(list1, -1, 0, 0, 0);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.changeColor(list1, 1, -2, 65, 234);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.changeColor(list1, 0, 256, 45, 68);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.changeColor(list1, 0, 256, 2568, -1839);
    });
  }

  /**
   * Test static method scale().
   */
  @Test
  public void testScale() {

    list1.add(O);
    list1.add(R);

    ShapesAction.scale(list1, 0, 1.2);
    assertEquals(72.0, list1.get(0).getHorizontal(), 0.0);
    assertEquals(36.0, list1.get(0).getVertical(), 0.0);

    ShapesAction.scale(list1, 1, 2.0);
    assertEquals(100.0, list1.get(1).getHorizontal(), 0.0);
    assertEquals(200.0, list1.get(1).getVertical(), 0.0);

    assertThrows(IndexOutOfBoundsException.class, () -> {
      ShapesAction.scale(list1, 2, 1.3);
    });
    assertThrows(IndexOutOfBoundsException.class, () -> {
      ShapesAction.scale(list1, -1, 1.3);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.scale(list1, 0, 0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.scale(list1, 0, -0.1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.scale(list1, 0, -8302);
    });
  }

  /**
   * Test static method rename().
   */
  @Test
  public void testRename() {

    list1.add(O);
    list1.add(R);

    ShapesAction.rename(list1, 0, "new oval");
    assertEquals("new oval", list1.get(0).getName());
    ShapesAction.rename(list1, 1, "new rectangle");
    assertEquals("new rectangle", list1.get(1).getName());

    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.rename(list1, 0, null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      ShapesAction.rename(list1, 0, "");
    });
    assertThrows(IndexOutOfBoundsException.class, () -> {
      ShapesAction.rename(list1, -1, "some name");
    });
    assertThrows(IndexOutOfBoundsException.class, () -> {
      ShapesAction.rename(list1, -100, "some name");
    });
    assertThrows(IndexOutOfBoundsException.class, () -> {
      ShapesAction.rename(list1, 2, "some name");
    });
    assertThrows(IndexOutOfBoundsException.class, () -> {
      ShapesAction.rename(list1, 138, "some name");
    });
  }

  /**
   * Test changeHorizontal().
   */
  @Test
  public void testChangeHorizontal() {

    list1.add(O);
    list1.add(R);

    ShapesAction.changeHorizontal(list1, 0, 47.7);
    assertEquals(47.7, list1.get(0).getHorizontal(), 0.0);
  }

  /**
   * Test printShapes().
   */
  @Test
  public void testPrintShapes() {

    list1.add(O);
    list1.add(R);

    String expected = """
            Name: O
            Type: oval
            Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)
                        
            Name: R
            Type: rectangle
            Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)
                        
            """;

    assertEquals(expected, ShapesAction.printShapes(list1));
  }
}