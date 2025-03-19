package views;

import album.PhotoAlbum;
import album.PhotoAlbumListener;
import album.Snapshot;
import shapes.Shape;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * This class represents a Graphical view extending JFrame and
 * implementing the PhotoAlbumListener interface.
 */
public class GraphicalView extends JFrame implements PhotoAlbumListener {
  private PhotoAlbum photoAlbum;
  private DrawPanel drawPanel;
  private int currSnapshotIndex;
  private String snapshotDescription;
  private JLabel snapshotLabel;
  private JButton prevButton;
  private JButton nextButton;
  private JButton selectButton;
  private JButton quitButton;

  /**
   * Takes photoAlbum as parameter and instantiates a new Graphical view.
   *
   * @param photoAlbum the photo album
   */
  public GraphicalView(PhotoAlbum photoAlbum) {
    this.photoAlbum = photoAlbum;
    this.photoAlbum.addListener(this);
    setTitle("CS5004 Photo Album");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    // initialize snapshot info label
    snapshotLabel = new JLabel();
    add(snapshotLabel, BorderLayout.NORTH);

    // initialize draw panel with the shapes from the first snapshot
    Snapshot currSnapshot = photoAlbum.getCurrentSnapshot();
    if (currSnapshot != null) {
      List<Shape> shapes = photoAlbum.getCurrentSnapshot().getShapes();
      drawPanel = new DrawPanel(shapes);
      add(new JScrollPane(drawPanel), BorderLayout.CENTER);
    } else {
      drawPanel = new DrawPanel(new ArrayList<>());
      add(new JScrollPane(drawPanel), BorderLayout.CENTER);
    }

    // initialize control buttons
    JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
    initButtons();
    buttonPanel.add(prevButton);
    buttonPanel.add(selectButton);
    buttonPanel.add(nextButton);
    buttonPanel.add(quitButton);
    add(buttonPanel, BorderLayout.SOUTH);

    if (currSnapshot != null) {
      currSnapshotIndex = photoAlbum.getSnapshotIDs().indexOf(currSnapshot.getID());
    } else {
      currSnapshotIndex = 0;
    }
    // set initial snapshot info
    updateSnapshotInfo();

    pack();
    setSize(1000, 1000);
    setVisible(true);
  }

  private void initButtons() {
    // initialize control buttons and add action listeners to each button
    nextButton = new JButton(">> Next >>");
    prevButton = new JButton("<< Prev <<");
    selectButton = new JButton("^^ Select ^^");
    quitButton = new JButton("xx Quit xx");

    nextButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showNextSnapshot();
      }
    });

    prevButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showPreviousSnapshot();
      }
    });

    selectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        jumpToSnapshot();
      }
    });

    quitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        quitPhotoAlbum();
      }
    });
  }

  private void updateSnapshotInfo() {
    // retrieves the current snapshot from the photo album,
    // updates and sets the snapshot info displayed in the label
    Snapshot currSnapshot = photoAlbum.getSnapshot(currSnapshotIndex);
    String snapshotID = "N/A";
    String snapshotDescription = "N/A";
    if (currSnapshot != null) {
      snapshotID = currSnapshot.getID();
      snapshotDescription = currSnapshot.getDescription();
    }
    String labelText = "<html><body>Snapshot ID: " + snapshotID + "<br>Description: "
            + snapshotDescription + "</body></html>";
    snapshotLabel.setText(labelText);
  }

  private void showPreviousSnapshot() {
    // display the previous snapshot in the photo album
    if (currSnapshotIndex > 0) {
      currSnapshotIndex--;
      updateSnapshotInfo();
      updateDrawPanel();
    } else {
      JOptionPane.showMessageDialog(this, "No previous snapshot available.");
    }
  }

  private void showNextSnapshot() {
    // display the next snapshot in the photo album
    List<String> snapshotIDs = photoAlbum.getSnapshotIDs();
    if (currSnapshotIndex < snapshotIDs.size() - 1) {
      currSnapshotIndex++;
      updateSnapshotInfo();
      updateDrawPanel();
    } else {
      JOptionPane.showMessageDialog(this, "End of the photo album. "
              + "No snapshots to show beyond this one.");
    }
  }

  private void jumpToSnapshot() {
    // display a dialog box with a dropdown menu of snapshot IDs
    // jump to the specified snapshot that the user select
    List<String> snapshotIDs = photoAlbum.getSnapshotIDs();
    String[] choices = snapshotIDs.toArray(new String[0]);
    String input = (String) JOptionPane.showInputDialog(null, "Choose", "Jump to Snapshot",
            JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
    if (input != null) {
      currSnapshotIndex = snapshotIDs.indexOf(input);
      updateSnapshotInfo();
      updateDrawPanel();
    }
  }

  private void quitPhotoAlbum() {
    // quit the graphical view, close the application
    dispose();
  }

  private void updateDrawPanel() {
    // retrieve the shapes from the current snapshot
    // update the draw panel with shapes from the current snapshot
    Snapshot currSnapshot = photoAlbum.getSnapshot(currSnapshotIndex);
    if (currSnapshot != null) {
      List<Shape> shapes = currSnapshot.getShapes();
      drawPanel.setShapes(shapes);
      drawPanel.repaint();
    }
    updateSnapshotInfo();
  }

  /**
   * Setter method for the description.
   *
   * @param description the description of the snapshot
   */
  public void setDescription(String description) {
    this.snapshotDescription = description;
  }

  @Override
  public void snapshotTaken(Snapshot snapshot) {
    if (snapshot != null) {
      setDescription(snapshot.getDescription()); // update the description of the snapshot
      currSnapshotIndex = photoAlbum.getSnapshotIDs().indexOf(snapshot.getID());
      updateDrawPanel();
    }
  }
}
