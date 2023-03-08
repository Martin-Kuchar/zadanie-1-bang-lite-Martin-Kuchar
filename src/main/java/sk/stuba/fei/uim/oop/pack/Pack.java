package sk.stuba.fei.uim.oop.pack;

import java.util.Collections;
import java.util.ArrayList;

import sk.stuba.fei.uim.oop.cards.*;


public class Pack {

    private ArrayList<Card> cards;

    public Pack() {
        cards = new ArrayList<Card>();  //inicializacia pola kariet
        //pridanie kariet do pola
        this.cards.add(new Dynamit());
        for(int i = 0; i < 2; i++){
            this.cards.add(new Barrel());
        }
        for(int i = 0; i < 3; i++){
            this.cards.add(new Vazenie());
        }
        for(int i = 0; i < 8; i++){
            this.cards.add(new Pivo());
        }
        for(int i = 0; i < 6; i++){
            this.cards.add(new CatBalou());
        }
        for(int i = 0; i < 4; i++){
            this.cards.add(new Dostavnik());
        }
        for(int i = 0; i < 2; i++){
            this.cards.add(new Indiani());
        }

        shuffleDeck();
    }

    public void removeCard(Card c) {
        this.cards.remove(c);
    }

    public void addCard(Card c) {
        this.cards.add(c);
    }

    public Card drawCard() {
        return this.cards.remove(0);
    }

    public void shuffleDeck() {
        Collections.shuffle(this.cards);
    }
}
