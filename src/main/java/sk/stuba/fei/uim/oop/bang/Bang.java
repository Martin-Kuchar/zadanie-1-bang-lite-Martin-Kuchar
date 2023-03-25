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
        int playerNum = 0;
        System.out.println("Welcome to Bang Lite");
        do {
            playerNum = ZKlavesnice.readInt("Enter number of players(2-4): ");
        } while (playerNum > 4 || playerNum < 2);
        
        this.players = new Player[playerNum];

        for (int i = 0; i < playerNum; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("Enter name of player " + (i + 1) + ": "), pack);
        }
        
        this.startGame();
    }

    private void startGame(){
        System.out.println("---Game has started---");
        
        int activeCount = -1;
        ArrayList<Card> playerHand = new ArrayList<Card>();
        int choosenCard;

        while(getNumberOfAlivePlayers() > 1){
            activeCount = incrementPlyer(activeCount);
            activePlayer = players[activeCount];
            playerHand = activePlayer.getCards();

            System.out.println("\n\n\n\nPlayer " + activePlayer.getName() + " is on turn!");
            
            if(activePlayer.hasDynamite() != null) {
                activePlayer.detonateDynamite(players[decrementPlyer(activeCount)], pack);
                if(activePlayer.getLives()<=0) {
                    continue;
                }
            }
            if(activePlayer.hasJail() != null) {
                if(!activePlayer.escapeJail(pack)) {
                    continue;
                }
            }
            System.out.println("You have " + activePlayer.getLives() + " lives");
            activePlayer.addCard(pack);
            activePlayer.addCard(pack);

            while(getNumberOfAlivePlayers() > 1) {
                System.out.println("Your cards on hand are: ");
                for (int i = 0; i < playerHand.size(); i++) {
                    System.out.print(i+1 + ". " + playerHand.get(i).getName() + ", ");
                }
                System.out.println();

                choosenCard = chooseCard(playerHand);
                if(choosenCard == -1){
                    break;
                }
                playerHand.get(choosenCard).use(activePlayer, pack, players);
            }
            activePlayer.removeExcessCards(pack);

        }
        this.printWinner();
    }

    private int incrementPlyer(int i) {
        do {
            i++;
            if(i >= this.players.length){
                i = 0;
            }
        } while (this.players[i].isAlive() == false);
        
        return i;
    }

    private int decrementPlyer(int i) {
        do {
            i--;
            if(i < 0){
                i = this.players.length-1;
            }
        } while (this.players[i].isAlive() == false);
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
        int playerIn = ZKlavesnice.readInt("Enter Number of card to play or 0 to end round: ");
        while(playerIn < -1 || playerIn > c.size()) {
            playerIn = ZKlavesnice.readInt("Not in range");
        }
        return playerIn-1;
    }

    private void printWinner() {
        for (Player player : players) {
            if(player.isAlive()) {
                System.out.println("\n\n\n\nCONGRATULATION! Player " + player.getName() + " won the game!");
            }
        }
    }
}
