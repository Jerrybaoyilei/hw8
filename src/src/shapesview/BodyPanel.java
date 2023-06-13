package shapesview;

import shapesmodel.Oval;
import shapesmodel.Rectangle;
import shapesmodel.Shape;

import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 * Concrete JPanel class representing the body panel on which the shapes in the screenshot
 * will be drawn.
 */
public class BodyPanel extends JPanel implements IBodyPanel {

  private final Color background;
  private final List<Shape> shapesList;


  /**
   * Constructor for the BodyPanel; sets the background of the BodyPanel and draw the shapes
   * included in the snapshot.
   * @param background  the background color of the BodyPanel
   * @param shapesList  the list of shapes to be drawn on the BodyPanel
   */
  public BodyPanel(Color background, List<Shape> shapesList) {
    this.background = background;
    this.shapesList = shapesList;
  }

  /**
   * Method to paint the components on the body panel.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);
    setBackground(this.background);
    // paint the shapes
    for (Shape s: this.shapesList) {
      // set the color of the shape
      g.setColor(new Color(s.getR(), s.getG(),s.getB()));
      // if shape is an Oval, draw the oval with fill
      if (s instanceof Oval) {
        g.fillOval((int) s.getX(), (int) s.getY(), (int) s.getHorizontal(), (int) s.getVertical());
      }
      else if (s instanceof Rectangle) {
        g.fillRect((int) s.getX(), (int) s.getY(), (int) s.getHorizontal(), (int) s.getVertical());
      }
    }
  }
}
