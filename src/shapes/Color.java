package shapes;

/**
 * This class represents the Color of a shape.
 */
public class Color {
  private int r;
  private int g;
  private int b;

  /**
   * A constructor that takes three parameters.
   *
   * @param r the red component
   * @param g the green component
   * @param b the blue component
   */
  public Color(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Getter method for r.
   *
   * @return the r
   */
  public int getR() {
    return r;
  }

  /**
   * Getter method for g.
   *
   * @return the g
   */
  public int getG() {
    return g;
  }

  /**
   * Getter method for b.
   *
   * @return the b
   */
  public int getB() {
    return b;
  }

  @Override
  public String toString() {
    return r + "," + g + "," + b;
  }
}
