package views;

import album.PhotoAlbum;
import album.PhotoAlbumListener;
import album.Snapshot;
import shapes.Oval;
import shapes.Rectangle;
import shapes.Shape;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class represents a WebView, implementing the PhotoAlbumListener interface.
 */
public class WebView implements PhotoAlbumListener {
  private static final String HTML_HEADER = "<!DOCTYPE html>\n<html>\n<head>\n"
          + "<title>Photo Album</title>\n</head>\n<body>\n";
  private static final String HTML_FOOTER = "</body>\n</html>";
  private PhotoAlbum model;
  private String outputFile;
  private FileWriter fileWriter;

  /**
   * Takes two parameters and instantiates a new Web view.
   *
   * @param model      the PhotoAlbum model
   * @param outputFile the output file
   */
  public WebView(PhotoAlbum model, String outputFile) {
    this.model = model;
    this.outputFile = outputFile;
    try {
      fileWriter = new FileWriter(outputFile);
      fileWriter.write(HTML_HEADER);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void snapshotTaken(Snapshot snapshot) {
    // writes the snapshotID, description and SVG markup to the HTML file using a FileWriter
    // when a snapshot is taken in the photo album
    try {
      fileWriter.write("<p> Snapshot ID: " + snapshot.getID() + "</p>\n");
      fileWriter.write("<p> Description: " + snapshot.getDescription() + "</p>\n");
      if (fileWriter != null) {
        String svgMarkup = generateSVGMarkup(snapshot);
        fileWriter.write(svgMarkup);
        fileWriter.flush();
      } else {
        System.out.println("Error: FileWriter is null or file is not open for writing.");
      }
    } catch (IOException e) {
      System.err.println("Error writing snapshot to HTML: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private String generateSVGMarkup(Snapshot snapshot) {
    // generates SVG markup for the shapes in the given snapshot
    StringBuilder svgMarkup = new StringBuilder();
    String snapshotID = snapshot.getID();
    svgMarkup.append("<svg id=\"").append(snapshotID).append("\" width=\"800\" height=\"800\">\n");

    List<Shape> shapes = snapshot.getShapes();
    for (Shape shape : shapes) {
      if (shape instanceof Rectangle) {
        Rectangle rectangle = (Rectangle) shape;
        svgMarkup.append("<rect x=\"").append(rectangle.getPosition().getX()).append("\" y=\"")
                .append(rectangle.getPosition().getY()) .append("\" width=\"")
                .append(rectangle.getWidth()).append("\" height=\"").append(rectangle.getHeight())
                .append("\" fill=\"rgb(").append(rectangle.getColor().getR()).append(",")
                .append(rectangle.getColor().getG()).append(",")
                .append(rectangle.getColor().getB()).append(")\"/>\n");
      } else if (shape instanceof Oval) {
        Oval oval = (Oval) shape;
        svgMarkup.append("<ellipse cx=\"").append(oval.getPosition().getX()).append("\" cy=\"")
                .append(oval.getPosition().getY()).append("\" rx=\"").append(oval.getXRadius())
                .append("\" ry=\"").append(oval.getYRadius()).append("\" fill=\"rgb(")
                .append(oval.getColor().getR()).append(",").append(oval.getColor().getG())
                .append(",").append(oval.getColor().getB()).append(")\"/>\n");
      }
    }
    svgMarkup.append("</svg>\n");
    return svgMarkup.toString();
  }

  /**
   * Close the FileWriter.
   */
  public void close() {
    try {
      fileWriter.write(HTML_FOOTER);
      fileWriter.close();
      System.out.println("HTML file closed successfully");
    } catch (IOException e) {
      System.err.println("Error closing HTML file: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
