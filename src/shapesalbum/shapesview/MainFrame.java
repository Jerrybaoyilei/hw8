package shapesview;

import shapesmodel.Album;
import shapesmodel.Shape;
import shapesmodel.Snapshot;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

/**
 * Class for creating a swing window for the album of snapshots (containing shapes).
 * The class extends from the Abstract JFrame class.
 */
public class MainFrame extends JFrame implements IView {

  // Aspect ratio is 16:9
  public static final int PREFERRED_WIDTH = 800;
  public static final int PREFERRED_HEIGHT = 450;
  public static final Color bodyBackground = Color.WHITE;
  public static final Color headerBackground = Color.PINK;
  public static final Color buttonsBackground = Color.ORANGE;
  // counter is the index of the current snapshot shown on the frame
  private int counter;
  // the album to be displayed
  private final Album album;

  /**
   * Constructor for the class. Creates the Graphical window displaying snapshots in album.
   * @param album  the album to be displayed
   */
  public MainFrame(Album album) {

    super();

    // set the size of the window
    super.setSize(getPreferredSize());

    // set counter to -1 by default
    counter = -1;

    this.album = album;

    // set the title of the window
    setTitle("Snapshots");

    // set the default operation of this frame window
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // add a JPanel of buttons
    this.add(createButtonsPanel(), BorderLayout.SOUTH);

    // if the album is not empty, add the id, description, and title of the first snapshot,
    // and draw the shapes in the first snapshot
    if (album.getAlbumList().size() > 0) {

      Snapshot current = album.getAlbumList().get(0);
      // add header panel
      String id = current.getId().toString();
      String description = current.getDescription();
      HeaderPanel headerPanel = new HeaderPanel(headerBackground, id, description);
      this.add(headerPanel, BorderLayout.NORTH);

      // add body panel containing shapes
      List<Shape> shapesList = current.getCopiedList();
      BodyPanel bodyPanel = new BodyPanel(bodyBackground, shapesList);
      this.add(bodyPanel, BorderLayout.CENTER);

      this.counter++;
    }
  }

  // ************* Below are methods for the graphical view **************

  /**
   * Getter for preferred window size.
   * @return  preferred window size
   */
  @Override
  public Dimension getPreferredSize() {
    Dimension size = super.getPreferredSize();
    size.width = PREFERRED_WIDTH;
    size.height = PREFERRED_HEIGHT;
    return size;
  }

  /**
   * Create the panel of buttons at the bottom of the window.
   * @return  A JPanel of buttons
   */
  @Override
  public JPanel createButtonsPanel() {

    // initialize the panel for buttons
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBackground(buttonsBackground);

    // initialize the buttons
    JButton previousButton = new JButton("Previous");
    JButton selectButton = this.createSelectButton();
    JButton nextButton = new JButton("Next");
    JButton endButton = new JButton("End");

    // create
    // add action listener to buttons
    previousButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addPreviousSnapshot();
      }
    });
    nextButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addNextSnapshot();
      }
    });
    endButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    // add popup menu for selectButton
    selectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    // add buttons to buttonsPanel
    buttonsPanel.add(previousButton);
    buttonsPanel.add(selectButton);
    buttonsPanel.add(nextButton);
    buttonsPanel.add(endButton);

    return buttonsPanel;
  }

  /**
   * Add the next snapshot onto the window (replacing the current snapshot).
   */
  @Override
  public void addNextSnapshot() {
    // make sure there is a next snapshot on the album
    if ((this.counter + 1) < album.getAlbumList().size()) {

      this.counter++;
      addSelectedSnapshot(this.counter);
      // DON'T add this.repaint()!!!!!!!!!
    }
    else {
      JOptionPane.showMessageDialog(null, "No next snapshot!", "Alert", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Add the previous snapshot onto the window (replacing the current snapshot).
   */
  @Override
  public void addPreviousSnapshot() {

    // make sure there is a previous snapshot on the album
    if ((this.counter - 1) >= 0) {
      this.counter--;
      addSelectedSnapshot(this.counter);
    }

    else {
      JOptionPane.showMessageDialog(null,
              "No previous snapshot!",
              "Alert",
              JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Add the selected snapshot at the given index of album onto the window.
   * @param index  index of the selected snapshot to be added
   */
  @Override
  public void addSelectedSnapshot(int index) {

    if (index < 0 || index >= album.getAlbumList().size()) {
      throw new IndexOutOfBoundsException("Invalid index.");
    }

    this.counter = index;
    Snapshot snap = album.getAlbumList().get(this.counter);

    // add body panel
    BodyPanel bodyPanel = new BodyPanel(bodyBackground,
            snap.getCopiedList());
    this.add(bodyPanel, BorderLayout.CENTER);

    // add header panel
    HeaderPanel headerPanel = new HeaderPanel(headerBackground,
            snap.getId().toString(),
            snap.getDescription());

    this.add(headerPanel, BorderLayout.NORTH);

    this.revalidate();
    // DON'T add this.repaint()!!!!!!!!!
  }

  /**
   * Create the select button, which allows user to select a snapshot based on ID to be displayed.
   * @return (JButton) the "select" button
   */
  @Override
  public JButton createSelectButton() {

    JButton selectButton = new JButton("Select");
    JPopupMenu menu = new JPopupMenu("Select screenshot");
    JMenuItem snapshotOption;
    // add menu options, i.e., screenshot IDs
    for (Snapshot snap: album.getAlbumList()) {
      // define new menu item displayed as the snapshot's ID
      snapshotOption = new JMenuItem(snap.getId().toString());
      // add action to the menu item so when user clicks, it will display the corresponding snapshot
      snapshotOption.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          addSelectedSnapshot(album.getAlbumList().indexOf(snap));
        }
      });
      menu.add(snapshotOption);
    }

    // add action to select button so when select is clicked, the popup menu will show up
    selectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        menu.show(selectButton, selectButton.getWidth() / 2, 0);
      }
    });

    return selectButton;
  }

  // ***************** Below are empty methods for web view *************

  /**
   * Draw the circle.
   *
   * @param circle the circle to be drawn.
   * @return html markup for drawing an oval
   */
  @Override
  public String drawOval(Shape circle) {
    return null;
  }

  /**
   * Draw the rectangle.
   *
   * @param rectangle the rectangle to be drawn
   * @return html markup for drawing a rectangle
   */
  @Override
  public String drawRectangle(Shape rectangle) {
    return null;
  }

  /**
   * Draw a single snapshot containing shapes in the snapshot.
   *
   * @param snap       the snapshot to be drawn
   * @param snapWidth  the width of a single snapshot
   * @param snapHeight the height of a single snapshot
   * @return the html markup for drawing a snapshot
   */
  @Override
  public String drawSnapshot(Snapshot snap, int snapWidth, int snapHeight) {
    return null;
  }

  /**
   * Draw the snapshots on the album.
   *
   * @param album      the album to be drawn
   * @param snapWidth  the width of a single snapshot
   * @param snapHeight the height of a single snapshot
   * @return the html markup for drawing the snapshots
   */
  @Override
  public String buildWeb(Album album, int snapWidth, int snapHeight) {
    return null;
  }

  /**
   * Write the html markup output into the html file.
   *
   * @param outputFileName the name of the html output file
   * @throws IOException when write fails
   */
  @Override
  public void writeFile(String outputFileName) throws IOException {

  }
}

