import shapescontroller.ShapesController;

import java.io.IOException;

/**
 * Class that contains the main function to show the swing window.
 */
public class PhotoAlbumMain {

  public static final int SNAP_WIDTH = 1600;
  public static final int SNAP_HEIGHT = 900;

  /**
   * Main method.
   * @param args  args for main method
   */
  public static void main(String[] args) throws IOException {
    ShapesController controller = new ShapesController();
    controller.readCommandLine(args);
    controller.displayView();
  }
}
