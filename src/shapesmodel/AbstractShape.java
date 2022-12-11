package shapesmodel;

/**
 * Abstract class for the Shape interface that covers the common methods among all shapes.
 */
public abstract class AbstractShape implements Shape {

  // Keep the parameters as private and use getters for access outside the class.
  private String name;
  // horizontal coordinate x
  private double x;
  // vertical coordinate y
  private double y;
  // color red
  private int r;
  // color green
  private int g;
  // color blue
  private int b;

  /**
   * Constructor for AbstractShape.
   * @param name  shape's name
   * @param x     shape's horizontal coordinate
   * @param y     shape's vertical coordinate
   * @param r     shape's red color parameter
   * @param g     shape's green color parameter
   * @param b     shape's blue color parameter
   * @throws IllegalArgumentException  name is empty or null;
   *                                   x, y negative;
   *                                   r, g, b value out of range
   */
  public AbstractShape(String name, double x, double y, int r, int g, int b)
          throws IllegalArgumentException {

    // exception throwing
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Shape's name cannot be null or empty. ");
    }
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Shape's coordinates cannot be negative. ");
    }
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("RGB value cannot be smaller than 0 or larger than 255. ");
    }

    this.name = name;
    this.x = x;
    this.y = y;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Move the shape to a new location (x, y).
   * @param x  the horizontal coordinate of the new location for the shape
   * @param y  the vertical coordinate of the new location for the shape
   * @throws IllegalArgumentException  coordinates cannot be negative
   */
  @Override
  public void move(double x, double y) throws IllegalArgumentException {

    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Shape's new location coordinates cannot be negative. ");
    }

    this.x = x;
    this.y = y;
  }

  /**
   * Change the shape's color, denoted as RGB value.
   * @param r  red value
   * @param g  green value
   * @param b  blue value
   * @throws IllegalArgumentException  RGB values cannot be smaller than 0 or larger than 255
   */
  @Override
  public void changeColor(int r, int g, int b) throws IllegalArgumentException {

    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("RGB value cannot be smaller than 0 or larger than 255. ");
    }

    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Rename the shape.
   * @param name  new name for the shape
   * @throws IllegalArgumentException  name cannot be null or empty.
   */
  @Override
  public void rename(String name) throws IllegalArgumentException {

    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Shape's new name cannot be null or empty. ");
    }

    this.name = name;
  }

  /**
   * Get the name.
   *
   * @return name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Get the x coordinate.
   * @return  x coordinate
   */
  @Override
  public double getX() {
    return this.x;
  }

  /**
   * Get the y coordinate.
   * @return  y coordinate
   */
  @Override
  public double getY() {
    return this.y;
  }

  /**
   * Get the red color value.
   * @return  red color value
   */
  @Override
  public int getR() {
    return this.r;
  }

  /**
   * Get the green color value.
   *
   * @return green color value
   */
  @Override
  public int getG() {
    return this.g;
  }

  /**
   * Get the blue color value.
   *
   * @return blue color value
   */
  @Override
  public int getB() {
    return this.b;
  }
}
