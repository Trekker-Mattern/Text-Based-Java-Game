package com.textbasedgame.world;

import java.util.ArrayList;
import java.util.Arrays;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.rooms.*;

import java.lang.reflect.Constructor;

public abstract class roomFactory {
    private static ArrayList<Class<? extends Room>> t0Rooms = new ArrayList<>(Arrays.asList(chestRoom.class, fountainRoom.class, cauldronRoom.class, spikeRoom.class));
    private static ArrayList<Class<?extends Room>> t1Rooms = new ArrayList<>(Arrays.asList(idolRoom.class, portalRoom.class, cauldronRoom.class, lizzyRoom.class));
    private static ArrayList<Class<? extends Room>> t2Rooms = new ArrayList<>(Arrays.asList(libraryRoom.class, soulWeighingRoom.class));
    private static ArrayList<Class<? extends Room>> t3Rooms = new ArrayList<>(Arrays.asList(swordInStoneRoom.class));



    public static Room getRandomRoom(){
        int randNum = TrekkerMath.randomInt(3, 0);
        //System.out.println("getRoom Random Number -- " + randNum);

        if(world.stageNum % 10 == 9){
            return new bossMonsterRoom();
        }

        if(randNum == 0){
            try{
                Constructor<? extends Room> ctor = getWeightedRoomClass().getDeclaredConstructor();
                Room r = ctor.newInstance();
                gui.setImage(r.getRoomID());
                return r;
            }
            catch(Exception e){
                System.out.println(e + " In getting Room");
                return new chestRoom();
            }
        }
        else{
            return new baseMonsterRoom();
        }
    }

    private static Class<? extends Room> getWeightedRoomClass(){
        //////// 40% T0 - 30% T1 - 20% T2 - 10% T3
        
        int roomSelector;
        int weight = TrekkerMath.randomInt(100, 0);
        ///T3 Rooms 
        if(weight >= 60){
            System.out.println("T3");
            roomSelector = TrekkerMath.randomInt(t3Rooms.size(), 0);
            return t3Rooms.get(roomSelector);
        }
        /// T2 Rooms
        else if (weight >= 30){
            System.out.println("T2");
            roomSelector = TrekkerMath.randomInt(t2Rooms.size(), 0);
            return t2Rooms.get(roomSelector);
        }
        /// T1 Rooms
        else if (weight >= 10){
            System.out.println("T1");
            roomSelector = TrekkerMath.randomInt(t1Rooms.size(), 0);
            return t1Rooms.get(roomSelector);
        }
        /// T0 Rooms
        else{
            System.out.println("T0");
            roomSelector = TrekkerMath.randomInt(t0Rooms.size() -1, 0);
            return t0Rooms.get(roomSelector);
        }
    }

    public static cauldronRoom getCauldronRoom(){
        return new cauldronRoom();
    }

    public abstract int getRoomID();

    public abstract void openRoom();
}