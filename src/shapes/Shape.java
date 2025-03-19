package shapes;

import java.util.Objects;

/**
 * This is an abstract class representing a generic Shape.
 */
public abstract class Shape {
  private Point position;
  private Color color;
  private String name;

  /**
   * A constructor that takes three parameters.
   *
   * @param position the position of the shape
   * @param color    the color of the shape
   * @param name     the name of the shape
   */
  public Shape(Point position, Color color, String name) {
    this.position = position;
    this.color = color;
    this.name = name;
  }

  /**
   * Getter method for shape type.
   *
   * @return the shape type
   */
  public abstract ShapeType getShapeType();

  /**
   * Copy method for a shape.
   *
   * @return the shape
   */
  public abstract Shape copy();

  /**
   * Getter method for position.
   *
   * @return the position of the shape
   */
  public Point getPosition() {
    return position;
  }

  /**
   * Getter method for color.
   *
   * @return the color of the shape
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Setter method for color.
   *
   * @param color the color of the shape
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Getter method for name.
   *
   * @return the name of the shape
   */
  public String getName() {
    return this.name;
  }

  /**
   * Describe the shape.
   *
   * @return the description of the shape
   */
  public abstract String describe();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Shape shape = (Shape) o;
    return Objects.equals(name, shape.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
