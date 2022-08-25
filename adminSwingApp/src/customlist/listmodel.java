/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customlist;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author Ananthu
 */
@SuppressWarnings("serial")
public class listmodel extends DefaultListModel{
    @SuppressWarnings("unchecked")
    public listmodel(){
            itemmodel sample1 = new itemmodel("Name","info",
                    new ImageIcon(getClass().getResource("/Icon 1/Icon 1/123456.png")));
            itemmodel sample2 = new itemmodel("Name","info",
                    new ImageIcon(getClass().getResource("/Icon 1/Icon 1/123456.png")));
            itemmodel sample3 = new itemmodel("Name","info",
                    new ImageIcon(getClass().getResource("/Icon 1/Icon 1/123456.png")));
            itemmodel sample4 = new itemmodel("Name","info",
                    new ImageIcon(getClass().getResource("/Icon 1/Icon 1/123456.png")));
            itemmodel sample5 = new itemmodel("Name","info",
                    new ImageIcon(getClass().getResource("/Icon 1/Icon 1/123456.png")));
            itemmodel sample6 = new itemmodel("Name","info",
                    new ImageIcon(getClass().getResource("/Icon 1/Icon 1/123456.png")));
            itemmodel sample7 = new itemmodel("Name","info",
                    new ImageIcon(getClass().getResource("/Icon 1/Icon 1/123456.png")));
            itemmodel sample8 = new itemmodel("Name","info",
                    new ImageIcon(getClass().getResource("/Icon 1/Icon 1/123456.png")));
            itemmodel sample9 = new itemmodel("Name","info",
                    new ImageIcon(getClass().getResource("/Icon 1/Icon 1/123456.png")));
            itemmodel sample10 = new itemmodel("Name","info",
                    new ImageIcon(getClass().getResource("/Icon 1/Icon 1/123456.png")));
            itemmodel sample11 = new itemmodel("Name","info",
                    new ImageIcon(getClass().getResource("/Icon 1/Icon 1/123456.png")));
            addElement(sample1);
            addElement(sample2);
            addElement(sample3);
            addElement(sample4);
            addElement(sample5);
            addElement(sample6);
            addElement(sample7);
            addElement(sample8);
            addElement(sample9);
            addElement(sample10);
            addElement(sample11);
    }
}
