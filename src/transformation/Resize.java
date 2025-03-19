package transformation;

import shapes.Oval;
import shapes.Rectangle;
import shapes.Shape;

/**
 * This class represents a shape transformation of Resize,
 * implementing the interface ITransformation.
 */
public class Resize implements ITransformation {
  private int xData;
  private int yData;

  /**
   * A constructor that takes two parameters.
   *
   * @param xData the x data, must be greater than 0
   * @param yData the y data, must be greater than 0
   */
  public Resize(int xData, int yData) {
    if (xData <= 0 || yData <= 0) {
      throw new IllegalArgumentException("data must be greater than 0");
    }
    this.xData = xData;
    this.yData = yData;
  }

  @Override
  public void apply(Shape shape) {
    if (shape instanceof Rectangle) {
      Rectangle rectangle = (Rectangle) shape;
      rectangle.setWidth(xData);
      rectangle.setHeight(yData);
    } else if (shape instanceof Oval) {
      Oval oval = (Oval) shape;
      oval.setXRadius(xData);
      oval.setYRadius(yData);
    }
  }
}
