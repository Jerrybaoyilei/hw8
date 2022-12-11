import shapescontroller.ShapesController;
import shapesview.MainFrame;
import shapesview.WebView;

import java.awt.*;
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
  public static void main(String[] args) throws IOException, IllegalArgumentException {

    // while loop variable
    int i = 0;
    // input file name
    String in = "";
    // view: graphical or web
    String view = "";
    // location of the output file; optional
    String out = "";
    // maximum window width; default to 1000
    int xmax = 1000;
    // maximum window height; default to 1000
    int ymax = 1000;

    // loop through command line arguments to collect information
    while (i < args.length) {

      // case 1: read in input file name
      if (args[i].equals("-in")) {
        in = args[i + 1];
        i++;
      }

      // case 2: read in view option
      if (args[i].equals("-view") || args[i].equals("-v")) {
        view = args[i + 1];
        i++;
      }

      // case 3: read in output location
      if (args[i].equals("-out")) {
        out = args[i + 1];
        i++;
      }

      // case 4: read in xmax and ymax
      if (args[i].matches("\\d+") && args[i + 1].matches("\\d+")) {
        xmax = Integer.parseInt(args[i]);
        ymax = Integer.parseInt(args[i + 1]);
        i++;
      }
      i++;
    }

    // error handling for wrong information or missing necessary information
    // view can only be graphical or web
    if (!(view.equals("graphical") || view.equals("web"))) {
      throw new IllegalArgumentException("View can only be 'graphical' or 'web'");
    }
    // web view must have out
    if (view.equals("web") && out.equals("")) {
      throw new IllegalArgumentException("Web view must have a valid output address");
    }

    // pass on information to controller and controller will create the album from the input file
    ShapesController controller = new ShapesController();
    controller.readCommands(in);

    // graphical view
    if (view.equals("graphical")) {
      MainFrame window = new MainFrame(controller.getAlbum());
      window.setMaximumSize(new Dimension(xmax, ymax));
      window.setVisible(true);
    }

    // web view
    if (view.equals("web")) {
      WebView web = new WebView(controller.getAlbum(), SNAP_WIDTH, SNAP_HEIGHT);
      web.writeFile(out);
    }
  }
}
