package sk.stuba.fei.uim.oop.pack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Queue;

import sk.stuba.fei.uim.oop.cards.Barrel;
import sk.stuba.fei.uim.oop.cards.Card;

public class Pack {

    private ArrayList<Card> cards;

    Pack() {
        

    }

    public void removeCard(Card c) {
        this.cards.remove(c);
    }

    public void addCard(Card c) {
        this.cards.add(c);
    }

    public void shuffleDeck() {
        Collections.shuffle(this.cards);
    }
}
