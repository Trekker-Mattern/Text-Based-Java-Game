package monsters;
import world.world;
import playerFiles.player;
import util.TrekkerMath;
public class slime extends monster{
    public slime(){
        super.setName("Slime");
        super.setStrength(TrekkerMath.randomInt(5, 1));
        super.setOrigionalHealth((int)(TrekkerMath.randomDouble(1.34, .7) * (player.playerLevel + world.AREANUM + 4)));

        //Fast
        super.setSpeed((int)(monsterCreator.fastMonsterSpeed() * 0.9));
    }
    
    @Override
    public String attackString(){
        return "squishes menacingly";
    }
}
