package album;

/**
 * The interface PhotoAlbumListener.
 */
public interface PhotoAlbumListener {

  /**
   * This method is called when a new snapshot is taken in the photo album.
   *
   * @param snapshot the snapshot
   */
  void snapshotTaken(Snapshot snapshot);
}
