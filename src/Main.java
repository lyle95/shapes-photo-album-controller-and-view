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
import transformation.Resize;
import transformation.TransformationSum;

/**
 * This class represents a smoke test for Snapshot class and PhotoAlbum class using text output.
 */
public class Main {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    // Create shapes
    Color red = new Color(1, 0, 0);
    Color blue = new Color(0, 0, 0);
    Rectangle rectangle = new Rectangle(new Point(200, 200), red, 50, 100, "R");
    Oval oval = new Oval(new Point(500, 100), blue, 60, 30, "O");

    // Create photo album
    PhotoAlbum album = new PhotoAlbum();

    // Add shapes to the album
    album.addShape(rectangle);
    album.addShape(oval);
    System.out.println(album.printDescription());
    // Take snapshots
    album.takeSnapshot("After first selfie");

    // First transformation: move, resize and change color of rectangle
    Map<Shape, ITransformation> transformations = new HashMap<>();
    TransformationSum recTransform = new TransformationSum();
    recTransform.addTransformation(new Move(100, 300));
    recTransform.addTransformation(new Resize(50, 300));
    recTransform.addTransformation(new ChangeColor(new Color(0,1,0)));

    // Apply transformations
    transformations.put(rectangle,recTransform);
    album.applyTransformation(transformations);
    System.out.println(album.printDescription());
    album.takeSnapshot("After moving, resizing and changing color of R");

    // Second transformation: move oval
    TransformationSum ovalTransform = new TransformationSum();
    ovalTransform.addTransformation(new Move(500, 400));

    transformations.clear();
    transformations.put(oval,ovalTransform);
    album.applyTransformation(transformations);
    System.out.println(album.printDescription());
    album.takeSnapshot("After moving O");

    // Third transformation: remove rectangle
    album.removeShape(rectangle);
    System.out.println(album.printDescription());
    album.takeSnapshot("After removing R");

    // Print snapshots
    System.out.println(album.printSnapshots());
  }
}

