package shapesmodel;

import java.util.List;

/**
 * Class to add shapes into a list, move shapes, change colors, reset, etc.
 */
public class ShapesAction {

  /**
   * Add shape to shapesList and avoid duplicated names; new method for hw8.
   * @param shapesList  list of shapes
   * @param type        type of shape
   * @param name        name of shape
   * @param x           x coordinate
   * @param y           y coordinate
   * @param r           Red color
   * @param g           Green color
   * @param b           Blue color
   * @param width       width of shape
   * @param height      height of shape
   */
  public static void addByName(List<Shape> shapesList, String type, String name, double x,
                               double y, int r, int g, int b, double width,
                               double height)  throws IllegalArgumentException {

    boolean duplicated = false;

    // check for duplicates
    for (Shape shape: shapesList) {
      if (shape.getName().equals(name)) {
        duplicated = true;
        System.out.printf("Shape with name %s already in the list. Add failed. %n", name);
        break;
      }
    }

    if (!duplicated) {
      if (type.equalsIgnoreCase("rectangle")) {
        shapesList.add(new Rectangle(name, x, y, r, g, b, width, height));
      }
      else if (type.equalsIgnoreCase("oval")) {
        shapesList.add(new Oval(name, x, y, r, g, b, width, height));
      }
      else {
        throw new IllegalArgumentException("Unrecognized shape. Add failed.");
      }
    }
  }

  /**
   * Move a shape at the given index in the shapeList to the new location (x, y).
   * @param shapesList  list of shapes
   * @param index       index of the shape in the shapeList
   * @param x           new x coordinate
   * @param y           new y coordinate
   * @throws IllegalArgumentException  shapeList index should not be out of bound
   */
  public static void move(List<Shape> shapesList, int index, double x, double y)
          throws IllegalArgumentException {

    if (index < 0 || index >= shapesList.size()) {
      throw new IllegalArgumentException("Index out of bound. ");
    }

    Shape copy = shapesList.get(index);
    copy.move(x, y);
    shapesList.set(index, copy);
  }

  /**
   * Move a shape with the given name in the shapesList to the new location (x, y).
   * @param shapesList  list of shapes
   * @param name        name of the shape in the shapesList
   * @param x           new x coordinate
   * @param y           new y coordinate
   * @throws IllegalArgumentException  shape with the given name not found in the list
   */
  public static void move(List<Shape> shapesList, String name, double x, double y)
          throws IllegalArgumentException {

    // indicate if the shape with the name is moved; also false if no shape with the name in list
    boolean moved = false;

    for (Shape shape: shapesList) {
      if (shape.getName().equals(name)) {
        moved = true;
        shape.move(x, y);
        shapesList.set(shapesList.indexOf(shape), shape);
        break;
      }

    }

    if (!moved) {
      throw new IllegalArgumentException("No shape with the given name in the list.");
    }
  }

  /**
   * Change color of the shape at the given index in the shapeList.
   * @param index  index of the shape in the shapeList
   * @param r      shape's new red color parameter
   * @param g      shape's new green color parameter
   * @param b      shape's new blue color parameter
   * @throws IllegalArgumentException  shapeList index should not be out of bound
   */
  public static void changeColor(List<Shape> shapeList, int index, int r, int g, int b)
          throws IllegalArgumentException {

    if (index < 0 || index >= shapeList.size()) {
      throw new IllegalArgumentException("Index out of bound. ");
    }

    Shape copy = shapeList.get(index);
    copy.changeColor(r, g, b);
    shapeList.set(index, copy);
  }

  /**
   * Change color of the shape with given name in the shapesList. New method for hw8.
   * @param shapesList  list of shapes
   * @param name        name of the shape
   * @param r           Rec
   * @param g           Green
   * @param b           Blue
   * @throws IllegalArgumentException  shape with the given name cannot be found in the list
   */
  public static void changeColor(List<Shape> shapesList, String name, int r, int g, int b)
          throws IllegalArgumentException {

    boolean colored = false;

    for (Shape shape: shapesList) {
      if (shape.getName().equals(name)) {
        colored = true;
        shape.changeColor(r, g, b);
        break;
      }
    }

    if (!colored) {
      throw new IllegalArgumentException("Shape with the given name cannot be found in the list.");
    }
  }

