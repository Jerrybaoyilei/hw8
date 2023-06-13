package shapescontroller;

import java.io.IOException;

/**
 * Interface for the ShapesController class.
 */
public interface IController {

  /**
   * Read in command line arguments and set the parameters accordingly.
   * @param args  command line arguments
   */
  void readCommandLine(String[] args);

  /**
   * display the corresponding view.
   * @throws IOException  cannot read input file
   */
  void displayView() throws IOException;
}
