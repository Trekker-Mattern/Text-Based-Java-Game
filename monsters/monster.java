package monsters;
import GUI.gui;
import playerFiles.player;

public class monster {

    //Subclasses for monster
    
    

    
    private String mName;
    private int mHealth;
    private int mStrength;
    private int mSpeed;
    private int mArmour;
    private int mLevel;

    /* 
    private static String[] fastMonsters = {"Goblin", "Snake", "Witch", "Vampire", "Banshee", "Kelpie", "Chupacabra", "Jersey Devil", "Kitsune", "Selkie", "Jackalope", "Qilin"};
    private static String[] armouredMonsters = {"Goblin", "Agressive Walrus", "Orc", "Troll", "Dragon", "Basilisk", "Boggart", "Naga", "Hodag"};
    private static String[] areaMonsterArray = {"Goblin", "Slime", "Agressive Walrus", "Dinosaur", "Snake", "Skeleton", "Orc", "Troll", "Witch", "Zombie", "Demon", "Dragon", "Vampire", "Werewolf", "Chimera", "Banshee", "Kelpie", "Dullahan", "Chupacabra", "Wendigo", "Jersey Devil", "Kitsune", "Nuckelavee", "Basilisk", "Fomorian", "Boggart", "Manticore", "Selkie", "Naga", "Bunyip", "Hodag", "Leshy", "Jackalope", "Qilin"};
    */
    
    public monster(){}
    public void setName(String name){
        mName = name;
    }
    public String getName(){
        return mName;
    }
    public void setOrigionalHealth(int hVal){
        mHealth = hVal;
        mLevel = (int)((mHealth / 5) + mStrength + mSpeed);
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
        mStrength = strength;
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
