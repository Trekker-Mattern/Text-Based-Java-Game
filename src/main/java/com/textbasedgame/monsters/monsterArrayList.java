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
