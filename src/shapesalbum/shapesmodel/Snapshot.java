package shapesmodel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class to model a single snapshot object, which includes the snapshot ID, timestamp, description,
 * and shapes currently in the list.
 */
public class Snapshot {

  // Keep the parameters private and provide getters for access outside the class.
  // Parameters are final because once we create the snapshot, we don't want to modify it.
  private final LocalDateTime id;
  private final String timestamp;
  private final String description;
  // a copy of the current shapeList
  private final List<Shape> copiedList;

  /**
   * Constructor for the class. It creates a snapshot of the shapesList at the current moment.
   * @param description   the snapshot's description
   * @param originalList  the shapesList to be copied into the Snapshot object
   * @throws IllegalArgumentException  the description cannot be null or empty
   */
  public Snapshot(String description, List<Shape> originalList) {

    // the snapshot ID
    this.id = LocalDateTime.now();

    // timestamp: HH refers to 2 digit time; MM refers to month while mm refers to minutes
    // credit to: https://medium.com/mendix/common-date-time-errors-in-mendix-16e6e1180161
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    this.timestamp = this.id.format(formatter);

    // If description is missing, automatically fill in empty description
    this.description = Objects.requireNonNullElse(description, "");

    this.copiedList = new ArrayList<>();
    for (Shape shape: originalList) {
      this.copiedList.add(shape.copySelf());
    }
  }

  /**
   * Getter for timestamp.
   * @return  the snapshot's timestamp as a String
   */
  public String getTimestamp() {
    return this.timestamp;
  }

  /**
   * Getter for description.
   * @return  the snapshot's description.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Getter for snapshot's id.
   * @return  the snapshot's id (it's LocalDateTime)
   */
  public LocalDateTime getId() {
    return this.id;
  }

  /**
   * Getter for the copied list in the snapshot.
   * @return  the copied list of shapes
   */
  public List<Shape> getCopiedList() {
    return this.copiedList;
  }

  @Override
  public String toString() {

    StringBuilder builder = new StringBuilder();

    builder.append("Snapshot ID: ").append(this.getId()).append("\n")
            .append("Timestamp: ").append(this.getTimestamp()).append("\n")
            .append("Description: ").append(this.getDescription()).append("\n")
            .append("Shape Information: ").append("\n");

    for (Shape shape : this.copiedList) {
      builder.append(shape.toString());
      builder.append("\n");
    }

    return builder.toString();
  }
}
