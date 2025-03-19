package album;

import controller.Controller;

/**
 * The PhotoAlbumMain.
 */
public class PhotoAlbumMain {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    System.out.println("Args length: " + args.length);
    if (args.length < 4) {
      System.err.println("Usage: MyProgram -in <input-file> -view <type-of-view> "
              + "[-out <output-file>]");
      System.exit(1);
    }

    boolean inFileSpecified = false;
    boolean viewSpecified = false;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in") && (i + 1) < args.length) {
        inFileSpecified = true;
        i++; // skip next element
      } else if ((args[i].equals("-view") || args[i].equals("-v")) && (i + 1) < args.length) {
        viewSpecified = true;
        i++; // skip next element
      }
    }

    // validate mandatory arguments
    if (!inFileSpecified || !viewSpecified) {
      System.err.println("Input file and view type are mandatory arguments.");
      System.exit(1);
    }

    Controller controller = new Controller(args);
    controller.go();
  }
}
