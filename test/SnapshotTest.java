import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import album.Snapshot;
import shapes.Color;
import shapes.Oval;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This is a test class to test Snapshot class.
 */
public class SnapshotTest {

  /**
   * Test getShapes method.
   */
  @Test
  public void testGetShapes() {
    List<Shape> shapes = new ArrayList<>();
    shapes.add(new Rectangle(new Point(100,100), new Color(1,0,0),200,400,"R"));
    shapes.add(new Oval(new Point(200,300), new Color(0,1,0),60,30,"O"));
    Snapshot snapshot = new Snapshot(shapes, "First snapshot");
    assertEquals(2, snapshot.getShapes().size());
  }

  /**
   * Test getDescription method.
   */
  @Test
  public void testGetDescription() {
    List<Shape> shapes = new ArrayList<>();
    shapes.add(new Rectangle(new Point(100,100), new Color(1,0,0),200,400,"R"));
    Snapshot snapshot = new Snapshot(shapes, "First snapshot");
    assertEquals("First snapshot", snapshot.getDescription());
  }

  /**
   * Test getID method.
   */
  @Test
  public void testGetID() {
    List<Shape> shapes = new ArrayList<>();
    shapes.add(new Oval(new Point(200,300), new Color(0,1,0),60,30,"O"));
    Snapshot snapshot = new Snapshot(shapes, "First snapshot");
    assertNotNull(snapshot.getID());
  }

  /**
   * Test getTimestamp method.
   */
  @Test
  public void testGetTimestamp() {
    List<Shape> shapes = new ArrayList<>();
    shapes.add(new Rectangle(new Point(100,100), new Color(1,0,0),200,400,"R"));
    shapes.add(new Oval(new Point(200,300), new Color(0,1,0),60,30,"O"));
    Snapshot snapshot = new Snapshot(shapes, "First snapshot");
    assertNotNull(snapshot.getTimestamp());
  }
}
