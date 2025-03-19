package shapes;

/**
 * This a subclass of Shape representing an Oval.
 */
public class Oval extends Shape {
  private int xRadius;
  private int yRadius;

  /**
   * A constructor that takes five parameters.
   *
   * @param position the position of the oval
   * @param color    the color of the oval
   * @param xRadius  the x radius of the oval, must greater than 0
   * @param yRadius  the y radius of the oval, must greater than 0
   * @param name     the name of the oval, cannot be null or empty
   */
  public Oval(Point position, Color color, int xRadius, int yRadius, String name) {
    super(position, color, name);
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty or null");
    }
    if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("Radius should be greater than 0");
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * A copy constructor.
   *
   * @param other the other oval
   */
  public Oval(Oval other) {
    super(other.getPosition(), other.getColor(), other.getName());
    this.xRadius = other.getXRadius();
    this.yRadius = other.getYRadius();
  }

  @Override
  public Oval copy() {
    return new Oval(this);
  }

  /**
   * Getter method for x radius.
   *
   * @return the x radius
   */
  public int getXRadius() {
    return this.xRadius;
  }

  /**
   * Getter method for y radius.
   *
   * @return the y radius
   */
  public int getYRadius() {
    return this.yRadius;
  }

  /**
   * Setter method for x radius.
   *
   * @param xRadius the x radius
   */
  public void setXRadius(int xRadius) {
    this.xRadius = xRadius;
  }

  /**
   * Setter method for y radius.
   *
   * @param yRadius the y radius
   */
  public void setYRadius(int yRadius) {
    this.yRadius = yRadius;
  }

  @Override
  public String describe() {
    return "Center: (" + getPosition().getX() + "," + getPosition().getY()
            + "), X radius: " + getXRadius() + ", Y radius: " + getYRadius()
            + ", Color: (" + getColor() + ")";
  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.OVAL;
  }

  @Override
  public String toString() {
    return "Oval: " + getName() + " Position: (" + getPosition().getX() + ","
            + getPosition().getY() + ") x radius: " + getXRadius() + ", y radius: " + getYRadius()
            + ", color: " + getColor();
  }
}
