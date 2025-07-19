package com.textbasedgame.monsters;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.world;

public  class goblin extends monster {
    public goblin(){
        super.setName("Goblin");
        super.setStrength(TrekkerMath.randomInt(7, 1));
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(1.34, .7) * (player.playerLevel + world.stageNum + 10)));
        super.setSpeed(monsterCreator.fastMonsterSpeed(mLevel));
    }
    @Override
    public String attackString(){
        return "stabs your toes with a toothpick sized knife";
    }
}