
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

final public class FastPanelListDemo {


    private static JFrame window = null;
    private static FastPanelList panelList = null;


    public static void main(final String[] args) {

        SwingUtilities.invokeLater(() -> {

            setLookAndFeelDefault();

            panelList = new FastPanelList(FastPanelList.FPLOrientation.VERTICAL,
                    FastPanelListDemo::supplyPanel,
                    0.1,
                    0.95,
                    false,
                    80,
                    Integer.MAX_VALUE);
            final Container contentPane = panelList.container;
            contentPane.setPreferredSize(new Dimension(300, 800));
            contentPane.setBackground(Color.GRAY);

            window = new JFrame("FastPanelList demo");
            window.setContentPane(contentPane);
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);


            contentPane.addMouseMotionListener(new MouseAdapter() {

                @Override
                public void mouseMoved(final MouseEvent e) {

                    final JPanel itemUnderMouse = panelList.getItemUnderMouse(e);
                    if (itemUnderMouse != null) {
                        itemUnderMouse.setBackground(new Color((float) Math.random(),
                                (float) Math.random(),
                                (float) Math.random()));
                    }
                }
            });

        });
    }


    private static JPanel supplyPanel(final int panelIndex) { // Just supply something that extends JPanel. You can put as much data in as you want. E.g. "boolean isMouseHovering" etc.

        final JLabel label = new JLabel("panel " + panelIndex);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        final JButton button = new JButton("click me");
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(window,
                    "That was button " + panelIndex + ".",
                    "* CLICK *",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        final JPanel panel = new JPanel(new BorderLayout(0,
                0));
        panel.setBorder(BorderFactory.createEmptyBorder(10,
                10,
                10,
                10));
        panel.setBackground(new Color((float) Math.random(),
                (float) Math.random(),
                (float) Math.random()));
        panel.add(label, BorderLayout.CENTER);
        panel.add(button, BorderLayout.EAST);

        return panel;
    }


    private static void setLookAndFeelDefault() {

        setLookAndFeel("Windows",
                UIManager.getSystemLookAndFeelClassName(),
                UIManager.getCrossPlatformLookAndFeelClassName(),
                "Windows Classic",
                "Nimbus",
                "Metal",
                "CDE/Motif");
    }


    /**
     * @param intendedLAFIs ANYTHING, but ideally a LookAndFeel name or several. The first value that equalsIgnoreCase
     *                      an installed LookAndFeelInfo.getName() will be used.
     */
    private static void setLookAndFeel(final String... intendedLAFIs) {

        if (intendedLAFIs != null && intendedLAFIs.length > 0) {
            final UIManager.LookAndFeelInfo[] installedLAFIs = UIManager.getInstalledLookAndFeels();
            LAFILOOP:
            for (String intendedLAFI : intendedLAFIs) {
                for (final UIManager.LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
                    if (lafi.getName().equalsIgnoreCase(intendedLAFI)) {
                        try {
                            UIManager.setLookAndFeel(lafi.getClassName());
                            break LAFILOOP;
                        } catch (Exception e) {
                            continue LAFILOOP;
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("intendedLAFIs is null or empty.");
        }
    }
}