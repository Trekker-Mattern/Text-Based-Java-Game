package monsters;
import world.world;
import playerFiles.player;
import util.TrekkerMath;

public  class snake extends monster {
    public snake(){
        super.setName("Snake");

        
        super.setStrength(TrekkerMath.randomInt(2, 1)* (world.AREANUM +1));

        //Lowish Health
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(1.34, .7) * (player.playerLevel + world.AREANUM + 4)));

        //Fast
        super.setSpeed((int)(monsterCreator.fastMonsterSpeed(mLevel) * 1.5));
    }
    public String attackString(){
        return "bites you really hard";
    }
}