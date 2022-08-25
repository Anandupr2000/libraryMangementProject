import java.awt.*;
import javax.swing.*;
import javax.swing.OverlayLayout;
public class overlylayoutdemo extends JFrame {
   public overlylayoutdemo() {
      setTitle("OverlayLayout Test");
      JPanel panel = new JPanel() {
         public boolean isOptimizedDrawingEnabled() {
            return false;
         }
      };
      LayoutManager overlay = new OverlayLayout(panel);
      panel.setLayout(overlay);
      JButton button = new JButton("Small");
//      button.setMaximumSize(new Dimension(75, 50));
//      button.setBackground(Color.white);
//      panel.add(button);
//      button = new JButton("Medium Btn");
//      button.setMaximumSize(new Dimension(125, 75));
//      button.setBackground(Color.lightGray);
//      panel.add(button);
//      button = new JButton("Large Button");
//      button.setMaximumSize(new Dimension(200, 100));
//      button.setBackground(Color.orange);
//      panel.add(button);
        backgroundFrame bg = new backgroundFrame();
        bg.setVisible(true);
        home home = new home();
        home.setVisible(true);
        home.setOpacity(0);
       panel.add(bg);
       panel.add(home);
      add(panel, BorderLayout.CENTER);
      setSize(new home().getSize());
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }  
   public static void main(String args[]) {
      new overlylayoutdemo();
   }
}