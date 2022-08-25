/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customlist;

import com.sun.jndi.toolkit.url.Uri;
import javax.swing.ImageIcon;

/**
 *
 * @author Ananthu
 */
public class itemmodel {
    String name,info;
    ImageIcon icon;

    public itemmodel() {
    }

    
    public itemmodel(String name, String info, ImageIcon icon) {
        this.name = name;
        this.info = info;
        this.icon = icon;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
}
