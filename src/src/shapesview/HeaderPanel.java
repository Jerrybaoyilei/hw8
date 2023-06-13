package shapesview;

import java.awt.*;
import javax.swing.*;

/**
 * Concrete JPanel class representing the header panel.
 */
public class HeaderPanel extends JPanel implements IHeaderPanel {

  /**
   * Constructor for the HeaderPanel class.
   * @param id           the snapshot's id as a string
   * @param description  the snapshot's description
   */
  public HeaderPanel(Color background, String id, String description) {

    // set the panel background color to pink
    this.setBackground(background);

    // add the snapshot's id and description as labels to the HeaderPanel
    addIdLabel(id);

    // add description to the HeaderPanel if the description is not empty
    addDescriptionLabel(description);
  }

  /**
   * Add the snapshot's id as labels to the HeaderPanel.
   * @param id  snapshot's id
   */
  @Override
  public void addIdLabel(String id) {
    JLabel idLabel = new JLabel(id);
    this.setLayout(new GridLayout(1, 1));
    this.add(idLabel);
  }

  /**
   * Add description to the HeaderPanel if the description is not empty.
   * @param description  snapshot's description
   */
  @Override
  public void addDescriptionLabel(String description) {
    // add description to the HeaderPanel if the description is not empty
    if (!description.equals("")) {
      JLabel descriptionLabel = new JLabel(description);
      this.setLayout(new GridLayout(2, 1));
      this.add(descriptionLabel);
    }
  }


}
