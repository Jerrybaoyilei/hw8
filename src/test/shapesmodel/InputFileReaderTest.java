package shapesmodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;

/**
 * Test class for the InputFileReader class.
 */
public class InputFileReaderTest {

  /**
   * Test readCommands().
   * @throws IOException  Unable to read file
   */
  @Test
  public void testReadCommands() throws IOException {
    InputFileReader r = new InputFileReader();
    r.readCommands("test.txt");
    // the test.txt only contains one shape
    assertEquals(1, r.getAlbum().getAlbumList().size());
    assertEquals("test selfie", r.getAlbum().getAlbumList().get(0).getDescription());
    assertEquals("myrect", r.getAlbum().getAlbumList().get(0).getCopiedList().get(0).getName());
  }

  /**
   * Test getAlbum().
   */
  @Test
  public void testGetAlbum() throws IOException {
    InputFileReader r = new InputFileReader();
    assertEquals(0, r.getAlbum().getAlbumList().size());
    r.readCommands("test.txt");
    assertEquals(1, r.getAlbum().getAlbumList().size());
    // make sure the rect is the one we want by asserting its various parameters
    Shape rect = r.getAlbum().getAlbumList().get(0).getCopiedList().get(0);
    assertEquals(new Rectangle("myrect", 300, 200, 255, 0, 0, 25, 100), rect);
  }

  /**
   * Test getShapesList().
   */
  @Test
  public void testGetShapesList() throws IOException {
    InputFileReader r = new InputFileReader();
    assertEquals(0, r.getShapesList().size());
    r.readCommands("test.txt");
    assertEquals(1, r.getShapesList().size());
    Shape rect = r.getShapesList().get(0);
    assertEquals(new Rectangle("myrect", 300, 200, 255, 0, 0, 25, 100), rect);
  }
}