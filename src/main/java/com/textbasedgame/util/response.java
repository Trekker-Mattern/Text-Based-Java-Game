package com.textbasedgame.util;
public class response{

    public static boolean respondYes(String response){

        response = response.toLowerCase();
        if (response.contains("Ye") || response.contains("Sure") || response.contains("ye") || response.contains("sure"))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public static boolean respondNo(String response){
        response = response.toLowerCase();

        if (response.contains("No") || response.contains("Na") || response.contains("no") || response.contains("na"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean quit(String response){
        response = response.toLowerCase();
        if(response.contains("exit")  || response.contains("Exit") || response.contains("Leave") || response.contains("leave") || response.contains("quit") || response.contains("Quit")){
            return true;
        }
        else return false;
    }
    public static boolean Shop(String response){
        response = response.toLowerCase();
        if(response.contains("Shop")  || response.contains("shop")){
            return true;
        }
        else return false;
    }
    public static boolean Save(String response){
        response = response.toLowerCase();
        if(response.contains("Save")  || response.contains("save")){
            return true;
        }
        else return false;
    }
    public static boolean Dungeon(String response){
        response = response.toLowerCase();
        if(response.contains("dungeon")  || response.contains("Dungeon")){
            return true;
        }
        else return false;
    }
    public static boolean Items(String response){
        response = response.toLowerCase();
        if(response.contains("items")  || response.contains("Items") || response.contains("Item") || response.contains("item")){
            return true;
        }
        else return false;
    }
    public static boolean respondFight(String response){
        response = response.toLowerCase();
        if(response.contains("fight")  || response.contains("attack") || response.contains("brawl") || response.contains("kill")){
            return true;
        }
        return false;
    }
    public static boolean Left(String response){
        response = response.toLowerCase();
        if(response.contains("left")){return true;}
        return false;
    }

    public static boolean respondSell(String response){
        response = response.toLowerCase();
        if(response.contains("sell")){return true;}
        return false;
    }
}

