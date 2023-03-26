package sk.stuba.fei.uim.oop.pack;

import java.util.Collections;
import java.util.ArrayList;

import sk.stuba.fei.uim.oop.cards.*;


public class Pack {

    private ArrayList<Card> cards;

    public Pack() {
        cards = new ArrayList<Card>();
        this.cards.add(new Dynamite());

        for(int i = 0; i < 2; i++){
            this.cards.add(new Barrel());
        }
        for(int i = 0; i < 3; i++){
            this.cards.add(new Jail());
        }
        for(int i = 0; i < 8; i++){
            this.cards.add(new Beer());
        }
        for(int i = 0; i < 6; i++){
            this.cards.add(new CatBalou());
        }
        for(int i = 0; i < 4; i++){
            this.cards.add(new Stagecoach());
        }
        for(int i = 0; i < 2; i++){
            this.cards.add(new Indians());
        }
        for(int i = 0; i < 30; i++){
            this.cards.add(new BangCard());
        }
        for(int i = 0; i < 15; i++){
            this.cards.add(new Missed());
        }
        
        Collections.shuffle(this.cards);
    }

    public void addCard(Card c) {
        this.cards.add(c);
    }

    public boolean isEmpty() {
        if(this.cards.size() == 0) {
            return true;
        }
        return false;
    }

    public Card drawCard() {
        return this.cards.remove(0);
    }
}
