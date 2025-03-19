import org.junit.jupiter.api.Test;

import shapes.Color;
import shapes.Oval;
import shapes.Point;
import shapes.Rectangle;
import shapes.ShapeType;
import transformation.ChangeColor;
import transformation.Move;
import transformation.Resize;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This is a test class to test Shape.
 */
public class ShapeTest {

  /**
   * Test Rectangle.
   */
  @Test
  public void testRectangle() {
    Color color = new Color(1,0,0);
    Point point = new Point(100, 100);
    Rectangle r = new Rectangle(point, color, 200, 400, "R");

    assertEquals(ShapeType.RECTANGLE, r.getShapeType());
    assertEquals("R", r.getName());
    assertEquals("Min corner: (100,100), Width 200, Height: 400, "
            + "Color: (1,0,0)", r.describe());
  }

  /**
   * Test invalid Rectangle.
   */
  @Test
  public void testInvalidRectangle() {
    // invalid width
    assertThrows(IllegalArgumentException.class, () -> {
      new Rectangle(new Point(100,100), new Color(1,0,0),0,400,"R");
    });
    // invalid height
    assertThrows(IllegalArgumentException.class, () -> {
      new Rectangle(new Point(100,100), new Color(1,0,0),200,0,"R");
    });
    // empty name
    assertThrows(IllegalArgumentException.class, () -> {
      new Rectangle(new Point(100,100), new Color(1,0,0),200,400,"");
    });
    // name is null
    assertThrows(IllegalArgumentException.class, () -> {
      new Rectangle(new Point(100,100), new Color(1,0,0),200,400,null);
    });
  }

  /**
   * Test Oval.
   */
  @Test
  public void testOval() {
    Color color = new Color(0,0,1);
    Point point = new Point(50,100);
    Oval oval = new Oval(point,color,60,30,"O");

    assertEquals("O", oval.getName());
    assertEquals(ShapeType.OVAL, oval.getShapeType());
    assertEquals("Center: (50,100), X radius: 60, Y radius: 30, "
            + "Color: (0,0,1)", oval.describe());
  }

  /**
   * Test invalid Oval.
   */
  @Test
  public void testInvalidOval() {
    // invalid x radius
    assertThrows(IllegalArgumentException.class, () -> {
      new Oval(new Point(50,100), new Color(0,0,1),0,30,"O");
    });

    // invalid y radius
    assertThrows(IllegalArgumentException.class, () -> {
      new Oval(new Point(50,100), new Color(0,0,1),60,0,"O");
    });

    // empty name
    assertThrows(IllegalArgumentException.class, () -> {
      new Oval(new Point(50,100), new Color(0,0,1),0,30,"");
    });

    // name is null
    assertThrows(IllegalArgumentException.class, () -> {
      new Oval(new Point(50,100), new Color(0,0,1),0,30,null);
    });
  }

  /**
   * Test ChangeColor.
   */
  @Test
  public void testChangeColor() {
    // change color of rectangle
    Color oldColor = new Color(0,0,1);
    Rectangle r = new Rectangle(new Point(100,100),oldColor,200,400,"R");
    Color newColor = new Color(1,0,0);
    ChangeColor changeColor = new ChangeColor(newColor);

    changeColor.apply(r);
    assertEquals(newColor,r.getColor());

    // change color of oval
    Oval o = new Oval(new Point(0,0),oldColor,40,20,"O");
    Color newColor2 = new Color(0,1,0);
    ChangeColor changeColor2 = new ChangeColor(newColor2);

    changeColor2.apply(o);
    assertEquals(newColor2, o.getColor());
  }

  /**
   * Test Move.
   */
  @Test
  public void testMove() {
    // move rectangle
    Rectangle r = new Rectangle(new Point(100,100),new Color(1,0,0),50,100,"R");
    Move move = new Move(0,200);

    move.apply(r);
    assertEquals(0, r.getPosition().getX());
    assertEquals(200, r.getPosition().getY());

    // move oval
    Oval o = new Oval(new Point(0,0),new Color(0,1,0),40,20,"O");
    Move move1 = new Move(100,200);

    move1.apply(o);
    assertEquals(100, o.getPosition().getX());
    assertEquals(200, o.getPosition().getY());
  }

  /**
   * Test Resize.
   */
  @Test
  public void testResize() {
    // resize rectangle
    Rectangle r = new Rectangle(new Point(100,100),new Color(1,0,0),200,400,"R");
    Resize resize1 = new Resize(50, 40);
    resize1.apply(r);

    assertEquals(100.0, r.getWidth());
    assertEquals(160.0, r.getHeight());

    // resize oval
    Oval o = new Oval(new Point(0,0),new Color(0,1,0),60,30,"O");
    Resize resize2 = new Resize(6,60);
    resize2.apply(o);

    assertEquals(6, o.getXRadius());
    assertEquals(60, o.getYRadius());
  }

  /**
   * test Invalid Resize.
   */
  @Test
  public void testInvalidResize() {
    // data equals to 0
    Rectangle r = new Rectangle(new Point(100,100),new Color(1,0,0),200,400,"R");
    assertThrows(IllegalArgumentException.class, () -> {
      new Resize(0, 100).apply(r);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Resize(100, 0).apply(r);
    });

    // negative data
    Oval o = new Oval(new Point(0,0),new Color(0,1,0),60,30,"O");
    assertThrows(IllegalArgumentException.class, () -> {
      new Resize(-100, 30).apply(o);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Resize(60, -100).apply(o);
    });
  }
}
