package monsters;
import world.world;
import items.consumableItems.genericPotion;
import playerFiles.player;
import playerFiles.player.buffTypes;
import util.TrekkerMath;

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
        player.addItemToPlayer(new genericPotion(buffTypes.ARMOR, 6, 5));
    }
}
