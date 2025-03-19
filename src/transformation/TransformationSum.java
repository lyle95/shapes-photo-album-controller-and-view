package transformation;

import java.util.ArrayList;
import java.util.List;

import shapes.Shape;

/**
 * This class represents a TransformationSummary implementing the interface ITransformation,
 * to apply multiple transformations sequentially.
 */
public class TransformationSum implements ITransformation {
  private List<ITransformation> transformations;

  /**
   * Instantiates a new TransformationSum.
   */
  public TransformationSum() {
    this.transformations = new ArrayList<>();
  }

  /**
   * Add a transformation to the list.
   *
   * @param transformation the transformation of the shape
   */
  public void addTransformation(ITransformation transformation) {
    transformations.add(transformation);
  }

  @Override
  public void apply(Shape shape) {
    for (ITransformation transformation : transformations) {
      transformation.apply(shape);
    }
  }
}
