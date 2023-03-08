package sk.stuba.fei.uim.oop.bang;


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

        while(getNumberOfAlivePlayers() > 1){   //loop pre hru pokial zije viac ako 1 hrac

            players[activePlayer].addCard(pack);    //tah dvoch kariet
            players[activePlayer].addCard(pack);
            


            activePlayer++; //inkrement aktivneho hraca a osetrenie mrtvich hracov
            if(activePlayer > getNumberOfAlivePlayers()){
                activePlayer = 0;
            }
        }
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
