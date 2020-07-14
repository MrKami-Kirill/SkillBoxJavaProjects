import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {

  private JPanel mainPanel;
  private JPanel cards;
  private JPanel collapsePanel;
  private JPanel expandPanel;
  private JButton collapseButton;
  private JButton expandButton;
  private JTextArea surNameTextArea;
  private JTextArea nameTextArea;
  private JTextArea patronymicTextArea;
  private JTextArea fullNameTextArea;
  private CardLayout cardLayout;

  public MainForm() {
    cardLayout = (CardLayout) cards.getLayout();
    collapseButton.addActionListener(new Action() {
      @Override
      public Object getValue(String key) {
        return null;
      }

      @Override
      public void putValue(String key, Object value) {

      }

      @Override
      public boolean isEnabled() {
        return false;
      }

      @Override
      public void addPropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void removePropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void actionPerformed(ActionEvent e) {
        String fullName = collapse();
        if ((!surNameTextArea.getText().isEmpty()) && (!nameTextArea.getText().isEmpty())) {
          fullNameTextArea.setText(fullName);
          cardLayout.next(expandPanel.getParent());
        }
      }

      @Override
      public void setEnabled(boolean b) {

      }
    });
    expandButton.addActionListener(new Action() {
      @Override
      public Object getValue(String key) {
        return null;
      }

      @Override
      public void putValue(String key, Object value) {

      }

      @Override
      public boolean isEnabled() {
        return false;
      }

      @Override
      public void addPropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void removePropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void actionPerformed(ActionEvent e) {
        String[] fullNameArray = expand();
        if ((!fullNameArray[0].trim().isEmpty()) || (!fullNameArray[1].trim().isEmpty())) {
          surNameTextArea.setText(fullNameArray[0]);
          nameTextArea.setText(fullNameArray[1]);
          patronymicTextArea.setText(fullNameArray[2]);
          cardLayout.next(collapsePanel.getParent());
        }
      }

      @Override
      public void setEnabled(boolean b) {

      }
    });
  }

  public JPanel getMainPanel() {
    return mainPanel;
  }

  private String collapse() {
    if((surNameTextArea.getText().trim().isEmpty()) && (nameTextArea.getText().trim().isEmpty())) {
      alert("Фамилия и Имя не заполнены!");
      return null;
    }
    if(surNameTextArea.getText().trim().isEmpty()) {
      alert("Фамилия не заполнена!");
      return null;
    }
    if (nameTextArea.getText().trim().isEmpty()) {
      alert("Имя не заполнено!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    builder.append(surNameTextArea.getText().trim());
    builder.append(" ");
    builder.append(nameTextArea.getText().trim());
    builder.append(" ");
    builder.append(patronymicTextArea.getText().trim());
    return builder.toString();
  }

  private String[] expand() {
    String fullName = fullNameTextArea.getText().trim();
    if (fullName.isEmpty()) {
      alert("ФИО не заполнено!");
      return null;
    }
    if ((fullName.split("\\s").length) > 3 || (fullName.split("\\s").length < 2)) {
      alert("ФИО запонено не правильно!");
      return null;
    }
    return fullName.split("\\s");
  }

  private void alert(String message) {
    JOptionPane.showMessageDialog(
      new JFrame(), message, "Ошибка", JOptionPane.WARNING_MESSAGE);
  }
}
