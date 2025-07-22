package com.textbasedgame.monsters;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.world.world;

public class monster {

    //Subclasses for monster
    
    

    
    protected String mName;
    protected int mHealth;
    protected int mStrength;
    protected int mSpeed;
    protected int mArmour;
    protected int mLevel;

    /* 
    private static String[] fastMonsters = {"Goblin", "Snake", "Witch", "Vampire", "Banshee", "Kelpie", "Chupacabra", "Jersey Devil", "Kitsune", "Selkie", "Jackalope", "Qilin"};
    private static String[] armouredMonsters = {"Goblin", "Agressive Walrus", "Orc", "Troll", "Dragon", "Basilisk", "Boggart", "Naga", "Hodag"};
    private static String[] areaMonsterArray = {"Goblin", "Slime", "Agressive Walrus", "Dinosaur", "Snake", "Skeleton", "Orc", "Troll", "Witch", "Zombie", "Demon", "Dragon", "Vampire", "Werewolf", "Chimera", "Banshee", "Kelpie", "Dullahan", "Chupacabra", "Wendigo", "Jersey Devil", "Kitsune", "Nuckelavee", "Basilisk", "Fomorian", "Boggart", "Manticore", "Selkie", "Naga", "Bunyip", "Hodag", "Leshy", "Jackalope", "Qilin"};
    */
    
    public monster(){
        mLevel = (int)((player.playerLevel + world.AREANUM) * TrekkerMath.randomDouble(3, .1));
    }
    public void setName(String name){
        mName = name;
    }
    public String getName(){
        return mName;
    }
    public void setOrigionalHealth(int hVal){
        mHealth = hVal;
    }
    public int getHealth(){
        return mHealth;
    }
    public void setHealth(int x){
        mHealth = x;
    }
    public int subtractHealth(int x){
        if(mArmour != 0){
            double armMultiplyer = 1.0 / mArmour;
            double subtractor = ((double)x * armMultiplyer);
            mHealth -= (int)subtractor;
            return (int)subtractor;
        }
        else{
            mHealth -= x;
            return x;
        }
    }
    public void setArmour(int armour){
        mArmour = armour;
    }
    public void setStrength(int strength){
        mStrength = strength + player.playerLevel;
    }
    public int getStrength(){
        return mStrength;
    }
    public int getSpeed(){
        return mSpeed;
    }
    public void setSpeed(int speed){
        mSpeed = speed;
    }
    public int getLevel(){
        return mLevel;
    }
    public int getArmour(){
        return mArmour;
    }
    public String attackString(){
        return "slashes you for";
    }
    public void attackEffects(int x){}
    public void onMonsterDeath(){}

    public void printMonster(){
        gui.printOnGameSide(mName + " level " + mLevel + " has " + mHealth + " HP.");
        if(player.getName().equals("debug")){
            gui.printOnGameSide(mName);
            gui.printOnGameSide("Stren" + mStrength);
            gui.printOnGameSide("Speed"+ mSpeed);
            gui.printOnGameSide("Armour"+ mArmour);
            gui.printOnGameSide("Health"+ mHealth);
            gui.printOnGameSide("Level"+ mLevel);
        }
    }

}
