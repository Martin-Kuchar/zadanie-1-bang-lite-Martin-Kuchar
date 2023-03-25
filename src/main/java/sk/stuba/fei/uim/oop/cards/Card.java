package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.pack.Pack;

public abstract class Card {

    protected String name;

    public String getName(){
        return this.name;
    }

    protected Player choosePlayer(Player curr, Player players[]) {
        for (int i = 0; i < players.length; i++) {
            if(players[i].isAlive() && players[i] != curr){
                System.out.print(i+1 +". " + players[i].getName() + " ");
            }
        }
        int playerIn = ZKlavesnice.readInt("\nEnter number of player to play card on")-1;
        while(playerIn < 0 || playerIn >= players.length || players[playerIn].isAlive() == false || players[playerIn] == curr) {
            playerIn = ZKlavesnice.readInt("Not in range. Try agains")-1;
        }
        return players[playerIn];
    }

    public abstract void use(Player p, Pack d, Player players[]);

    
}