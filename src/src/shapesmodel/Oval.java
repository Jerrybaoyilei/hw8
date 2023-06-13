package shapesmodel;

import java.util.Objects;

/**
 * Concrete class for Ovals.
 */
public class Oval extends AbstractShape {

  // Keep the parameters private and provide getters for access outside the class.
  // horizontal radius
  private double xRadius;
  // vertical radius
  private double yRadius;

  /**
   * Constructor for Oval.
   * @param name     shape's name
   * @param x        shape's horizontal coordinate
   * @param y        shape's vertical coordinate
   * @param r        shape's red color parameter
   * @param g        shape's green color parameter
   * @param b        shape's blue color parameter
   * @param xRadius  horizontal radius
   * @param yRadius  vertical radius
   * @throws IllegalArgumentException  xRadius and yRadius must be positive
   */
  public Oval(String name, double x, double y, int r, int g, int b,
              double xRadius, double yRadius) throws IllegalArgumentException {

    super(name, x, y, r, g, b);

    if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("Oval's radii must be positive. ");
    }

    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * Scale the shape with the given factor.
   * @param factor  non-negative double that denotes the scaling factor;
   *                factor > 1 means enlarge; factor < 1 means shrink.
   * @throws IllegalArgumentException  Scaling factor must be positive
   */
  @Override
  public void scale(double factor) throws IllegalArgumentException {

    if (factor <= 0) {
      throw new IllegalArgumentException("Scaling factor must be positive. ");
    }

    this.xRadius = this.xRadius * factor;
    this.yRadius = this.yRadius * factor;
  }

  /**
   * Getter for xRadius.
   * @return  oval's xRadius
   */
  @Override
  public double getHorizontal() {
    return this.xRadius;
  }

  /**
   * Getter for yRadius.
   * @return  oval's yRadius
   */
  @Override
  public double getVertical() {
    return this.yRadius;
  }

  /**
   * Change oval's x radius, i.e. horizontal radius.
   * @param newRadius  new x radius
   * @throws IllegalArgumentException  New x radius must be positive
   */
  @Override
  public void changeHorizontal(double newRadius) throws IllegalArgumentException {

    if (newRadius <= 0) {
      throw new IllegalArgumentException("New x radius must be positive. ");
    }

    this.xRadius = newRadius;
  }

  /**
   * Change oval's y radius, i.e. vertical radius.
   * @param newRadius  new y radius
   * @throws IllegalArgumentException  New y radius must be positive
   */
  @Override
  public void changeVertical(double newRadius) throws IllegalArgumentException {

    if (newRadius <= 0) {
      throw new IllegalArgumentException("New y radius must be positive. ");
    }

    this.yRadius = newRadius;
  }

  /**
   * Override equals() to compare two ovals and see if all fields are the same.
   * @param o  the object to be compared
   * @return  true if two ovals have same fields and false if not
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (this.getClass() != o.getClass()) {
      return false;
    }
    Oval oval = (Oval) o;
    return Objects.equals(this.getName(), oval.getName())
            && this.getX() == oval.getX()
            && this.getY() == oval.getY()
            && this.getR() == oval.getR()
            && this.getG() == oval.getG()
            && this.getB() == oval.getB()
            && this.getHorizontal() == oval.getHorizontal()
            && this.getVertical() == oval.getVertical();
  }

  /**
   * Output a string to display oval's information.
   * @return  a String containing oval's information
   */
  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
            + "Type: oval" + "\n"
            + "Center: (" + this.getX() + "," + this.getY()
            + "), X radius: " + this.getHorizontal()
            + ", Y radius: " + this.getVertical()
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
    double xRadius = this.getHorizontal();
    double yRadius = this.getVertical();

    return new Oval(name, x, y, r, g, b, xRadius, yRadius);
  }
}
