package shapesmodel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to read in an input file full of commands and create & modify album accordingly.
 */
public class InputFileReader implements InputReader {

  // Container for the snapshots, which has a list of snapshots and a list of snapshots' IDs
  private final Album album;
  // container for the shapes
  private final List<Shape> shapesList;

  /**
   * Constructor.
   */
  public InputFileReader() {
    this.album = new Album();
    this.shapesList = new ArrayList<>();
  }

  /**
   * Read in commands from an input file. Create and change shapes. Take snapshots.
   * @param filename  name of input file
   * @throws IOException  cannot read input file
   * @throws IllegalArgumentException  empty input file
   */
  @Override
  public void readCommands(String filename) throws IOException, IllegalArgumentException {

    if (filename == null || filename.equals("")) {
      throw new IllegalArgumentException("Filename cannot be null or empty");
    }

    try {

      // read in the file
      File file = new File(filename);
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;

      // empty file exception
      if (file.length() == 0) {
        throw new IllegalArgumentException("Input file is empty.");
      }

      // while there is a line to be read
      while ((line = bufferedReader.readLine()) != null) {

        // line begins with #, ignore the line
        if (line.trim().indexOf("#") == 0) {
          continue;
        }

        //split the line by white spaces and turn into ArrayList
        List<String> wordsList = new ArrayList<>(Arrays.asList(line.trim().split("\\s+")));

        String command = wordsList.get(0);

        // case 1: create shape
        if (command.equalsIgnoreCase("shape")) {
          // set up parameters for the shape
          String id = wordsList.get(1);
          String type = wordsList.get(2);
          double x = Double.parseDouble(wordsList.get(3));
          double y = Double.parseDouble(wordsList.get(4));
          double width = Double.parseDouble(wordsList.get(5));
          double height = Double.parseDouble(wordsList.get(6));
          int red = Integer.parseInt(wordsList.get(7));
          int green = Integer.parseInt(wordsList.get(8));
          int blue = Integer.parseInt(wordsList.get(9));
          // add the shape
          ShapesAction.addByName(shapesList, type, id, x, y, red, green, blue, width, height);
        }

        // case 2: take a snapshot
        else if (command.equalsIgnoreCase("snapShot")) {
          String description;
          // empty description
          if (wordsList.size() == 1) {
            description = "";
          }
          // non-empty description
          else {
            description = String.join(" ", wordsList.subList(1, wordsList.size()));
          }
          // take screenshot
          album.takeSnapshot(description, shapesList);
        }

        // case 3: move shape
        else if (command.equalsIgnoreCase("move")) {
          // set up parameters for moving the shape
          String id = wordsList.get(1);
          double x = Double.parseDouble(wordsList.get(2));
          double y = Double.parseDouble(wordsList.get(3));
          // move (using new method in ShapesAction class)
          ShapesAction.move(shapesList, id, x, y);
        }

        // case 4: resize shape
        else if (command.equalsIgnoreCase("resize")) {
          //set up parameters
          String id = wordsList.get(1);
          double horizontal = Double.parseDouble(wordsList.get(2));
          double vertical = Double.parseDouble(wordsList.get(3));
          // resize
          ShapesAction.resize(shapesList, id, horizontal, vertical);
        }

        // case 5: change color
        else if (command.equalsIgnoreCase("color")) {
          //set up parameters
          String name = wordsList.get(1);
          int red = Integer.parseInt(wordsList.get(2));
          int green = Integer.parseInt(wordsList.get(3));
          int blue = Integer.parseInt(wordsList.get(4));
          // change color
          ShapesAction.changeColor(shapesList, name, red, green, blue);
        }

        // case 6: remove shape
        else if (command.equalsIgnoreCase("remove")) {
          // set up parameters
          String name = wordsList.get(1);
          // remove shape
          ShapesAction.removeShape(shapesList, name);
        }
      }

    }
    catch (IOException e) {
      throw new IOException("Cannot read from file");
    }
  }

  /**
   * Getter for album.
   * @return (Album) the album created adn modified from the input file
   */
  @Override
  public Album getAlbum() {
    return this.album;
  }

  /**
   * Getter for the list of shapes.
   * @return (List) the list of Shapes
   */
  @Override
  public List<Shape> getShapesList() {
    return this.shapesList;
  }
}

