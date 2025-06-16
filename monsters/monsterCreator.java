package monsters;

import util.*;
import world.*;

import java.lang.reflect.*;

import playerFiles.*;

public class monsterCreator {
    
    public monsterCreator(world world, player Player){
    }

    public static monster createMonster(){

        //Get monster type
        //Class<?> monsterType = 

        Class<? extends monster> mType = monsterArrayList.getMonsterType();
        try {
            Constructor<? extends monster> ctor = mType.getDeclaredConstructor();
            monster m = ctor.newInstance();
            return m;
        } catch (Exception e) {
            // Handle the case where the default constructor is not found
            e.printStackTrace();
        }

        return null;
    }


    public static boss createBoss(){

        //Get monster type
        //Class<?> monsterType = 

        Class<? extends boss> mType = monsterArrayList.getBossMonsterType();
        try {
            Constructor<? extends boss> ctor = mType.getDeclaredConstructor();
            boss b = ctor.newInstance();
            return b;
        } catch (Exception e) {
            // Handle the case where the default constructor is not found
            e.printStackTrace();
        } 

        return null;
    }


    public static int fastMonsterSpeed(){
        if(player.getPlayerLevel() > 10){
            return (int)(TrekkerMath.randomDouble(2, .9) * (world.stageNum / 4));
        }
        return (int)(TrekkerMath.randomDouble(5, 1.1) * 1);
    }

    public static int slowMonsterSpeed(){
        if(player.getPlayerLevel() > 10){
            return (int)(TrekkerMath.randomDouble(1.2, .5) * (world.stageNum / 4));
        }
        return (int)(TrekkerMath.randomDouble(3, 1.1) * 1);
    }
}
