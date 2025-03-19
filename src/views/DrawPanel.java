package views;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import shapes.Color;
import shapes.Point;
import shapes.Shape;
import shapes.Rectangle;
import shapes.Oval;

/**
 * This class represents a DrawPanel extending JPanel.
 */
public class DrawPanel extends JPanel {
  private List<Shape> shapes;

  /**
   * Takes one parameter and instantiates a new Draw panel.
   *
   * @param shapes the list of shapes
   */
  public DrawPanel(List<Shape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Updates the list of shapes to be drawn and repaint the panel.
   *
   * @param newShapes the list of new shapes
   */
  public void setShapes(List<Shape> newShapes) {
    this.shapes = newShapes;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    // paints the component with the specified graphics context
    super.paintComponent(g);
    for (Shape shape : shapes) {
      drawShape(g, shape);
    }
  }

  private void drawShape(Graphics g, Shape shape) {
    // draw the specified shape on the panel using the provided graphics context
    Point position = shape.getPosition();
    Color color = shape.getColor();

    g.setColor(new java.awt.Color(color.getR(), color.getG(), color.getB()));

    if (shape instanceof Rectangle) {
      Rectangle r = (Rectangle) shape;
      g.fillRect(position.getX(), position.getY(), r.getWidth(), r.getHeight());
    } else if (shape instanceof Oval) {
      Oval o = (Oval) shape;
      g.fillOval(position.getX(), position.getY(), o.getXRadius() * 2, o.getYRadius() * 2);
    }
  }
}
