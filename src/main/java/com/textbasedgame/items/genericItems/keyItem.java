package com.textbasedgame.items.genericItems;

import com.google.errorprone.annotations.ForOverride;
import com.textbasedgame.items.item;
import com.textbasedgame.playerFiles.player;

public abstract class keyItem extends item {
   	@ForOverride 
	public void Use(){
		player.keyItemInventory.remove(this);
	};
}