  /**
   * Scale the shape at the given index in the shapeList with the given factor.
   * @param index   index of the shape in the shapeList
   * @param factor  non-negative double that denotes the scaling factor;
   *                factor > 1 means enlarge; factor < 1 means shrink.
   * @throws IllegalArgumentException  shapeList index should not be out of bound
   */
  public static void scale(List<Shape> shapeList, int index, double factor)
          throws IndexOutOfBoundsException {

    if (index < 0 || index >= shapeList.size()) {
      throw new IndexOutOfBoundsException("Index out of bound. ");
    }

    Shape copy = shapeList.get(index);
    copy.scale(factor);
    shapeList.set(index, copy);
  }

  /**
   * Rename a shape at the given index in the shapeList to the new name.
   * @param index  index of the shape in the shapeList
   * @param name   new name
   * @throws IllegalArgumentException  shapeList index should not be out of bound
   */
  public static void rename(List<Shape> shapeList, int index, String name)
          throws IndexOutOfBoundsException {

    if (index < 0 || index >= shapeList.size()) {
      throw new IndexOutOfBoundsException("Index out of bound. ");
    }

    Shape copy = shapeList.get(index);
    copy.rename(name);
    shapeList.set(index, copy);
  }

  /**
   * Change the x radius of oval or width of rectangle.
   * @param shapeList      list of shapes
   * @param index          location of the shape in the list
   * @param newHorizontal  the new xRadius/width
   * @throws IndexOutOfBoundsException  index out of list bound
   * @throws IllegalArgumentException   newHorizontal must be positive
   */
  public static void changeHorizontal(List<Shape> shapeList, int index, double newHorizontal)
          throws IndexOutOfBoundsException, IllegalArgumentException {

    if (index < 0 || index > shapeList.size()) {
      throw new IndexOutOfBoundsException();
    }

    if (newHorizontal <= 0) {
      throw new IllegalArgumentException("New width/xRadius must be positive. ");
    }

    Shape temp = shapeList.get(index);
    temp.changeHorizontal(newHorizontal);
    shapeList.set(index, temp);
  }

  /**
   * Change the y radius of oval or height of rectangle.
   * @param shapeList      list of shapes
   * @param index          location of the shape in the list
   * @param newVertical  the new yRadius/height
   * @throws IndexOutOfBoundsException  index out of list bound
   * @throws IllegalArgumentException   newHorizontal must be positive
   */
  public static void changeVertical(List<Shape> shapeList, int index, double newVertical)
          throws IndexOutOfBoundsException, IllegalArgumentException {

    if (index < 0 || index > shapeList.size()) {
      throw new IndexOutOfBoundsException();
    }

    if (newVertical <= 0) {
      throw new IllegalArgumentException("New width/xRadius must be positive. ");
    }

    Shape temp = shapeList.get(index);
    temp.changeVertical(newVertical);
    shapeList.set(index, temp);
  }

  /**
   * Resize both horizontal and vertical length. New method for hw8.
   * @param shapesList     list of shapes
   * @param name           name of shape
   * @param newHorizontal  new horizontal length value
   * @param newVertical    new vertical length value
   * @throws IllegalArgumentException  shape with given name not found in the list
   */
  public static void resize(List<Shape> shapesList, String name,
                            double newHorizontal, double newVertical)
          throws IllegalArgumentException {

    boolean resized = false;

    for (Shape shape: shapesList) {
      if (shape.getName().equals(name)) {
        resized = true;
        shape.changeHorizontal(newHorizontal);
        shape.changeVertical(newVertical);
        break;
      }
    }
    if (!resized) {
      throw new IllegalArgumentException("No shape with the given name in the list");
    }
  }

  /**
   * remove shape with given name from the shapesList.
   * @param shapesList  list of shapes
   * @param name        name of the shape to be removed
   * @throws IllegalArgumentException  no shape with the given name found in the list
   */
  public static void removeShape(List<Shape> shapesList, String name)
          throws IllegalArgumentException {

    boolean removed = false;

    for (Shape shape: shapesList) {
      if (shape.getName().equals(name)) {
        removed = true;
        shapesList.remove(shape);
      }
    }

    if (!removed) {
      throw new IllegalArgumentException("No shape with the given name found in the shapesList.");
    }
  }

  /**
   * Print out shapes in the shapeList.
   * @param shapesList  list of shapes
   */
  public static String printShapes(List<Shape> shapesList) {

    StringBuilder builder = new StringBuilder();

    for (Shape shape: shapesList) {
      builder.append(shape.toString()).append("\n");
    }

    return builder.toString();
  }
}
