import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.textbasedgame.monsters.monster;
import com.textbasedgame.monsters.monsterCreator;
import com.textbasedgame.monsters.regularMonsters.demon;
import com.textbasedgame.monsters.regularMonsters.giant;
import com.textbasedgame.monsters.regularMonsters.goblin;
import com.textbasedgame.monsters.regularMonsters.jailer;
import com.textbasedgame.monsters.regularMonsters.mimic;
import com.textbasedgame.monsters.regularMonsters.rat;
import com.textbasedgame.monsters.regularMonsters.skeleton;
import com.textbasedgame.monsters.regularMonsters.slime;
import com.textbasedgame.monsters.regularMonsters.snake;
import com.textbasedgame.monsters.regularMonsters.turtle;
import com.textbasedgame.monsters.regularMonsters.witch;
import com.textbasedgame.playerFiles.player;
import java.util.HashMap;

public class monsterTest {
    
    @Test
    public void testMonsterLevel() {

        monster testMonster = monsterCreator.createMonster();

        assertTrue(testMonster.getLevel() > 0, "Monster level should be greater than 0");
    }
    @Test
    public void testMonsterStartingHealth() {

        monster testMonster = monsterCreator.createMonster();

        assertTrue(testMonster.getHealth() > 0, "Monster health should be greater than 0");
    }
    @Test
    public void testBasicMonsterStats() {

        monster testMonster = monsterCreator.createMonster();
        System.out.println(testMonster.getSpeed());

        assertTrue(testMonster.getSpeed() > 0, "Monster speed should be greater than 0");
        assertTrue(testMonster.getStrength() > 0, "Monster strength should be greater than 0");

        testMonster.setSpeed(10);
        testMonster.setStrength(15);
        testMonster.setArmour(5);

        assertTrue(testMonster.getSpeed() == 10, "Monster speed should be set to 10");
        assertTrue(testMonster.getStrength() == 15 + player.playerLevel, "Monster strength should be set to 15");
        assertTrue(testMonster.getArmour() == 5, "Monster armour should be set to 5");

    }

    @Test
    public void testMonsterSpawnDistribution() {
        HashMap<Class<? extends monster>, Integer> monsterCount = new HashMap<>();
        monsterCount.put(skeleton.class, 0);
        monsterCount.put(slime.class, 0);
        monsterCount.put(witch.class, 0);
        monsterCount.put(goblin.class, 0);
        monsterCount.put(snake.class, 0);
        monsterCount.put(mimic.class, 0);
        monsterCount.put(giant.class, 0);
        monsterCount.put(turtle.class, 0);
        monsterCount.put(rat.class, 0);
        monsterCount.put(jailer.class, 0);
        monsterCount.put(demon.class, 0);

        int totalSpawns = 10000;

        for (int i = 0; i < totalSpawns; i++) {
            monster testMonster = monsterCreator.createMonster();
            Class<? extends monster> monsterClass = testMonster.getClass();
            monsterCount.put(monsterClass, monsterCount.get(monsterClass) + 1);
        }

        for (Class<? extends monster> monsterClass : monsterCount.keySet()) {
            int count = monsterCount.get(monsterClass);
            double ratio = (double) count / totalSpawns;
            System.out.println(monsterClass.getSimpleName() + ": " + ratio * 100 + "%");
            double expectedRatio = ((double)monsterCount.size())/ totalSpawns;

            assertTrue(ratio >= expectedRatio - .12 && ratio <= expectedRatio + .12, "Monster " + monsterClass.getSimpleName() + " spawn ratio is out of expected range");
        }
    }
}
