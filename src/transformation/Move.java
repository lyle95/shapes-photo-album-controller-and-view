package transformation;

import shapes.Shape;

/**
 * This class represents a shape transformation of Move,
 * implementing the interface ITransformation.
 */
public class Move implements ITransformation {
  private int newX;
  private int newY;

  /**
   * A constructor that takes two parameters.
   *
   * @param newX the new x position
   * @param newY the new y position
   */
  public Move(int newX, int newY) {
    this.newX = newX;
    this.newY = newY;
  }

  @Override
  public void apply(Shape shape) {
    shape.getPosition().setX(newX);
    shape.getPosition().setY(newY);
  }
}
