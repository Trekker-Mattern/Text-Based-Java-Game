package monsters;
import world.world;
import playerFiles.player;
import util.TrekkerMath;

public  class goblin extends monster {
    public goblin(){
        super.setName("Goblin");
        super.setStrength(TrekkerMath.randomInt(7, 1));
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(1.34, .7) * (player.playerLevel + world.stageNum + 10)));
        super.setSpeed(monsterCreater.fastMonsterSpeed());
    }
    @Override
    public String attackString(){
        return "stabs your toes with a toothpick sized knife";
    }
}