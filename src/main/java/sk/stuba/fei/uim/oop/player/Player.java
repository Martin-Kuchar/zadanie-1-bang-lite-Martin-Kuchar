package sk.stuba.fei.uim.oop.player;

import java.util.ArrayList;
import java.util.Random;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.pack.Pack;

public class Player {

    private final String name;
    private int lives;
    private Random rnd;
    private boolean isAlive;
    private ArrayList<Card> cards;
    private ArrayList<Card> tableCards;

    public Player(String name, Pack p) {
        this.name = name;
        this.lives = 4;
        this.isAlive = true;
        this.rnd = new Random();
        this.cards = new ArrayList<Card>();
        this.tableCards = new ArrayList<Card>();

        for(int i = 0; i < 4; i++){
            addCard(p);
        }
    }
    
    public String getName() {
        return this.name;
    }

    public Card hasJail() {
        for (Card c : this.tableCards) {
            if(c instanceof Jail) {
                return c;
            }
        }
        return null;
    }

    public boolean escapeJail(Pack p) {
        if(this.rnd.nextInt(4) == 0) {
            System.out.println("Yous succesfuly escaped jail!");
            p.addCard(this.hasJail());
            this.tableCards.remove(this.hasJail());
            return true;
        }
        System.out.println("You saddly didnt escape jail. Turn over.");
        this.tableCards.remove(this.hasJail());
        return false;
    }
    
    public Card hasDynamite() {
        for (Card c : this.tableCards) {
            if(c instanceof Dynamite) {
                return c;
            }
        }
        return null;
    }

    public void detonateDynamite(Player nextPlayer, Pack p) {
        if(rnd.nextInt(8) == 0) {
            System.out.println("Dynamite exploeded in fornt " + this.getName() + " you loose 3 lives!");
            this.removeLives(3, p);
            p.addCard(this.hasDynamite());
        }
        else {
            System.out.println("Dynamite didnt explode passing to player " + nextPlayer.getName() + "!");
            nextPlayer.placeCard(this.hasDynamite());
        }
        this.tableCards.remove(this.hasDynamite());
    }

    public Card hasBarrel() {
        for (Card c : this.tableCards) {
            if(c instanceof Barrel) {
                return c;
            }
        }
        return null;
    }
    
    public boolean checkBarrel() {
        if(this.rnd.nextInt(4) == 0) {
            System.out.println(this.getName() + " ducked behind barrel!");
            return true;
        }
        return false;
    }

    public int getLives() {
        return this.lives;
    }

    public void addLives(int lives) {
        this.lives += lives;
    }

    public void removeLives(int lives, Pack p) {
        this.lives -= lives;
        if (this.lives <=0) {
            kill(p);
        }
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void kill(Pack p) {
        System.out.println("!!!Player " + this.getName() + " has died!!!");
        this.isAlive = false;
        for (Card c : this.cards) {
            p.addCard(c);
        }
        this.cards.removeAll(cards);
    }

    public void addCard(Pack p) {
        if(!p.isEmpty()){
            this.cards.add(p.drawCard());
        }
        else {
            System.out.println("You ran out of cards in deck!");
        }
    }

    public void placeCard(Card c) {
        this.tableCards.add(c);
    }

    public Card removeCard(Card c, Pack p) {
        p.addCard(c);
        return this.cards.remove(cards.indexOf(c));
    }

    public void removeExcessCards(Pack p) {
        while(this.cards.size() > this.getLives()) {
            p.addCard(this.cards.remove(rnd.nextInt(this.cards.size())));
            this.cards.remove(rnd.nextInt(this.cards.size()));
        }
    }

    public Card removeTableCard(Card c, Pack p) {
        p.addCard(c);
        return this.tableCards.remove(tableCards.indexOf(c));
    }

    public Card hasVedla() {
        for(Card c : this.cards){
            if(c instanceof Missed) {
                return c;
            }
        }
        return null;
    }

    public Card hasBang() {
        for(Card c : this.cards){
            if(c instanceof BangCard) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public ArrayList<Card> getTable() {
        return this.tableCards;
    }
}
