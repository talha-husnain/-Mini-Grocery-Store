import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    // Invoke the GUI application on the event dispatch thread
    SwingUtilities.invokeLater(GrocerySalesApp::new);
  }
}
