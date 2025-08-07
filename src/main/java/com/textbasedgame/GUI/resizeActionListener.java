package com.textbasedgame.GUI;

import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JLabel;

public class resizeActionListener extends ComponentAdapter {
    @Override
    public void componentResized(ComponentEvent e) {
        if(gui.imagePanel.getComponents().length > 0){
            Icon i = ((JLabel)(gui.imagePanel.getComponents()[0])).getIcon();
            ImageIcon imgIcn;
            if(i instanceof ImageIcon){
                imgIcn = (ImageIcon)i;
            }
            else{
                imgIcn = null;
            }
            ImageIcon imgIcon = new ImageIcon(imgIcn.getImage().getScaledInstance(gui.imagePanel.getWidth(), gui.imagePanel.getHeight(), Image.SCALE_DEFAULT));
    
            gui.imagePanel.removeAll();
    
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(imgIcon);
            gui.imagePanel.add(imageLabel);
            gui.imagePanel.revalidate();
            gui.imagePanel.repaint();
            gui.frame.revalidate();
            gui.frame.repaint();
        }
    }
}
