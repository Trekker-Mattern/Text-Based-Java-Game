package com.textbasedgame.monsters;

import java.util.ArrayList;
import java.util.Arrays;

import com.textbasedgame.monsters.bossMonsters.*;
import com.textbasedgame.monsters.regularMonsters.*;

import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.world;

public abstract class monsterArrayList {

    public static ArrayList<Class<? extends monster>> monsterSubclasses = new ArrayList<>(Arrays.asList(skeleton.class, slime.class, witch.class, goblin.class, snake.class, mimic.class, giant.class, orc.class,knight.class, sloth.class, turtle.class, rat.class, livingStone.class, jailer.class, demon.class));
    public static ArrayList<Class<? extends boss>> bossSubclasses = new ArrayList<>(Arrays.asList(cthulhu.class, hydra.class, thor.class, mothMan.class));
    
    public static ArrayList<Class<? extends monster>> t0monsterSubclasses = new ArrayList<>(Arrays.asList(skeleton.class, slime.class, goblin.class, snake.class, sloth.class, rat.class, livingStone.class));
    public static ArrayList<Class<? extends monster>> t1monsterSubclasses = new ArrayList<>(Arrays.asList(troll.class, giant.class, orc.class, demon.class));
    public static ArrayList<Class<? extends monster>> t2monsterSubclasses = new ArrayList<>(Arrays.asList( witch.class, knight.class, turtle.class, mimic.class));
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
        
        //////// 45% T0 - 40% T1 - 10% T2 - 5% T3 ---- This was really fucked up. Diff between prev weight and next weight is the weight for the tier.
        
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
