package com.textbasedgame.world;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.rooms.*;

import java.lang.reflect.Constructor;

public abstract class roomFactory {
    private static ArrayList<Class<? extends Room>> t0Rooms = new ArrayList<>(Arrays.asList(chestRoom.class, fountainRoom.class, cauldronRoom.class, spikeRoom.class));
    private static ArrayList<Class<?extends Room>> t1Rooms = new ArrayList<>(Arrays.asList(idolRoom.class, portalRoom.class, cauldronRoom.class, lizzyRoom.class, gambleRoom.class));
    private static ArrayList<Class<? extends Room>> t2Rooms = new ArrayList<>(Arrays.asList(libraryRoom.class, soulWeighingRoom.class));
    private static ArrayList<Class<? extends Room>> t3Rooms = new ArrayList<>(Arrays.asList(swordInStoneRoom.class));

    private static Queue<Room> roomQueue = new ArrayDeque<>();

    public static int seed;
    private static Random seededRand;

    public static Room getRandomRoom(Random seededRand){
        int randNum = TrekkerMath.seededRandomInt(3, 0, seededRand);
        //System.out.println("getRoom Random Number -- " + randNum);

        if(world.stageNum % 10 == 9){
            return new bossMonsterRoom();
        }

        if(randNum == 0){
            try{
                Constructor<? extends Room> ctor = getWeightedRoomClass(seededRand).getDeclaredConstructor();
                Room r = ctor.newInstance();
                return r;
            }
            catch(Exception e){
                System.out.println(e + " In getting Room");
                return new chestRoom();
            }
        }
        else{
            Room r = new baseMonsterRoom();
            return r;
        }
    }

    private static void createRoomQueue(int seed){
        seededRand = new Random(seed);
        for(int i = 0; i < 10; i++){
            roomQueue.add(getRandomRoom(seededRand));
        }
    }


    private static Class<? extends Room> getWeightedRoomClass(Random seededRand){
        //////// 40% T0 - 30% T1 - 20% T2 - 10% T3 ---- This was really fucked up. Diff between prev weight and next weight is the weight for the teir.
        
        int roomSelector;
        int weight = TrekkerMath.seededRandomInt(100, 0, seededRand);
        System.out.println("Weight: " + weight);
        ///T0 Rooms 
        if(weight >= 60){
            System.out.println("T0");
            roomSelector = TrekkerMath.seededRandomInt(t0Rooms.size(), 0, seededRand);
            return t0Rooms.get(roomSelector);
        }
        /// T1 Rooms
        else if (weight >= 30){
            System.out.println("T1");
            roomSelector = TrekkerMath.seededRandomInt(t1Rooms.size(), 0, seededRand);
            return t1Rooms.get(roomSelector);
        }
        /// T2 Rooms
        else if (weight >= 10){
            System.out.println("T2");
            roomSelector = TrekkerMath.seededRandomInt(t2Rooms.size(), 0, seededRand);
            return t2Rooms.get(roomSelector);
        }
        /// T3 Rooms
        else{
            System.out.println("T3");
            roomSelector = TrekkerMath.seededRandomInt(t3Rooms.size(), 0, seededRand);
            return t3Rooms.get(roomSelector);
        }
    }

    public static cauldronRoom getCauldronRoom(){
        return new cauldronRoom();
    }

    public static Room getNextRoom(){
        if(roomQueue.isEmpty()){
            throw new IllegalStateException("Room queue is empty. Please RESTART the game with a new seed to generate a new room queue.");
        }
        roomQueue.add(getRandomRoom(seededRand));
        return roomQueue.poll();
    }

    public abstract int getRoomID();

    public abstract void openRoom();

    public static void setSeed(int seedValToSet){
        seed = seedValToSet;
        createRoomQueue(seed);
    }
}