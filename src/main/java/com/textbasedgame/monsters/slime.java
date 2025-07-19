package com.textbasedgame.monsters;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.world;
public class slime extends monster{
    public slime(){
        super.setName("Slime");
        super.setStrength(TrekkerMath.randomInt(5, 1));
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(1.34, .7) * (player.playerLevel + world.AREANUM + 4)));

        //Fast
        super.setSpeed((int)(monsterCreator.fastMonsterSpeed(mLevel) * 0.9));
    }
    
    @Override
    public String attackString(){
        return "squishes menacingly";
    }
}
