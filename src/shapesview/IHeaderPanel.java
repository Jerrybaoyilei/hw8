package shapesview;

/**
 * Interface for HeaderPanel.
 */
public interface IHeaderPanel {

  /**
   * Add the snapshot's id as labels to the HeaderPanel.
   * @param id  snapshot's id
   */
  void addIdLabel(String id);

  /**
   * Add description to the HeaderPanel if the description is not empty.
   * @param description  snapshot's description
   */
  void addDescriptionLabel(String description);
}
