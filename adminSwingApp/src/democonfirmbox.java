/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/****************************** SPLASH SCREEN ********************************************/

//import /*FirebaseInit*/.setDB;

//import javax.media.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

//import static FirebaseInit.firebaseinit.firebaseinit;

/**
 *
 * @author Ananthu
 */
public class democonfirmbox extends javax.swing.JFrame{

    /** Creates new form java */
    public democonfirmbox() throws InterruptedException {
        initComponents();
        prepareScreen();
    }

    private void prepareScreen() throws InterruptedException {
        for(int i=0;i<100;i++){
//            if(i==0){
//                todo :create media player
//                Player mediaPlayer=Manager.createRealizedPlayer(new MediaLocator(welcome.getClass().g(welcome.getClass().getResource("/welcome.avi"))));

//                Component video=mediaPlayer.getVisualComponent();
//
////                Component control=mediaPlayer.getControlPanelComponent();
//                mediaPlayer.start();
//            }
//            if(i==0){
////                welcome.backgroundlabel.setIcon(new javax.swing.ImageIcon(welcome.class.getResource("/intro.gif")));
//                Player mediaPlayer= null;
////                MP3Player mp3Player = null;
//                try {
//                    mediaPlayer = Manager.createRealizedPlayer(new MediaLocator(welcome.getClass().getResource("/welcome.avi").toURI().toURL()));
//                } catch (IOException | NoPlayerException | CannotRealizeException | URISyntaxException e) {
//                    System.out.println("******************** Exception *****************");
//                    e.printStackTrace();
//                }
//
//                assert mediaPlayer != null;
//                Component video=mediaPlayer.getVisualComponent();
//                video.setBounds(0,0,welcome.getWidth(),welcome.getHeight());
//                welcome.add(video);
//
////                Component control=mediaPlayer.getControlPanelComponent();
//                mediaPlayer.start();
//                Thread.sleep(7000);
//                video.setVisible(false);
//            }
            if(i==0){
//                backgroundlabel.setIcon(new javax.swing.ImageIcon(democonfirmbox.class.getResource("/symbol1.gif")));
                loadinglabel.setText("Loading....");
                percentagelabel.setText("0 %");
                jProgressBar.setValue(0);
                Thread.sleep(800);
            }

            if(i==10){
                loadinglabel.setText("Connecting to database");
                checkInternetCon();
                percentagelabel.setText("10 %");
                jProgressBar.setValue(10);
                Thread.sleep(500);
            }
//                Thread.currentThread().wait();

            if(i==30){
                loadinglabel.setText("Fetching data......");
                percentagelabel.setText("30 %");
                jProgressBar.setValue(30);
                try {
//                    firebaseinit();
//                    setDB.getInstance();
//                    JOptionPane.showMessageDialog(null,"Firebase intialization sucess");
                } catch (Exception ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null,"Firebase intialization failed");
                }
                Thread.sleep(2000);
            }

            if(i==50){
                percentagelabel.setText("50 %");
                jProgressBar.setValue(50);
                Thread.sleep(400);
            }

            if(i==80){
                loadinglabel.setText("Launching Application....");
                percentagelabel.setText("80 %");
                jProgressBar.setValue(80);
                Thread.sleep(1000);
            }

            if(i==99){
                percentagelabel.setText("100 %");
                jProgressBar.setValue(100);
                Thread.sleep(200);
            }

        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar = new javax.swing.JProgressBar();
        percentagelabel = new javax.swing.JLabel();
        loadinglabel = new javax.swing.JLabel();
        backgroundlabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(750, 460));
        setUndecorated(true);
        setLocation(new java.awt.Point(320, 180));
        getContentPane().setLayout(null);
        getContentPane().add(jProgressBar);
        jProgressBar.setBounds(0, 420, 750, 5);

        percentagelabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        percentagelabel.setForeground(new java.awt.Color(204, 255, 204));
        percentagelabel.setText("0 %");
        getContentPane().add(percentagelabel);
        percentagelabel.setBounds(705, 400, 45, 17);

        loadinglabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loadinglabel.setForeground(new java.awt.Color(204, 255, 204));
        loadinglabel.setText("Loading .......");
        getContentPane().add(loadinglabel);
        loadinglabel.setBounds(10, 400, 180, 17);

//        backgroundlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/symbol1.gif"))); // NOI18N
        backgroundlabel.setText("");
        getContentPane().add(backgroundlabel);
        backgroundlabel.setBounds(0, 0, 750, 460);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        democonfirmbox demo = new democonfirmbox();
        login login = new login();
        demo.setVisible(true);

        login.setVisible(true);
        demo.dispose();

    }

    private void checkInternetCon() {
        Socket socket = new Socket();
//        InetSocketAddress("siteaddress",portnumber)
        InetSocketAddress addr = new InetSocketAddress("www.google.com",80);
        try{
//         socket.connect("address",no of packets)
            socket.connect(addr,3000);
        }
        catch (Exception e){
            loadinglabel.setText("database connection failed");
            JOptionPane.showMessageDialog(null,"Check internet connection and try again...");
            checkInternetCon();
        }
        finally {
            try { socket.close(); }
            catch (Exception e){ }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundlabel;
    private javax.swing.JLabel percentagelabel;
    private javax.swing.JLabel loadinglabel;
    private javax.swing.JProgressBar jProgressBar;
    // End of variables declaration//GEN-END:variables

}
