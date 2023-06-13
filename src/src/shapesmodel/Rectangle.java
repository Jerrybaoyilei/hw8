package shapesmodel;

import java.util.Objects;

/**
 * Concrete Rectangle class that extends the AbstractShape class.
 */
public class Rectangle extends AbstractShape {

  // Keep the parameters private and provide getters for access outside the class.
  private double width;
  private double height;

  /**
   * Constructor for AbstractShape.
   * @param name    shape's name
   * @param x       shape's horizontal coordinate
   * @param y       shape's vertical coordinate
   * @param r       shape's red color parameter
   * @param g       shape's green color parameter
   * @param b       shape's blue color parameter
   * @param width   shape's width
   * @param height  shape's height
   * @throws IllegalArgumentException name is empty or null; x, y negative; r, g, b value out of
   *                                  range
   */
  public Rectangle(String name, double x, double y, int r, int g, int b,
                   double width, double height) throws IllegalArgumentException {

    super(name, x, y, r, g, b);

    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width or height must be positive. ");
    }

    this.width = width;
    this.height = height;
  }

  /**
   * Scale the shape with the given factor.
   * @param factor  non-negative double that denotes the scaling factor;
   *                factor > 1 means enlarge; factor < 1 means shrink.
   * @throws IllegalArgumentException  scaling factor must be positive
   */
  @Override
  public void scale(double factor) throws IllegalArgumentException {

    if (factor <= 0) {
      throw new IllegalArgumentException("Scaling factor must be positive. ");
    }

    this.width = this.width * factor;
    this.height = this.height * factor;
  }

  /**
   * Change rectangle's width.
   * @param newWidth  new width
   * @throws IllegalArgumentException  New width must be positive
   */
  @Override
  public void changeHorizontal(double newWidth) throws IllegalArgumentException {

    if (newWidth <= 0) {
      throw new IllegalArgumentException("New width must be positive. ");
    }

    this.width = newWidth;
  }

  /**
   * Change rectangle's height.
   * @param newHeight  new height
   * @throws IllegalArgumentException  New height must be positive
   */
  @Override
  public void changeVertical(double newHeight) throws IllegalArgumentException {

    if (newHeight <= 0) {
      throw new IllegalArgumentException("New height must be positive. ");
    }

    this.height = newHeight;
  }

  /**
   * Getter for rectangle's width.
   * @return  the rectangle's width
   */
  @Override
  public double getHorizontal() {
    return this.width;
  }

  /**
   * Getter for rectangle's height.
   * @return  the rectangle's height
   */
  @Override
  public double getVertical() {
    return this.height;
  }

  /**
   * Override equals() to compare two rectangles.
   * @param o  the object to be compared
   * @return  true if two rectangles are the equal and false if not
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (this.getClass() != o.getClass()) {
      return false;
    }
    Rectangle rec = (Rectangle) o;
    return Objects.equals(this.getName(), rec.getName())
            && this.getX() == rec.getX()
            && this.getY() == rec.getY()
            && this.getR() == rec.getR()
            && this.getG() == rec.getG()
            && this.getB() == rec.getB()
            && this.getHorizontal() == rec.getHorizontal()
            && this.getVertical() == rec.getVertical();
  }

  /**
   * Output a string to display rectangle's information.
   * @return  a String containing rectangle's information
   */
  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
            + "Type: rectangle" + "\n"
            + "Min corner: (" + this.getX() + "," + this.getY()
            + "), Width: " + this.getHorizontal()
            + ", Height: " + this.getVertical()
            + ", Color: (" + this.getR() + "," + this.getG() + "," + this.getB() + ")" + "\n";
  }

  /**
   * return a copy of itself (i.e., shape).
   * @return  a copy
   */
  @Override
  public Shape copySelf() {

    String name = this.getName();
    double x = this.getX();
    double y = this.getY();
    int r = this.getR();
    int g = this.getG();
    int b = this.getB();
    double width = this.getHorizontal();
    double height = this.getVertical();

    return new Rectangle(name, x, y, r, g, b, width, height);
  }
}
