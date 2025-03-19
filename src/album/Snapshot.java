package album;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import shapes.Shape;

/**
 * This class represents a Snapshot of the current status of shapes in the photo album.
 */
public class Snapshot {
  private List<Shape> shapes;
  private String description;
  private String id;
  private String timestamp;

  /**
   * A constructor that takes two parameters.
   *
   * @param shapes      the list of shapes
   * @param description the description of the snapshot
   */
  public Snapshot(List<Shape> shapes, String description) {
    this.shapes = new ArrayList<>(shapes);
    this.description = description;
    generateTimestampAndID();
  }

  /**
   * Getter method for shapes.
   *
   * @return the list of shapes
   */
  public List<Shape> getShapes() {
    return shapes;
  }

  /**
   * Getter method for description.
   *
   * @return the description of the snapshot
   */
  public String getDescription() {
    return description;
  }

  /**
   * Getter method for the id.
   *
   * @return the id of the snapshot
   */
  public String getID() {
    return id;
  }

  /**
   * Getter method for the timestamp.
   *
   * @return the timestamp of the snapshot
   */
  public String getTimestamp() {
    return timestamp;
  }

  private void generateTimestampAndID() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    id = now.format(formatter);
    timestamp = now.format(timeFormatter);
  }
}
