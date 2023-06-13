package shapesmodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for methods in the Snapshot class.
 */
public class SnapshotTest {

  private final List<Shape> list1 = new ArrayList<>();
  private final Oval O = new Oval("O", 500.0, 100.0, 0, 0, 0, 60.0, 30.0);
  private final Rectangle R = new Rectangle("R", 200.0, 200.0, 1, 0, 0, 50.0, 100.0);

  /**
   * Test getTimestamp().
   */
  @Test
  public void testGetTimestamp() {

    list1.add(O);
    list1.add(R);

    Snapshot snap = new Snapshot("the first snapshot", list1);
    LocalDateTime current = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String currentTime = current.format(formatter);
    assertEquals(currentTime, snap.getTimestamp());
  }

  /**
   * Test getDescription().
   */
  @Test
  public void testGetDescription() {

    list1.add(O);
    list1.add(R);

    Snapshot snap = new Snapshot("the second snapshot", list1);
    assertEquals("the second snapshot", snap.getDescription());
    Snapshot snap2 = new Snapshot(null, list1);
    assertEquals("", snap2.getDescription());
  }

  /**
   * Test getId(). Convert the id into String and compare with the time when snap was taken,
   * but compare only up to seconds.
   */
  @Test
  public void testGetId() {

    list1.add(O);
    list1.add(R);

    Snapshot snap = new Snapshot("the third snapshot", list1);
    LocalDateTime current = LocalDateTime.now();
    String snapTime = snap.getId().toString().substring(0, 20);
    String currentTime = current.toString().substring(0, 20);
    assertEquals(currentTime, snapTime);
  }

  /**
   * Test getCopiedList().
   */
  @Test
  public void testGetCopiedList() {

    list1.add(O);
    list1.add(R);

    Snapshot snap = new Snapshot("the fourth snapshot", list1);

    List<Shape> listInSnapshot = snap.getCopiedList();

    assertEquals(list1.size(), listInSnapshot.size());

    for (int i = 0; i < list1.size(); i++) {
      assertEquals(list1.get(i), listInSnapshot.get(i));
    }

    list1.remove(0);

    assertEquals(1, list1.size());
    assertEquals(2, listInSnapshot.size());
  }
}