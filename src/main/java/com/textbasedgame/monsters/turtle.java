package com.textbasedgame.monsters;
import com.textbasedgame.items.consumableItems.genericPotion;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.playerFiles.player.buffTypes;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.world;

public class turtle extends monster{
    public turtle(){
        super.setName("Turtle");
        super.setStrength(TrekkerMath.randomInt(2, 1));
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(5.34, 4) * (world.stageNum + world.AREANUM + 4)));
        super.setSpeed(1);
    }
    @Override
    public String attackString(){
        return "slowly chomps down on your leg";
    }
    @Override
    public void onMonsterDeath(){
        player.addItemToPlayer(new genericPotion("Turtle Blood" ,buffTypes.ARMOR, 6, 5));
    }
}
