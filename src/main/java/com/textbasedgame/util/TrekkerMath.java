package com.textbasedgame.util;
import java.util.Random;
public class TrekkerMath {
    
    public static Random rand = new Random();
    
    /**
    * random integer generator [fromval, toVal) 
    */
    public static int randomInt(int toVal, int fromVal){
        int ret = (rand.nextInt(toVal-fromVal)) + fromVal;
        return ret;
    }

    /**
    * Seeded random integer generator [fromval, toVal) 
    * @param toVal The upper bound (exclusive) for the random integer.
    * @param fromVal The lower bound (inclusive) for the random integer.
    * @param seededRand The Random object to use for generating the random integer.
    * @return A random integer between fromVal (inclusive) and toVal (exclusive).
    */
    public static int seededRandomInt(int toVal, int fromVal, Random seededRand){
        int ret = (seededRand.nextInt(toVal-fromVal)) + fromVal;
        return ret;
    }

    //Exclusive double generator
    public static double randomDouble(double toVal, double fromVal){
        double ret = fromVal + (toVal - fromVal) * rand.nextDouble();
        return ret;
    }

    public static boolean arrContains(String[] array, String string){
        for(String e: array){
            if(e.equals(string)){
                return true;
            }
        }
        return false;
    }
    public static boolean arrContains(int[] array, int i){
        for(int e: array){
            if(e == i){
                return true;
            }
        }
        return false;
    }
    public static boolean arrContains(double[] array, double d){
        for(double e: array){
            if(e == d){
                return true;
            }
        }
        return false;
    }

}
