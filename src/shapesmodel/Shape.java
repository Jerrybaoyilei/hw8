package shapesmodel;

/**
 * Interface for all kinds of shapes.
 */
public interface Shape {

  /**
   * Move the shape to a new location (x, y).
   * @param x  the horizontal coordinate of the new location for the shape
   * @param y  the vertical coordinate of the new location for the shape
   */
  void move(double x, double y);

  /**
   * Change the shape's color, denoted as RGB value.
   * @param r  red value
   * @param g  green value
   * @param b  blue value
   */
  void changeColor(int r, int g, int b);

  /**
   * Scale the shape with the given factor.
   * @param factor  non-negative double that denotes the scaling factor;
   *                factor > 1 means enlarge; factor < 1 means shrink.
   */
  void scale(double factor);

  /**
   * Rename the shape.
   * @param name  new name for the shape
   */
  void rename(String name);

  /**
   * Get the name.
   * @return  name
   */
  String getName();

  /**
   * Get the x coordinate.
   * @return  x coordinate
   */
  double getX();

  /**
   * Get the y coordinate.
   * @return  y coordinate
   */
  double getY();

  /**
   * Get the red color value.
   * @return  red color value
   */
  int getR();

  /**
   * Get the green color value.
   * @return  green color value
   */
  int getG();

  /**
   * Get the blue color value.
   * @return  blue color value
   */
  int getB();

  /**
   * For rectangles, getter for width. For ovals, getter for xRadius.
   * @return  rectangle's width/oval's xRadius
   */
  double getHorizontal();

  /**
   * For rectangles, getter for height. For ovals, getter for yRadius.
   * @return  rectangle's height/oval's yRadius
   */
  double getVertical();

  /**
   * Change rectangle's width/oval's xRadius.
   */
  void changeHorizontal(double newHorizontal);

  /**
   * Change rectangle's height/oval's yRadius.
   */
  void changeVertical(double newVertical);

  /**
   * return a copy of itself (i.e., shape). New method for hw8.
   * (I thought I achieved deep copy in hw7, but I didn't. This is a correction for that mistake)
   * @return  a copy
   */
  Shape copySelf();
}
