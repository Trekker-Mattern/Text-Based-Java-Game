package com.textbasedgame.world.rooms;

import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.google.errorprone.annotations.ForOverride;
import com.textbasedgame.GUI.gui;

public abstract class Room {

    public abstract imageIDs getRoomID();
    
    /**
     * This method is called when the player enters the room. It sets the room's image in the GUI.
     * Subclasses MUST override this method to provide additional behavior when the room is opened.
     * SUBCLASSES MUST CALL super.openRoom() to ensure the room's image is set in the GUI.
     */
    @ForOverride
    public void openRoom(){
        gui.setImage(this.getRoomID());
        gui.setImage(this.getRoomID());
    };
    
}
