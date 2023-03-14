package sk.stuba.fei.uim.oop.bang;

import java.util.ArrayList;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Bang {
    private final Player[] players;
    private Pack pack;
    private Player activePlayer;

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
        
        int activeCount = -1;   //index aktivneho hraca
        int playerIn = -1;        //input hraca
        ArrayList<Card> playerHand = new ArrayList<Card>();
        int choosenCard;

        while(getNumberOfAlivePlayers() > 1){   //loop pre hru pokial zije viac ako 1 hrac
            activeCount = incrementPlyer(activeCount);
            activePlayer = players[activeCount];
            playerHand = activePlayer.getCards();

            //vykonanie efektovich kariet
            if(activePlayer.hasDynamite()) {
                activePlayer.detonateDynamite(players[incrementPlyer(activeCount)], pack);
            }
            if(activePlayer.isInJail()) {
                activePlayer.escapeJail(pack);
                continue;
            }
            activePlayer.addCard(pack);    //tah dvoch kariet
            activePlayer.addCard(pack);


            System.out.println("Player " + activePlayer.getName() + " is on turn!");
            System.out.println("Tvoje zivoty su:  " + activePlayer.getLives());

            while(true) {
                System.out.println("Tvoje karty na ruke su: ");
                playerHand.forEach((c) -> System.out.print(c.getName() + " "));
                System.out.println();

                choosenCard = chooseCard(playerHand);
                if(choosenCard == -1){
                    break;
                }
                playCard(playerHand.get(choosenCard));
            }
            activePlayer.setCards(playerHand);
            playerIn = -1;

        }
    }

    private int incrementPlyer(int i) {
        i++; //inkrement aktivneho hraca a osetrenie mrtvich hracov
            if(i >= getNumberOfAlivePlayers()){
                i = 0;
            }
        return i;
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

    private int chooseCard(ArrayList<Card> c){
        int playerIn = ZKlavesnice.readInt("Zadaj cislo karty ktoru chces zahrat alebo 0 pre ukoncenie tahu: ");
        while(playerIn < -1 || playerIn > c.size()) {
            playerIn = ZKlavesnice.readInt("Netrafil si range skus znova");
        }
        return playerIn-1;
    }

    private Player choosePlayer(Card c) {
        for (int i = 0; i < this.players.length; i++) {
            if(players[i].getLives()>0){
                System.out.print(i+". " + players[i].getName() + " ");
            }
        }
        int playerIn = ZKlavesnice.readInt("\nZadaj cislo hraca na ktoreho chces pouzit katu");

        while(playerIn < 0 || playerIn > getNumberOfAlivePlayers() || this.players[playerIn].getLives() <= 0) {
            playerIn = ZKlavesnice.readInt("Netrafil si range skus znova");
        }
        return this.players[playerIn];
    }

    private void playCard(Card c) {

        if(c.getClass() == Vedla.class) {
            System.out.println("card not playable");
        }
        else if(c.getClass() == Pivo.class || c.getClass() == Barrel.class || c.getClass() == Dostavnik.class || c.getClass() == Dynamit.class) {
            this.activePlayer.playCard(c, this.activePlayer, this.pack);

        }
        else if(c.getClass() == BangCard.class || c.getClass() == Vazenie.class || c.getClass() == CatBalou.class) {
            this.activePlayer.playCard(c, choosePlayer(c), this.pack);
        }
        else {
            //indiani
        }

    }
    
}
