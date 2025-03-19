package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import album.PhotoAlbum;
import shapes.Color;
import shapes.Oval;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import views.GraphicalView;
import views.WebView;

/**
 * This class represents a Controller.
 */
public class Controller {
  private PhotoAlbum model;
  private GraphicalView graphicalView;
  private WebView webView;
  private String viewType;
  private String outputFile;
  private HashMap<String, Consumer<String []>> commandMap;

  /**
   * Instantiates a new Controller, initializes the command map and parses the arguments.
   *
   * @param args the arguments
   */
  public Controller(String[] args) {
    model = new PhotoAlbum();
    initCommandMap();
    parseArguments(args);
  }

  private void initCommandMap() {
    // initializes  the command map with command types and their corresponding actions
    commandMap = new HashMap<>();
    commandMap.put("shape", this::createShape);
    commandMap.put("remove", this::removeShape);
    commandMap.put("move", this:: moveShape);
    commandMap.put("color", this::changeColor);
    commandMap.put("resize", this::resizeShape);
    commandMap.put("snapshot", this::takeSnapshot);
  }

  private void parseArguments(String[] args) {
    // parses the command-line arguments to determine the input file, view type and output file
    String filename = null;
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          filename = args[++i];
          break;
        case "-v":
        case "-view":
          viewType = args[++i];
          break;
        case "-out":
          outputFile = args[++i];
          break;
      }
    }
    processCommand(filename);
  }

  private void processCommand(String filename) {
    // processes commands from the input file
    try {
      List<String> lines = Files.lines(Paths.get(filename)).collect(Collectors.toList());
      for (String line : lines) {
        if (line.startsWith("#") || line.trim().isEmpty()) {
          continue; // skip comments and empty lines
        }
        executeCommand(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void executeCommand(String line) {
    // executes the corresponding action defines in the command map
    String[] tokens = line.trim().split("\\s+");
    if (tokens.length < 1) { // invalid command format
      System.out.println("Unknown or incomplete command: " + line);
      return;
    }

    String commandType = tokens[0].toLowerCase();
    Consumer<String[]> command = commandMap.get(commandType);
    if (command != null) {
      command.accept(tokens);
    } else {
      System.out.println("Unknown command: " + line);
    }
  }

  private void createShape(String[] tokens) {
    // create a new shape
    if (tokens.length < 10) {
      System.out.println("Invalid shape creation command: " + String.join(" ", tokens));
      return;
    }

    String name = tokens[1];
    String shapeType = tokens[2];
    int x = Integer.parseInt(tokens[3]);
    int y = Integer.parseInt(tokens[4]);
    int r = Integer.parseInt(tokens[7]);
    int g = Integer.parseInt(tokens[8]);
    int b = Integer.parseInt(tokens[9]);

    Shape shape;
    try {
      switch (shapeType.toLowerCase()) {
        case "rectangle":
          int width = Integer.parseInt(tokens[5]);
          int height = Integer.parseInt(tokens[6]);
          shape = new Rectangle(new Point(x, y), new Color(r, g, b), width, height, name);
          break;
        case "oval":
          int xRadius = Integer.parseInt(tokens[5]);
          int yRadius = Integer.parseInt(tokens[6]);
          shape = new Oval(new Point(x, y), new Color(r, g, b), xRadius, yRadius, name);
          break;
        default:
          System.out.println("Unknown shape type: " + shapeType);
          return;
      }
      model.addShape(shape);
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }

  private void moveShape(String[] tokens) {
    // move the shape to a new position
    if (tokens.length < 4) {
      System.out.println("Invalid move command: " + String.join(" ", tokens));
      return;
    }

    String name = tokens[1];
    int x = Integer.parseInt(tokens[2]);
    int y = Integer.parseInt(tokens[3]);

    for (Shape shape : model.getShapes()) {
      if (shape.getName().equals(name)) {
        shape.getPosition().setX(x);
        shape.getPosition().setY(y);
        return;
      }
    }
    System.out.println("Shape not found: " + name);
  }

  private void changeColor(String[] tokens) {
    // change the color of the shape
    if (tokens.length < 5) {
      System.out.println("Invalid color change command: " + String.join(" ", tokens));
      return;
    }

    String name = tokens[1];
    int r = Integer.parseInt(tokens[2]);
    int g = Integer.parseInt(tokens[3]);
    int b = Integer.parseInt(tokens[4]);

    for (Shape shape : model.getShapes()) {
      if (shape.getName().equals(name)) {
        shape.setColor(new Color(r,g,b));
        return;
      }
    }
    System.out.println("Shape not found: " + name);
  }

  private void resizeShape(String[] tokens) {
    // resize the shape
    if (tokens.length < 4) {
      System.out.println("Invalid resize command: " + String.join(" ", tokens));
      return;
    }

    String name = tokens[1];
    int xData = Integer.parseInt(tokens[2]);
    int yData = Integer.parseInt(tokens[3]);

    for (Shape shape : model.getShapes()) {
      if (shape.getName().equals(name)) {
        if (shape instanceof Rectangle) {
          Rectangle r = (Rectangle) shape;
          r.setWidth(xData);
          r.setHeight(yData);
        } else if (shape instanceof Oval) {
          Oval o = (Oval) shape;
          o.setXRadius(xData);
          o.setYRadius(yData);
        }
        return;
      }
    }
    System.out.println("Shape not found: " + name);
  }

  private void removeShape(String[] tokens) {
    // remove the shape
    if (tokens.length < 2) {
      System.out.println("Invalid remove command: " + String.join(" ", tokens));
      return;
    }

    String name = tokens[1];

    for (Shape shape : model.getShapes()) {
      if (shape.getName().equals(name)) {
        model.removeShape(shape);
        return;
      }
    }
    System.out.println("Shape not found: " + name);
  }

  private void takeSnapshot(String[] tokens) {
    // take a snapshot for the current status of the shapes
    if (viewType != null) {
      if (viewType.equalsIgnoreCase("web")) {
        if (webView == null) {
          createWebView();
          if (webView != null) {
            model.addListener(webView);
          } else {
            System.err.println("Failed to create WebView");
            return;
          }
        }
      } else if (viewType.equalsIgnoreCase("graphical")) {
        if (graphicalView == null) {
          createGraphicalView();
          if (graphicalView != null) {
            model.addListener(graphicalView);

          } else {
            System.err.println("GraphicalView not yet created.");
            return;
          }
        }
      } else {
        System.err.println("Invalid view type: " + viewType);
        return;
      }
    }

    String description = "";
    if (tokens.length > 1) {
      description = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
    }
    model.takeSnapshot(description);
  }


  private void createWebView() {
    // create instance of the WebView class
    webView = new WebView(model, outputFile);
  }

  private void createGraphicalView() {
    // create instance of the GraphicalView class
    if (model != null) {
      try {
        graphicalView = new GraphicalView(model);
      } catch (Exception e) {
        System.err.println("Error creating graphical view: " + e.getMessage());
        e.printStackTrace();
      }
    } else {
      System.err.println("PhotoAlbum model not initialized");
    }
  }

  /**
   * Starts the application by setting up the selected view and adding listeners to the model.
   */
  public void go() {
    switch (viewType.toLowerCase()) {
      case "graphical":
        if (graphicalView != null) {
          model.addListener(graphicalView);
          graphicalView.setVisible(true);
        }
        break;
      case "web":
        if (webView == null) {
          createWebView();
          model.addListener(webView);
        }
        break;
      default:
        System.out.println("Invalid view type: " + viewType);
        break;
    }
    if (webView != null) {
      webView.close();
    }
  }
}
