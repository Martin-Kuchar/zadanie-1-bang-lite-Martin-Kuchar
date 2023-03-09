package sk.stuba.fei.uim.oop.bang;


import java.util.ArrayDeque;
import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Bang {
    private final Player[] players;
    private Pack pack;

    public Bang(){

        this.pack = new Pack();

        System.out.println("Welcome to Bang Lite"); //welcome message
        int playerNum = ZKlavesnice.readInt("Enter number of players(2-4): ");
        while(playerNum > 4 || playerNum < 2){
            playerNum = ZKlavesnice.readInt("Enter number of players(2-4): ");
        }
        this.players = new Player[playerNum];

        for (int i = 0; i < playerNum; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("Enter name of player " + (i + 1) + ": "), pack); //inicializacia hracov
        }

        this.startGame();
    }

    private void startGame(){
        System.out.println("Game has started");
        
        int activePlayer = 0;   //index aktivneho hraca
        String playerIn;        //input hraca

        //this.players[0].playCard(new BangCard(), this.players[1]);

        ArrayList<Card> playerHand = this.players[0].getCards();
        playerHand.forEach((c) -> System.out.println(c.getName()));

        int pc = ZKlavesnice.readInt("ktoru kartu");

        players[0].playCard(playerHand.get(pc), players[1]);
        

        players[0].removeCard(playerHand.get(pc));
        players[1].getCards().forEach((c) -> System.out.println(c.getName()));
        System.out.println(players[1].getLives());
        players[1].getCards().forEach((c) -> System.out.println(c.getName()));

        /*while(getNumberOfAlivePlayers() > 1){   //loop pre hru pokial zije viac ako 1 hrac

            players[activePlayer].addCard(pack);    //tah dvoch kariet
            players[activePlayer].addCard(pack);
            


            activePlayer++; //inkrement aktivneho hraca a osetrenie mrtvich hracov
            if(activePlayer > getNumberOfAlivePlayers()){
                activePlayer = 0;
            }
        }*/
    }

    private int getNumberOfAlivePlayers(){
        int count = 0;
        for(int i = 0; i < this.players.length; i++){
            if(this.players[i].getLives() > 0){
                count++;
            }
        }
        return count;
    }
    
}
