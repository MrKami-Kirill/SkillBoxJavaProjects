import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setSize(600, 150);
    frame.setName("collapseExpandApplication");
    frame.setTitle("Приложение на Swing");


    frame.add(new MainForm().getMainPanel());

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
