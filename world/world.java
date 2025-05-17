package world;

import items.*;
import java.util.Scanner;
import monsters.*;
import playerFiles.*;
import util.*;


public abstract class world { 

    static Scanner input = new Scanner(System.in);
    public static int AREANUM = 0;
    private static String areas[] = {"Village", "Grassland", "Cave", "Hell", "A second Cave?", "A THIRD CAVE??", "Why is there two hells?", "Are you actually still playing???", "Bored Yet?", "How bout now?"}; 
    public static int stageNum = 0;

    public world(){}
    public static String getArea(){
        return areas[AREANUM];
    }
    public static int getStage(){
        return stageNum;
    }

    public static void menu(){
        if(stageNum % 5 == 0){
            System.out.println("You have some options of what to do: \n");
            System.out.println("Shop \nDungeon \nItems \nQuit \nSave \n");
            String Ans = input.nextLine();
            if (response.quit(Ans)){System.exit(0);}

            //OPEN SHOP!!!
            if(response.Shop(Ans)){
                openShop();
            }
            if(response.Dungeon(Ans)){
                openDungeon();
            }
            if(response.Items(Ans)){
                itemMenu();
            }
            if(response.Save(Ans)){
                //// SAVE
                saveFiles.save(AREANUM, stageNum);
            }
        }
        else{
            openDungeon();
        }
    }
    private static void openShop(){
        shopitems.printShop();
        System.out.println("Would you like to purchase one of these items?");
        String userInput = input.nextLine();
                
        try{
            int UserResp = Integer.parseInt(userInput);
            //int numUserIsBuying = input.nextInt();
            item[] shop = shopitems.getShopArray();
            
            if(player.BankBalance >= shopitems.getShopArray()[UserResp-1].getPrice()){
                shopitems.buyItem(UserResp);
                System.out.println();
                System.out.println("You successfully bought " + shop[UserResp - 1] + " for " + shop[UserResp - 1].getPrice() + " shmeckles.");
            }
            else{
                System.out.println("You dont have enough money to buy that! /n You only have " + player.BankBalance + " shmeckles.");
                menu();
            }
        }
        catch(NumberFormatException ex){
                    //do nothing ig
        }
        //yes buy shit
        if(response.respondYes(userInput)){
            item[] shop = shopitems.getShopArray();
            System.out.println("What Item Would you like to buy?");
            System.out.println("Number ___");
            int numUserIsBuying = input.nextInt();
            input.nextLine();

            if(player.BankBalance >= shop[numUserIsBuying - 1].getPrice()){
                shopitems.buyItem(numUserIsBuying);
                System.out.println();
                System.out.println("You successfully bought " + shop[numUserIsBuying - 1] + " for " + shop[numUserIsBuying - 1].getPrice() + " shmeckles.");
            }
            else{
                System.out.println("You dont have enough money to buy that! /n You only have " + player.BankBalance + " shmeckles.");
                menu();
            }            
        }
            //no dont buy shit recurse back to display menu()
            if(response.respondNo(userInput)){
                menu();
            }
    }

    private static void openDungeon(){
        if(stageNum % 5 == 0 && AREANUM < areas.length - 1){
            AREANUM++;
            monsterArrayList.updateMonsterArrayListOnAreaUpdate();
        }
        System.out.println("You arrive in " + areas[AREANUM] + " on stage "  + stageNum);
        //create monster
        if(stageNum % 10 == 9){
            boss b = monsterCreater.createBoss();
            b.printMonster();
            monsterMenu(b);
        }
        else{
            int randNum = TrekkerMath.randomInt(5, 0);
            System.out.println(randNum);
            if(randNum == 3){
                rooms.getRandomRoom();
            }
            else{
                monster m = monsterCreater.createMonster();
                m.printMonster();
                monsterMenu(m);
            }
        }
    }

    public static void monsterMenu(monster m){
        while(m.getHealth() > 0){
            System.out.println();
            System.out.println("What would you like to do?");
            String h = input.nextLine(); 
            if(response.quit(h)){System.exit(0);}
            if(response.respondFight(h)){
                    player.fightMonster(m);
                if(m.getHealth() <= 0){
                    System.out.println("You defeated " + m.getName() + "!");
                    int coinGain = (int)((player.luck * m.getLevel()) + 4);
                    int xpGain = (int)((player.luck * m.getLevel())*4);
                    System.out.println("You obtained " + coinGain + " shmeckles and " + xpGain + " XP!");
                    player.BankBalance += coinGain;
                    player.gainXP(xpGain);
                    stageNum++;
                }
                else{
                    m.printMonster();
                }
            }
            //////////////////////////////////////////
            //Use an item during a fight
            /////////////////////////////////////////
            else if(response.Items(h)){
                itemMenu();
            }
        }
    }
    public static void itemMenu(){
        player.printPlayerItems();
                System.out.println("Would you like to use an item?");
                String h = input.nextLine();
                try{
                    int number = Integer.parseInt(h);
                    try {
                        player.inventory.get(number-1).Use();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You dont have that many items you goof!");
                    }
                }
                catch(NumberFormatException ex){
                    //do nothing
                }
                if(response.respondYes(h)){
                    System.out.println("What is the number of the item you would like to use");
                    int temp = input.nextInt();
                    input.nextLine();
                    try {
                        player.inventory.get(temp-1).Use();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You dont have that many items you goof!");
                    }
                }
    }
}
