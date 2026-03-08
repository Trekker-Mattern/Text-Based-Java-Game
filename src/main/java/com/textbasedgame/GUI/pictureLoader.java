package com.textbasedgame.GUI;

import java.net.URL;

import javax.swing.ImageIcon;

public class pictureLoader {

    private final String IMAGES_PATH = "/images/";
    public enum imageIDs{
        SHOP, LIBRARY, CAVE
    };

    public ImageIcon getImage(imageIDs imageID){
        URL imageUrl;
        switch(imageID){
            case SHOP:
                imageUrl = getClass().getResource(IMAGES_PATH + "Shop.PNG");
                return new ImageIcon(imageUrl);
            case LIBRARY:
                imageUrl = getClass().getResource(IMAGES_PATH+"Library.PNG");
                return new ImageIcon(imageUrl);
            case CAVE:
                imageUrl = getClass().getResource(IMAGES_PATH+"Cave_1.png");
                return new ImageIcon(imageUrl);
        }
        return null;
    }
}
