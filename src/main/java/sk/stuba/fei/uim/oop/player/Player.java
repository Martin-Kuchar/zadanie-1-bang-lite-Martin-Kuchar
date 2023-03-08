package sk.stuba.fei.uim.oop.player;

import java.util.List;

import sk.stuba.fei.uim.oop.cards.Card;

public class Player {

    private final String name;
    private int lives;
    private boolean isOnTurn;
    private List<Card> cards;//idk

    public Player(String name){ //inicializacia hraca
        this.name = name;   //zapisanie mena
        this.lives = 4;     //zapisanie pociatocnich zivotov
    }
    
    public String getName(){
        return this.name;
    }

    public int getLives(){
        return this.lives;
    }
    
}
