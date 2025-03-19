package transformation;

import shapes.Color;
import shapes.Shape;

/**
 * This class representing a shape transformation of ChangeColor,
 * implementing the interface ITransformation.
 */
public class ChangeColor implements ITransformation {
  private Color color;

  /**
   * A constructor that takes one parameter.
   *
   * @param color the color of the shape
   */
  public ChangeColor(Color color) {
    this.color = color;
  }

  @Override
  public void apply(Shape shape) {
    shape.setColor(color);
  }
}
