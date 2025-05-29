package monsters;
import world.world;
import playerFiles.player;
import util.TrekkerMath;
public class witch extends monster{
    public witch(){
        super.setName("Witch");
        super.setStrength(TrekkerMath.randomInt(5, 1));
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(1.34, .7) * (player.playerLevel + world.stageNum + 10)));
    }
    @Override
    public String attackString(){
        return "casts a spell on you";
    }
}