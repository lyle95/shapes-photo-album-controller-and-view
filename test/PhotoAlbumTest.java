import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import album.PhotoAlbum;
import shapes.Color;
import shapes.Oval;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import transformation.ChangeColor;
import transformation.ITransformation;
import transformation.Move;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This is a test class to test PhotoAlbum class.
 */
public class PhotoAlbumTest {
  private PhotoAlbum photoAlbum;
  private Shape rectangle;
  private Shape oval;

  /**
   * Sets up.
   */
  @BeforeEach
  public void setUp() {
    photoAlbum = new PhotoAlbum();
    rectangle = new Rectangle(new Point(100,200),new Color(1,0,0),200,400,"R");
    oval = new Oval(new Point(50,50),new Color(0,1,0),60,30,"O");
  }

  /**
   * Test addShape method.
   */
  @Test
  public void testAddShape() {
    photoAlbum.addShape(rectangle);
    assertTrue(photoAlbum.printDescription().contains("Name: R"));
  }

  /**
   * Test addShape method with duplicate shape.
   */
  @Test
  public void testAddSameShape() {
    photoAlbum.addShape(rectangle);
    assertThrows(IllegalArgumentException.class, () -> {
      photoAlbum.addShape(rectangle);
    });
  }

  /**
   * Test removeShape method.
   */
  @Test
  public void testRemoveShape() {
    photoAlbum.addShape(rectangle);
    photoAlbum.removeShape(rectangle);
    assertTrue(photoAlbum.printDescription().isEmpty());
  }

  /**
   * Test applyTransformation.
   */
  @Test
  public void testApplyTransformation() {
    photoAlbum.addShape(rectangle);
    photoAlbum.addShape(oval);
    Map<Shape, ITransformation> transformations = new HashMap<>();
    transformations.put(rectangle, new Move(200,100));
    transformations.put(oval, new ChangeColor(new Color(0,0,1)));
    photoAlbum.applyTransformation(transformations);
    assertTrue(photoAlbum.printDescription().contains("Min corner: (200,100"));
    assertTrue(photoAlbum.printDescription().contains("Color: (0,0,1"));
  }

  /**
   * Test applyTransformation with non-existent shape.
   */
  @Test
  public void testInvalidTransformation() {
    photoAlbum.addShape(rectangle);
    Map<Shape, ITransformation> transformations = new HashMap<>();
    transformations.put(oval, new Move(100,100));
    assertThrows(IllegalArgumentException.class, () -> {
      photoAlbum.applyTransformation(transformations);
    });
  }

  /**
   * Test takeSnapshot method.
   */
  @Test
  public void testTakeSnapshot() {
    photoAlbum.addShape(rectangle);
    photoAlbum.addShape(oval);
    photoAlbum.takeSnapshot("First snapshot");
    assertFalse(photoAlbum.printSnapshots().isEmpty());
    assertTrue(photoAlbum.printSnapshots().contains("Description: First snapshot"));
  }

  /**
   * Test printDescription method.
   */
  @Test
  public void testPrintDescription() {
    photoAlbum.addShape(rectangle);
    photoAlbum.addShape(oval);
    assertTrue(photoAlbum.printDescription().contains("Name: R"));
    assertTrue(photoAlbum.printDescription().contains("Name: O"));
  }

  /**
   * Test printSnapshots method.
   */
  @Test
  public void testPrintSnapshots() {
    photoAlbum.addShape(rectangle);
    photoAlbum.addShape(oval);
    photoAlbum.takeSnapshot("Snapshot");
    assertTrue(photoAlbum.printSnapshots().contains("Snapshot ID:"));
    assertTrue(photoAlbum.printSnapshots().contains("Description: Snapshot"));
  }

  /**
   * Test reset method.
   */
  @Test
  public void testReset() {
    photoAlbum.addShape(rectangle);
    photoAlbum.addShape(oval);
    photoAlbum.takeSnapshot("Snapshot before reset");
    photoAlbum.reset();
    assertTrue(photoAlbum.printDescription().isEmpty());
    assertFalse(photoAlbum.printSnapshots().contains("Snapshot ID:"));
  }
}
