package shapesmodel;

import shapesmodel.Album;
import shapesmodel.Shape;

import java.io.IOException;
import java.util.List;

/**
 * Interface for the controller.
 */
public interface InputReader {

  /**
   * Read in commands from an input file. Create and change shapes. Take snapshots.
   * @param filename  name of input file
   * @throws IOException  cannot read input file
   * @throws IllegalArgumentException  empty input file
   */
  void readCommands(String filename) throws IOException, IllegalArgumentException;

  /**
   * Getter for album.
   * @return (Album) the album created adn modified from the input file
   */
  Album getAlbum();

  /**
   * Getter for the list of shapes.
   * @return (List) the list of Shapes
   */
  List<Shape> getShapesList();
}
