package shapesmodel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class to represent an album, i.e., a collection of snapshots of shapesList.
 */
public class Album implements IAlbum {

  // the list of snapshots
  List<Snapshot> album;
  // the list of snapshot's IDs, in the same order and the snapshots
  List<LocalDateTime> idList;

  /**
   * Constructor to create an empty album.
   */
  public Album() {
    this.album = new ArrayList<>();
    this.idList = new ArrayList<>();
  }

  /**
   * Add the snapshot of given description and shapesList to the album.
   * @param description  the description of the snapshot
   * @param shapesList   the shapesList at the moment of capture
   * @throws IllegalArgumentException  the description cannot be null or empty
   */
  @Override
  public void takeSnapshot(String description, List<Shape> shapesList)
          throws IllegalArgumentException {
    Snapshot temp = new Snapshot(description, shapesList);
    this.album.add(temp);
    this.idList.add(temp.getId());
  }

  /**
   * Default method to remove the earliest snapshot taken.
   *
   * @throws IllegalArgumentException thrown when the album is empty; nothing can be removed
   */
  @Override
  public void removeSnapshot() throws NoSuchElementException {

    if (this.album.size() == 0) {
      throw new NoSuchElementException("No snapshot in the album to be removed. ");
    }

    this.idList.remove(0);
    this.album.remove(0);
  }

  /**
   * Remove the screenshot at the given index.
   *
   * @param index the index location of the snapshot to be removed
   * @throws IndexOutOfBoundsException if the index is out of bound of the album list
   */
  @Override
  public void removeSnapshot(int index)
          throws IndexOutOfBoundsException {

    if (index < 0 || index >= album.size()) {
      throw new IndexOutOfBoundsException(
              String.format("The index is out of bound. It should be between 0 and %d",
                      this.album.size() - 1));
    }

    this.idList.remove(index);
    this.album.remove(index);
  }

  /**
   * Remove a snapshot with the given timestamp.
   *
   * @param timestamp the timestamp of the snapshot to be removed
   * @throws IllegalArgumentException when the timestamp is empty or null
   * @throws NoSuchElementException   when a snapshot with the given timestamp cannot be found
   */
  @Override
  public void removeByTimestamp(String timestamp)
          throws IllegalArgumentException, NoSuchElementException {

    if (timestamp == null || timestamp.equals("")) {
      throw new IllegalArgumentException("Timestamp cannot be null or empty. ");
    }

    for (int i = 0; i < album.size(); i++) {
      if (Objects.equals(this.album.get(i).getTimestamp(), timestamp)) {
        this.idList.remove(i);
        this.album.remove(i);
        return;
      }
    }

    throw new NoSuchElementException("No such snapshot with the given timestamp in the album. ");
  }

  /**
   * Remove a snapshot with the given description.
   *
   * @param description the description of the snapshot to be removed
   * @throws IllegalArgumentException when the description is empty or null
   * @throws NoSuchElementException   when a snapshot with the given timestamp cannot be found
   */
  @Override
  public void removeByDescription(String description)
          throws NoSuchElementException {

    for (int i = 0; i < this.album.size(); i++) {
      if (Objects.equals(this.album.get(i).getDescription(), description)) {
        this.idList.remove(i);
        this.album.remove(i);
        return;
      }
    }

    throw new NoSuchElementException("No such snapshot with the given description in the album. ");
  }

  /**
   * Method to return a list of the id's of the snapshots.
   * @return  a list of snapshot's id
   */
  @Override
  public List<LocalDateTime> getIdList() {
    return this.idList;
  }

  /**
   * Getter for the album list containing snapshots.
   * @return  album, i.e. a list of snapshots
   */
  @Override
  public List<Snapshot> getAlbumList() {
    return this.album;
  }

  @Override
  public String toString() {

    StringBuilder builder = new StringBuilder();

    builder.append("Printing Snapshots").append("\n");

    for (Snapshot snapshot: album) {
      builder.append(snapshot.toString());
      builder.append("\n");
    }

    return builder.toString();
  }

  /**
   * Main method replicating the assignment example.
   */
  public static void main(String[] args) {

    // shape creation and addition to list of shapes
    Rectangle R = new Rectangle("R", 200.0, 200.0, 1, 0, 0, 50.0, 100.0);
    Oval O = new Oval("O", 500.0, 100.0, 0, 0, 1, 60.0, 30.0);
    List<Shape> shapesList = new ArrayList<>();
    shapesList.add(R);
    shapesList.add(O);
    Album album1 = new Album();

    System.out.println(ShapesAction.printShapes(shapesList));
    album1.takeSnapshot("After first selfie", shapesList);


    // Moving and resizing and changing color of rectangle
    ShapesAction.move(shapesList, 0, 100.0, 300.0);
    ShapesAction.changeHorizontal(shapesList, 0, 25);
    ShapesAction.changeColor(shapesList,0,0,1,0);
    System.out.println(ShapesAction.printShapes(shapesList));
    album1.takeSnapshot("2nd selfie", shapesList);

    // Moving oval
    ShapesAction.move(shapesList,1,500.0, 400.0);
    Snapshot snap3 = new Snapshot("", shapesList);
    System.out.println(ShapesAction.printShapes(shapesList));
    album1.takeSnapshot("", shapesList);

    // Remove the rectangle from the picture
    shapesList.remove(0);
    System.out.println(ShapesAction.printShapes(shapesList));
    album1.takeSnapshot("Selfie after removing the rectangle from the picture",
            shapesList);

    System.out.println("List of snapshots taken before reset: " + album1.getIdList() + "\n\n");
    System.out.println(album1.toString());
  }
}
