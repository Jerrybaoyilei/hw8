package shapesmodel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interface for Album.
 */
public interface IAlbum {

  /**
   * Add the snapshot of given description and shapesList to the album.
   * @param description  the description of the snapshot
   * @param shapesList   the shapesList at the moment of capture
   * @throws IllegalArgumentException  the description cannot be null or empty
   */
  void takeSnapshot(String description, List<Shape> shapesList)
          throws IllegalArgumentException;

  /**
   * Default method to remove the earliest snapshot taken.
   *
   * @throws IllegalArgumentException thrown when the album is empty; nothing can be removed
   */
  void removeSnapshot() throws NoSuchElementException;

  /**
   * Remove the screenshot at the given index.
   *
   * @param index the index location of the snapshot to be removed
   * @throws IndexOutOfBoundsException if the index is out of bound of the album list
   */
  void removeSnapshot(int index)
          throws IndexOutOfBoundsException;

  /**
   * Remove a snapshot with the given timestamp.
   *
   * @param timestamp the timestamp of the snapshot to be removed
   * @throws IllegalArgumentException when the timestamp is empty or null
   * @throws NoSuchElementException   when a snapshot with the given timestamp cannot be found
   */
  void removeByTimestamp(String timestamp)
          throws IllegalArgumentException, NoSuchElementException;

  /**
   * Remove a snapshot with the given description.
   *
   * @param description the description of the snapshot to be removed
   * @throws IllegalArgumentException when the description is empty or null
   * @throws NoSuchElementException   when a snapshot with the given timestamp cannot be found
   */
  void removeByDescription(String description)
          throws NoSuchElementException;

  /**
   * Method to return a list of the id's of the snapshots.
   * @return  a list of snapshot's id
   */
  List<LocalDateTime> getIdList();

  /**
   * Getter for the album list containing snapshots.
   * @return  album, i.e. a list of snapshots
   */
  List<Snapshot> getAlbumList();

  /**
   * Main method replicating the assignment example.
   */
  String toString();
}
