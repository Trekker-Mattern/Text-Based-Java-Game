package com.textbasedgame.monsters;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.world;

public  class troll extends monster {
    public troll(){
        super.setName("Troll");
        super.setStrength(TrekkerMath.randomInt(7, 3));
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(3.34, 2) * (player.playerLevel + world.AREANUM + 4)));
        super.setSpeed(monsterCreator.slowMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "whacks you with a large club";
    }
}