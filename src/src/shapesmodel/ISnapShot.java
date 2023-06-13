package shapesmodel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface for Snapshot.
 */
public interface ISnapShot {

  /**
   * Getter for timestamp.
   * @return  the snapshot's timestamp as a String
   */
  String getTimestamp();

  /**
   * Getter for description.
   * @return  the snapshot's description.
   */
  String getDescription();

  /**
   * Getter for snapshot's id.
   * @return  the snapshot's id (it's LocalDateTime)
   */
  LocalDateTime getId();

  /**
   * Getter for the copied list in the snapshot.
   * @return  the copied list of shapes
   */
  List<Shape> getCopiedList();

  /**
   * Turn snapshot into a descriptive text block.
   * @return the text block describing a snapshot
   */
  String toString();
}
