package shapes;

/**
 * This is a subclass representing Rectangle.
 */
public class Rectangle extends Shape {
  private int width;
  private int height;

  /**
   * A constructor that takes five parameters.
   *
   * @param position the position of the rectangle
   * @param color    the color of the rectangle
   * @param width    the width of the rectangle, must be greater than 0
   * @param height   the height of the rectangle, must be greater than 0
   * @param name     the name of the rectangle, cannot be null or empty
   */
  public Rectangle(Point position, Color color, int width, int height, String name) {
    super(position, color, name);
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty or null");
    }
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height should be greater than 0");
    }
    this.width = width;
    this.height = height;
  }

  /**
   * A copy constructor.
   *
   * @param other the other rectangle
   */
  public Rectangle(Rectangle other) {
    super(other.getPosition(), other.getColor(), other.getName());
    this.width = other.getWidth();
    this.height = other.getHeight();
  }

  @Override
  public Rectangle copy() {
    return new Rectangle(this);
  }

  /**
   * Getter method for width.
   *
   * @return the width of the rectangle
   */
  public int getWidth() {
    return width;
  }

  /**
   * Getter method for height.
   *
   * @return the height of the rectangle
   */
  public int getHeight() {
    return height;
  }

  /**
   * Setter method for width.
   *
   * @param width the width of the rectangle
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Setter method for height.
   *
   * @param height the height of the rectangle
   */
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public String describe() {
    return "Min corner: (" + getPosition().getX() + "," + getPosition().getY()
            + "), Width " + getWidth() + ", Height: " + getHeight()
            + ", Color: (" + getColor() + ")";
  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.RECTANGLE;
  }

  @Override
  public String toString() {
    return "Rectangle: " + getName() + " Position: (" + getPosition().getX() + ","
            + getPosition().getY() + ") width: " + getWidth() + ", height: " + getHeight()
            + ", color: " + getColor();
  }
}
