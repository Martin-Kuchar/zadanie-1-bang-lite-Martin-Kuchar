package sk.stuba.fei.uim.oop.player;

import java.util.ArrayList;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.pack.Pack;

public class Player {

    private final String name;
    private int lives;
    private boolean isOnTurn;
    private boolean barrel;
    private boolean dynamit;
    private boolean vazenie;
    private ArrayList<Card> cards;//araylist pre karty na ruke

    public Player(String name, Pack p) { //inicializacia hraca
        this.name = name;   //zapisanie mena
        this.lives = 4;     //zapisanie pociatocnich zivotov

        this.cards = new ArrayList<Card>();
        for(int i = 0; i < 4; i++){
            cards.add(addCard(p));
        }
        System.out.println(this.cards.get(0).getName());
    }
    
    public String getName() {
        return this.name;
    }

    public int getLives() {
        return this.lives;
    }

    public void addLives(int lives) {
        this.lives += lives;
    }

    public void toggleActive() {
        this.isOnTurn ^= true;
    }

    public Card addCard(Pack p) {
        return p.drawCard();
    }

    public Card removeCard(Card c) {
        return cards.remove(cards.indexOf(c));
    }
    
}
