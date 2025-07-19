package com.textbasedgame.monsters;

import java.util.ArrayList;
import java.util.Arrays;

import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.world;

public abstract class monsterArrayList {

    public static ArrayList<Class<? extends monster>> monsterSubclasses = new ArrayList<Class<? extends monster>>(Arrays.asList(skeleton.class, slime.class, witch.class, goblin.class, snake.class, mimic.class, giant.class, turtle.class, rat.class));
    public static ArrayList<Class<? extends boss>> bossSubclasses = new ArrayList<Class<? extends boss>>(Arrays.asList(cthulhu.class, hydra.class));
    
    public static void createMonsterList(){
    }


    public static void updateMonsterArrayListOnAreaUpdate(){
        if(world.AREANUM == 2){
            monsterSubclasses.remove(slime.class);
            monsterSubclasses.add(troll.class);
        }
    }
    
    public static Class<? extends monster> getMonsterType(){
        return monsterSubclasses.get(TrekkerMath.randomInt( monsterSubclasses.size()-1, 0));
    }
    public static Class<? extends boss> getBossMonsterType(){
        return bossSubclasses.get(TrekkerMath.randomInt( bossSubclasses.size()-1, 0));
    }
}
