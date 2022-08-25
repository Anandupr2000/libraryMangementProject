
import customlist.itemspainter;
import customlist.listmodel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.CellRendererPane;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ananthu
 */
public class demolist extends javax.swing.JFrame {

    private boolean checked = true;
    /**
     * Creates new form demolist
     */
    public demolist() {
        initComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        setSize(800,713);
        setLocationRelativeTo(null);

        
//        setBounds(100,100,500,750);
//        Container controlhost = getContentPane();
//        controlhost.setLayout(new BorderLayout());
////        controlhost.setLayout(new AbsoluteLayout());
//        JPanel tabpanel = new JPanel();
//        tabpanel.setBackground(Color.yellow);
//        
//        JPanel switchPanel = new JPanel();
//        tabpanel.setBackground(Color.BLUE);

//        controlhost.add(tabpanel,BorderLayout.PAGE_START);
//        controlhost.add(switchPanel,BorderLayout.PAGE_END);
////                controlhost.setLayout(new FlowLayout());
//// controlhost.setLayout(FlowLayout.TRAILING);

//        listmodel model = new listmodel();
//        itemspainter painter =new itemspainter();
//        
//        @SuppressWarnings("unchecked")
//        JList list = new JList(model);
//        list.setCellRenderer(painter);
//        list.setVisibleRowCount(4);
//        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        list.setBorder(new EmptyBorder(10,10,10,10));
//        System.out.println("list.getComponentCount() => "+list.getComponentCount());// => 1
//
//        list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent lse) {
                
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                JCheckBox checkBox =  (JCheckBox) painter.getComponent(1);
//                JLabel label = (JLabel) painter.getComponent(2);
//                Boolean temp = checkBox.isSelected();
//                System.out.println("value of checkbox => "+temp);

////                checkBox.addItemListener(new ItemListener() {
////                    @Override
////                    public void itemStateChanged(ItemEvent ie) {
//////                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////                        checkBox.setSelected(true);
////                    }
////                });
////                CellRendererPane pane =  (CellRendererPane) list.getCellRenderer();
////                pane.get
////                if(temp){
//                checkBox.setSelected(true);
////                }else{
                    
////                }
////                if(checkBox.isSelected()){
//////                System.out.println("painter.getComponent(1) => "+painter.getComponent(1));
////                checkBox.setSelected(false);
////                }
//            }
//            
//        });
//        
//        JScrollPane jsp = new JScrollPane(list);
//        jsp.setBorder(new javax.swing.border.LineBorder(null, 2 ));
//        jsp.setBorder(new EmptyBorder(0,0,0,5 ));
//        jsp.setSize(userlistPanel.getWidth(), userlistPanel.getHeight());
//        userlistPanel.add(jsp);
        
//        jsp.setBorder(new BorderFactory.createEmptyBorder(null,2,true));
//        controlhost.add(tabpanel,BorderLayout.WEST);
//        controlhost.add(jsp,BorderLayout.CENTER);
JPanel jPanel =new JPanel(new GridLayout(6, 1));
        
        jPanel.setSize(userlistPanel.getWidth(),userlistPanel.getHeight());
//        jPanel.setBorder(new EmptyBorder(5,5,5,10 ));
//        jPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5,5,5,10 ), new EmptyBorder(5,5,5,10 )));
        JScrollPane jsp = new JScrollPane(jPanel);
//        jsp.setBorder(new javax.swing.border.LineBorder(null, 2 ));
        jsp.setSize(userlistPanel.getWidth(), userlistPanel.getHeight());
//        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        jsp.setViewport();
//        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        jsp.setBorder(new EmptyBorder(0,0,0,5));
        jsp.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK,1,true), new EmptyBorder(0,0,10,5)));
        itemspainter item1 = new itemspainter();
        itemspainter item2 = new itemspainter(); 
//        item1.setSize(getContentPane().getWidth(),100);
//        item2.setSize(getContentPane().getWidth(),100);
        item1.setLocation(0, 0);
        item2.setLocation(0,70);
        jPanel.add(item1);
        jPanel.add(item2);
        
        userlistPanel.add(jsp);
        userlistPanel.setBorder(new LineBorder(Color.BLACK,1,true));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainpanel = new javax.swing.JPanel();
        tabpanel = new javax.swing.JPanel();
        sendToAllBtn = new javax.swing.JButton();
        sendBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        userlistPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainpanel.setMinimumSize(new java.awt.Dimension(763, 713));
        mainpanel.setPreferredSize(new java.awt.Dimension(763, 713));
        mainpanel.setLayout(new java.awt.GridLayout(1, 2));

        tabpanel.setBackground(new java.awt.Color(51, 51, 255));

        sendToAllBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sendToAllBtn.setText("Send to all");
        sendToAllBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        sendToAllBtn.setMinimumSize(new java.awt.Dimension(150, 40));
        sendToAllBtn.setPreferredSize(new java.awt.Dimension(100, 40));

        sendBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sendBtn.setText("Send");
        sendBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        sendBtn.setMinimumSize(new java.awt.Dimension(150, 40));
        sendBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBtnActionPerformed(evt);
            }
        });

        messageTextArea.setColumns(20);
        messageTextArea.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        messageTextArea.setLineWrap(true);
        messageTextArea.setRows(5);
        messageTextArea.setText("Type your message here...");
        messageTextArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                messageTextAreaFocusGained(evt);
            }
        });
        messageTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                messageTextAreaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(messageTextArea);

        jLabel1.setFont(new java.awt.Font("Android Insomnia", 2, 36)); // NOI18N
        jLabel1.setText("Send Notification");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Compose Message : ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("*  select user from the list to send");

        javax.swing.GroupLayout tabpanelLayout = new javax.swing.GroupLayout(tabpanel);
        tabpanel.setLayout(tabpanelLayout);
        tabpanelLayout.setHorizontalGroup(
            tabpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabpanelLayout.createSequentialGroup()
                .addGroup(tabpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabpanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tabpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabpanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(sendToAllBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(tabpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabpanelLayout.setVerticalGroup(
            tabpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabpanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tabpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendToAllBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        mainpanel.add(tabpanel);

        jPanel1.setBackground(new java.awt.Color(102, 255, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(509, 750));

        javax.swing.GroupLayout userlistPanelLayout = new javax.swing.GroupLayout(userlistPanel);
        userlistPanel.setLayout(userlistPanelLayout);
        userlistPanelLayout.setHorizontalGroup(
            userlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        userlistPanelLayout.setVerticalGroup(
            userlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        jLabel4.setText("Select user");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 216, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(userlistPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userlistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        mainpanel.add(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 110, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void messageTextAreaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_messageTextAreaFocusGained
        // TODO add your handling code here:
//        messageTextArea.setEnabled(true);
    }//GEN-LAST:event_messageTextAreaFocusGained

    private void messageTextAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageTextAreaKeyTyped
        // TODO add your handling code here:
        if(messageTextArea.getText().equals("Type your message here...")){
        String c = String.valueOf(evt.getKeyChar());
        messageTextArea.setText("");
//        messageTextArea.setText(c);
        }
    }//GEN-LAST:event_messageTextAreaKeyTyped

    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(demolist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(demolist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(demolist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(demolist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new demolist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JButton sendBtn;
    private javax.swing.JButton sendToAllBtn;
    private javax.swing.JPanel tabpanel;
    private javax.swing.JPanel userlistPanel;
    // End of variables declaration//GEN-END:variables
}
