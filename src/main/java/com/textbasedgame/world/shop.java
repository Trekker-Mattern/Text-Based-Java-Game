package com.textbasedgame.world;

import com.textbasedgame.items.consumables;
import com.textbasedgame.items.equipables;
import com.textbasedgame.items.item;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.response;
import com.textbasedgame.GUI.gui;
import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.textbasedgame.util.itemInfoPrinter;



public class shop {
    public static void openShop(){
        gui.setImage(imageIDs.SHOP);
        shopitems.printShop();
        gui.printOnGameSide("Would you like to purchase one of these items?");
        gui.printOnGameSide("You can also sell items by typing sell!");
        String userInput = gui.getInput();

        gui.pushOldText();
        
        try{

            int UserResp = Integer.parseInt(userInput);
            item[] shop = shopitems.getShopArray();
            
            boolean buyConfirm = buyConfirmationMenu(shopitems.getShopArray()[UserResp-1]);

            if (buyConfirm == false){
                gui.printOnGameSide("You decided not to buy " + shop[UserResp-1].getItemName() + ".");
                return;
            }
            //BUY FROM NUMBER
            if(player.BankBalance >= shopitems.getShopArray()[UserResp-1].getPrice()){
                shopitems.buyItem(UserResp);
                gui.newlOnGameSide();
                gui.printOnGameSide("You successfully bought " + shop[UserResp - 1] + " for " + shop[UserResp - 1].getPrice() + " shmeckles.");
            }
            else{
                gui.printOnGameSide("You dont have enough money to buy that!");
                gui.printOnGameSide("You only have " + player.BankBalance + " shmeckles.");
            }
        }
        catch(NumberFormatException | IndexOutOfBoundsException ex){
            System.out.print(ex);
        }
        //BUY AFTER SAYING YES B/C PLAYERS ARE IDIOTS
        if(response.respondYes(userInput)){
            item[] shop = shopitems.getShopArray();
            gui.printOnGameSide("What Item Would you like to buy?");
            gui.printOnGameSide("Number ___");
            try{
                int numUserIsBuying = Integer.parseInt(gui.getInput());
                
                boolean confirm = buyConfirmationMenu(shop[numUserIsBuying-1]);

                if(!confirm){
                    gui.printOnGameSide("You decided not to buy " + shop[numUserIsBuying-1].getItemName() + ".");
                    return;
                }


                if(player.BankBalance >= shop[numUserIsBuying - 1].getPrice()){
                    shopitems.buyItem(numUserIsBuying);
                    gui.newlOnGameSide();
                    gui.printOnGameSide("You successfully bought " + shop[numUserIsBuying - 1] + " for " + shop[numUserIsBuying - 1].getPrice() + " shmeckles.");
                }
                else{
                    gui.printOnGameSide("You dont have enough money to buy that!  You only have " + player.BankBalance + " shmeckles.");
                    return;
                }
            }
            catch(NumberFormatException e){
                for(item i : shopitems.getShopArray()){
                    if(i.getItemName().toLowerCase().equals(userInput.toLowerCase())){

                        boolean confirm = buyConfirmationMenu(i);

                        if(!confirm){
                            gui.printOnGameSide("You decided not to buy " + i.getItemName() + ".");
                            return;
                        }

                        if(player.BankBalance >= i.getPrice()){
                            shopitems.buyItem(i);
                            gui.printOnGameSide("You successfully bought " + i + " for " + i.getPrice() + " shmeckles.");
                            return;
                        }
                        else{
                            gui.printOnGameSide("You dont have enough money to buy that!  You only have " + player.BankBalance + " shmeckles.");
                            return;
                        }
                    }
                }
            }   
            catch(IndexOutOfBoundsException e){
                gui.printOnGameSide("There isnt that many items in the shop");
                return;
            }         
        }

        //Selling menu (nice brick of code dumbass good luck reading that later during debugging)
        if(response.respondSell(userInput)){
            player.printPlayerItems();
            gui.printOnGameSide("Which item would you like to sell?");
            try{
                int itemSell = Integer.parseInt(gui.getInput());
                itemSell--; //adjust for 0 index
                if(player.inventory.get(itemSell) instanceof equipables && ((equipables)player.inventory.get(itemSell)).isEquipped()){
                    player.inventory.get(itemSell).Use();
                }
                int sellPrice = (int)(player.inventory.get(itemSell).getPrice() * .75);
                player.BankBalance += sellPrice;
                gui.printOnGameSide("You sell " + player.inventory.get(itemSell).getItemName() + " for " + sellPrice + " shmeckles");
                player.inventory.remove(player.inventory.get(itemSell));
                return;
            }
            catch(IndexOutOfBoundsException e){
                gui.printOnGameSide("You dont have that many items you goof!");
                return;
            }
            catch(NumberFormatException e){return;}
            
        }

        //////////////////////////////////////////////////////////////
        /// Check to see if user put name of item and buy accordingly
        //////////////////////////////////////////////////////////////
        for(item i : shopitems.getShopArray()){
            if(i.getItemName().toLowerCase().equals(userInput.toLowerCase())){

                boolean buyConfirm = buyConfirmationMenu(i);

                if(!buyConfirm){
                    gui.printOnGameSide("You decided not to buy " + i.getItemName() + ".");
                    return;
                }

                if(player.BankBalance >= i.getPrice()){
                    shopitems.buyItem(i);
                    gui.printOnGameSide("You successfully bought " + i + " for " + i.getPrice() + " shmeckles.");
                    return;
                }
                else{
                    gui.printOnGameSide("You dont have enough money to buy that!  You only have " + player.BankBalance + " shmeckles.");
                }
            }
        }
        //no dont buy shit recurse back to display
        if(response.respondNo(userInput)){gui.pushOldText();}
    }



    private static boolean buyConfirmationMenu(item i){

        if(i instanceof consumables){
            itemInfoPrinter.printConsumableInfo((consumables)i);
        }
        else if(i instanceof equipables){
            itemInfoPrinter.printEquipablesInfo((equipables) i);
        }

        gui.printOnGameSide("-------------------------------------");
        gui.newlOnGameSide();
        gui.printOnGameSide("Are you sure you want to buy " + i.getItemName() + " for " + i.getPrice() + " shmeckles?");
        gui.printOnGameSide("1: Yes");
        gui.printOnGameSide("2: No");

        String input = gui.getInput();

        if(input.equals("1") || response.respondYes(input)){
            return true;
        }
        else{
            return false;
        }
    }
}
