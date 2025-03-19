package shapes;

/**
 * This class represents a Point in 2D space with x and y coordinates.
 */
public class Point {

  private int x;
  private int y;

  /**
   * A constructor that takes two parameters.
   *
   * @param x the x position
   * @param y the y position
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Getter method for x.
   *
   * @return the x position of the shape
   */
  public int getX() {
    return x;
  }

  /**
   * Getter method for y.
   *
   * @return the y position of the shape
   */
  public int getY() {
    return y;
  }

  /**
   * Setter method for x.
   *
   * @param x the x position of the shape
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Setter method for y.
   *
   * @param y the y position of the shape
   */
  public void setY(int y) {
    this.y = y;
  }
}
