package monsters;

import util.TrekkerMath;
import world.world;

public class rat extends monster{
    public rat(){
        super.setName("Rat");
        super.setStrength(TrekkerMath.randomInt(3, 1));
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(1.2, 1) * (world.stageNum + world.AREANUM + 4)));
        super.setSpeed(monsterCreator.fastMonsterSpeed());
    }
    @Override
    public String attackString(){
        return "bites down with its little teeth";
    }
}

