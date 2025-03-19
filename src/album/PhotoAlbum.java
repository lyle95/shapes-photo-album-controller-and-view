package album;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shapes.Shape;
import transformation.ITransformation;

/**
 * This class represents a Photo Album.
 */
public class PhotoAlbum {
  private List<Shape> shapes;
  private List<Snapshot> snapshots;
  private List<String> snapshotIDs;
  private List<PhotoAlbumListener> listeners;
  private int currentIndex;

  /**
   * Instantiates a new Photo album.
   */
  public PhotoAlbum() {
    shapes = new ArrayList<>();
    snapshots = new ArrayList<>();
    snapshotIDs = new ArrayList<>();
    listeners = new ArrayList<>();
    this.currentIndex = 0;
  }

  /**
   * Add a shape to the shapes list.
   *
   * @param shape the shape
   */
  public void addShape(Shape shape) {
    for (Shape s: shapes) {
      if (s.getName().equals(shape.getName())) {
        throw new IllegalArgumentException("Shape name already exists: " + shape.getName());
      }
    }
    shapes.add(shape);
  }

  /**
   * Remove a shape from the shapes list.
   *
   * @param shape the shape
   */
  public void removeShape(Shape shape) {
    shapes.remove(shape);
  }

  /**
   * Getter method for shapes.
   *
   * @return the list of shapes
   */
  public List<Shape> getShapes() {
    return new ArrayList<>(shapes);
  }


  /**
   * Apply a set of transformations to the shapes.
   *
   * @param transformations the transformations
   */
  public void applyTransformation(Map<Shape, ITransformation> transformations) {
    for (Map.Entry<Shape, ITransformation> entry : transformations.entrySet()) {
      Shape shape = entry.getKey();
      if (!shapes.contains(shape)) {
        throw new IllegalArgumentException("Shape not found: " + shape.getName());
      }
      ITransformation transformation = entry.getValue();
      transformation.apply(shape);
    }
  }

  /**
   * Take snapshot for the current shapes and notify snapshot taken.
   *
   * @param description the description of the current shapes
   */
  public void takeSnapshot(String description) {
    List<Shape> shapesCopy = new ArrayList<>();
    for (Shape shape : shapes) {
      shapesCopy.add(shape.copy());
    }
    Snapshot snapshot = new Snapshot(shapesCopy, description);
    snapshots.add(snapshot);
    snapshotIDs.add(snapshot.getID());
    notifySnapshotTaken(snapshot);
  }

  /**
   * Getter method for the current snapshot.
   *
   * @return the current snapshot
   */
  public Snapshot getCurrentSnapshot() {
    if (snapshots.isEmpty()) {
      return null;
    } else {
      return snapshots.get(currentIndex);
    }
  }

  /**
   * Getter method for the specified snapshot.
   *
   * @param index the index of the snapshot
   * @return the snapshot
   */
  public Snapshot getSnapshot(int index) {
    if (index < 0 || index >= snapshots.size()) {
      return null;
    } else {
      return snapshots.get(index);
    }
  }

  /**
   * Print description of the shapes.
   *
   * @return the string of description
   */
  public String printDescription() {
    StringBuilder sb = new StringBuilder();
    for (Shape shape : shapes) {
      sb.append("Name: ").append(shape.getName()).append("\n");
      sb.append("Type: ").append(shape.getShapeType()).append("\n");
      sb.append(shape.describe()).append("\n\n");
    }
    return sb.toString();
  }

  /**
   * Print the list of snapshots.
   *
   * @return the string of snapshots
   */
  public String printSnapshots() {
    StringBuilder result = new StringBuilder();

    result.append("List of snapshots taken before reset: ").append(snapshotIDs).append("\n\n");

    result.append("Printing Snapshots\n");
    for (Snapshot snapshot : snapshots) {
      result.append("Snapshot ID: ").append(snapshot.getID()).append("\n");
      result.append("Timestamp: ").append(snapshot.getTimestamp()).append("\n");
      result.append("Description: ").append(snapshot.getDescription()).append("\n");
      result.append("Shape Information:\n");
      for (Shape shape : snapshot.getShapes()) {
        result.append("Name: ").append(shape.getName()).append("\n");
        result.append("Type: ").append(shape.getShapeType()).append("\n");
        result.append(shape.describe()).append("\n\n");
      }
      result.append("\n");
    }
    return result.toString();
  }

  /**
   * Reset the photo album.
   */
  public void reset() {
    shapes.clear();
    snapshots.clear();
    snapshotIDs.clear();
  }

  /**
   * Notify snapshot taken to listners.
   *
   * @param snapshot the snapshot
   */
  public void notifySnapshotTaken(Snapshot snapshot) {
    for (PhotoAlbumListener listener: listeners) {
      listener.snapshotTaken(snapshot);
    }
  }

  /**
   * Getter method for snapshot IDs.
   *
   * @return the snapshot i ds
   */
  public List<String> getSnapshotIDs() {
    List<String> snapshotIDs = new ArrayList<>();
    for (Snapshot snapshot : snapshots) {
      snapshotIDs.add(snapshot.getID());
    }
    return snapshotIDs;
  }

  /**
   * Add listener to the list.
   *
   * @param listener the listener
   */
  public void addListener(PhotoAlbumListener listener) {
    if (!listeners.contains(listener))  {
      listeners.add(listener);
    }
  }
}
