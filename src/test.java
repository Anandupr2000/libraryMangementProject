
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;

public class test extends JApplet {
    public void init() {
        setBackground(Color.gray);
    }
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(5, 5, Color.cyan, 220,
                220,Color.red);
        g2d.setPaint(gp);
        g2d.fill(new RoundRectangle2D.Double(5, 5, 220, 220, 10, 10));
        g2d.setPaint(Color.black);
    }
    public static void main(String s[]) {
        JFrame frame = new JFrame("Paint Round Rectangle");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        JApplet applet = new test();
        frame.getContentPane().add("Center", applet);
        applet.init();
        frame.setSize(new Dimension(300, 270));
        frame.show();
    }
}