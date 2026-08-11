package com.textbasedgame.monsters;

import java.util.ArrayList;
import java.util.Arrays;

import com.textbasedgame.monsters.bossMonsters.cthulhu;
import com.textbasedgame.monsters.bossMonsters.hydra;
import com.textbasedgame.monsters.bossMonsters.mothMan;
import com.textbasedgame.monsters.bossMonsters.thor;
import com.textbasedgame.monsters.regularMonsters.demon;
import com.textbasedgame.monsters.regularMonsters.giant;
import com.textbasedgame.monsters.regularMonsters.goblin;
import com.textbasedgame.monsters.regularMonsters.jailer;
import com.textbasedgame.monsters.regularMonsters.mimic;
import com.textbasedgame.monsters.regularMonsters.rat;
import com.textbasedgame.monsters.regularMonsters.skeleton;
import com.textbasedgame.monsters.regularMonsters.slime;
import com.textbasedgame.monsters.regularMonsters.snake;
import com.textbasedgame.monsters.regularMonsters.troll;
import com.textbasedgame.monsters.regularMonsters.turtle;
import com.textbasedgame.monsters.regularMonsters.witch;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.world;

public abstract class monsterArrayList {

    public static ArrayList<Class<? extends monster>> monsterSubclasses = new ArrayList<>(Arrays.asList(skeleton.class, slime.class, witch.class, goblin.class, snake.class, mimic.class, giant.class, turtle.class, rat.class, jailer.class, demon.class));
    public static ArrayList<Class<? extends boss>> bossSubclasses = new ArrayList<>(Arrays.asList(cthulhu.class, hydra.class, thor.class, mothMan.class));
    
    public static ArrayList<Class<? extends monster>> t0monsterSubclasses = new ArrayList<>(Arrays.asList(skeleton.class, slime.class, goblin.class, snake.class, rat.class));
    public static ArrayList<Class<? extends monster>> t1monsterSubclasses = new ArrayList<>(Arrays.asList(troll.class, giant.class, demon.class));
    public static ArrayList<Class<? extends monster>> t2monsterSubclasses = new ArrayList<>(Arrays.asList( witch.class, turtle.class, mimic.class));
    public static ArrayList<Class<? extends monster>> t3monsterSubclasses = new ArrayList<>(Arrays.asList( jailer.class));

    public static void createMonsterList(){
    }

    @Deprecated
    public static void updateMonsterArrayListOnAreaUpdate(){
        if(world.AREANUM == 2){
            monsterSubclasses.remove(slime.class);
            monsterSubclasses.add(troll.class);
        }
    }
    
    public static Class<? extends monster> getMonsterType(){
        
        //////// 45% T0 - 40% T1 - 10% T2 - 5% T3 ---- This was really fucked up. Diff between prev weight and next weight is the weight for the teir.
        
        int roomSelector;
        int weight = TrekkerMath.randomInt(100, 0);
        System.out.println("Weight: " + weight);
        ///T0 Rooms 
        if(weight >= 55){
            System.out.println("T0");
            roomSelector = TrekkerMath.randomInt(t0monsterSubclasses.size(), 0);
            return t0monsterSubclasses.get(roomSelector);
        }
        /// T1 Rooms
        else if (weight >= 15){
            System.out.println("T1");
            roomSelector = TrekkerMath.randomInt(t1monsterSubclasses.size(), 0);
            return t1monsterSubclasses.get(roomSelector);
        }
        /// T2 Rooms
        else if (weight >= 5){
            System.out.println("T2");
            roomSelector = TrekkerMath.randomInt(t2monsterSubclasses.size(), 0);
            return t2monsterSubclasses.get(roomSelector);
        }
        /// T3 Rooms
        else{
            System.out.println("T3");
            roomSelector = TrekkerMath.randomInt(t3monsterSubclasses.size(), 0);
            return t3monsterSubclasses.get(roomSelector);
        }
        
        
        
        
    }
    public static Class<? extends boss> getBossMonsterType(){
        return bossSubclasses.get(TrekkerMath.randomInt( bossSubclasses.size(), 0));
    }
}
