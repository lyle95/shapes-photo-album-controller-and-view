package transformation;

import shapes.Shape;

/**
 * The interface ITransformation for shape transformations.
 */
public interface ITransformation {

  /**
   * Apply the transformation to a shape.
   *
   * @param shape the shape
   */
  void apply(Shape shape);
}
