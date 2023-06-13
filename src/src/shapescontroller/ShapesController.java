package shapescontroller;

import shapesmodel.InputFileReader;
import shapesview.MainFrame;
import shapesview.WebView;

import java.awt.*;
import java.io.IOException;



/**
 * Class to handle user input (command line arguments and click actions in the graphical window).
 */
public class ShapesController implements IController {

  // while loop variable
  private int i;
  // input file name
  private String in;
  // view: graphical or web
  private String view;
  // location of the output file; optional
  private String out;
  // maximum window width; default to 1000
  private int xmax;
  // maximum window height; default to 1000
  private int ymax;
  // dimension for a single snapshot in the web view
  public static final int SNAP_WIDTH = 1600;
  public static final int SNAP_HEIGHT = 900;

  /**
   * Constructor to initialize the parameters.
   */
  public ShapesController() {
    this.i = 0;
    this.in = "";
    this.view = "";
    this.out = "";
    this.xmax = 1000;
    this.ymax = 1000;
  }

  /**
   * Read in command line arguments and set the parameters accordingly.
   * @param args  command line arguments
   */
  @Override
  public void readCommandLine(String[] args) {

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
    //reset i just in case need to run this method again to parse args (so while loop can run)
    i = 0;
  }

  /**
   * display the corresponding view.
   * @throws IOException  cannot read input file
   */
  @Override
  public void displayView() throws IOException {

    // error handling for wrong information or missing necessary information
    // view can only be graphical or web
    if (!(view.equals("graphical") || view.equals("web"))) {
      throw new IllegalArgumentException("View can only be 'graphical' or 'web'");
    }
    // web view must have out, and out must be .html format
    if (view.equals("web") && (out.equals("") || out.matches(".*\\.html$"))) {
      throw new IllegalArgumentException("Web view must have a valid output address");
    }

    // pass on information to InputFileReader and InputFileReader will create
    // the album from the input file
    InputFileReader fr = new InputFileReader();
    fr.readCommands(in);

    // graphical view
    if (view.equals("graphical")) {
      MainFrame window = new MainFrame(fr.getAlbum());
      window.setMaximumSize(new Dimension(xmax, ymax));
      window.setVisible(true);
    }

    // web view
    if (view.equals("web")) {
      WebView web = new WebView(fr.getAlbum(), SNAP_WIDTH, SNAP_HEIGHT);
      web.writeFile(out);
    }
  }

  /**
   * Getter for the input command file name.
   * @return  input command file name
   */
  public String getIn() {
    return this.in;
  }

  /**
   * Getter for the view type.
   * @return  view type
   */
  public String getView() {
    return this.view;
  }

  /**
   * Getter for the output html file name.
   * @return  output html file name
   */
  public String getOut() {
    return this.out;
  }

  /**
   * Getter for maximum width.
   * @return  max width
   */
  public int getXmax() {
    return this.xmax;
  }

  /**
   * Getter for maximum height.
   * @return  max height
   */
  public int getYmax() {
    return this.ymax;
  }
}
