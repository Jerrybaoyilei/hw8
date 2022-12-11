package shapesalbum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Test class for methods in Album class.
 */
public class AlbumTest {

  private final List<Shape> list1 = new ArrayList<>();
  private final Oval O = new Oval("O", 500.0, 100.0, 0.0, 0.0, 1.0, 60.0, 30.0);
  private final Rectangle R = new Rectangle("R", 200.0, 200.0, 1.0, 0.0, 0.0, 50.0, 100.0);
  private final Album album = new Album();

  /**
   * Method to test takeSnapshot().
   */
  @Test
  public void testTakeScreenshot() {

    list1.add(O);
    list1.add(R);

    album.takeSnapshot("1st snapshot", list1);
    assertEquals(1, album.getAlbum().size());
    assertEquals(new Oval("O", 500.0, 100.0, 0.0, 0.0, 1.0, 60.0, 30.0),
            album.getAlbum().get(0).getCopiedList().get(0));
  }

  /**
   * Test removeSnapshot().
   */
  @Test
  public void testRemoveSnapshot() {

    list1.add(O);
    list1.add(R);

    album.takeSnapshot("1st snapshot", list1);
    list1.remove(1);
    album.takeSnapshot("2nd snapshot", list1);
    assertEquals(2, album.getAlbum().size());

    album.removeSnapshot();
    // now the only snapshot in album is the 2nd snapshot with only O in the snapshot.
    assertEquals(1, album.getAlbum().size());
    assertEquals(new Oval("O", 500.0, 100.0, 0.0, 0.0, 1.0, 60.0, 30.0),
            album.getAlbum().get(0).getCopiedList().get(0));
  }

  /**
   * Test removeSnapshot(), but remove the snapshot at index.
   */
  @Test
  public void testRemoveSnapshotAtIndex() {
    list1.add(O);
    list1.add(R);

    album.takeSnapshot("1st snapshot", list1);
    list1.remove(1);
    album.takeSnapshot("2nd snapshot", list1);
    assertEquals(2, album.getAlbum().size());

    album.removeSnapshot(1);
    // now the only snapshot in album is the 1st snapshot with O and R in the snapshot.
    assertEquals(1, album.getAlbum().size());
    assertEquals(new Oval("O", 500.0, 100.0, 0.0, 0.0, 1.0, 60.0, 30.0),
            album.getAlbum().get(0).getCopiedList().get(0));
    assertEquals(new Rectangle("R", 200.0, 200.0, 1.0, 0.0, 0.0, 50.0, 100.0),
            album.getAlbum().get(0).getCopiedList().get(1));

    assertThrows(IndexOutOfBoundsException.class, () -> {
      album.removeSnapshot(2);
    });
  }

  /**
   * Test removeByTimestamp().
   */
  @Test
  public void removeByTimestamp() {
    list1.add(O);
    list1.add(R);

    album.takeSnapshot("1st snapshot", list1);
    list1.remove(1);
    album.takeSnapshot("2nd snapshot", list1);
    // timestamp for the second snapshot
    String timestamp = album.getAlbum().get(0).getTimestamp();

    album.removeByTimestamp(timestamp);

    assertEquals("2nd snapshot", album.getAlbum().get(0).getDescription());

    assertThrows(IllegalArgumentException.class, () -> {
      album.removeByTimestamp(null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      album.removeByTimestamp("");
    });

    assertThrows(NoSuchElementException.class, () -> {
      album.removeByTimestamp("fake");
    });
  }

  /**
   * Test removeByDescription().
   */
  @Test
  public void testRemoveByDescription() {

    list1.add(O);
    list1.add(R);

    album.takeSnapshot("1st snapshot", list1);
    list1.remove(1);
    album.takeSnapshot("2nd snapshot", list1);

    String description = "2nd snapshot";

    assertEquals("1st snapshot", album.getAlbum().get(0).getDescription());

    assertThrows(NoSuchElementException.class, () -> {
      album.removeByDescription("fake hahahaha");
    });
  }

  /**
   * Test getIdList().
   */
  @Test
  public void getIdList() {

    list1.add(O);
    list1.add(R);

    album.takeSnapshot("1st snapshot", list1);
    list1.remove(1);
    album.takeSnapshot("2nd snapshot", list1);

    assertEquals(2, album.getIdList().size());
    assertEquals(album.getAlbum().get(0).getId(), album.getIdList().get(0));
  }

  /**
   * Test toSting().
   */
  @Test
  public void testToString() {
    list1.add(O);
    list1.add(R);

    album.takeSnapshot("1st snapshot", list1);
    list1.remove(1);
    album.takeSnapshot("2nd snapshot", list1);

    System.out.println(album.toString());

    String expected = "Printing Snapshots\n"
            + "Snapshot ID: " + album.getAlbum().get(0).getId() + "\n"
            + "Timestamp: " + album.getAlbum().get(0).getTimestamp() + "\n"
            + "Description: 1st snapshot\n"
            + "Shape Information: \n"
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n\n\n"
            + "Snapshot ID: " + album.getAlbum().get(1).getId() + "\n"
            + "Timestamp: " + album.getAlbum().get(1).getTimestamp() + "\n"
            + "Description: 2nd snapshot\n"
            + "Shape Information: \n"
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n\n\n";

    assertEquals(expected, album.toString());
  }
}