package com.textbasedgame.GUI;

import java.net.URL;

import javax.swing.ImageIcon;

public class pictureLoader {

    private final String IMAGES_PATH = "/images/";

    public ImageIcon getImage(int imageID){
        URL imageUrl;
        switch(imageID){
            case 0:
                imageUrl = getClass().getResource(IMAGES_PATH + "Shop.PNG");
                return new ImageIcon(imageUrl);
            case 4:
                imageUrl = getClass().getResource(IMAGES_PATH+"Library.PNG");
                return new ImageIcon(imageUrl);
        }
        return null;
    }
}
